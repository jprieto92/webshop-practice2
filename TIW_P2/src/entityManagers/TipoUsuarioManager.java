package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import entitiesJPA.TipoUsuario;

public class TipoUsuarioManager {

	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TipoUsuarioManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
    public void insertar(TipoUsuario tipoUsuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(tipoUsuario);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("Lanzando excepcion en la clase TipoUsuarioManager");
			throw new RollbackException(e);
			 		
		}
    }
    
    public void modificar(TipoUsuario tipoUsuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(tipoUsuario);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			throw new RollbackException(e);
		}
    }
    
    //Devuelve todos los tipos de usuario
    public List<TipoUsuario> buscarTodas(){
    	TypedQuery<TipoUsuario> consultaTiposUsuario = null;
    	try{
    		consultaTiposUsuario = em.createNamedQuery("TipoUsuario.findAll", TipoUsuario.class);
	    }catch(Exception e){
	    	throw new NoResultException();		
		}
    	return consultaTiposUsuario.getResultList();
    }
    
}
