package com.iberdrola.respadel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Tabla de solicitudes de disponbilidad de jugadores
 * 
 * @author u247429
 *
 */

public class Meapunto implements Serializable {

	private static final long serialVersionUID = -5630490676186234594L;
	private Long idMeapunto;
	private Long fkReserva;
	private Double nivelMinimo;
	private Double nivelMaximo;
	private String notas;
	private Date diaLimite;
	private Date horaLimite;
	private Long inscritos;
	private String estado;

	public Long getIdMeapunto() {
		return idMeapunto;
	}

	public void setIdMeapunto(Long idMeapunto) {
		this.idMeapunto = idMeapunto;
	}

	public Long getFkReserva() {
		return fkReserva;
	}

	public void setFkReserva(Long fkReserva) {
		this.fkReserva = fkReserva;
	}

	public Double getNivelMinimo() {
		return nivelMinimo;
	}

	public void setNivelMinimo(Double nivelMinimo) {
		this.nivelMinimo = nivelMinimo;
	}

	public Double getNivelMaximo() {
		return nivelMaximo;
	}

	public void setNivelMaximo(Double nivelMaximo) {
		this.nivelMaximo = nivelMaximo;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getDiaLimite() {
		return diaLimite;
	}

	public void setDiaLimite(Date diaLimite) {
		this.diaLimite = diaLimite;
	}

	public Date getHoraLimite() {
		return horaLimite;
	}

	public void setHoraLimite(Date horaLimite) {
		this.horaLimite = horaLimite;
	}

	public Long getInscritos() {
		return inscritos;
	}

	public void setInscritos(Long inscritos) {
		this.inscritos = inscritos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
