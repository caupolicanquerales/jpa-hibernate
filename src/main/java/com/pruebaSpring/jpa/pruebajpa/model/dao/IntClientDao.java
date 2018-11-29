package com.pruebaSpring.jpa.pruebajpa.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pruebaSpring.jpa.pruebajpa.model.entities.Tabla;

/* El metodo CRUD REPOSITORY implementa los metodos 
 * 
 * 	- findAll()
 * 	- save()
 * 	- findOne()
 * 	- delete() 
 * 
 * 	Metodos propios de un CRUD pero que estan predeterminados.
 * 
 * 	NOTA: La Interface "CrudRepository<Entity, Integer>" --- se usa para efectuar el CRUD con los metodos basicos.
 * 
 * 	NOTA: La Interface "PagingAndSortingRepository<Entity,Integer>" ---- esta interface implementa los metodos del "CrudRepository"
 * 			mas los metodos para efectuar la paginacion 
 * */

public interface IntClientDao extends PagingAndSortingRepository<Tabla,Integer> {
	
	/* No se usa para cuando se implementa el "CrudRepository"*/
	/*
	public List<Tabla> findAll();
	
	public void save(Tabla tabla);
	
	public Tabla findOne(int id); 
	
	public void delete(int id);
	*/
}
