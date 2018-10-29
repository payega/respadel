package com.iberdrola.respadel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Tabla de reservas.
 */

public class Reserva implements Serializable {

	private static final long serialVersionUID = -6560299207378309253L;
	private Long idReserva;
	private Long fkFranjaDia;
	private Long fkInstalacion;
	private Date dia;
	private Date fecHora = new Date();
	private String usuario;
	private String nombreOriginal;
	private String estado;

	public Date getFecHora() {
		return fecHora;
	}

	public void setFecHora(Date fecHora) {
		this.fecHora = fecHora;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public Long getFkFranjaDia() {
		return fkFranjaDia;
	}

	public void setFkFranjaDia(Long fkFranjaDia) {
		this.fkFranjaDia = fkFranjaDia;
	}

	public Long getFkInstalacion() {
		return fkInstalacion;
	}

	public void setFkInstalacion(Long fkInstalacion) {
		this.fkInstalacion = fkInstalacion;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreOriginal() {
		return nombreOriginal;
	}

	public void setNombreOriginal(String nombreOriginal) {
		this.nombreOriginal = nombreOriginal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", fkFranjaDia=" + fkFranjaDia + ", fkInstalacion=" + fkInstalacion
				+ ", dia=" + dia + ", fecHora=" + fecHora + ", usuario=" + usuario + ", nombreOriginal="
				+ nombreOriginal + ", estado=" + estado + "]";
	}

}
