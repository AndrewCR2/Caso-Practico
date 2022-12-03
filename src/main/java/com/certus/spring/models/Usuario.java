package com.certus.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotEmpty(message = "No ingresaste tu nombre")
	@Size (max= 12, message ="El nombre es muy largo, prueba ingresando solo un nombre")
	private String nombre;
	
	@NotEmpty(message = "No ingresaste tus apellidos")
	private String apellidos;
	
	@Size(min=8, message = "Usa minimo 8 caracteres")
	@NotEmpty(message = "No ingresaste una contraseña")
	private String password;
	

	@NotEmpty(message = "No ingresaste una contraseña")
	private String sucursal;

	

	

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	

}
