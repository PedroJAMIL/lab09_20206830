package com.example.lab09.servlets;

import com.example.lab09.beans.Usuario;
import com.example.lab09.dao.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/com/example/lab09/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("pass");

        Usuario usuario = usuarioDAO.TraerUsuarioporCorreoyPass(correo, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioEnSesion", usuario);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else {
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("/com/example/lab09/jsp/login.jsp").forward(request, response);
        }
    }
}