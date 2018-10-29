package com.iberdrola.respadel.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iberdrola.respadel.model.Actividad;
import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Meapunto;
import com.iberdrola.respadel.model.Reserva;

/**
 * Servicio de reservas.
 * 
 * @author U328075
 */
@Service
public interface ReservaService {

	List<Centro> getListaCentrosPorActividad(Long idActividad);

	Centro getCentroById(Long idCentro);

	Map<Long, List<Instalacion>> getMapaListaInstalaciones(List<Centro> listaCentros);

	List<Instalacion> getInstalacionesByCentroYActividad(Long idCentro, Long idActividad);

	Map<String, Map<String, String>> getMapaReservas(Date diaInicio, Date diaFin);

	Boolean isReservaPosible(String usuario, Long idActividad);

	List<Date> getProximosDias();

	Map<Date, List<FranjaDia>> getMapaDiasFranjasDia(List<Date> listaDias, Long idInstalacion, Long idCentro);

	Map<Long, List<FranjaDia>> getMapaInstalacionFranjasDia(Date dia, List<Instalacion> listaInsalaciones,
			Long idCentro);

	void crearReserva(Reserva reserva);

	int eliminarReserva(Long idReserva, String usuario);

	Reserva getReserva(Long idReserva);

	FranjaDia getFranjaDia(Long idFranjaDia);

	List<Reserva> getHistoricoReservas(String usu);

	Instalacion getInstalacion(Long idInstalacion);

	boolean isReservaAutorizada(String usu);

	Date getFechaRealReserva(Date date, Time time);

	Meapunto getMeapuntoPorId(Long idMeapunto);

	Map<Long, Map> getProximosMeapuntoUsuario(String usuario);

	List<Map> getProximosMeapuntoNoUsuario(String usuario);

	boolean isHorarioEnRango(Date dia, Long idFranjaDia);

	List<Actividad> getListaActividades();

	Actividad getActividad(Long idActividad);

	Map<Long, Actividad> getMapaActividades();

	boolean esAdmin(String idUsuario);

}
