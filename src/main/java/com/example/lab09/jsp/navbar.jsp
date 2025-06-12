<% Object userName = null; %>
import com.example.lab09.beans.Usuario

Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
String userName = (usuarioEnSesion != null) ? usuarioEnSesion.getNombre() : "Invitado"; // Display user's name

<nav style="background-color: #333; padding: 10px 20px; color: white; display: flex; justify-content: space-between; align-items: center;">
    <h3 style="margin: 0;">Gestión de Gastos - <%= userName %></h3>
    <ul style="list-style-type: none; margin: 0; padding: 0; display: flex;">
        <li style="margin-left: 20px;"><a href="${pageContext.request.contextPath}/usuarios" style="color: white; text-decoration: none;">Usuarios</a></li> <li style="margin-left: 20px;"><a href="${pageContext.request.contextPath}/transacciones" style="color: white; text-decoration: none;">Transacciones</a></li> <li style="margin-left: 20px;"><a href="${pageContext.request.contextPath}/logout" style="color: white; text-decoration: none;">Cerrar Sesión</a></li>
    </ul>
</nav>
<hr style="border: 0; height: 1px; background-color: #ccc; margin-bottom: 20px;">