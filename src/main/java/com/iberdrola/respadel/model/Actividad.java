package com.iberdrola.respadel.model;

import java.io.Serializable;

/**
 * Representa las actividades: tenis, padel, fronton...
 * 
 * @author u247429
 *
 */

public class Actividad implements Serializable {

	private static final long serialVersionUID = 5442934315061759316L;
	private Long idActividad;
	private String nombre;

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Actividad [idActividad=" + idActividad + ", nombre=" + nombre + "]";
	}

}
