package utilidades;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;

public class UtilidadesImagen {

	public static String mostrarImagen(Producto producto){
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(producto.getImagen(), false)));
		return sb.toString();
	}
	
	public static String mostrarImagenPerfil(Usuario usuario){
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(usuario.getImagenPerfil(), false)));
		return sb.toString();
	}
}
