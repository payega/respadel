package com.iberdrola.respadel.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iberdrola.respadel.model.Actividad;
import com.iberdrola.respadel.service.ReservaService;

/**
 * Controlador de la página de selección de actividad
 * 
 * @author u247429
 *
 */
@Controller
public class SeleccionActividadController {

	private ReservaService reservaService;

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/index.action")
	public String index(Model model) throws Exception {
		List<Actividad> listaActividades = reservaService.getListaActividades();
		model.addAttribute("listaActividades", listaActividades);
		return "SeleccionActividad";
	}

}
