
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import com.example.lab09.beans.Usuario %>
<%@ page import java.util.List %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin: auto; max-width: 800px; }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .button-link { display: inline-block; background-color: #28a745; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-top: 20px; }
        .button-link:hover { background-color: #218838; }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Lista de Usuarios</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>DNI</th>
            <th>Correo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${listaUsuarios}">
            <tr>
                <td><c:out value="${usuario.idusuarios}"/></td>
                <td><c:out value="${usuario.nombre}"/></td>
                <td><c:out value="${usuario.apellido}"/></td>
                <td><c:out value="${usuario.dni}"/></td>
                <td><c:out value="${usuario.correo}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/usuarios/crear" class="button-link">Registrar Nuevo Usuario</a> </div>
</body>
</html>