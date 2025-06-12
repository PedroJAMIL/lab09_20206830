<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - Gestión de Gastos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 300px; margin: auto; }
        h2 { text-align: center; color: #333; }
        label { display: block; margin-bottom: 8px; font-weight: bold; }
        input[type="email"], input[type="password"] { width: calc(100% - 20px); padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; }
        input[type="submit"] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .error { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Iniciar Sesión</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required><br>
        <label for="pass">Contraseña:</label>
        <input type="password" id="pass" name="pass" required><br><br>
        <input type="submit" value="Iniciar Sesión">
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
</div>
</body>
</html>