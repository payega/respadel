package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.Franja;

import java.util.Date;
import org.apache.ibatis.annotations.Param;

/**
 * Acceso a tablas de franjas. Las franjas son los slots reservables por cada
 * día.
 * 
 * @author U328075
 */

public interface FranjaMapper {

	/**
	 * Devuelve una franja por su id
	 * 
	 * @param idFranja
	 * @return
	 */
	Franja getFranjaById(Long idFranja);

	/**
	 * Devuelve una frajna válida para una fecha y un día (festivo o no)
	 * 
	 * @param fecha
	 * @param festivo
	 * @param idInstalacion
	 * @return
	 */
	Franja getFranjaValida(@Param("fecha") Date fecha, @Param("festivo") Long festivo,
			@Param("idInstalacion") Long idInstalacion);

}
