package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.Instalacion;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;

/**
 * Acceso a la tabla de instalaciones
 */

public interface InstalacionMapper {

	/**
	 * Devuelve una instalación por ID
	 * 
	 * @param idInstalacion
	 * @return
	 */
	Instalacion getInstalacionById(Long idInstalacion);

	/**
	 * Devuelve las instalaciones de un centro
	 * 
	 * @param fkCentro
	 * @return
	 */
	List<Instalacion> getInstalacionByFkCentro(Long fkCentro);

	/**
	 * Devuelve las instalaciones que cumplen un filtro
	 * 
	 * @param example
	 * @return
	 */
	List<Instalacion> getInstalacionByExample(Instalacion example);

	/**
	 * Devuelve las instalaciones de una actividad. Ejemplo, todas las pistas de
	 * pádel.
	 * 
	 * @param idActividad
	 * @return
	 */
	List<Centro> getListaCentrosPorActividad(Long idActividad);

	/**
	 * Devuelve un mapa de instalaciones
	 * 
	 * @return
	 */
	@MapKey("idInstalacion")
	Map<Long, Instalacion> getMapaInstalaciones();

}
