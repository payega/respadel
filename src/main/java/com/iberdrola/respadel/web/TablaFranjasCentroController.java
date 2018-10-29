package com.iberdrola.respadel.web;

import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.service.ReservaService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de la página de franjas disponibles por centro
 * 
 * @author u247429
 *
 */
@Controller
public class TablaFranjasCentroController {

	private Properties props = null;

	private ReservaService reservaService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/tablaFranjasCentro.action")
	public String show(@RequestParam("idCentro") Long idCentro, @RequestParam("dia") String dia, Model model)
			throws Exception {
		Integer numeroDiasAMostrar = new Integer(props.getProperty("NUMERO_DIAS_A_MOSTRAR", "7"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Integer diaAMostrarInteger = new Integer(dia);
		Date diaAMostrar = sdf.parse(dia);
		Calendar diaCalendar = Calendar.getInstance();
		Integer diaLimiteInfInteger = new Integer(sdf.format(diaCalendar.getTime()));
		diaCalendar.add(Calendar.DATE, numeroDiasAMostrar);
		Integer diaLimiteSupInteger = new Integer(sdf.format(diaCalendar.getTime()));

		if (diaAMostrarInteger > diaLimiteSupInteger || diaAMostrarInteger < diaLimiteInfInteger) {
			diaCalendar = Calendar.getInstance();
			diaAMostrarInteger = new Integer(sdf.format(diaCalendar.getTime()));
			diaAMostrar = diaCalendar.getTime();
		}
		if (diaLimiteSupInteger > diaAMostrarInteger) {
			diaCalendar.setTime(diaAMostrar);
			diaCalendar.add(Calendar.DATE, 1);
			model.addAttribute("siguienteDia", diaCalendar.getTime());
		}
		if (diaLimiteInfInteger < diaAMostrarInteger) {
			diaCalendar.setTime(diaAMostrar);
			diaCalendar.add(Calendar.DATE, -1);
			model.addAttribute("anteriorDia", diaCalendar.getTime());
		}

		// TODO ojo que esto no funciona ya
		List<Instalacion> listaInstalaciones = null; // reservaService.getInstalacionesByCentro(idCentro);
		model.addAttribute("diaAMostrar", diaAMostrar);
		model.addAttribute("listaInstalaciones", listaInstalaciones);
		model.addAttribute("mapaInstalacionFranjasDia",
				reservaService.getMapaInstalacionFranjasDia(diaAMostrar, listaInstalaciones, idCentro));
		model.addAttribute("mapaReservas", reservaService.getMapaReservas(diaAMostrar, diaAMostrar));
		model.addAttribute("centro", reservaService.getCentroById(idCentro));
		return "TablaFranjasCentro";
	}

}
