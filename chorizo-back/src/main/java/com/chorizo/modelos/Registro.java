package com.chorizo.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity 
@Table (name="Registro")
public class Registro implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String inicioRegistro;
	
	private String finRegistro;
	
	private String diferencia;
	
	// Cada registros es una pausa. Habra distintos tipos de pausa (Ir al baño, descanso, reunion , pausa para comer.)
	private String tipo;
	
	@ManyToOne 
    @JoinColumn(name="fichaje")
    private Fichaje fichaje;

	public Registro() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInicioRegistro() {
		return inicioRegistro;
	}


	public void setInicioRegistro(String inicioRegistro) {
		this.inicioRegistro = inicioRegistro;
	}


	public String getFinRegistro() {
		return finRegistro;
	}


	public void setFinRegistro(String finRegistro) {
		this.finRegistro = finRegistro;
	}


	public String getDiferencia() {
		return diferencia;
	}


	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}


	public void setFichaje(Fichaje fichaje) {
		this.fichaje = fichaje;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	
}
