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

<title>SHOPIFY</title>

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
	<script type="text/javascript" src="js/sha256.js"></script>
	<script type="text/javascript" src="js/funcionesFormulario.js"></script>
</head>

<body id="page-top" class="index">

	<%@include file="includes/headerWithoutSession.jsp"%>


	<% String variable = (String) request.getAttribute("indexMessage"); %>
	<p>
		<% if(variable!=null) out.println(variable); %>
	</p>

	<!-- Login Section -->
	<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Login</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" action="ControllerServlet" id="contactForm" method="post" onsubmit="hash('passLogin', 'passHashLogin')">
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Email Address</label> <input type="email"
								class="form-control" placeholder="Correo electrónico" id="email"
								name="emailLogin" required
								data-validation-required-message="Por favor, introduzca un correo electrónico.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Password</label> <input type="password" class="form-control"
								placeholder="Contraseña" id="passLogin" name="passLogin" required
								data-validation-required-message="Por favor, introduzca una contraseña">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					
					<!-- Campo donde almacenar la contraseña en hash -->
					<input type="hidden" id="passHashLogin" name="passHashLogin">

					<input type="hidden" name="pAccion" value="login"> <br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Send</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>

	<!-- Register Section -->
	<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Registro</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->

				<form name="sentMessage" action="ControllerServlet" id="contactForm"
					method="post" enctype="multipart/form-data" onsubmit="hash('passRegister', 'passHashRegister')">


					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Email Address</label> <input type="email"
								class="form-control" placeholder="Correo electrónico" id="email"
								name="email" required
								data-validation-required-message="Por favor, introduzca un correo electrónico.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					
					<!-- Campo donde almacenar la contraseña en hash -->
					<input type="hidden" id="passHashRegister" name="passHashRegister">
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Contraseña</label> <input type="password"
								class="form-control" placeholder="Contraseña" id="passRegister"
								name="passRegister" required
								data-validation-required-message="Por favor, introduzca una contraseña">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label> Verificar Contraseña</label> <input type="password"
								class="form-control" placeholder="Verficar Contraseña"
								id="verifypass" name="verifypass" required
								data-validation-required-message="Por favor, introduzca una contraseña de verificación." onblur="verificacionPass('passRegister', 'verifypass')">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Nombre</label> <input type="text" class="form-control"
								placeholder="Nombre real" id="Nombre" name="name" required
								data-validation-required-message="Por favor, introduce tu nombre.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Apellido 1</label> <input type="text" class="form-control"
								placeholder="Apellido 1" id="Apellido 1" name="apellido1"
								required data-validation-required-message="Por favor, introduce tu primer apellido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Apellido 2</label> <input type="text" class="form-control"
								placeholder="Apellido 2" id="Apellido 2" name="apellido2"
								required data-validation-required-message="Por favor, introduce tu segundo apellido.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Ciudad</label> <input type="text" class="form-control"
								placeholder="Ciudad" id="Ciudad" name="ciudad" required
								data-validation-required-message="Por favor, introduce tu ciudad.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Teléfono</label> <input type="phone" class="form-control"
								placeholder="Teléfono" id="phone" name="phone">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls ">
							<label>Imagen perfil</label> <input type="file"
								class="form-control" required placeholder="Imagen de perfil"
								id="imagenPerfil" name="imagenPerfil">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<input type="hidden" name="pAccion" value="register"> <br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Enviar</button>
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