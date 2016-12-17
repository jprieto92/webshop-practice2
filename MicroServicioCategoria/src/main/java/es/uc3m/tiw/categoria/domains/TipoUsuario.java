package es.uc3m.tiw.categoria.domains;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the tipoUsuario database table.
 * 
 */
@Entity
@Table(name="tipo_Usuario")
@NamedQuery(name="TipoUsuario.findAll", query="SELECT t FROM TipoUsuario t")
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_tipoUsuario")
	private int idTipoUsuario;

	private String descripccion;

	private String nombre;

	//Un tipo de usuario puede tener muchos usuarios (one-to-many)
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "tipoUsuario")
	private Set<Usuario> usuario;
	
	public TipoUsuario() {
	}

	public int getIdTipoUsuario() {
		return this.idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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

}