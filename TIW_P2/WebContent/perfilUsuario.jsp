<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Perfil Usuario</title>

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


	<!--  Se recupera la entidad usuario de la sesión -->
	<% Usuario usuario = (Usuario) session.getAttribute("entityUser"); %>

	<!-- Register Section -->
	<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Modificación Perfil</h2>
				<hr class="star-primary">
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerServlet" id="contactForm"
					novalidate method="post" enctype="multipart/form-data">
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<img style="height: 50px;" src="<% out.print(UtilidadesImagen.mostrarImagenPerfil(usuario)); %>">
						</div>
					</div>
					
					<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls ">
                                <input type="file" class="form-control" placeholder="Imagen de perfil" id="imagenPerfil" name="imagenPerfil" >
                                <p class="help-block text-danger"></p>
                            </div>
                    </div>
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Contraseña</p> <input type="password"
								class="form-control" name="Nueva contraseña" id="pass"
								name="pass" required
								data-validation-required-message="Por favor, introduce tu contraseña.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p> Verificar Contraseña</p> <input type="password"
								class="form-control" name="Verficar Contraseña" id="verifypass"
								name="verifypass" required
								data-validation-required-message="Las contraseñas no coinciden">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Nombre</p> <input type="text" class="form-control"
								value="<%=usuario.getNombre()%>" id="Nombre" name="name"
								required
								data-validation-required-message="Por favor, introduce tu nombre.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Apellido 1</p> <input type="text" class="form-control"
								value="<%=usuario.getApellido1()%>" id="Apellido 1"
								name="apellido1" required
								data-validation-required-message="Campo requerido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Apellido 2</p> <input type="text" class="form-control"
								value="<%=usuario.getApellido2()%>" id="Apellido 2"
								name="apellido2" required
								data-validation-required-message="Campo requerido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Ciudad</p> <input type="text" class="form-control"
								value="<%=usuario.getCiudad()%>" id="Ciudad" name="ciudad"
								required data-validation-required-message="Campo requerido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<p>Teléfono</p> <input type="phone" class="form-control"
								value="<%=usuario.getTelefono()%>" id="phone" name="phone"
								required data-validation-required-message="Campo opcional">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<input type="hidden" name="pAccion" value="modificarPerfil">
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

	<!-- Unsubscribe Section -->
	<section id="unsubscribe">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Dar de baja usuario</h2>
				<hr class="star-light">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerServlet" id="contactForm"
					novalidate method="post">
					<input type="hidden" name="pAccion" value="darBajaUsuario">
					<br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Dar
								de baja</button>
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