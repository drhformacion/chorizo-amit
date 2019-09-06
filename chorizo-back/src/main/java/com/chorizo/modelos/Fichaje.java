package com.chorizo.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table (name="Fichaje")
public class Fichaje implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private LocalDate dia;
	
	private String inicioFichaje;
	
	private String finFichaje;
	
	private String tiempoTotal;
	
	
	@ManyToOne 
    @JoinColumn(name="usuario")
    private Usuario usuario;
	
	@OneToMany(mappedBy="fichaje",cascade= CascadeType.ALL)
    private Set<Registro> registros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public String getInicioFichaje() {
		return inicioFichaje;
	}

	public void setInicioFichaje(String inicioFichaje) {
		this.inicioFichaje = inicioFichaje;
	}

	public String getFinFichaje() {
		return finFichaje;
	}

	public void setFinFichaje(String finFichaje) {
		this.finFichaje = finFichaje;
	}

	public String getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(String tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public Set<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<Registro> registros) {
		this.registros = registros;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	

}
