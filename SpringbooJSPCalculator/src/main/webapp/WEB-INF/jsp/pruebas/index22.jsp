<!DOCTYPE html>
<%@ page import="es.uc3m.tiw.domains.*" %>

<html lang="en">

<body>
	
	<% Result a = (Result)request.getAttribute("result"); %>
	<span>Resultado: </span>
	<%= (a!=null)?a.getResult():"..." %>
	<br>

	<span>Resultado: </span>
	  ${(result!=null)?result.getResult():"..."}
	
	<span id="resultado"></span>
	<hr>
	<form  action="calculate" method="post">
		<label for="sumar">Sum: </label>
		<input type="radio" name="operation"  value="sum"> 
		<br>
		<label for="restar">Subtraction: </label>
		<input type="radio" name="operation" value="subtraction">
		<br>
		<label for="multiplicar">Multiplication: </label> 
		<input type="radio" name="operation" value="multiplication"> 
		<hr>
		Operand 1: 
		<input type="number" name="operand1">
		<br>
		Operand 2:
		<input type ="number" name="operand2">
		<br>
		<input type ="submit" value="Calculate!">
	</form>
	
	
	<%for(int i=0;i<5;i++){ %>
	<h3>Iteration: <%=i %></h3>
	<%} %>
	
</body>

</html>