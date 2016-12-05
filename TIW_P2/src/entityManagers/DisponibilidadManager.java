package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;

public class DisponibilidadManager {

	
	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DisponibilidadManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
    public void insertar(Disponibilidad disponibilidad) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(disponibilidad);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("Lanzando excepcion en la clase DisponibilidadManager");
			throw new RollbackException(e);
			 		
		}
    }
    
    public void modificar(Disponibilidad disponibilidad) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(disponibilidad);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			throw new RollbackException(e);
		}
    }
    
    //Devuelve todas las disponibilidades
    public List<Disponibilidad> buscarTodas(){
    	TypedQuery<Disponibilidad> consultaDisponibilidades = null;
    	try{
    		consultaDisponibilidades = em.createNamedQuery(Disponibilidad.BUSCAR_TODOS, Disponibilidad.class);
	    }catch(Exception e){
	    	throw new NoResultException();		
		}
    	return consultaDisponibilidades.getResultList();
    }
    
	//Devuelve una disponibilidad dado su ID
	public Disponibilidad buscarPorId(Integer idDisponibilidad) throws NoResultException {
		Disponibilidad resultado;
		EntityManager em = emf.createEntityManager();
		try{
			resultado = em.find(Disponibilidad.class, idDisponibilidad);
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
    
}
