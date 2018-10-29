package com.iberdrola.respadel.model;

import java.io.Serializable;
import java.sql.Time;

/**
 * Relación entre franjas y días
 * 
 * @author u247429
 *
 */

public class FranjaDia implements Serializable {

	private static final long serialVersionUID = -267295503431739042L;
	private Long idFranjaDia;
	private Long fkFranja;
	private Time horaInicio;
	private Time horaFin;

	public Long getFkFranja() {
		return fkFranja;
	}

	public void setFkFranja(Long fkFranja) {
		this.fkFranja = fkFranja;
	}

	public Long getIdFranjaDia() {
		return idFranjaDia;
	}

	public void setIdFranjaDia(Long idFranjaDia) {
		this.idFranjaDia = idFranjaDia;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "FranjaDia [idFranjaDia=" + idFranjaDia + ", fkFranja=" + fkFranja + ", horaInicio=" + horaInicio
				+ ", horaFin=" + horaFin + "]";
	}

}
