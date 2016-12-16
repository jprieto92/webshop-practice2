package es.uc3m.tiw.catalogo.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	// Nombre de las b�squedas mapeadas
	public static final String BUSCAR_TODOS = "Categoria.findAll";
	public static final String BUSCAR_TODOS_SOLO_ID_Y_NOMBRE = "Categoria.todos_solo_id_nombre";
	@Id
	@GeneratedValue
	@Column(name="id_categoria")
	private int idCategoria;

	private String descripccion;

	private String nombre;

//	//Una categoria puede tener muchos productos (one-to-many)
//	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "categoria")
//	private Set<Producto> producto;
	
	public Categoria() {
	}
	

	public Categoria(int idCategoria, String descripccion, String nombre /*, Set<Producto> producto*/) {
		super();
		this.idCategoria = idCategoria;
		this.descripccion = descripccion;
		this.nombre = nombre;
//		this.producto = producto;
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