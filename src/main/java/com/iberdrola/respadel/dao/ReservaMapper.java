package com.iberdrola.respadel.dao;

import java.util.List;

import com.iberdrola.respadel.model.Reserva;

import java.util.Date;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * Tabla de reservas.
 * 
 * @author U328075
 */

public interface ReservaMapper {

	/**
	 * Devuelve una reserva por id
	 * 
	 * @param idReserva
	 * @return
	 */
	Reserva getReservaPorId(Long idReserva);

	/**
	 * Devuelve las reservas de un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	List<Reserva> getReservasPorUsuario(String usuario);

	/**
	 * Devuelve las reservas en formato mapa entre fechas
	 * 
	 * @param diaInicio
	 * @param diaFin
	 * @return
	 */
	@MapKey("reserva")
	Map<String, Map<String, String>> getMapaReservas(@Param("diaInicio") Date diaInicio, @Param("diaFin") Date diaFin);

	/**
	 * Devuelve las reservas de un día
	 * 
	 * @param dia
	 * @return
	 */
	@MapKey("reserva")
	Map<String, Map<String, String>> getMapaReservasDia(@Param("dia") Date dia);

	/**
	 * Devuelve las proximas reservas a partir de un día
	 * 
	 * @param dia
	 * @param usuario
	 * @param idActividad
	 * @return
	 */
	List<Reserva> getProximasReservas(@Param("dia") Date dia, @Param("usuario") String usuario,
			@Param("actividad") Long idActividad);

	/**
	 * Crea una nueva reserva
	 * 
	 * @param reserva
	 */
	void crearReserva(Reserva reserva);

	/**
	 * Elimina una reserva existente
	 * 
	 * @param idReserva
	 * @param usuario
	 * @return
	 */
	int eliminarReserva(@Param("idReserva") Long idReserva, @Param("usuario") String usuario);

	/**
	 * Consulta de reservas por filtro
	 * 
	 * @param fkInstalacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param usuario
	 * @param rowBounds
	 * @return
	 */
	List<Reserva> getReservasFiltro(@Param("fkInstalacion") Long fkInstalacion, @Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("usuario") String usuario, RowBounds rowBounds);

	/**
	 * Consulta del número de reservas por filtro
	 * 
	 * @param fkInstalacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param usuario
	 * @return
	 */
	int getCountReservasFiltro(@Param("fkInstalacion") Long fkInstalacion, @Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("usuario") String usuario);

	List<Reserva> getByExample(Reserva example);

}
