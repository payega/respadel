/*
 * Created on 16-sep-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.iberdrola.respadel.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iberdrola.frwk.batch.TareaBatch;

/**
 * No se usa!
 * 
 * @author u247429
 *
 */
public class Testeo implements TareaBatch {
	protected static Log log = LogFactory.getLog(Testeo.class);

	private String parametro;

	public String runBatch(String parametro) {
		try {
			log.info("Testeo.runBatch(parametro), parametro: " + parametro);
			if ((parametro != null) && (!"".equals(parametro))) {
				long milis = Long.parseLong(parametro) * 1000;
				Thread.sleep(milis);
			}
			return OK;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
