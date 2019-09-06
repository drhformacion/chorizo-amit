package com.chorizo.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chorizo.modelos.Fichaje;
import com.chorizo.modelos.Registro;
import com.chorizo.repositorio.FichajesRepositorio;
import com.chorizo.repositorio.RegistrosRepositorio;
import com.chorizo.repositorio.UsuariosRepositorio;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
		
public class RegistroControlador {
	
	@Autowired
	private RegistrosRepositorio registrosRepositorio;
	
	@Autowired
	private FichajesRepositorio fichajesRepositorio;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/registroAdd/{nombreUsuario}/{tipo}")
	public @ResponseBody Integer aniadirRegistro(@PathVariable String nombreUsuario, @PathVariable String tipo ) {
		
		
		
		Registro registro = new Registro();
		
		LocalDate hoy = LocalDate.now();
		
		LocalTime horaActual = LocalTime.now();
		
		System.out.println(hoy + " "+ nombreUsuario);
		
		Optional <Fichaje> fichaje = fichajesRepositorio.findByDiaAndUsuarioNombreUsuario(hoy, nombreUsuario);
		
		
		
		if (fichaje.isPresent()) {
			registro.setInicioRegistro(horaActual.toString());
			registro.setId(null);
			registro.setTipo(tipo);
			registro.setFichaje(fichaje.get());
			
			registrosRepositorio.save(registro);
			
			return registro.getId();
		}
		
		return 0;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/registroEnd/{id}")
	public @ResponseBody Registro finRegistro(@PathVariable Integer id) {
		
		Optional <Registro> registro = registrosRepositorio.findById(id);
		
		LocalTime horaActual = LocalTime.now();
		
		if (registro.isPresent()) {
			registro.get().setFinRegistro(horaActual.toString());
			
			//registro.get().setDiferencia(calcularDiferencia(LocalTime.parse(registro.get().getInicioRegistro()),LocalTime.parse(registro.get().getFinRegistro())).toString());
			registro.get().setDiferencia(calcularDiferencia(registro.get()).toString());
			System.out.println(registro.get().getDiferencia());
			
			registrosRepositorio.save(registro.get());
			return registro.get();
		}
		
		return null;
	}
	
	
	
	public LocalTime calcularDiferencia(Registro registro) {
		LocalTime fecha1 = LocalTime.parse(registro.getInicioRegistro());
		LocalTime fecha2 = LocalTime.parse(registro.getFinRegistro());
		LocalTime exceso;
		int segundosInicio = fecha1.toSecondOfDay();
		int segundosFinal = fecha2.toSecondOfDay();
		int segundos = segundosFinal-segundosInicio;
		int minutos = 0;
		int horas = 0;
		
		while (segundos >= 60) {
			minutos++;
			segundos= segundos -60;
		}
		while (minutos >= 60 ) {
			horas ++;
			minutos = minutos - 60;
		}
		
		
		int limite; 
		LocalTime diferencia = LocalTime.of(horas, minutos, segundos);
		
		System.out.println("Diferencia entre inicio y fin "+ diferencia);
		
		switch (registro.getTipo()) {
		case "normal":
			limite = 10;
			exceso = LocalTime.of(0, limite);
			if (diferencia.isAfter(exceso)) {
				diferencia = diferencia.minusMinutes(limite);
			}
			break;

		case "inactivo":
			limite = 5;
			exceso = LocalTime.of(0, limite);
			if (diferencia.isAfter(exceso)) {
				diferencia = diferencia.minusMinutes(limite);
			} else {
				diferencia = LocalTime.of(0, 0);
			}
			break;
		case "banio":
			limite = 1;
			exceso = LocalTime.of(0, limite);
			if (diferencia.isAfter(exceso)) {
				diferencia = diferencia.minusMinutes(limite);
				System.out.println("diferencia "+diferencia );
			} else {
				diferencia = LocalTime.of(0, 0);
			}
			break;
		case "comida":
			limite = 30;
			exceso = LocalTime.of(0, limite);
			if (diferencia.isAfter(exceso)) {
				diferencia = diferencia.minusMinutes(limite);
				System.out.println("diferencia "+diferencia );
			} else {
				diferencia = LocalTime.of(0, 0);
			}
			break;
			
		default:
			diferencia = LocalTime.of(0, 0);
			break;
		}
		
		return diferencia;
	}
	

}
