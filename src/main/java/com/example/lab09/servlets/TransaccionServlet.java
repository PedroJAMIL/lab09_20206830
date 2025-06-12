package com.example.lab09.servlets;

import com.example.lab09.beans.Transaccion;
import com.example.lab09.beans.Usuario;
import com.example.lab09.dao.TransaccionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({"/transacciones", "/transacciones/crear", "/transacciones/eliminar"})
public class TransaccionServlet extends HttpServlet {
    private TransaccionDAO transaccionDAO;

    public void init() {
        transaccionDAO = new TransaccionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioEnSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        String path = request.getServletPath();

        if ("/transacciones".equals(path)) {
            // Displays a list of transactions for the user in session
            List<Transaccion> transacciones = transaccionDAO.TransaccionesPorUsuarios(usuarioEnSesion.getIdusuarios());
            request.setAttribute("listaTransacciones", transacciones);
            request.getRequestDispatcher("/com/example/lab09/jsp/transacciones.jsp").forward(request, response);
        } else if ("/transacciones/crear".equals(path)) {
            request.getRequestDispatcher("/com/example/lab09/jsp/crearTransaccion.jsp").forward(request, response);
        } else if ("/transacciones/eliminar".equals(path)) {
            String idTransaccionStr = request.getParameter("id");
            if (idTransaccionStr != null && !idTransaccionStr.trim().isEmpty()) {
                try {
                    int idTransaccion = Integer.parseInt(idTransaccionStr);

                    Transaccion transaccionToDelete = transaccionDAO.TransaccionPorID(idTransaccion);
                    if (transaccionToDelete != null && transaccionToDelete.getUsuarios_idusuarios() == usuarioEnSesion.getIdusuarios()) {
                        transaccionDAO.BORRARtransaccion(idTransaccion, usuarioEnSesion.getIdusuarios());
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid transaction ID for deletion: " + idTransaccionStr);
                }
            }
            // After deletion, redirect to the transaction list view
            response.sendRedirect(request.getContextPath() + "/transacciones");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioEnSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        String path = request.getServletPath();

        if ("/transacciones/crear".equals(path)) {
            String titulo = request.getParameter("titulo"); //
            String montoStr = request.getParameter("monto"); //
            String tipo = request.getParameter("tipo"); //
            String descripcion = request.getParameter("descripcion"); //

            // Basic Validation
            if (titulo == null || titulo.trim().isEmpty() ||
                    montoStr == null || montoStr.trim().isEmpty() ||
                    tipo == null || tipo.trim().isEmpty()) {
                request.setAttribute("error", "Título, Monto y Tipo son campos requeridos.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearTransaccion.jsp").forward(request, response);
                return;
            }

            double monto;
            try {
                monto = Double.parseDouble(montoStr);
                if (monto <= 0) { // Monto must be greater than 0
                    request.setAttribute("error", "El monto debe ser un número mayor a 0.");
                    request.getRequestDispatcher("/com/example/lab09/jsp/crearTransaccion.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Monto inválido. Ingrese un número.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearTransaccion.jsp").forward(request, response);
                return;
            }

            if (!"ingreso".equalsIgnoreCase(tipo) && !"egreso".equalsIgnoreCase(tipo)) { // Tipo must be 'ingreso' or 'egreso'
                request.setAttribute("error", "Tipo de transacción inválido. Debe ser 'ingreso' o 'egreso'.");
                request.getRequestDispatcher("/com/example/lab09/jsp/crearTransaccion.jsp").forward(request, response);
                return;
            }
        }
    }
}
