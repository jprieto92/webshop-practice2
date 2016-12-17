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
