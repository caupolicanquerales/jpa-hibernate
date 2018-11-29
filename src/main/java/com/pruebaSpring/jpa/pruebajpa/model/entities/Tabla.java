package com.pruebaSpring.jpa.pruebajpa.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tabla")
public class Tabla implements Serializable 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // este comando Auto Incrementa el ID en la tabla
	private int id;
	
	@NotEmpty //estrategia de validacion, se obliga a que la variable no este VACIA
	@Column(name="nombre")
	private String nombre;
	
	@NotEmpty //estrategia de validacion, se obliga a que la variable no este VACIA
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="foto")
	private String foto;
	
	public Tabla() {
		
	}
	
	
	public Tabla(int id,String nombre, String apellido,String foto) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.foto=foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}
