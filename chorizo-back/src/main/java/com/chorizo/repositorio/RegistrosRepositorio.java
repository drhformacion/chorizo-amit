package com.chorizo.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.chorizo.modelos.Registro;

public interface RegistrosRepositorio extends CrudRepository<Registro, Integer> {
	

}
