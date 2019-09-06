package com.chorizo.repositorio;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.chorizo.modelos.Fichaje;
import com.chorizo.modelos.Registro;

public interface FichajesRepositorio extends CrudRepository<Fichaje, Integer> {

	Optional<Fichaje> findByDiaAndUsuarioNombreUsuario (LocalDate dia, String nombreUsuario);
	Optional<Fichaje> findByDia(LocalDate dia);
	
	Set<Registro> findByUsuarioNombreUsuarioAndDia (String nombreUsuario, LocalDate localdate);
}
