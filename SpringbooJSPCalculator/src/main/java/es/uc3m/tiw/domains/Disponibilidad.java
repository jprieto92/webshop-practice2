package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.List;

public class Disponibilidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idDisponibilidad;

	private String descripccion;

	private String nombre;

	private List<Producto> productos;

	public Disponibilidad() {
	}

	public int getIdDisponibilidad() {
		return this.idDisponibilidad;
	}

	public void setIdDisponibilidad(int idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
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

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setDisponibilidad(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setDisponibilidad(null);

		return producto;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

        Disponibilidad other = (Disponibilidad) obj;
        
		if(this.idDisponibilidad == other.getIdDisponibilidad()) return true;
		
		return false;
	}

}