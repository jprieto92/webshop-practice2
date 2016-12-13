<%@page import="entitiesJPA.Producto"%>
<%@page import="entitiesJPA.Usuario"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
    <title>Enviar Mensaje</title>

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

<!--  Se recuperan los parámetros enviados en el manejador asociado a la accion "enviarMensajeProducto" -->
<%String destinatario = (String)request.getAttribute("destinatario");
  
  HttpSession sesion = request.getSession(false);
  String usuarioSession = (String) sesion.getAttribute("userEmailSession");%>
  
  
  
<!-- Register Product Section -->
    <section id="MessageSection">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Mensaje</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <form name="sentMessage" action="ControllerAdminServlet" id="contactForm" method="post">
   						<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <p><b>Destinatario: </b> <%out.print(destinatario); %></p>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <p><b>Mensaje nuevo: </b></p>
                                <!-- <textarea name="message" class="form-control" cols="40" rows="6" id="descripcion" name="descripcion" required data-validation-required-message="Por favor, introduce una descripcción."></textarea>-->
                                <textarea class="form-control" placeholder="Mensaje" id="mensaje" name ="mensaje" maxlength="500" cols="50" rows="8"required data-validation-required-message="Por favor, introduce un mensaje."></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <!--  Campos de recolección de datos para enviar -->
                        <input type="hidden" id="destinatario" name="destinatario" value="<%out.print(destinatario);%>">                                                
                        <input type="hidden" id="emisor" name="emisor" value="<%out.print(usuarioSession);%>">
                        <input type="hidden" name="pAccion" value="sendMessageAdmin">
                        
                        <br>
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


<%@include file="includes/footer.jsp" %>
</body>
</html>