<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entitiesJPA.Categoria"%>
<%@page import="utilidades.UtilidadesImagen"%>
<%@page import="entitiesJPA.Disponibilidad"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar disponibilidad</title>
</head>
<body>
	<%@include file="includes/headerWithSession.jsp"%>
	<% Categoria categoria = (Categoria) request.getAttribute("categoriaModificar"); %>
	
	<!-- Register Section -->
	<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Modificar categoría</h2>
				<hr class="star-light">
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerAdminServlet" id="contactForm" enctype="multipart/form-data" novalidate method="post">
					<div class="row control-group">
							<label>Id&nbsp;&nbsp;</label> 
							<label><%out.print(categoria.getIdCategoria());%></label>
							<input type="hidden" name="idCategoria" value="<%out.print(categoria.getIdCategoria());%>">
					</div>
					<div class="row control-group">
							<label>Nombre</label> 
							<input type="text" class="form-control"
							value="<%out.print(categoria.getNombre()); %>" id="nombreCategoria"name="nombreCategoria" 
								required
								data-validation-required-message="Por favor, introduce el nombre de la categoría.">
							<p class="help-block text-danger"></p>
						
					</div>

					<div class="row control-group">
						
							<label>Descripcion</label> 
							<textarea class="form-control" id="descripcionCategoria" 
							name ="descripcionCategoria" maxlength="500" cols="50" rows="6"
							required data-validation-required-message="Por favor, introduzca una descripccion de categoria."><%out.print(categoria.getDescripccion()); %></textarea>
							
							<p class="help-block text-danger"></p>
						
					</div>
					<input type="hidden" name="pAccion" value=actualizarCategoriaAdmin>
					<br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Modificar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>

	<%@include file="includes/footer.jsp"%>


</body>
</html>