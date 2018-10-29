package com.iberdrola.respadel.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iberdrola.respadel.service.ReservaService;

/**
 * Controlador de la página de selección de centro
 * 
 * @author u247429
 *
 */
@Controller
public class SeleccionCentroController {

	private ReservaService reservaService;

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/seleccionCentro.action")
	public String index(@RequestParam("idActividad") Long idActividad, Model model) throws Exception {
		model.addAttribute("listaCentros", reservaService.getListaCentrosPorActividad(idActividad));
		model.addAttribute("actividad", reservaService.getActividad(idActividad));
		return "SeleccionCentro";
	}

}
