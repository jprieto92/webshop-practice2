<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
    <%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Producto</title>
</head>
<body>

	
	<%@include file="includes/headerWithSession.jsp"%>
    <%Producto producto = (Producto) request.getAttribute("productoMostrar"); 
	  Usuario usuario = (Usuario) request.getAttribute("usuarioMostrar");%>
	<section id="portfolio">
		<div class="container">
			<div class="row">
			<div class="col-lg-12 text-center">
				<h2>
					<%out.print(producto.getTitulo());%>
				</h2>
				<hr class="star-primary">
				</div>
			
			</div>
		</div>
		<div class="container">
		<div class="row">
		<div class="col-lg-12 text-center">
				<div style="width: 160px; height: 160px; margin:0 auto; -webkit-border-radius: 20px; -moz-border-radius: 20px; border-radius: 20px; background: rgba(24, 188, 156, 0.5); -webkit-box-shadow: #BFBEBF 7px 7px 7px; -moz-box-shadow: #BFBEBF 7px 7px 7px; box-shadow: #BFBEBF 7px 7px 7px;">
				<img style="width: 160px; height: 160px;"
					src="<% out.print(UtilidadesImagen.mostrarImagen(producto.getImagen())); %>">
				</div>
			</div>
			<div class="col-lg-12 text-center">
				<h4>Descripcion</h4>
				<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
					<textarea style="text-align: center;" cols="100" rows="6" disabled><%out.print(producto.getDescripccion());%></textarea>
				</div></div>

			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio ($)</h4>
				<p>
					<%out.print(producto.getPrecio() + "$");%>
				</p>

			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio negociable</h4>
				<p>
					<%out.print(producto.getPrecioNegociable());%>
				</p>


			</div>
			
			<div class="col-lg-12 text-center">
				<h4>Categoría de producto</h4>
				<p>
					<%out.print(producto.getCategoria().getNombre());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Envios</h4>
				<p>
					<%out.print(producto.getEnvios());%>
				</p>


			</div>
		
			<div class="col-lg-12 text-center">
				<h4>Fecha publicación</h4>
				<p>
					<%out.print(producto.getFechaPublicacion());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Propietario</h4>
				<p>
					<%out.print(usuario.getEmail());%>
				</p>
			</div>
		</div>
		
		<div class="col-lg-12 text-center">
				<h4>Disponibilidad</h4>
				<p>
					<%out.print(producto.getDisponibilidad().getNombre());%>
				</p>
			</div>
		<div class="row">
		
			<form action="ControllerServlet" name="formVerPropietario" novalidate method="post">
					<input type="hidden" name="pAccion" value="comprobarUsuarioMostrarDatosUsuario"> <input type="hidden" 
					name="idUsuario" value="<%out.print(usuario.getEmail());%>">
					<div id="success"></div>
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Ver Propietario</button>
						</div>
				</form>	
				</div>
				</div>
	</section>
    <%@include file="includes/footer.jsp"%>
</body>
</html>