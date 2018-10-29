package com.iberdrola.respadel.dao;

import java.util.List;

import com.iberdrola.respadel.model.Actividad;

/**
 * Mapper para obtener la lista de actividades disponibles (Padel, tenis...)
 * 
 * @author u247429
 *
 */

public interface ActividadMapper {

	/**
	 * Consulta la lista de actividades
	 * 
	 * @return Actividades configuradas en BD
	 */
	List<Actividad> getListaActividades();

	/**
	 * Devuelve el detalle de una actividad
	 * 
	 * @param idActividad
	 *            Id de actividad
	 * @return Detalle de la actividad
	 */
	Actividad getActividad(Long idActividad);

}
