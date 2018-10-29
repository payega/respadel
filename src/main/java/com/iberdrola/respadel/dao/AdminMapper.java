package com.iberdrola.respadel.dao;

/**
 *
 * Dado un usuario indica si el usuario es Admin o no
 * 
 * @author u247429
 *
 */

public interface AdminMapper {

	/**
	 * Devuelve si un usuario es admin o no.
	 * 
	 * @param idUsuario
	 *            Id del usuario
	 * @return Id del usuario o null si no es admin.
	 */
	String getAdmin(String idUsuario);

}
