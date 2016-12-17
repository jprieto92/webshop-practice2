package es.uc3m.tiw.chat.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the usuario database table.
 * 
 */

@Entity
@Table(name = "mensaje")

public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	// Nombre de las b�squedas mapeadas
	public static final String BUSCAR_TODOS = "Mensaje.seleccionarTodos";
	public static final String BUSCAR_MENSAJES_EMISOR = "Mensaje.mensajesEmisor";
	public static final String BUSCAR_MENSAJES_RECEPTOR = "Mensaje.mensajesReceptor";
	public static final String BUSCAR_CONVERSACION = "Mensaje.buscarConvesacion";
	
	@Id
	@Column(name="mensaje_id")    
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer mensajeId;
	
	
	private String emisor;
	
	
	private String receptor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;
	public Mensaje(){
		
	}
	public Mensaje(String emisor, String receptor, String mensaje) {
		super();
		this.emisor = emisor;
		this.receptor = receptor;
		//this.fechaPublicacion = fechaPublicacion;
		this.mensaje = mensaje;
	}
	

	public Mensaje(String mensajeAux) {
		this.mensaje = mensajeAux;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId != null ? mensajeId : this.mensajeId;
	}


	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	

}