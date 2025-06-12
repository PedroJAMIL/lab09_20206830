<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario - Gestión de Gastos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 400px; margin: auto; }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"] { width: calc(100% - 20px); padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; }
        input[type="submit"] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .error { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Crear Nuevo Usuario</h2>
    <form action="${pageContext.request.contextPath}/usuarios/crear" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        <label for="dni">DNI (8 dígitos):</label>
        <input type="text" id="dni" name="dni" maxlength="8" pattern="[0-9]{8}" title="Debe ser un número de 8 dígitos" required><br>
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required><br>
        <label for="pass">Contraseña (mínimo 8 caracteres, letras y números):</label>
        <input type="password" id="pass" name="pass" required minlength="8" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" title="Debe tener al menos 8 caracteres, incluyendo letras y números."><br>
        <label for="confirmPass">Confirmar Contraseña:</label>
        <input type="password" id="confirmPass" name="confirmPass" required><br><br>
        <input type="submit" value="Registrar Usuario">
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
</div>
</body>
</html>