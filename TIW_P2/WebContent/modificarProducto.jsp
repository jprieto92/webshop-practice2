<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
    <%@page import="utilidades.UtilidadesImagen" %>
    <%@page import="entitiesJPA.Categoria"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar producto</title>
</head>
<body>
<%@include file="includes/headerWithSession.jsp"%>


	<!--  Se recupera la entidad usuario de la sesión -->
	<% Producto producto = (Producto) request.getAttribute("productoModificar"); %>

	<!-- Register Section -->
	<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Modificación de un producto</h2>
				<hr class="star-primary">
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerServlet" id="contactForm" enctype="multipart/form-data"
					novalidate method="post">
					<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
					
					<div class="row control-group">
                                <p>Categoria</p>
                                <select class="form-control" id="categoriaProducto" name ="categoriaProducto">
                                	<% List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("listaDeCategorias");
                                	for(Categoria categoria : listaCategorias){
                                		if(categoria.equals(producto.getCategoria())){
                                			System.out.println("La categoria que voy a imprimir es:"+categoria.getNombre()+"");
                                			out.println("<option value=\""+ categoria.getIdCategoria() + "\" selected >"+categoria.getNombre()+"</option>");
                                		}else{
                                			out.println("<option value=\""+ categoria.getIdCategoria() + "\">"+categoria.getNombre()+"</option>");
                                		}                               		
                                	}
                                	%>
                                </select>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Titulo</p> 
							<input type="text" class="form-control"
							value="<%out.print(producto.getTitulo()); %>" id="tituloProducto"name="tituloProducto" 
								required
								data-validation-required-message="Por favor, introduce el titulo del producto.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Descripcion</p> <input type="text" class="form-control"
								value="<%out.print(producto.getDescripccion()); %>" id="descripcionProducto"
								name="descripcionProducto" required
								data-validation-required-message="Campo requerido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <p>Realiza envios</p>
                                <select class="form-control" id="realizaEnviosProducto" name ="realizaEnviosProducto">
                                	<%if(producto.getEnvios().equals("SI")){
                                		out.println("<option value=\"SI\" selected >SI</option>");
                                		out.println("<option value=\"NO\" >NO</option>");
                                		                   		
                                	}else{
                                		out.println("<option value=\"SI\" >SI</option>");
                                		out.println("<option value=\"NO\" selected >NO</option>");
                                	} %>
             
                                </select>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>      
                        
                       <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <p>Precio negociable</p>
                                <select class="form-control" id="precioNegociable" name ="precioNegociable">
                                	<%if(producto.getPrecioNegociable().equals("SI")){
                                		out.println("<option value=\"SI\" selected >SI</option>");
                                		out.println("<option value=\"NO\" >NO</option>");
                                		                   		
                                	}else{
                                		out.println("<option value=\"SI\" >SI</option>");
                                		out.println("<option value=\"NO\" selected >NO</option>");
                                	} %>
                                </select>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
					
					
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Precio</p> <input type="text" class="form-control"
								value="<%out.print(producto.getPrecio()); %>" id="precioProducto"
								name="precioProducto" required
								data-validation-required-message="Campo requerido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<img style="height: 50px;" src="<% out.print(UtilidadesImagen.mostrarImagen(producto)); %>">
						</div>
					</div>
					
					<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls ">
                                <input type="file" class="form-control" placeholder="Imagen 1 del producto" id="imagen1Producto" name="imagen1Producto" >
                                <p class="help-block text-danger"></p>
                            </div>
                    </div>
					



					<input type="hidden" name="pAccion" value="actualizarProducto">
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