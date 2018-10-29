package com.iberdrola.respadel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa las franjas reservables. Dependen de si es día es festivo o no.
 * 
 * @author u247429
 *
 */

public class Franja implements Serializable {

	private static final long serialVersionUID = -2197660200647930582L;
	private Long idFranja;
	private String nombre;
	private Date fechaInicioValidez;
	private Date fechaFinValidez;
	private Long franjaFestivos;

	public Long getIdFranja() {
		return idFranja;
	}

	public void setIdFranja(Long idFranja) {
		this.idFranja = idFranja;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicioValidez() {
		return fechaInicioValidez;
	}

	public void setFechaInicioValidez(Date fechaInicioValidez) {
		this.fechaInicioValidez = fechaInicioValidez;
	}

	public Date getFechaFinValidez() {
		return fechaFinValidez;
	}

	public void setFechaFinValidez(Date fechaFinValidez) {
		this.fechaFinValidez = fechaFinValidez;
	}

	public Long getFranjaFestivos() {
		return franjaFestivos;
	}

	public void setFranjaFestivos(Long franjaFestivos) {
		this.franjaFestivos = franjaFestivos;
	}

	@Override
	public String toString() {
		return "Franja [idFranja=" + idFranja + ", nombre=" + nombre + ", fechaInicioValidez=" + fechaInicioValidez
				+ ", fechaFinValidez=" + fechaFinValidez + ", franjaFestivos=" + franjaFestivos + "]";
	}

}
