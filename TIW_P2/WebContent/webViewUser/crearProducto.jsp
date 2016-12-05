<%@page import="entitiesJPA.Categoria"%>
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
    
    <title>Crear producto</title>

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

<!-- Register Product Section -->
    <section id="registerProduct">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>A�adir producto</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <form name="sentMessage" action="ControllerServlet" id="contactForm" method="post" enctype="multipart/form-data">
                        <div class="row control-group">
                                <label>Titulo</label>
                                <input type="text" class="form-control" placeholder="Titulo" id="tituloProducto" name ="tituloProducto" required data-validation-required-message="Por favor, introduce un t�tulo de producto">
                                <p class="help-block text-danger"></p>
                            </div>
                        

                        <div class="row control-group">
                                <label>Categoria</label>
                                <select class="form-control" required id="categoriaProducto" name ="categoriaProducto">
                                	<% List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("listaDeCategorias");
                                	for(Categoria categoria : listaCategorias){
                                		out.println("<option value=\""+ categoria.getIdCategoria() + "\">"+categoria.getNombre()+"</option>");
                                	}
                                	%>
                                </select>
                                <p class="help-block text-danger"></p>
                            </div>
                        
                        
                        <div class="row control-group">
                            
                                <label>Descripci�n</label>
                                <!-- <textarea name="message" class="form-control" cols="40" rows="6" id="descripcion" name="descripcion" required data-validation-required-message="Por favor, introduce una descripcci�n."></textarea>-->
							    <textarea class="form-control" placeholder="Descripcci�n" id="descripcion" name ="descripcion" maxlength="500" cols="50" rows="6"required data-validation-required-message="Por favor, introduce una descripcci�n."></textarea>
                                
                                <p class="help-block text-danger"></p>
                            
                        </div>
                                                                        
                        <div class="row control-group">
                           
                                <label>Imagen 1</label>
                                <input type="file" class="form-control" required placeholder="Imagen 1 del producto" id="imagen1Producto" name="imagen1Producto" >
                                <p class="help-block text-danger"></p>
                            
                        </div>
         
                        
                       <div class="row control-group">
                                <label>Realiza Env�os</label>
                                <select class="form-control" required id="realizaEnviosProducto" name ="realizaEnviosProducto">
                                	<option value="si">Si</option>
                                	<option value="no">No</option>
                                </select>
                                <p class="help-block text-danger"></p>

                        </div>      
                        
                       <div class="row control-group">
                                <label>Precio negociable</label>
                                <select class="form-control" required id="precioNegociable" name ="precioNegociable">
                                	<option value="si">Si</option>
                                	<option value="no">No</option>
                                </select>
                                <p class="help-block text-danger"></p>
                            
                        </div>
                        
                        <div class="row control-group">
                       
                                <label>Precio ($)</label>
                                <input type="text" class="form-control" placeholder="Precio del producto en euros" id="precioProducto" name ="precioProducto" required data-validation-required-message="Inserte un precio para el producto.">
                                <p class="help-block text-danger"></p>

                        </div>
                        
                        <input type="hidden" name="pAccion" value="createProduct">
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