package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.DiaFestivo;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * Tablas de festivos. Los festivos son elementos con ID y se asignan a Centros
 * dado que los festivos son locales.
 *
 * @author U328075
 */

public interface DiaFestivoMapper {

	/**
	 * Devuelve un Día Festivo por id
	 * 
	 * @param idDiaFestivo
	 * @return Dia Festivo
	 */
	DiaFestivo getDiaFestivoById(Long idDiaFestivo);

	/**
	 * Obtiene una ocurrencia de dia festivo por fecha
	 * 
	 * @param dia
	 *            Fecha
	 * @param idCentro
	 *            Centro
	 * @return Día festivo
	 */
	List<DiaFestivo> getDiaFestivoByDate(@Param("dia") Date dia, @Param("fkCentro") Long idCentro);

	/**
	 * Devuelve festivos por rango de fechas para un centro
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idCentro
	 * @param rowBounds
	 * @return
	 */
	List<DiaFestivo> getDiaFestivoByFiltro(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,
			@Param("fkCentro") Long idCentro, RowBounds rowBounds);

	/**
	 * Cuenta los festivos entre dos fechas para un centro
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idCentro
	 * @return
	 */
	int getCountFestivoByFiltro(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,
			@Param("fkCentro") Long idCentro);

	/**
	 * Crear un nuevo día festivo
	 * 
	 * @param diaFestivo
	 */
	void crearDiaFestivo(DiaFestivo diaFestivo);

	/**
	 * Borrar un día festivo por ID
	 * 
	 * @param idDiaFestivo
	 * @return
	 */
	int eliminarDiaFestivo(Long idDiaFestivo);
}
