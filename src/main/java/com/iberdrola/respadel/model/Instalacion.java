package com.iberdrola.respadel.model;

import java.io.Serializable;

/**
 * Representa las instalaciones
 * 
 * @author U328075
 */

public class Instalacion implements Serializable {

	private static final long serialVersionUID = 1829591813856940958L;
	private Long idInstalacion;
	private Long fkCentro;
	private Long fkActividad;
	private String nombre;

	public Long getIdInstalacion() {
		return idInstalacion;
	}

	public void setIdInstalacion(Long idInstalacion) {
		this.idInstalacion = idInstalacion;
	}

	public Long getFkCentro() {
		return fkCentro;
	}

	public void setFkCentro(Long fkCentro) {
		this.fkCentro = fkCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getFkActividad() {
		return fkActividad;
	}

	public void setFkActividad(Long fkActividad) {
		this.fkActividad = fkActividad;
	}

	@Override
	public String toString() {
		return "Instalacion [idInstalacion=" + idInstalacion + ", fkCentro=" + fkCentro + ", fkActividad=" + fkActividad
				+ ", nombre=" + nombre + "]";
	}

}
