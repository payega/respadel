package com.iberdrola.respadel.dao;

import com.iberdrola.respadel.model.Meapunto;

import java.util.List;

import java.util.Date;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * Tabla de Me apunto, que permite a los usuarios ofrecerse para jugar un
 * partido.
 * 
 * @author U328075
 */

public interface MeapuntoMapper {

	/**
	 * Devuelve un me apunto pro ID
	 * 
	 * @param idMeapunto
	 * @return
	 */
	Meapunto getMeapuntoPorId(Long idMeapunto);

	/**
	 * Devuelve los proximos me apunto
	 * 
	 * @param dia
	 * @return
	 */
	List<Meapunto> getProximosMeapunto(Date dia);

	/**
	 * Crea un nuevo me apunto
	 * 
	 * @param meapunto
	 */
	void crearMeapunto(Meapunto meapunto);

	/**
	 * Borra un me apunto
	 * 
	 * @param idMeapunto
	 * @return
	 */
	int eliminarMeapunto(Long idMeapunto);

	/**
	 * Devuelve los proximos me apunto para un usuario y día.
	 * 
	 * @param dia
	 * @param usuario
	 * @return
	 */
	@MapKey("IDMEAPUNTO")
	Map<Long, Map> getMapaProximosMeapuntoUsuario(@Param("dia") Date dia, @Param("usuario") String usuario);

	List<Map> getMapaProximosMeapunto(@Param("dia") Date dia, @Param("usuario") String usuario);

}
