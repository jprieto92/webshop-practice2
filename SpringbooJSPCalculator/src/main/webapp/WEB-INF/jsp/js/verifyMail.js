function validarEmail(valor) 
{
	if( !(/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)/.test(valor)) ) {
		alert("La dirección de email es incorrecta");  
		return false;
		}
}