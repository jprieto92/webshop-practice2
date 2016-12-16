package es.uc3m.tiw.usuario.domains;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, String> {
	public List<Usuario> findAll();
	
	@Query("SELECT u FROM Usuario u WHERE u.email = (SELECT p FROM Producto p WHERE p.productId =:productId)")
	public Usuario findByProductoProductId(@Param("productId")Integer productId);
	
	
}