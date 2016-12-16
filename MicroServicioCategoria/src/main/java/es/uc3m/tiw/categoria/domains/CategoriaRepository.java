package es.uc3m.tiw.categoria.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository  extends CrudRepository<Categoria, Integer> {
	public List<Categoria> findAll();
	
	@Query("SELECT p FROM Producto p WHERE p.categoria = (SELECT c FROM Categoria c WHERE c.idCategoria =:idCategoria)")
	public List<Producto> findByCategoriaIdCategoria(@Param("idCategoria")Integer idCategoria);


}