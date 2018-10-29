package com.iberdrola.respadel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa los festivos, cada uno tiene un ID único.
 * 
 */

public class DiaFestivo implements Serializable {

	private static final long serialVersionUID = 7285110340095267573L;
	private Long idDiaFestivo;
	private Date dia;
	private Long fkCentro;

	public Long getFkCentro() {
		return fkCentro;
	}

	public void setFkCentro(Long fkCentro) {
		this.fkCentro = fkCentro;
	}

	public Long getIdDiaFestivo() {
		return idDiaFestivo;
	}

	public void setIdDiaFestivo(Long idDiaFestivo) {
		this.idDiaFestivo = idDiaFestivo;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "DiaFestivo [idDiaFestivo=" + idDiaFestivo + ", dia=" + dia + "]";
	}
}
