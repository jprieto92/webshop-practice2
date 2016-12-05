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
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">
				<input type="hidden" name="tipoBusqueda"
					value="busquedaPorCategoria">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<label>Categoria</label> <select class="form-control"
							id="categoriaProducto" name="campoBusqueda">
							<% List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("listaDeCategorias");
                                	for(Categoria categoria : listaCategorias){
                                		out.println("<option value=\""+ categoria.getNombre() + "\">"+categoria.getNombre()+"</option>");
                                	}
                                	%>
						</select>
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar
							por categoría</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<form action="ControllerServlet" name="formProductos" novalidate
				method="post">
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">
				<input type="hidden" name="tipoBusqueda" value="busquedaPorCiudad">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<input type="text" class="form-control"
							placeholder="Término de búsqueda" id="campoBusqueda"
							name="campoBusqueda" required
							data-validation-required-message="Introduzca un término de búsqueda.">
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar
							por ciudad</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<form action="ControllerServlet" name="formProductos" novalidate
				method="post">
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">
				<input type="hidden" name="tipoBusqueda" value="busquedaPorNombreUsuario">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<input type="text" class="form-control"
							placeholder="Término de búsqueda" id="campoBusqueda"
							name="campoBusqueda" required
							data-validation-required-message="Introduzca un término de búsqueda.">
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar
							por vendedor</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<form action="ControllerServlet" name="formProductos" novalidate
				method="post">
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">
				<input type="hidden" name="tipoBusqueda" value="busquedaPorTitulo">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<input type="text" class="form-control"
							placeholder="Término de búsqueda" id="campoBusqueda"
							name="campoBusqueda" required
							data-validation-required-message="Introduzca un término de búsqueda.">
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar
							por título</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<form action="ControllerServlet" name="formProductos" novalidate
				method="post">
				<input type="hidden" name="pAccion" value="buscarProductosAvanzada">
				<input type="hidden" name="tipoBusqueda" value="busquedaPorDescripccion">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<input type="text" class="form-control"
							placeholder="Término de búsqueda" id="campoBusqueda"
							name="campoBusqueda" required
							data-validation-required-message="Introduzca un término de búsqueda.">
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar
							por descripcción</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	</section>


	<%@include file="includes/footer.jsp"%>




</body>
</html>