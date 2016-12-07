package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int productId;

	private String descripccion;

	private String envios;

	private Date fechaPublicacion;

	private int precio;

	private String precioNegociable;

	private String titulo;

	private Categoria categoria;

	private Disponibilidad disponibilidad;

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