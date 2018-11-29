package com.pruebaSpring.jpa.pruebajpa.util.paginador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/* Proceso para efectuar la paginacion
 * 	
 * 	NOTA: Esta clase crea la paginacion, es el proceso de especificar cuantas paginas se requieren para mostrar el resultado de una consulta.
 * 		Se establecen el valor inicial y el final (numero de la pagina final), para cuadrar todos los registros.
 * 		Se crea un ARRAY LIST para inicializar los estados de las paginas.
 * 
 *   NOTA: Esta clase tambien se encarga de regresar los estados de la paginacion:
 *   		getUrl();
 *   		getTotalPaginas();
 *   		getPaginaActual();
 *   		getPaginas();
 *  		isFirst();
 *  		isLast();
 *  		isHasNext();
 *  		isHasPrevious();
 *  
 *  */

public class PageRender<T> 
{
	private String url;
	private Page<T> page; //este objeto es para guargar los resultados de la busqueda.
	
	private int totalPaginas;
	private int numElementosPorPaginas;
	private int paginaActual;
	private List<PageItem> paginas;
	
	
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.paginas= new ArrayList<PageItem>();
		
		numElementosPorPaginas= page.getSize(); //esto regresa la cantidad de resultados por pagina
		totalPaginas= page.getTotalPages(); // esto regresa el numero de paginas requeridas para mostrar
		paginaActual= page.getNumber()+1; // el valor por defecto es 'defaultValue="0"'
		
		int desde, hasta; // variables para determinar desde que pagina hasta la ultima
		/* Esta estructura que establece el rango de paginacion para mostrar, el rango de vistas para cuadrar con el numero de registros obtenidos por la consulta*/
		if(totalPaginas<=numElementosPorPaginas) {
			desde=1;
			hasta=totalPaginas;
		}else {
			if(paginaActual<=numElementosPorPaginas/2) {
				desde=1;
				hasta= numElementosPorPaginas;
			}else if(paginaActual>=totalPaginas-numElementosPorPaginas/2) {
				desde=totalPaginas-numElementosPorPaginas+1;
				hasta= numElementosPorPaginas;
			}else{
				desde=paginaActual-numElementosPorPaginas/2;
				hasta= numElementosPorPaginas;
			}
		}
		
		/* Con esta estructura se va cargando el numero de pagina y el estado de activacion*/
		for(int i=0; i<hasta;i++) {
			paginas.add(new PageItem(desde+i,paginaActual==desde+i));// 
			
		}
	
	}


	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
