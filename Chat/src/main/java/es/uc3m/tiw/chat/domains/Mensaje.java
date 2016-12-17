package es.uc3m.tiw.chat.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	@Column(name="mensajeId")
	private int mensajeId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="emisor")
	private Usuario emisor;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receptor")
	private Usuario receptor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;
	
	public Mensaje(String mensajeAux) {
		this.mensaje = mensajeAux;
	}

	public int getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(int mensajeId) {
		this.mensajeId = mensajeId;
	}


	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
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