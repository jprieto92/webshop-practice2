function hash(idPassword, idPasswordHash){
	//Se recupera el valor de la contraseña
	var pass = document.getElementById(idPassword).value;

	if(pass!=""){
		//Se calcula el hash sha256 de la constraseña
		var hashPass = sha256_digest(pass);

		//Se establece en el campo input hidden el valor de la contraseña en hash
		document.getElementById(idPasswordHash).value = hashPass;	
	}
}

function verificacionPass(pass, verifyPass) {
	//Se recuperan ambas contraseñas y se comparan
    var pass = document.getElementById(pass).value;
    var verifypass = document.getElementById(verifyPass).value;

    //Si no coinciden, se borra la segunda contraseña de verificación y se lanza una ventana de aviso
    if(pass != verifypass){
        document.getElementById("verifypass").value="";
    	alert("Las claves de acceso deben coincidir. Por favor, verifiquelas.");
    }
 }