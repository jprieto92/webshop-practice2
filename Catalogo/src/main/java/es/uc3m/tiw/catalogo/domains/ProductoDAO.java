package es.uc3m.tiw.catalogo.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductoDAO  extends CrudRepository<Producto, Integer> {

	 // los métodos findOne, save... ya viene por herencia
	
	  public List<Producto> findAll();
	  
}