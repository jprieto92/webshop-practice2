package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.List;

public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id_tipoUsuario;

	private String descripccion;

	private String nombre;

	private List<Usuario> usuarios;

	public TipoUsuario() {
	}

	public int getId_tipoUsuario() {
		return this.id_tipoUsuario;
	}

	public void setId_tipoUsuario(int id_tipoUsuario) {
		this.id_tipoUsuario = id_tipoUsuario;
	}

	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipoUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipoUsuario(null);

		return usuario;
	}

}