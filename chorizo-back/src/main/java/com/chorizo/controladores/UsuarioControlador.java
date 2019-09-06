package com.chorizo.controladores;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chorizo.modelos.Usuario;
import com.chorizo.repositorio.UsuariosRepositorio;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
		
public class UsuarioControlador {
	
	@Autowired
	private UsuariosRepositorio usuariosRepositorio;
	

	private static final Log logger = LogFactory.getLog(UsuarioControlador.class);
	
	
	@PostMapping(path="/login")
	public boolean login(@RequestBody Usuario user) {
		
		try {
		Usuario aux = usuariosRepositorio.findByNombreUsuario(user.getNombreUsuario());
		
			if (aux.getContrasenia().equals(user.getContrasenia())) {
				logger.info("El usuario "+ aux.getNombreUsuario() + " ha iniciado sesion");
				return true;
			}
			else {
				logger.info("Introducida incorrectamente la contraseña del usuario "+ user.getNombreUsuario());
			}
		
		return false;
		
		} catch (Exception e){
			logger.error("El usuario no ha sido encontrado");
			return false;
		}
	}
	

}
