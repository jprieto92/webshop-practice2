package es.uc3m.tiw.catalogo.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the usuario database table.
 * 
 */

@Entity
@Table(name = "usuario")

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	// Nombre de las b�squedas mapeadas
	public static final String BUSCAR_TODOS = "Usuario.seleccionarTodos";
	public static final String BUSCAR_NOMBRE = "Usuario.seleccionarNombre";
	public static final String BUSCAR_APELLIDO_1 = "Usuario.seleccionarApellido1";
	public static final String BUSCAR_APELLIDO_2 = "Usuario.seleccionarApellido2";
	public static final String BUSCAR_CIUDAD = "Usuario.seleccionarCiudad";
	public static final String BUSCAR_EMAIL = "Usuario.seleccionarEmail";
	public static final String BUSCAR_CREDENCIALES = "Usuario.comprobarCredenciales";
	public static final String BUSCAR_CREDENCIALES_SOLO_ID = "Usuario.comprobarCredencialesSoloId";
	public static final String DEVOLVER_TIPO_USUARIO_DADO_EMAIL = "Usuario.devuelveIdTipoUsuarioDadoEmail";
	public static final String BUSCAR_USUARIOS_USERS = "Usuario.buscaTodosUsuariosTipoUser";

	@Id
	@Column(name="email")
	private String email;
	
	private String nombre;
	
	private String apellido1;

	private String apellido2;

	private String ciudad;

	private int telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;
	
	private String contraseña;

	@Lob
	@Column(name="imagen_perfil")
	private byte[] imagenPerfil;

	//Un usuario puede tener muchos productos (one-to-many)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="usuarioEmail")
	private Set<Producto> producto;

	public Usuario() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public byte[] getImagenPerfil() {
		return this.imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}