package entitiesJPA;

import java.io.Serializable;

public class UsuarioLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String pass;
	
	private int idTipoUsuario;

	public UsuarioLogin(String email, String pass, int idTipoUsuario) {
		super();
		this.email = email;
		this.pass = pass;
		this.idTipoUsuario = idTipoUsuario;
	}
	
	public UsuarioLogin() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	
}
