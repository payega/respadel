package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.Centro;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;

/**
 * DAO para accedera a las tablas de Centros
 *
 * @author U328075
 */

public interface CentroMapper {

	/**
	 * Devuelve la lista de centros.
	 * 
	 * @return Centros
	 */
	List<Centro> getListaCentros();

	/**
	 * Devuelve el detalle de un centro
	 * 
	 * @param idCentro
	 *            ID de centro
	 * @return Detalle de centro
	 */
	Centro getCentroById(Long idCentro);

	/**
	 * Devuelve los centros en formato Mapa
	 * 
	 * @return Mapa de centros
	 */
	@MapKey("idCentro")
	Map<Long, Centro> getMapaCentros();

}
