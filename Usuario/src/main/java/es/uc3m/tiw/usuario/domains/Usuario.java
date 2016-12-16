package es.uc3m.tiw.usuario.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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

//	// Nombre de las b�squedas mapeadas
//	public static final String BUSCAR_TODOS = "Usuario.seleccionarTodos";
//	public static final String BUSCAR_NOMBRE = "Usuario.seleccionarNombre";
//	public static final String BUSCAR_APELLIDO_1 = "Usuario.seleccionarApellido1";
//	public static final String BUSCAR_APELLIDO_2 = "Usuario.seleccionarApellido2";
//	public static final String BUSCAR_CIUDAD = "Usuario.seleccionarCiudad";
//	public static final String BUSCAR_EMAIL = "Usuario.seleccionarEmail";
//	public static final String BUSCAR_CREDENCIALES = "Usuario.comprobarCredenciales";
//	public static final String BUSCAR_CREDENCIALES_SOLO_ID = "Usuario.comprobarCredencialesSoloId";
//	public static final String DEVOLVER_TIPO_USUARIO_DADO_EMAIL = "Usuario.devuelveIdTipoUsuarioDadoEmail";
//	public static final String BUSCAR_USUARIOS_USERS = "Usuario.buscaTodosUsuariosTipoUser";

	@Id
	@Column(name="email")
	private String email;
	
	private String nombre;
	
	private String apellido1;

	private String apellido2;

	private String ciudad;

	private Integer telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;
	
	private String contraseña;

	@Lob
	@Column(name="imagen_perfil")
	private byte[] imagenPerfil;

	//Un usuario puede tener muchos productos (one-to-many)
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<Producto> producto;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_usuario_id")
	private TipoUsuario tipoUsuario;
	
	public Usuario(String email, String nombre, String apellido1, String apellido2, String ciudad, int telefono,
			Date fechaAlta, String contraseña, byte[] imagenPerfil, TipoUsuario tipoUsuario) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.fechaAlta = fechaAlta;
		this.contraseña = contraseña;
		this.imagenPerfil = imagenPerfil;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email != null ? email : this.email;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1 != null ? apellido1 : this.apellido1;
		
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2 != null ? apellido2 : this.apellido2;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad != null ? ciudad : this.ciudad;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña != null ? contraseña : this.contraseña;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta != null ? fechaAlta : this.fechaAlta;
	}

	public byte[] getImagenPerfil() {
				return imagenPerfil;
			}
		
			public void setImagenPerfil(byte[] imagenPerfil) {
				this.imagenPerfil = imagenPerfil != null ? imagenPerfil : this.imagenPerfil;
			}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre != null ? nombre : this.nombre;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono != null ? telefono : this.telefono;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}