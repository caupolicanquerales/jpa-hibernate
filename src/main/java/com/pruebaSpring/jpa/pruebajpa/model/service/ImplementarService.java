package com.pruebaSpring.jpa.pruebajpa.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pruebaSpring.jpa.pruebajpa.model.entities.Tabla;
import com.pruebaSpring.jpa.pruebajpa.model.dao.IntClientDao;

/* Este metodo esta implementando en patron de diseno FACADE, o fachada.
 * NOTA: La clase servicio, se encarga de ordenar ejecutar las distintas acciones sobre el BACK-END por medio de llamados de interfaces.
 * 
 * NOTA: En los metodo de ejecucion donde se emplee ENTITY-MANAGER, las anotaciones @Transactional pueden ser declaradas sobre los metodos de la clase
 * 		Service, asi en la clase que implementa los metodo de ejecucion, pueden ser borrados esas anotaciones.
 * */


@Service
public class ImplementarService implements IntService
{
	
	@Autowired
	IntClientDao clientedao;
	

	@Override
	public void save(Tabla tabla) {
		
		clientedao.save(tabla);
	}

	@Override
	public List<Tabla> findAll() {
		// TODO Auto-generated method stub
		return (List<Tabla>) clientedao.findAll();
	}
	
	/* Pendiente con el CRUD*/
	@Override
	public Optional<Tabla> findOne(int id) {
		//return null;
		return  clientedao.findById(id);//El nuevo metodo que se usa es el "findById()", esta fuera de uso en metodo "findOne()"
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		clientedao.deleteById(id); //Debo cambiarlo a deleteById(id) ---- nueva nomenclatura
		
	}

	@Override
	public Page<Tabla> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return  clientedao.findAll(pageable);
	}

	
	
	
	
}
