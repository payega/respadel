package com.iberdrola.respadel.web.admin;

import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.service.AdministracionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Mantenimiento de servicios
 * 
 * @author U247429
 */
@Controller
public class ServiciosController {

	private Properties props = null;
	private AdministracionService administracionService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setAdministracionService(AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	@RequestMapping("/admin/servicios/franjasDisponibles.action")
	public @ResponseBody List<FranjaDia> franjasDisponibles(@RequestParam("dia") String fecha,
			@RequestParam("idInstalacion") Long idInstalacion) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaParseada = sdf.parse(fecha);
		return administracionService.getMapaDiasFranjasDia(fechaParseada, idInstalacion);
	}

	@RequestMapping("/admin/servicios/comprobarFestivo.action")
	public @ResponseBody Map comprobarFestivo(@RequestParam("dia") String fecha,
			@RequestParam("idInstalacion") Long idInstalacion) throws Exception {
		Map resultado = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEEEEEEEE");
		Date fechaParseada = sdf.parse(fecha);
		resultado.put("festivo", administracionService.isFestivoByInstalacion(fechaParseada, idInstalacion));
		resultado.put("dia", sdf2.format(fechaParseada));
		return resultado;
	}

}
