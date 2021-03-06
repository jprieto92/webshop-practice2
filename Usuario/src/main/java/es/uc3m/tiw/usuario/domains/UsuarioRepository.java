package es.uc3m.tiw.usuario.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, String> {

	public List<Usuario> findAll();

	public List<Usuario> findByProductoProductId(@Param("productId")Integer productId);
	
	public Usuario findByEmailAndContrase├▒aAndTipoUsuarioIdTipoUsuario(
			@Param("email") String email, 
			@Param("pass") String pass, 
			@Param("tipoUser") Integer idTipoUsuario);

	public List<Usuario> findByNombreContains(String terminoFiltrado);

	public List<Usuario> findByTipoUsuarioIdTipoUsuario(Integer idTipoUsuario);

}