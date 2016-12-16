package es.uc3m.tiw.categoria.domains;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "producto")
//@NamedQueries({ 
//	@NamedQuery(name = Producto.BUSCAR_TODOS, query = "SELECT p FROM Producto p"),
//	@NamedQuery(name = Producto.BUSCAR_REALIZA_ENVIOS, query = "SELECT p FROM Producto p where p.envios=:envios"),
//	@NamedQuery(name = Producto.BUSCAR_FECHA_PUBLICACION, query = "SELECT p FROM Producto p where p.fechaPublicacion=:fechaPublicacion"),
//	@NamedQuery(name = Producto.BUSCAR_CATEGORIA, query = "SELECT p FROM Producto p where p.categoria=:categoria"),
//	@NamedQuery(name = Producto.BUSCAR_CATEGORIA_LIKE, query = "SELECT p FROM Producto p where p.categoria.nombre LIKE :categoria"),
//	@NamedQuery(name = Producto.BUSCAR_DISPONIBILIDAD, query = "SELECT p FROM Producto p where p.disponibilidad=:disponibilidad"),
//	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO, query = "SELECT p FROM Producto p where p.usuario=:usuario"),
//	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO_POR_EMAIL, query = "SELECT p FROM Producto p where p.usuario.email=:emailUsuario"),
//	//Los par�metros deben contener % a cada uno de los lados
//	@NamedQuery(name = Producto.BUSCAR_DESCRIPCCION, query = "SELECT p FROM Producto p where p.descripccion LIKE :descripccion"),
//	@NamedQuery(name = Producto.BUSCAR_TITULO_Y_DESCRIPCCION, query = "SELECT p FROM Producto p where p.titulo LIKE :titulo OR p.descripccion LIKE :descripccion"),
//	@NamedQuery(name = Producto.BUSCAR_TITULO, query = "SELECT p FROM Producto p where p.titulo LIKE :titulo"),
//	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO_POR_NOMBRE, query = "SELECT p FROM Producto p JOIN p.usuario u WHERE u.nombre  LIKE :nombre"),
//	@NamedQuery(name = Producto.BUSCAR__POR_CIUDAD, query = "SELECT p FROM Producto p JOIN p.usuario u WHERE u.ciudad  LIKE :ciudad"),
//	@NamedQuery(name = Producto.buscarPorEmail, query = "SELECT p FROM Producto p JOIN Usuario u WHERE u.email = :emailUser"),
//	@NamedQuery(name = Producto.BUSQUEDA_AVANZADA, query = "SELECT p FROM Producto p JOIN p.usuario u WHERE (p.titulo LIKE :titulo OR \"\"=:titulo) AND (p.descripccion LIKE :descripccion OR \"\"=:descripccion) AND (u.email=:emailUsuario OR \"\"=:emailUsuario) AND (u.ciudad=:ciudadUsuario OR \"\"=:ciudadUsuario)  AND (p.categoria.nombre=:nombreCategoria OR \"\"=:nombreCategoria)")
//
//})
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

//	// Nombre de las b�squedas mapeadas
//	public static final String BUSCAR_TODOS = "Producto.findAll";
//	public static final String BUSCAR_REALIZA_ENVIOS = "Producto.seleccionarRealizaEnvios";
//	public static final String BUSCAR_FECHA_PUBLICACION = "Producto.seleccionarFechaPublicacion";
//	public static final String BUSCAR_CATEGORIA = "Producto.seleccionarCategoria";
//	public static final String BUSCAR_CATEGORIA_LIKE = "Producto.seleccionarCategoriaLike";
//	public static final String BUSCAR_DISPONIBILIDAD = "Producto.seleccionarDisponibilidad";
//	public static final String BUSCAR_USUARIO_PROPIETARIO = "Producto.seleccionarUsuarioPropietario";
//	public static final String BUSCAR_USUARIO_PROPIETARIO_POR_EMAIL = "Producto.seleccionarUsuarioPropietarioPorEmail";
//	public static final String BUSCAR_DESCRIPCCION = "Producto.seleccionarDescripccion";
//	public static final String BUSCAR_TITULO_Y_DESCRIPCCION = "Producto.seleccionarTituloYDescripccion";
//	public static final String BUSCAR_TITULO = "Producto.seleccionarTitulo";
//	public static final String BUSCAR__POR_CIUDAD = "Producto.seleccionarPorCiudad";
//	public static final String BUSCAR_USUARIO_PROPIETARIO_POR_NOMBRE = "Producto.seleccionarPorNombreUsuario";
//	public static final String buscarPorEmail = "Producto.buscarPorEmail";
//	public static final String BUSQUEDA_AVANZADA = "Producto.busquedaAvanzada";
	
	@Id
	@Column(name="product_id")
	@GeneratedValue
	private Integer productId;

	private String descripccion;

	private String envios;

	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;

	private Integer precio;

	private String precioNegociable;

	private String titulo;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="email_usuario_propietario")
	private Usuario usuario;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="disponibilidad_id")
	private Disponibilidad disponibilidad;
    
	public Producto() {
	}

	public Producto(String descripccion, String envios, Date fechaPublicacion, int precio,
			String precioNegociable, String titulo, Usuario usuario, Categoria categoria,
			Disponibilidad disponibilidad) {
		super();
		this.descripccion = descripccion;
		this.envios = envios;
		this.fechaPublicacion = fechaPublicacion;
		this.precio = precio;
		this.precioNegociable = precioNegociable;
		this.titulo = titulo;
		this.usuario = usuario;
		this.categoria = categoria;
		this.disponibilidad = disponibilidad;
	}

	 /*******************************************************************
	 * 																	*
	 * SETTERS: Solo se modifican si el parametro es distinto de null	*
	 * 																	*
	 *******************************************************************/
	
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId != null ? productId : this.productId;
	}

	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion != null ? descripccion : this.descripccion;
	}

	public String getEnvios() {
		return this.envios;
	}

	public void setEnvios(String envios) {
		this.envios = envios != null ? envios : this.envios;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion != null ? fechaPublicacion : this.fechaPublicacion;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio != null ? precio : this.precio;
	}

	public String getPrecioNegociable() {
		return this.precioNegociable;
	}

	public void setPrecioNegociable(String precioNegociable) {
		this.precioNegociable = precioNegociable != null ? precioNegociable : this.precioNegociable;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo != null ? titulo : this.titulo;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario != null ? usuario : this.usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria != null ? categoria : this.categoria;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad != null ? disponibilidad : this.disponibilidad;
	}
}