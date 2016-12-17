package utilidades;

import java.util.Arrays;
import java.util.List;

import entitiesJPA.Producto;

public class UtilidadesList {

	public static List<Producto> reordenarAleatoriamente(List<Producto> lista){
		
		Producto[] listaProductos = (Producto[]) lista.toArray();
		
		//Se realizará una iteración de 5 veces por cada elemento de la lista
		for(int i=0; i<(3*lista.size()); i++){
			
			//Limite inferior
			int n=0;
			//Limite superior
			int m=lista.size();
			
			//Se genera el valor aleatorio en n y m
			int valorAleatorio = (int) Math.floor(Math.random()*(n-m)+m);
			
			//Se guarda el elemento en una variable auxiliar
			Producto productoAux = listaProductos[valorAleatorio];
			
			//Se pasa el ultimo elemento a la posicion del valor aleatorio
			listaProductos[valorAleatorio] = listaProductos[lista.size() - 1];
			
			//Se inserta al final de la lista
			listaProductos[lista.size() - 1] = productoAux;
			
		}
		
		return Arrays.asList(listaProductos);
	}
}

/** 
 * MÉTODO ANTIGUO. DEBE SER QUE LA LISTA QUE SE RECIBE YA NO ES UN AUTÉNTICO LIST, YA QUE EL LISTA.REMOVE DA ERROR.
 * */
/* 

package utilidades;

import java.util.List;

public class UtilidadesList {

	public static <T> void reordenarAleatoriamente(List<T> lista){
		
		//Se realizará una iteración de 5 veces por cada elemento de la lista
		for(int i=0; i<(5*lista.size()); i++){
			
			//Limite inferior
			int n=0;
			//Limite superior
			int m=lista.size();
			
			//Se genera el valor aleatorio en n y m
			int valorAleatorio = (int) Math.floor(Math.random()*(n-m)+m);
			
			//Se guarda el elemento en una variable auxiliar
			T tAux = lista.get(valorAleatorio);
			
			//Se elimina el elemento
			lista.remove(tAux);
			
			//Se inserta al final de la lista
			lista.add(tAux);
		}
		
	}
}
*/