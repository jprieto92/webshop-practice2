package es.uc3m.tiw.chat.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.uc3m.tiw.chat.domains.Mensaje;

public interface MensajeRepository  extends CrudRepository<Mensaje, Integer> {

	public List<Mensaje> findAll();

	public List<Mensaje> findByEmisor(String emisorId);
	
	public List<Mensaje> findByReceptor(String receptorId);
	
	/**
	 * Selecciona una conversacion entre dos usuarios
	 * */
	@Query("SELECT m FROM Mensaje m WHERE (m.emisor =:usuario1 AND"
			+ " m.receptor =:usuario2) OR "
			+ "(m.emisor =:usuario2 AND"
			+ " m.receptor =:usuario1)")
	public List<Mensaje> findOneConversation(@Param("usuario1")String usuario1, @Param("usuario2")String usuario2);
	
	@Query("SELECT DISTINCT m.emisor FROM Mensaje m WHERE m.receptor =:receptorId")
	public List<String> findConversations(@Param("receptorId")String receptorId);

}