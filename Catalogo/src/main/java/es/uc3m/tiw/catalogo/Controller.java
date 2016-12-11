package es.uc3m.tiw.catalogo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import es.uc3m.tiw.catalogo.domains.*;

@RestController
// The following annotation is necessary to allow 
// different domains to be able to use this microservice
// La siguiente anotación es necesaria para permitir 
// que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {

	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	DisponibilidadRepository disponibilidadRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

	/**
	 * Búsqueda de todos los productos
	 * @return Devuelve una lista de productos.
	 *  */
	@RequestMapping(value="productos", method = RequestMethod.GET)
	public List<Producto> productos(){
		System.out.println("Buscar todos los productos");
		return productoRepository.findAll();
	}

	/**
	 * Búsqueda de un producto
	 * @return Devuelve un producto.
	 * @param idProducto Establece la búsqueda del producto por dicho parámetro
	 *  */
	@RequestMapping(value="producto", method = RequestMethod.GET)
	public Producto productosPorId(@RequestParam(value="idProducto", required=true) Integer idProducto){
		System.out.println("Busca producto por id");
		Producto producto = productoRepository.findOne(idProducto);

		if(producto==null)throw new DataIntegrityViolationException("No existe el producto con el id: "+idProducto);

		return producto;
	}

	/** 
	 * Cantidad de productos
	 * @return Devuelve la cantidad de productos.
	 * */
	@RequestMapping(value="productosCount", method=RequestMethod.GET)
	public Count cantidad(){
		System.out.println("Cantidad Elementos");
		return new Count(productoRepository.count());
	}

	/** 
	 * Búsqueda de productos por email
	 * @param email Establece la búsqueda de productos por dicho parámetro
	 * @return Devuelve una lista de productos.
	 * */
	@RequestMapping(value="productosBusqueda", method=RequestMethod.GET)
	public List<Producto> buscarPorEmail(@RequestParam(value="email", required=true) String email){
		System.out.println("Buscar productos por email");
		return productoRepository.findByUsuarioEmail(email);
	}

	/** 
	 * Búsqueda de productos simple
	 * @param terminoBusqueda Establece la búsqueda de productos cuyo título o descripcción contenga el parámetro
	 * @return Devuelve una lista de productos.
	 * */
	@RequestMapping(value="productosBusquedaSimple", method=RequestMethod.GET)
	public List<Producto> busquedaSimple(@RequestParam(value="terminoBusqueda", required=true) String terminoBusqueda){
		System.out.println("Buscar productos simple");
		return productoRepository.findByTituloContainsOrDescripccionContains(terminoBusqueda, terminoBusqueda);
	}

	/** 
	 * Búsqueda de productos avanzada
	 * @param email Establece la búsqueda de productos por dichos parámetros
	 * @return Devuelve una lista de productos.
	 * Ejemplo: http://localhost:8020/productosBusquedaAvanzada?categoriaId=1&ciudad=&email=&titulo=coche&descripccion=azul
	 * */
	@RequestMapping(value="productosBusquedaAvanzada", method=RequestMethod.GET)
	public List<Producto> busquedaAvanzada(
			@RequestParam(value="categoriaId", required=true) int categoriaId, 
			@RequestParam(value="ciudad", required=true) String ciudad, 
			@RequestParam(value="email", required=true) String email, 
			@RequestParam(value="titulo", required=true) String titulo, 
			@RequestParam(value="descripccion", required=true) String descripccion){
		System.out.println("Buscar productos avanzada");
		return productoRepository.findByCategoriaIdCategoriaAndUsuarioCiudadAndUsuarioEmailAndTituloContainsAndDescripccionContains(categoriaId, ciudad, email, titulo, descripccion);
	}

	/******************************************************************/
	/*			  		  SECCIÓN POST (CREAR)						  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8020/crear
	 * 	 * JSON
	 * {
		"descripccion": "Coche azul modelo",
		"envios": "SI",
		"fechaPublicacion": "2016-10-20",
		"precio": 2500,
		"precioNegociable": "SI",
		"titulo": "COCHES",
		"email": "100@mail.com",
		"idCategoria": 1
		}
	 * */
	@RequestMapping(value="/crear", method = RequestMethod.POST)
	public Producto crear(@RequestBody ProductoCrear productoCrear){

		Producto producto = new Producto(productoCrear.getDescripccion(), productoCrear.getEnvios(), productoCrear.getFechaPublicacion(), productoCrear.getPrecio(), productoCrear.getPrecioNegociable(), productoCrear.getTitulo(), null, null, null);
		/* Se establece la categoria del producto */
		producto.setCategoria(categoriaRepository.findOne(productoCrear.getIdCategoria()));
		/* Se establece la disponibilidad del producto, por defecto será 'Disponible' */
		producto.setDisponibilidad(disponibilidadRepository.findOne(1));
		/* Se establece el propietario del producto */
		producto.setUsuario(usuarioRepository.findOne(productoCrear.getEmail()));

		System.out.println("Almacenar producto");

		Producto producto2 = productoRepository.save(producto);

		System.out.println(producto2.getProductId());
		return producto2;
	}


	/******************************************************************/
	/*			  		  SECCIÓN PUT (MODIFICAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8020/modificar
	 * 	 * JSON
	 * 
		{
		"productId": "15",
		"envios": "SI",
		"descripccion": "AA",
		 "fechaPublicacion": "2054-10-20",
		 "precio": 1100,
		 "precioNegociable": "NO",
		 "titulo": "MOTOS",
		 "idCategoria": 2,
		 "idDisponibilidad": 2
		}
	 * @throws Exception 
	 * */
	@RequestMapping(value="/modificar", method = RequestMethod.PUT)
	public Producto modificar(@RequestBody ProductoCrear productoCrear){
		System.out.println("Modificar producto");

		if(productoCrear.getProductId()==null){
			throw new DataIntegrityViolationException("Debe indicar un id de producto");
		}

		/* Se recupera el producto original */
		Producto producto = productosPorId(productoCrear.getProductId());

		/* Se efectuan las modificaciones que sean necesarias. Los setters ya controlan que no sean null */
		producto.setProductId(productoCrear.getProductId());
		producto.setDescripccion(productoCrear.getDescripccion());
		producto.setEnvios(productoCrear.getEnvios());
		producto.setFechaPublicacion(productoCrear.getFechaPublicacion());
		producto.setPrecio(productoCrear.getPrecio());
		producto.setPrecioNegociable(productoCrear.getPrecioNegociable());
		producto.setTitulo(productoCrear.getTitulo());
		/* Se busca y establece la categoria */
		producto.setCategoria(categoriaRepository.findOne(productoCrear.getIdCategoria()));
		/* Se busca y establece la disponibilidad */
		producto.setDisponibilidad(disponibilidadRepository.findOne(productoCrear.getIdDisponibilidad()));

		return productoRepository.save(producto);
	}


	/******************************************************************/
	/*			 		  SECCIÓN DELETE (BORRAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8020/eliminar
	 * 	 * JSON
	 * 
		{
		"productId": "15"
		}
	 * @throws Exception 
	 * */
	@RequestMapping(value="/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestBody ProductoCrear productoCrear){
		System.out.println("Eliminar producto con id: "+productoCrear.getProductId());

		if(productoCrear.getProductId()==null){
			throw new DataIntegrityViolationException("Debe indicar un id de producto");
		}
		
		/* Se consulta que ese id de producto existe */
		productosPorId(productoCrear.getProductId());
		
		productoRepository.delete(productoCrear.getProductId());
	}
	

}