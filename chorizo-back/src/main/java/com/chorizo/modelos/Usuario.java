package com.chorizo.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity 
@Table (name="Usuario")
public class Usuario implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(unique = true, length = 150)
	private String nombreUsuario;
	
	private String contrasenia;
	
	private String nombre;
	
	private String apellidos;
	
	
	@OneToMany(mappedBy="usuario",cascade= CascadeType.ALL)
    private Set<Fichaje> fichajes;
	

	public Usuario() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public Set<Fichaje> getFichajes() {
		return fichajes;
	}


	public void setFichajes(Set<Fichaje> fichajes) {
		this.fichajes = fichajes;
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

	
	
}