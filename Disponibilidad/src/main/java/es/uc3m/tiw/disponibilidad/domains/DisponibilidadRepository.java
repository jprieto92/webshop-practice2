package es.uc3m.tiw.disponibilidad.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DisponibilidadRepository extends CrudRepository<Disponibilidad, Integer>{

	
	public List<Disponibilidad> findAll();
}
