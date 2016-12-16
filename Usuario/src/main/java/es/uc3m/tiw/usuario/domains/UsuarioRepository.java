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
	
	@Query("SELECT p FROM Producto p JOIN p.usuario u WHERE (p.titulo LIKE '%' || :titulo || '%'  OR \'\' = :titulo) AND (p.descripccion LIKE '%' || :descripccion || '%' OR \'\' = :descripccion) AND (u.email=:emailUsuario OR \'\' = :emailUsuario) AND (u.ciudad=:ciudadUsuario OR \'\' = :ciudadUsuario)  AND (p.categoria.id=:idCategoria OR \'\' = :idCategoria)")
	public List<Producto> findByCategoriaIdCategoriaAndUsuarioCiudadAndUsuarioEmailAndTituloContainsAndDescripccionContains(@Param("idCategoria") int idCategoria, @Param("ciudadUsuario") String ciudadUsuario, @Param("emailUsuario") String emailUsuario, @Param("titulo") String titulo, @Param("descripccion") String descripccion);
	
}