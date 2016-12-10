package es.uc3m.tiw.catalogo.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


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
	
	// Nombre de las b�squedas mapeadas
	public static final String BUSCAR_TODOS = "Disponibilidad.findAll";
	public static final String BUSCAR_DISPONIBILIDAD_ID = "Disponibilidad.seleccionarProductId";
	
	@Id
	@GeneratedValue
	@Column(name="id_disponibilidad")
	private int idDisponibilidad;

	private String descripccion;

	private String nombre;

	//Una disponibilidad puede tener muchos productos (one-to-many)
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "disponibilidad")
	private Set<Producto> producto;
	
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