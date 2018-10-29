package com.iberdrola.respadel.service;

import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.DiaFestivo;
import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Reserva;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Servicios de administración y parametrización.
 * 
 * @author u247429
 *
 */
public interface AdministracionService {

	List<Reserva> getReservasFiltro(Long fkInstalacion, Date fechaDesde, Date fechaHasta, String usuario, int step);

	int getCountReservasFiltro(Long fkInstalacion, Date fechaDesde, Date fechaHasta, String usuario);

	void crearReserva(Reserva reserva);

	Long crearReservaPeriodica(Reserva reserva, Date diaFinal, Long tipoReserva);

	void borrarReserva(Long idReserva, String usuario);

	void crearDiaFestivo(DiaFestivo diaFestivo);

	Map<Long, FranjaDia> getMapaFranjas();

	Map<Long, Centro> getMapaCentros();

	List<Instalacion> getInstalacionByExample(Instalacion instalacion);

	Map<Long, Instalacion> getMapaInstalaciones();

	List<FranjaDia> getMapaDiasFranjasDia(Date dia, Long idInstalacion);

	List<DiaFestivo> getDiaFestivoByFiltro(Date fechaDesde, Date fechaHasta, Long idCentro, int step);

	int getCountFestivoByFiltro(Date fechaDesde, Date fechaHasta, Long idCentro);

	int eliminarDiaFestivo(Long idDiaFestivo);

	List<Centro> getListaCentros();

	boolean isFestivoByCentro(Date dia, Long idCentro);

	boolean isFestivoByInstalacion(Date dia, Long idCentro);
}
