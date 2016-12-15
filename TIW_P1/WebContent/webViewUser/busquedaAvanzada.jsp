<%@page import="entitiesJPA.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@include file="includes/headerWithSession.jsp"%>

	<!--  Div que contiene la búsqueda avanzada -->
	<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Búsqueda avanzada</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<form action="ControllerServlet" name="formProductos" novalidate
				method="post">

				<!-- Parámetro que leerá el Controller Servlet -->
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">


				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Categoria</p> <select class="form-control"
								id="categoriaProducto" name="campoBusquedaCategoria">
								<option value="">Escoge categoría</option>
								<%
									List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("listaDeCategorias");
									for (Categoria categoria : listaCategorias) {
										out.println(
												"<option value=\"" + categoria.getIdCategoria() + "\">" + categoria.getNombre() + "</option>");
									}
								%>
							</select>
							<p></p>
						</div>
					</div>
				</div>

				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Ciudad</label> <input type="text" class="form-control"
								placeholder="Introduzca un nombre de ciudad"
								id="campoBusquedaCiudad" name="campoBusquedaCiudad" required
								data-validation-required-message="Introduzca un nombre de ciudad">
							<p></p>
						</div>
					</div>
				</div>

				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Título de producto</label> <input type="text"
								class="form-control"
								placeholder="Introduzca un título de producto"
								id="campoBusquedaTitulo" name="campoBusquedaTitulo" required
								data-validation-required-message="Introduzca un título de producto">
							<p></p>
						</div>
					</div>
				</div>

				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Descripcción de producto</label> <input type="text"
								class="form-control"
								placeholder="Introduzca una descripcción de producto"
								id="campoBusquedaDescripccion" name="campoBusquedaDescripccion"
								required
								data-validation-required-message="Introduzca una descripcción de producto">
							<p></p>
						</div>
					</div>
				</div>

				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Email del vendedor</label> <input type="text"
								class="form-control"
								placeholder="Introduzca un email de vendedor"
								id="campoBusquedaEmailVendedor"
								name="campoBusquedaEmailVendedor" required
								data-validation-required-message="Introduzca un email de vendedor">
							<p></p>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-success btn-lg">Buscar</button>
			</form>
		</div>


	</div>
	</section>


	<%@include file="includes/footer.jsp"%>




</body>
</html>