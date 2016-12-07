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

<title>headerWithSession</title>

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

</head>
<body>
	<!-- Navigation -->
	<nav id="mainNav"
		class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand" href="#page-top">Shopify</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"><a
					HREF="javascript:document.misProductosSubmitForm.submit()">Gestionar
						productos</a>
					<form action="ControllerAdminServlet" name="misProductosSubmitForm"
						novalidate method="post">
						<input type="hidden" name="pAccion" value="gestionarProductos">
					</form></li>
				<li class="page-scroll"><a
					HREF="javascript:document.catalogSubmitForm.submit()">Gestionar
						usuarios</a>
					<form action="ControllerAdminServlet" name="catalogSubmitForm"
						novalidate method="post">
						<input type="hidden" name="pAccion" value="gestionarUsuarios">
					</form></li>

				<li class="page-scroll"><a
					HREF="javascript:document.misMensajesSubmitForm.submit()">Bandeja de Entrada</a>
					<form action="ControllerAdminServlet" name="misMensajesSubmitForm"
						novalidate method="post">
						<input type="hidden" name="pAccion" value="bandejaEntradaAdmin">
					</form></li>
				<li class="page-scroll"><a
					HREF="javascript:document.logoutSubmitForm.submit()">Logout</a>
					<form action="ControllerAdminServlet" name="logoutSubmitForm" novalidate
						method="post">
						<input type="hidden" name="pAccion" value="logoutAdmin">
					</form></li>

			</ul>
		</div>
	<%HttpSession sesionGeneral = request.getSession(false);
		String usuarioSessionGeneral = (String) session.getAttribute("userEmailSession"); %>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-2">
			<ul class="nav navbar-nav left">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"> <font color="#81F79F">Usuario: <%out.print(usuarioSessionGeneral);%></font>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="intro-text">
					<span class="name">Shopify - Web-Admin</span>
					<hr class="star-light">
				</div>
			</div>
		</div>
	</div>
	</header>
<div class="container">
		<div class="row">
	<label id="redirect2">
	
		<font size=4><b><% if(null!=request.getAttribute("Message")){
		out.println(request.getAttribute("Message"));} %>
		</b> </font>
	</label>
	</div></div>
	<script type="text/javascript">
	//<![CDATA[
		var countdownfrom=3;
		var currentsecond=4;
		function countredirect(){
			if (currentsecond!=1){
				currentsecond-=1;
			}
			else{
				document.getElementById("redirect2").innerHTML="";
			return
			}
			setTimeout("countredirect()",1000);
		}	
		countredirect()
//]]>
</script> <!--webbot bot="HTMLMarkup" endspan -->



</body>
</html>