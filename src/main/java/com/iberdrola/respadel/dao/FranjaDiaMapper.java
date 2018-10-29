package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.FranjaDia;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;

/**
 * Tabla de relación entre franjas y días.
 * 
 * @author U328075
 */

public interface FranjaDiaMapper {

	/**
	 * Devuelve las franjas de un día.
	 * 
	 * @param idFranjaDia
	 * @return
	 */
	FranjaDia getFranjaDiaById(Long idFranjaDia);

	/**
	 * Devuelve el día al que pertenece una franja
	 * 
	 * @param idFranja
	 * @return
	 */
	List<FranjaDia> getFranjaDiaByFranja(Long idFranja);

	/**
	 * Devuelve las franjas en formato mapa
	 * 
	 * @return
	 */
	@MapKey("idFranjaDia")
	Map<Long, FranjaDia> getMapaFranjas();

}
