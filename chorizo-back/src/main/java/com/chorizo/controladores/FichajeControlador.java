package com.chorizo.controladores;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chorizo.modelos.Fichaje;
import com.chorizo.modelos.Registro;
import com.chorizo.repositorio.FichajesRepositorio;
import com.chorizo.repositorio.UsuariosRepositorio;



@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
		
public class FichajeControlador {
	
	@Autowired
	private FichajesRepositorio fichajesRepositorio;
	
	@Autowired
	private UsuariosRepositorio usuariosRepositorios;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/inicio/{nombreUsu}")
	public @ResponseBody Integer aniadirFichaje(@PathVariable String nombreUsu) {
		
		LocalDate hoy = LocalDate.now();
		
		if (fichajesRepositorio.findByDia(hoy).isPresent()) {
			return fichajesRepositorio.findByDia(hoy).get().getId();
		}
		
		Fichaje fichaje = new Fichaje();
		
		fichaje.setId(null);
		
		fichaje.setUsuario(usuariosRepositorios.findByNombreUsuario(nombreUsu));
		
		fichaje.setDia(hoy);
		
		fichaje.setInicioFichaje(LocalTime.now().toString());
		
		fichajesRepositorio.save(fichaje);

		return fichaje.getId();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/fin/{nombreUsuario}")
	public @ResponseBody Fichaje finFichaje(@PathVariable String nombreUsuario) {
		
		try {
		Fichaje fichaje = fichajesRepositorio.findByDiaAndUsuarioNombreUsuario(LocalDate.now(), nombreUsuario).get();
		fichaje.setFinFichaje(LocalTime.now().toString());
		return fichaje;
		
		} catch (Exception ex) {
			System.out.println("No se a encontrado fichaje con esa id");
			return null;
		}
		
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/tiempoTotal/{nombreUsuario}")
	public @ResponseBody LocalTime tiempoTotalRegistros(@PathVariable String nombreUsuario) {
		
		Optional<Fichaje> fichaje = fichajesRepositorio.findByDiaAndUsuarioNombreUsuario(LocalDate.now(), nombreUsuario);
		
		Set <Registro> registros = fichaje.get().getRegistros();
		Iterator<Registro> it = registros.iterator();
		int tiempoTotal = 0; 
		
		while (it.hasNext()) {
			try {	 
				LocalTime hora = LocalTime.parse(it.next().getDiferencia());
				int segundosInicio = hora.toSecondOfDay();
				tiempoTotal = tiempoTotal + segundosInicio;
			} catch (Exception ex) {
				
			}
		
		}
		
		int minutos = 0;
		int horas = 0;
		
		while (tiempoTotal >= 60) {
			minutos++;
			tiempoTotal= tiempoTotal -60;
		}
		while (minutos >= 60 ) {
			horas ++;
			minutos = minutos - 60;
		}
		
		LocalTime diferencia = LocalTime.of(horas, minutos, tiempoTotal);
		
		return diferencia;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/tiempoIndividual/{nombreUsuario}")
	public @ResponseBody Hashtable tiempoindividualRegistros(@PathVariable String nombreUsuario) {
		
		Optional<Fichaje> fichaje = fichajesRepositorio.findByDiaAndUsuarioNombreUsuario(LocalDate.now(), nombreUsuario);
		
		Set <Registro> registros = fichaje.get().getRegistros();
		Iterator<Registro> it = registros.iterator();
		Hashtable<String, LocalTime> horas= new Hashtable<String, LocalTime>();
		
		int tiempoTotalBanio = 0;
		int tiempoExcedidoBanio = 0;
		int tiempoTotalReunio = 0;
		int tiempoTotalNormal = 0;
		int tiempoExcedidoNormal = 0;
		int tiempoTotalComida = 0;
		int tiempoExcedidoComida = 0;
		int tiempoTotalInactividad = 0;
		int tiempoExcedidoinactividad = 0;
		
		while (it.hasNext()) {
			Registro registro = it.next();
			switch (registro.getTipo()) {
			case "banio":
				try {
				LocalTime hora = LocalTime.parse(registro.getDiferencia());
				int segundosInicio = hora.toSecondOfDay();
				tiempoExcedidoBanio = tiempoExcedidoBanio + segundosInicio;
				System.out.println("Tiempo excedido "+ tiempoExcedidoBanio );
				
				tiempoTotalBanio = tiempoTotalBanio + restarHoras(LocalTime.parse(registro.getInicioRegistro()), LocalTime.parse(registro.getFinRegistro()));
				System.out.println("Tiempo total "+ tiempoTotalBanio );
				} catch(Exception ex) {
					System.out.println("Fecha vacia");
				}
				break;
			case "reunion":
				try {
				tiempoTotalReunio = tiempoTotalReunio + restarHoras(LocalTime.parse(registro.getInicioRegistro()), LocalTime.parse(registro.getFinRegistro()));
				System.out.println("Tiempo total "+ tiempoTotalReunio);
				} catch(Exception ex) {
					System.out.println("Fecha vacia");
				}
				
				break;
			case "normal":
				try {
					LocalTime hora = LocalTime.parse(registro.getDiferencia());
					int segundosInicio = hora.toSecondOfDay();
					tiempoExcedidoNormal = tiempoExcedidoNormal+ segundosInicio;
					System.out.println("Tiempo excedido "+ tiempoExcedidoNormal);
					
					tiempoTotalNormal = tiempoTotalNormal+ restarHoras(LocalTime.parse(registro.getInicioRegistro()), LocalTime.parse(registro.getFinRegistro()));
					System.out.println("Tiempo total "+ tiempoTotalNormal );
					} catch(Exception ex) {
						System.out.println("Fecha vacia");
					}
				break;
			case "comida":
				try {
					LocalTime hora = LocalTime.parse(registro.getDiferencia());
					int segundosInicio = hora.toSecondOfDay();
					tiempoExcedidoComida = tiempoExcedidoComida + segundosInicio;
					System.out.println("Tiempo excedido "+ tiempoExcedidoComida );
					
					tiempoTotalNormal = tiempoTotalNormal+ restarHoras(LocalTime.parse(registro.getInicioRegistro()), LocalTime.parse(registro.getFinRegistro()));
					System.out.println("Tiempo total "+ tiempoTotalNormal);
					} catch(Exception ex) {
						System.out.println("Fecha vacia");
					}
				break;

			default:
				break;
			}
		}
		
		horas.put("ttb", calcularLocalTime(tiempoTotalBanio));
		horas.put("teb", calcularLocalTime(tiempoExcedidoBanio));
		horas.put("ttc", calcularLocalTime(tiempoTotalComida));
		horas.put("tec", calcularLocalTime(tiempoExcedidoComida));
		horas.put("ttr", calcularLocalTime(tiempoTotalReunio));
		horas.put("tei", calcularLocalTime(tiempoExcedidoinactividad));
		horas.put("ttn", calcularLocalTime(tiempoTotalNormal));
		horas.put("ten", calcularLocalTime(tiempoExcedidoNormal));
			
		return horas;
	}
	
	public LocalTime calcularLocalTime(int segundos) {
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
		
		return LocalTime.of(horas, minutos, segundos);
	}
	
	public int restarHoras (LocalTime hora1, LocalTime hora2) {
		int segundosInicio = hora1.toSecondOfDay();
		int segundosFinal = hora2.toSecondOfDay();
		int segundosTotales = segundosFinal - segundosInicio;
		System.out.println("Segundos Totales de la pausa "+ segundosTotales );
		System.out.println("HOra inicio "+ segundosInicio + " "+ hora1 +  "\n hora final "+ segundosFinal + " " + hora2);
		
		return segundosTotales;
	}
	
	
}
