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

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	@GeneratedValue
	private Integer productId;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="email_usuario_propietario")
	private Usuario usuario;
	
    @ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
    @ManyToOne
	@JoinColumn(name="disponibilidad_id")
	private Disponibilidad disponibilidad;
    
	private String descripccion;

	private String titulo;

	private Integer precio;
	
	private String precioNegociable;
	
	private String envios;

	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;
    
	@Lob
	private byte[] imagen;
    
	public Producto() {
	}

	public Producto(String descripccion, String envios, Date fechaPublicacion, int precio,
			String precioNegociable, String titulo, Usuario usuario, Categoria categoria,
			Disponibilidad disponibilidad, byte[] imagen) {
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
		this.imagen = imagen;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen != null ? imagen : this.imagen;
	}
	
	@Override
	public String toString() {
		return "Producto [productId=" + productId + ", descripccion=" + descripccion + ", envios=" + envios
				+ ", fechaPublicacion=" + fechaPublicacion + ", precio=" + precio + ", precioNegociable="
				+ precioNegociable + ", titulo=" + titulo + ", usuario=" + usuario + ", categoria=" + categoria
				+ ", disponibilidad=" + disponibilidad + "]";
	}
	
}