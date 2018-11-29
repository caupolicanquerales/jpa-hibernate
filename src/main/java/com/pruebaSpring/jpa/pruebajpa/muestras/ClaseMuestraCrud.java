package com.pruebaSpring.jpa.pruebajpa.muestras;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.pruebaSpring.jpa.pruebajpa.model.entities.Tabla;

public class ClaseMuestraCrud {

	/* Clase muestra para ver los metodos del CRUD aplicado de forma manual*/
	
	/*

	@ PersistenceContext  //forma de injectar EntityManager sin crear el "EntityManagerFactory"
	private EntityManager em;
	
	@SuppressWarnings("unchecked")//suprime las precauciones
	@Transactional(readOnly=true)//especifica la accion a ejecutar por el metodo
	@Override
	public List<Tabla> findAll() {
		
		return em.createQuery("from Tabla").getResultList();
	}
	
	@Transactional
	@Override
	public void save(Tabla tabla) {
		
		if(tabla.getId()>0) {
			em.merge(tabla); //esta instruccion actualiza la base de datos
			//System.err.println("valor id Reusado: "+tabla.getId());
		}else {
			em.persist(tabla); // este metodo inserta en la tabla el contenido.
			//System.err.println("valor id Nuevo: "+tabla.getId()+" "+tabla.getNombre()+" "+tabla.getApellido());
		}
		
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Tabla findById(int id) {
		
		return em.find(Tabla.class,id); //ejecuta la accion de consulta a la base de datos.
	}

	//metodo para eliminar registro en la base de datos.
	
	@Override
	@Transactional
	public void delete(int id) {
		Tabla tabla= findOne(id); // hace la consulta a la Base de Datos
		em.remove(tabla);
	}

*/	

}
