package es.uc3m.tiw.chat.domains;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, String> {

	
}