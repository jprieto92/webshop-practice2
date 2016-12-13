package utilidades;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class UtilidadesImagen {

	public static String mostrarImagen(byte[] imagenEnBytes){
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imagenEnBytes, false)));
		return sb.toString();
	}
}
