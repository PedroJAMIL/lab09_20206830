package com.example.lab09.servlets;


import com.example.lab09.beans.Usuario;
import com.example.lab09.dao.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({"/usuarios", "/usuarios/crear"})
public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioEnSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getServletPath();

        if ("/usuarios".equals(path)) {
            List<Usuario> usuarios = usuarioDAO.traertodoslosUsuarios(); //
            request.setAttribute("listaUsuarios", usuarios); // Set attribute for JSP to display
            request.getRequestDispatcher("/com/example/lab09/jsp/usuarios.jsp").forward(request, response);
        } else if ("/usuarios/crear".equals(path)) {
            request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response); // [cite: 13]
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioEnSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getServletPath();

        if ("/usuarios/crear".equals(path)) {
            String nombre = request.getParameter("nombre"); //
            String apellido = request.getParameter("apellido"); //
            String dni = request.getParameter("dni"); //
            String correo = request.getParameter("correo"); //
            String pass = request.getParameter("pass"); //
            String confirmPass = request.getParameter("confirmPass");

            if (nombre == null || nombre.trim().isEmpty() ||
                    apellido == null || apellido.trim().isEmpty() ||
                    dni == null || dni.trim().isEmpty() ||
                    correo == null || correo.trim().isEmpty() ||
                    pass == null || pass.trim().isEmpty()) {
                request.setAttribute("error", "Todos los campos requeridos deben ser completados.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }

            if (!dni.matches("\\d{8}")) {
                request.setAttribute("error", "DNI debe ser un número de 8 dígitos.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }

            if (!pass.equals(confirmPass)) {
                request.setAttribute("error", "Las contraseñas no coinciden.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }

            if (pass.length() < 8 || !pass.matches(".*[a-zA-Z].*") || !pass.matches(".*\\d.*")) {
                request.setAttribute("error", "La contraseña debe tener al menos 8 caracteres y contener letras y números.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }

            if (!usuarioDAO.DniUNICO(dni)) {
                request.setAttribute("error", "El DNI ingresado ya está registrado.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }
            if (!usuarioDAO.CorreoUNICO(correo)) {
                request.setAttribute("error", "El correo ingresado ya está registrado.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
                return;
            }

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setDni(dni);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setPass(pass);

            boolean success = usuarioDAO.createUsuario(nuevoUsuario);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/usuarios"); // Redirect to user list on success
            } else {
                request.setAttribute("error", "Error al registrar el usuario. Inténtelo de nuevo.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearUsuario.jsp").forward(request, response);
            }
        }
    }
}