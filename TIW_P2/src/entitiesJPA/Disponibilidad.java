package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the disponibilidad database table.
 * 
 */
@Entity
@Table(name="disponibilidad")
@NamedQueries({ 
	@NamedQuery(name= Disponibilidad.BUSCAR_TODOS, query="SELECT d FROM Disponibilidad d"),
	@NamedQuery(name = Disponibilidad.BUSCAR_DISPONIBILIDAD_ID, query = "SELECT d FROM Disponibilidad d where d.idDisponibilidad = :idDisponibilidad") })
public class Disponibilidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Nombre de las búsquedas mapeadas
	public static final String BUSCAR_TODOS = "Disponibilidad.findAll";
	public static final String BUSCAR_DISPONIBILIDAD_ID = "Disponibilidad.seleccionarProductId";
	
	@Id
	@Column(name="id_disponibilidad")
	private int idDisponibilidad;

	private String descripccion;

	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="disponibilidad")
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