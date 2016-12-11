package es.uc3m.tiw.catalogo.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository  extends CrudRepository<Producto, Integer> {

	public List<Producto> findAll();

	public List<Producto> findByUsuarioEmail(String email);
	
	public List<Producto> findByTituloContainsOrDescripccionContains(String terminoBusqueda, String terminoBusqueda2);
	
	/* 
	 * Tabla: Categoria Campo: IdCategoria	Operador:
	 * Tabla: Usuario	Campo: Ciudad		Operador:
	 * Tabla: Usuario	Campo: email		Operador: 
	 * Tabla: Producto	Campo: titulo		Operador: contains
	 * Tabla: Producto	Campo: Descripccion	Operador: contains
	 * */
	@Query("SELECT p FROM Producto p JOIN p.usuario u WHERE (p.titulo LIKE '%' || :titulo || '%'  OR \'\' = :titulo) AND (p.descripccion LIKE '%' || :descripccion || '%' OR \'\' = :descripccion) AND (u.email=:emailUsuario OR \'\' = :emailUsuario) AND (u.ciudad=:ciudadUsuario OR \'\' = :ciudadUsuario)  AND (p.categoria.id=:idCategoria OR \'\' = :idCategoria)")
	public List<Producto> findByCategoriaIdCategoriaAndUsuarioCiudadAndUsuarioEmailAndTituloContainsAndDescripccionContains(@Param("idCategoria") int idCategoria, @Param("ciudadUsuario") String ciudadUsuario, @Param("emailUsuario") String emailUsuario, @Param("titulo") String titulo, @Param("descripccion") String descripccion);

}