package com.iberdrola.respadel.model;

import java.io.Serializable;

/**
 * Representa los centros: Madrid, Bilbao...
 * 
 * @author u247429
 *
 */

public class Centro implements Serializable {

	private static final long serialVersionUID = 4213352033221802225L;
	private Long idCentro;
	private String nombre;

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Centro [idCentro=" + idCentro + ", nombre=" + nombre + "]";
	}

}
