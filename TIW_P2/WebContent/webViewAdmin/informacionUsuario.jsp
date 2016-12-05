<%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Infomaci�n detallada del usuario</title>
</head>
<body>
	<%@include file="includes/headerWithSession.jsp"%>




	<!--  Se recupera la entidad usuario de la sesi�n -->
	<% Usuario usuario = (Usuario) request.getAttribute("userEntity"); %>


	<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>
					Informaci�n detallada de
					<%out.print(usuario.getEmail());%></h2>
				<hr class="star-primary">
				<p><img style="width:160px;height:160px;" src="<% out.print(UtilidadesImagen.mostrarImagen(usuario.getImagenPerfil())); %>"></p>
				<p><b>Nombre:</b> <%out.print(usuario.getNombre());%></p>
				<p><b>Apellido 1:</b> <%out.print(usuario.getApellido1());%></p>
				<p><b>Apellido 2:</b> <%out.print(usuario.getApellido2());%></p>
				<p><b>Ciudad:</b> <%out.print(usuario.getCiudad());%></p>
				<p><b>Tel�fono:</b> <%out.print(usuario.getTelefono());%></p>
				<p><b>Fecha de alta:</b> <%out.print(usuario.getFechaAlta());%></p>
			</div>
		</div>
		<form action="ControllerAdminServlet" name="formModificarProducto"
					novalidate method="post">
					<input type="hidden" name="pAccion" value="comprobarUsuarioMostrarPerfilAdmin">
					<input type="hidden" name="idUsuario"
						value="<%out.print(usuario.getEmail());%>">
						<%System.out.println("Se va a proceder a modificar el usuario con email:"+usuario.getEmail()+" desde gestionarUsuarios.JSP"); %>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Modificar</button>
						</div>
					</div>
		</form>
		<form action="ControllerAdminServlet" name="formEliminarProducto" novalidate method="post">
					<input type="hidden" name="pAccion" value="comprobarUsuarioEliminarUsuarioAdmin">
					<input type="hidden" name="idUsuario" value="<%out.print(usuario.getEmail());%>">
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Eliminar</button>
						</div>
					</div>
		</form>	
		<form action="ControllerAdminServlet" name="formEnviarMensaje" novalidate method="post">
				<input type="hidden" name="pAccion" value="enviarMensajeAdmin">
				<input type="hidden" name="destinatario" value="<% out.print(usuario.getEmail()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Enviar mensaje</button>
					</div>
				</div>
		</form>

	</div>
	</section>












	<%@include file="includes/footer.jsp"%>
</body>
</html>