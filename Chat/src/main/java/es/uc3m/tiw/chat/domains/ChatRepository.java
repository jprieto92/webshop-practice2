package es.uc3m.tiw.chat.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatRepository  extends CrudRepository<Mensaje, Integer> {

	public List<Mensaje> findAll();

	public List<Mensaje> findByEmisorEmail(String email);
	
	public List<Mensaje> findByReceptorEmail(String email);
	
	public List<Mensaje> findConversation(String email);

}