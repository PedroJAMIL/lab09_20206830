<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Transacción - Gestión de Gastos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 400px; margin: auto; }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; }
        input[type="text"], input[type="number"], select, textarea { width: calc(100% - 20px); padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; }
        input[type="submit"] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .error { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Crear Nueva Transacción</h2>
    <form action="${pageContext.request.contextPath}/transacciones/crear" method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br>
        <label for="monto">Monto:</label>
        <input type="number" id="monto" name="monto" step="0.01" min="0.01" required><br>
        <label for="tipo">Tipo:</label>
        <select id="tipo" name="tipo" required>
            <option value="ingreso">Ingreso</option>
            <option value="egreso">Egreso</option>
        </select><br>
        <label for="descripcion">Descripción (opcional):</label>
        <textarea id="descripcion" name="descripcion" rows="4" cols="50"></textarea><br><br>
        <input type="submit" value="Registrar Transacción">
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
</div>
</body>
</html>