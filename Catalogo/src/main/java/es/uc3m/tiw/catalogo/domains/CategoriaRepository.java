package es.uc3m.tiw.catalogo.domains;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository  extends CrudRepository<Categoria, Integer> {
	public List<Categoria> findAll();

}