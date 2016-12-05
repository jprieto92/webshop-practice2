package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = Usuario.BUSCAR_NOMBRE, query = "SELECT u FROM Usuario u where u.nombre=:nombre"),
		@NamedQuery(name = Usuario.BUSCAR_APELLIDO_1, query = "SELECT u FROM Usuario u where u.apellido1=:apellido1"),
		@NamedQuery(name = Usuario.BUSCAR_APELLIDO_2, query = "SELECT u FROM Usuario u where u.apellido2=:apellido2"),
		@NamedQuery(name = Usuario.BUSCAR_CIUDAD, query = "SELECT u FROM Usuario u where u.ciudad=:ciudad"),
		@NamedQuery(name = Usuario.BUSCAR_EMAIL, query = "SELECT u FROM Usuario u where u.email=:email"),
		@NamedQuery(name = Usuario.BUSCAR_CREDENCIALES, query = "SELECT u FROM Usuario u where u.email=:email AND u.contrase�a=:contrase�a") })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Nombre de las b�squedas mapeadas
		public static final String BUSCAR_NOMBRE = "Usuario.seleccionarNombre";
		public static final String BUSCAR_APELLIDO_1 = "Usuario.seleccionarApellido1";
		public static final String BUSCAR_APELLIDO_2 = "Usuario.seleccionarApellido2";
		public static final String BUSCAR_CIUDAD = "Usuario.seleccionarCiudad";
		public static final String BUSCAR_EMAIL = "Usuario.seleccionarEmail";
		public static final String BUSCAR_CREDENCIALES = "Usuario.comprobarCredenciales";

	@Id
	private String email;

	private String apellido1;

	private String apellido2;

	private String ciudad;

	private String contrase�a;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Lob
	@Column(name="imagen_perfil")
	private byte[] imagenPerfil;

	private String nombre;

	private int telefono;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="usuario",  cascade = CascadeType.ALL)
	private List<Producto> productos;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="id_tipoUsuario")
	private TipoUsuario tipoUsuario;

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

	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUsuario(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUsuario(null);

		return producto;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}