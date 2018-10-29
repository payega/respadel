package com.iberdrola.respadel.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Reserva;
import com.iberdrola.respadel.service.AdministracionService;
import com.iberdrola.respadel.service.ReservaService;

/**
 * Controlador de la página de me apunto
 *
 * @author U247429
 */

@Controller
public class MeapuntoController {

	private Properties props = null;

	private ReservaService reservaService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/meapunto.action")
	public String mostrar(HttpServletRequest request, Model model) throws Exception {
		String usuario = request.getRemoteUser();
		model.addAttribute("proximosMeapunto", reservaService.getProximosMeapuntoNoUsuario(usuario));
		model.addAttribute("proximosMeapuntoUsuario", reservaService.getProximosMeapuntoUsuario(usuario));
		return "Meapunto";
	}

}
