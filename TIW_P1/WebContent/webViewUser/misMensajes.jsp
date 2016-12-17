<%@page import="entitiesJPA.Producto"%>
<%@page import="entitiesJPA.Mensaje"%>
<%@page import="java.util.List"%>
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
    
    <title>Mensajes nuevos</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/freelancer.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<%@include file="includes/headerWithSession.jsp" %>

<%String mensaje = (String) request.getAttribute("chatMessage");
  HttpSession sesion = request.getSession(false);
  String usuarioSession = (String) session.getAttribute("userEmailSession");
  String conversacion= (String) session.getAttribute("conversacion");
  List<Mensaje> mensajesRecibidos =  (List<Mensaje>)request.getAttribute("mensajesRecibidos");%>
<!-- Register Product Section -->
    <section id="MessageSection">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Nuevos Mensajes</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <!-- IMPORTANTE, BUCLE FOR QUE GENERA ESTE CÓDIGO TANTAS VECES COMO MENSAJES TENGAMOS -->
                    <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <p class="help-block text-danger"><h5>Conversación con: <%out.print(conversacion);%></h5>
                                </p>
                            </div>
                    </div>
             		<%if(mensajesRecibidos!=null){
						for(Mensaje mensajeNuevo : mensajesRecibidos){%>
                       <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <textarea cols="100" rows="4" placeholder="<%out.print(mensajeNuevo.getMensaje());%>" disabled></textarea>
                            </div>
                        </div>
                        <%}}%>
                        <br>
                        <form action="ControllerServlet" name="formResponderMensaje" novalidate method="post">
                        	<input type="hidden" name="pAccion" value="enviarMensajeProducto">
                        	<input type="hidden" name="destinatario" value="<%out.print(conversacion);%>">
							<div id="success"></div>
								<div class="row">
									<div class="form-group col-xs-12">
									<button type="submit" class="btn btn-success btn-lg">Nuevo mensaje</button>
									</div>
								</div>
						</form>
						</div>
						<form action="ControllerServlet" name="formResponderMensaje" novalidate method="post">	
                        <div id="fail"></div>
                        <input type="hidden" name="pAccion" value="catalog">
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button type="submit" class="btn btn-success btn-lg">OK</button>
                            </div>
                        </div>
                    	</form>
                </div>
            </div>
    </section>

<%@include file="includes/footer.jsp" %>
</body>
</html>