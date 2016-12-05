package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entitiesJPA.Categoria;



public class CategoriaManager {

	private EntityManagerFactory emf;

	public CategoriaManager(String unidadDePersistencia)
	{
		emf = Persistence.createEntityManagerFactory(unidadDePersistencia);
	}

	public CategoriaManager()
	{
		emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
	} 
	
	public String insertar(Categoria categoria) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "La categoria "+categoria.getIdCategoria()+" se ha insertado correctamente";
	}
    
	public String modificar(Categoria categoria) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(categoria);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			ex.printStackTrace();
			throw ex;
		} finally {
			em.close();
		}
		return "La categoria "+categoria.getIdCategoria()+" se ha modificado correctamente";
	} 
    
    //Devuelve todas los tipos de categoria
    @SuppressWarnings("unchecked")
	public List<Categoria> buscarTodas() throws Exception{
		List<Categoria> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Categoria.BUSCAR_TODOS,Categoria.class);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepci�n
			if(resultado.size()==0){
				throw new NoResultException("No existen categorias.");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
    	
    }
    
  //Devuelve una disponibilidad dado su ID
  	public Categoria buscarPorId(Integer idCategoria) throws Exception {
  		Categoria resultado;
  		EntityManager em = emf.createEntityManager();
  		try{
  			resultado = (Categoria) em.find(Categoria.class, idCategoria);
			//Si no existen coincidencias, se lanza una excepci�n
			if(resultado==null){
				throw new NoResultException("No existe la categoria dado el id "+idCategoria);
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
  		return resultado;
  	}
    
    //Devuelve el id y el nombre de todas las disponibilidades
  	@SuppressWarnings("unchecked")
	public List<Categoria> buscarTodasSoloNombreEId() throws Exception {
		List<Categoria> resultado;
  		EntityManager em = emf.createEntityManager();
  		try{
  			Query query = em.createNamedQuery(Categoria.BUSCAR_TODOS_SOLO_ID_Y_NOMBRE, Categoria.class);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepci�n
			if(resultado.size()==0){
				throw new NoResultException("No existe disponibilidad dado el id de producto.");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
  		return resultado;
  	}
  	
}
