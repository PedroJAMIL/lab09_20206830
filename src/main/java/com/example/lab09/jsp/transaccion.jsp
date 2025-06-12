<%@ page import com.example.lab09.beans.Transaccion %>
<%@ page import com.example.lab09.beans.Transaccion %>
<%@ page import java.util.List %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Transacciones</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin: auto; max-width: 900px; }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .button-link { display: inline-block; background-color: #007bff; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-top: 20px; }
        .button-link:hover { background-color: #0056b3; }
        .delete-link { color: red; text-decoration: none; }
        .delete-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Lista de Transacciones</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Monto</th>
            <th>Tipo</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="transaccion" items="${listaTransacciones}">
            <tr>
                <td><c:out value="${transaccion.idtransacciones}"/></td>
                <td><c:out value="${transaccion.titulo}"/></td>
                <td><c:out value="${transaccion.monto}"/></td>
                <td><c:out value="${transaccion.tipo}"/></td>
                <td><c:out value="${transaccion.descripcion}"/></td>
                <td><c:out value="${transaccion.fecha}"/></td>
                <td><a href="${pageContext.request.contextPath}/transacciones/eliminar?id=<c:out value='${transaccion.idtransacciones}'/>" class="delete-link" onclick="return confirm('¿Estás seguro de que quieres eliminar esta transacción?');">Eliminar</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/transacciones/crear" class="button-link">Registrar Nueva Transacción</a>
</div>
</body>
</html>