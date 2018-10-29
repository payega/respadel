package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.FranjaDia;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;

/**
 * Tabla de relaci�n entre franjas y d�as.
 * 
 * @author U328075
 */

public interface FranjaDiaMapper {

	/**
	 * Devuelve las franjas de un d�a.
	 * 
	 * @param idFranjaDia
	 * @return
	 */
	FranjaDia getFranjaDiaById(Long idFranjaDia);

	/**
	 * Devuelve el d�a al que pertenece una franja
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
