package com.pruebaSpring.jpa.pruebajpa.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pruebaSpring.jpa.pruebajpa.model.entities.Tabla;

public interface IntService {
	
	public List<Tabla> findAll();
	
	public Page<Tabla> findAll(Pageable pageable);
	
	public void save(Tabla tabla);
	
	public Optional<Tabla> findOne(int id); 
	
	public void delete(int id);

}
