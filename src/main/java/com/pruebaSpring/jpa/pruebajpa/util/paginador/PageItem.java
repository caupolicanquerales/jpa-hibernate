package com.pruebaSpring.jpa.pruebajpa.util.paginador;

public class PageItem {
	
	private int numero; //indica el numero de la pagina que esta en uso
	private boolean actual; // establece la condicion a ver si la pagina esta activa o no.
	
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
	
	

}
