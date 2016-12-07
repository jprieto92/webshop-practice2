<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entitiesJPA.Producto"%>
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

	<!-- Register Section -->
	<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Modificación disponibilidad de un producto</h2>
				<hr class="star-light">
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerAdminServlet" id="contactForm"
					enctype="multipart/form-data" novalidate method="post">

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Disponibilidad</label> <select class="form-control"
								id="categoriaProducto" name="disponibilidadProducto">
								<%	Disponibilidad disponibilidadProducto= (Disponibilidad)request.getAttribute("disponibilidadProducto"); 
								List<Disponibilidad> listaDisponibilidades = (List<Disponibilidad>) request.getAttribute("listaDeDisponibilidades");
                                	for(Disponibilidad disponibilidad : listaDisponibilidades){
                                		if(disponibilidad.equals(disponibilidadProducto)){
                                			out.println("<option value=\""+ disponibilidad.getIdDisponibilidad() + "\" selected>"+disponibilidad.getNombre()+"</option>");
                                		}else{
                                			out.println("<option value=\""+ disponibilidad.getIdDisponibilidad() + "\" >"+disponibilidad.getNombre()+"</option>");
                                		}
                                	}
                                	%>
							</select>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<!--  Se recoge el idProducto para luego volverlo a enviar -->
					<input type="hidden" name="idProducto" value="<% out.print(request.getParameter("idProducto"));%>"> <br>
					<!--  Se envia la acción del formulario -->
					<input type="hidden" name="pAccion"
						value="cambiarDisponibilidadProductoAdmin"> <br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Modificar disponibilidad</button>
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