package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "producto")
@NamedQueries({ 
	@NamedQuery(name = Producto.BUSCAR_TODOS, query = "SELECT p FROM Producto p"),
	@NamedQuery(name = Producto.BUSCAR_REALIZA_ENVIOS, query = "SELECT p FROM Producto p where p.envios=:envios"),
	@NamedQuery(name = Producto.BUSCAR_FECHA_PUBLICACION, query = "SELECT p FROM Producto p where p.fechaPublicacion=:fechaPublicacion"),
	@NamedQuery(name = Producto.BUSCAR_CATEGORIA, query = "SELECT p FROM Producto p where p.categoria=:categoria"),
	@NamedQuery(name = Producto.BUSCAR_CATEGORIA_LIKE, query = "SELECT p FROM Producto p where p.categoria.nombre LIKE :categoria"),
	@NamedQuery(name = Producto.BUSCAR_DISPONIBILIDAD, query = "SELECT p FROM Producto p where p.disponibilidad=:disponibilidad"),
	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO, query = "SELECT p FROM Producto p where p.usuario=:usuario"),
	//Los parámetros deben contener % a cada uno de los lados
	@NamedQuery(name = Producto.BUSCAR_DESCRIPCCION, query = "SELECT p FROM Producto p where p.descripccion LIKE :descripccion"),
	@NamedQuery(name = Producto.BUSCAR_TITULO_Y_DESCRIPCCION, query = "SELECT p FROM Producto p where p.titulo LIKE :titulo OR p.descripccion LIKE :descripccion"),
	@NamedQuery(name = Producto.BUSCAR_TITULO, query = "SELECT p FROM Producto p where p.titulo LIKE :titulo"),
	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO_POR_NOMBRE, query = "SELECT p FROM Producto p JOIN p.usuario u WHERE u.nombre  LIKE :nombre"),
	@NamedQuery(name = Producto.BUSCAR__POR_CIUDAD, query = "SELECT p FROM Producto p JOIN p.usuario u WHERE u.ciudad  LIKE :ciudad")

})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Nombre de las búsquedas mapeadas
	public static final String BUSCAR_TODOS = "Producto.findAll";
	public static final String BUSCAR_REALIZA_ENVIOS = "Producto.seleccionarRealizaEnvios";
	public static final String BUSCAR_FECHA_PUBLICACION = "Producto.seleccionarFechaPublicacion";
	public static final String BUSCAR_CATEGORIA = "Producto.seleccionarCategoria";
	public static final String BUSCAR_CATEGORIA_LIKE = "Producto.seleccionarCategoriaLike";
	public static final String BUSCAR_DISPONIBILIDAD = "Producto.seleccionarDisponibilidad";
	public static final String BUSCAR_USUARIO_PROPIETARIO = "Producto.seleccionarUsuarioPropietario";
	public static final String BUSCAR_DESCRIPCCION = "Producto.seleccionarDescripccion";
	public static final String BUSCAR_TITULO_Y_DESCRIPCCION = "Producto.seleccionarTituloYDescripccion";
	public static final String BUSCAR_TITULO = "Producto.seleccionarTitulo";
	
	public static final String BUSCAR__POR_CIUDAD = "Producto.seleccionarPorCiudad";
	public static final String BUSCAR_USUARIO_PROPIETARIO_POR_NOMBRE = "Producto.seleccionarPorNombreUsuario";

	@Id
	@Column(name="product_id")
	private int productId;

	private String descripccion;

	private String envios;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;

	@Lob
	private byte[] imagen;

	private int precio;

	@Column(name="precio_negociable")
	private String precioNegociable;

	private String titulo;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Disponibilidad
	@ManyToOne
	@JoinColumn(name="id_disponibilidad")
	private Disponibilidad disponibilidad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="email_usuario_propietario")
	private Usuario usuario;

	public Producto() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getEnvios() {
		return this.envios;
	}

	public void setEnvios(String envios) {
		this.envios = envios;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getPrecioNegociable() {
		return this.precioNegociable;
	}

	public void setPrecioNegociable(String precioNegociable) {
		this.precioNegociable = precioNegociable;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Disponibilidad getDisponibilidad() {
		return this.disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}