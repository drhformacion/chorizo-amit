package com.chorizo.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.chorizo.modelos.Usuario;


public interface UsuariosRepositorio extends CrudRepository<Usuario, Integer> {
	
	//Optional <Usuario> findByNombreUsuario (String nombreUsuario);
	
	public abstract Usuario findByNombreUsuario (String nombreUsuario);

}