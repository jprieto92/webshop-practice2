<%@page import="entitiesJPA.Producto"%>
<%@page import="utilidades.UtilidadesImagen"%>
<%@page import="java.util.List"%>
<%@page import="utilidades.UtilidadesList"%>
<%@ page
	import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64;"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Gestionar Productos</title>
<link href="css/my_style.css" rel="stylesheet">
<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Theme CSS -->
<link href="css/freelancer.min.css" rel="stylesheet">



<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<%@include file="includes/headerWithSession.jsp"%>

	<p></p>


	<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Gestión de productos</h2>
				<hr class="star-primary">
			</div>
		</div>
		
		<!-- Búsquedas -->
		<div class="row">
			<form action="ControllerAdminServlet" name="formProductos"
				method="post">
				<input type="hidden" name="pAccion" value="buscarProductosAdmin">
				<input type="hidden" name="tipoBusqueda"
					value="busquedaPorTituloDescripccion">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<input type="text" class="form-control"
							placeholder="Término de búsqueda" id="campoBusqueda"
							name="campoBusqueda" required
							data-validation-required-message="Por favor, introduzca un término de búsqueda">
						<p></p>
						<button type="submit" class="btn btn-success btn-lg">Buscar</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row">
			<form action="ControllerAdminServlet" name="formProductos" novalidate
				method="post">
				<input type="hidden" name="pAccion" value="ShowFormAdvancedSearchAdmin">
				<input type="hidden" name="tipoBusqueda"
					value="busquedaPorTituloDescripccion">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">						
						<button type="submit" class="btn btn-success btn-lg">Búsqueda avanzada</button>
					</div>
				</div>
			</form>
		</div>
		
		
		<div class="row">
			<% List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaDeProductos");
			if(listaProductos!=null){
				//Se reordena aleatoriamente la lista de productos
				UtilidadesList.reordenarAleatoriamente(listaProductos);
			for(Producto producto : listaProductos){%>


			<div class="col-sm-4 portfolio-item">
				<h4><%=producto.getTitulo() %></h4>
				<div
					style="width: 160px; height: 160px; -webkit-border-radius: 20px; -moz-border-radius: 20px; border-radius: 20px; background: rgba(24, 188, 156, 0.5); -webkit-box-shadow: #BFBEBF 7px 7px 7px; -moz-box-shadow: #BFBEBF 7px 7px 7px; box-shadow: #BFBEBF 7px 7px 7px;">
					<img style="width: 160px; height: 160px;"
						src="<% out.print(UtilidadesImagen.mostrarImagen(producto.getImagen())); %>">
				</div>

				<p></p>
				

				<form action="ControllerAdminServlet" name="formModificarProducto"
					novalidate method="post">
					<input type="hidden" name="pAccion" value="mostrarProductoAdmin">
					<input type="hidden" name="idProducto"
						value="<% out.print(producto.getProductId()); %>">
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">M&aacute;s informaci&oacute;n</button>
						</div>
					</div>
				</form>

			</div>

			<%}} %>
		</div>
	</div>
	</section>




	<%@include file="includes/footer.jsp"%>
</body>
</html>