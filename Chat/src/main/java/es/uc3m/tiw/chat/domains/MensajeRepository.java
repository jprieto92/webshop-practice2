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
	@Query("SELECT m FROM Mensaje m WHERE (m.emisor = (SELECT u FROM Usuario u WHERE u.email =:usuario1) and"
			+ " m.receptor(SELECT u FROM Usuario u WHERE u.email =:usuario2))or "
			+ "(m.emisor = (SELECT u FROM Usuario u WHERE u.email =:usuario2) and"
			+ " m.receptor = (SELECT u FROM Usuario u WHERE u.email =:usuario1))")
	public List<Mensaje> findOneConversation(@Param("usuario1")String usuario1, @Param("usuario2")String usuario2);
	
	@Query("SELECT DISTINCT m.emisor FROM Mensaje m WHERE m.receptor = (SELECT u FROM Usuario u WHERE u.email =:receptorId)")
	public List<Usuario> findConversations(@Param("receptor")String receptorId);

}