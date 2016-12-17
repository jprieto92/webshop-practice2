package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entitiesJPA.Usuario;



public class UserManager {

	private EntityManagerFactory emf;

	public UserManager(String unidadDePersistencia)
	{
		emf = Persistence.createEntityManagerFactory(unidadDePersistencia);
	}

	public UserManager()
	{
		emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
	}  
	
	/*
	public String insertar(Usuario usuario) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
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
		return "El usuario "+usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+" "+" se ha insertado correctamente";
	}
	*/
	/**
	 * Actualiza un usuario dado su entidad
	 * @param usuario
	 * @return String message
	 * @throws Exception
	 */
	/*
	public String modificar(Usuario usuario) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(usuario);
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
		return "El usuario "+usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+" "+" se ha modificado correctamente";
	}   
	*/
	
	/**
	 * Dado un email de usuario, busca la entidad usuario
	 * @param email
	 * @return Usuario entity
	 * @throws Exception
	 */
	/*
	public Usuario buscarPorEmail(String email) throws Exception {
		Usuario resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_EMAIL,Usuario.class);
			query.setParameter("email", email);
			resultado = (Usuario) query.getSingleResult();
		}
		catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException("No existe el usuario en la BBDD");		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}*/
	
	/** Devuelve el id del tipo de usuario dado un email de usuario
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	
	/*
	public Integer obtenerIdTipoUsuario(String email) throws Exception {
		Integer resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.DEVOLVER_TIPO_USUARIO_DADO_EMAIL,Usuario.class);
			query.setParameter("email", email);
			resultado = (Integer) query.getSingleResult();
		}
		catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException("No existe el usuario en la BBDD");		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}*/
	
	/**
	 * Proporciona una lista de usuarios dado coincidencias por su nombre
	 * @param nombre_usuario
	 * @return List<Usuario>
	 * @throws Exception
	 */
	
	/*
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarPorNombre(String nombre) throws Exception {
		List<Usuario> resultado;
		EntityManager em = emf.createEntityManager();
		
		//Caracteres comodín
		nombre = "%"+nombre+"%";
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_NOMBRE,Usuario.class);
			query.setParameter("nombre", nombre);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen usuarios.");
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
	*/
	/*
	public Usuario comprobarCredenciales(String email, String contraseña, Integer idTipoUser) throws Exception {
		Usuario resultado;
		EntityManager em = emf.createEntityManager();
		
		
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_CREDENCIALES,Usuario.class);
			query.setParameter("email", email);
			query.setParameter("contraseña", contraseña);
			query.setParameter("idTipoUsuario", idTipoUser);
			resultado = (Usuario) query.getSingleResult();
		}
		catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException("Ha habido un error con las credenciales. Inserte su usuario y contraseña nuevamente");		
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
	*/
	
	
	/**
	 * Método para comprobar si las credenciales pasadas son correctas. También verifica si el usuario es user o admin.
	 * @param email
	 * @param contraseña
	 * @param idTipoUser
	 * @return String con el email (campo clave) del usuario
	 * @throws Exception
	 */
	
	/*
	public String comprobarCredencialesDevuelveEmail(String email, String contraseña, Integer idTipoUser) throws Exception {
		String resultado;
		EntityManager em = emf.createEntityManager();
		
		
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_CREDENCIALES_SOLO_ID,String.class);
			query.setParameter("email", email);
			query.setParameter("contraseña", contraseña);
			query.setParameter("idTipoUsuario", idTipoUser);
			resultado =  (String) query.getSingleResult();
		}
		catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException("Ha habido un error con las credenciales. Inserte su usuario y contraseña nuevamente");		
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

	
	public String darDeBaja(String email) throws Exception {
		EntityManager em = emf.createEntityManager();
		Usuario usuarioBBDD = null;
		try {
			usuarioBBDD = em.find(Usuario.class, email);
			em.getTransaction().begin();
			System.out.println("Antes del remove");
			em.remove(usuarioBBDD);
			System.out.println("Despues del remove");
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.close();
			throw ex;
		}
		return "El usuario "+usuarioBBDD.getNombre()+" "+usuarioBBDD.getApellido1()+" "+usuarioBBDD.getApellido2()+" "+" se ha dado de baja correctamente";
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() throws Exception {
		List<Usuario> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_TODOS,Usuario.class);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen usuarios.");
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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodosUsers() throws Exception {
		List<Usuario> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_USUARIOS_USERS,Usuario.class);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen usuarios.");
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
	
	*/
}
