package es.uc3m.tiw.disponibilidad.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_categoria")
	private int idCategoria;

	private String nombre;
	
	private String descripccion;

	//Una categoria puede tener muchos productos (one-to-many)
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "categoria")
	private Set<Producto> producto;
	
	public Categoria() {
	}
	
	public Categoria(String nombre, String descripccion) {
		super();
		this.nombre = nombre;
		this.descripccion = descripccion;
	}
	
	public Categoria(int idCategoria, String nombre, String descripccion) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripccion = descripccion;
	}
	


	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre != null ? nombre : this.nombre;
	}
	
	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion != null ? descripccion : this.descripccion;
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
