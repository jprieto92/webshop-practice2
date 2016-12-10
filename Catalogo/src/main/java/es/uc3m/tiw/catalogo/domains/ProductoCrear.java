package es.uc3m.tiw.catalogo.domains;

import java.io.Serializable;
import java.util.Date;


public class ProductoCrear implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripccion;

	private String envios;

	private Date fechaPublicacion;

	private int precio;

	private String precioNegociable;

	private String titulo;
    
	private String email;
    
	private int idCategoria;
	
	private int idDisponibilidad;

	public String getDescripccion() {
		return descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getEnvios() {
		return envios;
	}

	public void setEnvios(String envios) {
		this.envios = envios;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getPrecioNegociable() {
		return precioNegociable;
	}

	public void setPrecioNegociable(String precioNegociable) {
		this.precioNegociable = precioNegociable;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdDisponibilidad() {
		return idDisponibilidad;
	}

	public void setIdDisponibilidad(int idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}
	
}

	