package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")

@NamedQueries({ 
	@NamedQuery(name = Categoria.BUSCAR_TODOS, query="SELECT c FROM Categoria c"),
	@NamedQuery(name = Categoria.BUSCAR_TODOS_SOLO_ID_Y_NOMBRE, query="SELECT c.idCategoria, c.nombre FROM Categoria c")
	})
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	// Nombre de las búsquedas mapeadas
	public static final String BUSCAR_TODOS = "Categoria.findAll";
	public static final String BUSCAR_TODOS_SOLO_ID_Y_NOMBRE = "Categoria.todos_solo_id_nombre";
	@Id
	@Column(name="id_categoria")
	private int idCategoria;

	private String descripccion;

	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;

	public Categoria() {
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
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
		producto.setCategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoria(null);

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

        Categoria other = (Categoria) obj;
        
		if(this.idCategoria == other.getIdCategoria()) return true;
		
		return false;
	}

}