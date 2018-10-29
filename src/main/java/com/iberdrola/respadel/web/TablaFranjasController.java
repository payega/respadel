package com.iberdrola.respadel.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.service.ReservaService;

/**
 * Controlador de franjas,
 * 
 * @author U328075
 */
@Controller
public class TablaFranjasController {

	private ReservaService reservaService;

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/tablaFranjas.action")
	public String show(@RequestParam(value = "idCentro") Long idCentro,
			@RequestParam(value = "idActividad") Long idActividad, Model model) throws Exception {

		List<Date> proximosDias = reservaService.getProximosDias();
		model.addAttribute("proximosDias", proximosDias);
		model.addAttribute("mapaReservas",
				reservaService.getMapaReservas(proximosDias.get(0), proximosDias.get(proximosDias.size() - 1)));
		model.addAttribute("actividad", reservaService.getActividad(idActividad));

		List<Instalacion> listaInstalaciones = reservaService.getInstalacionesByCentroYActividad(idCentro, idActividad);
		Centro centro = reservaService.getCentroById(idCentro);
		model.addAttribute("centro", centro);

		List<Map<String, Object>> listaMapasInstalaciones = new ArrayList<Map<String, Object>>();
		for (Instalacion instalacion : listaInstalaciones) {
			Map<String, Object> mapaInstalaciones = new HashMap<String, Object>();
			mapaInstalaciones.put("instalacion", instalacion);
			Map<Date, List<FranjaDia>> mapaDiasFranjasDia = reservaService.getMapaDiasFranjasDia(proximosDias,
					instalacion.getIdInstalacion(), idCentro);
			mapaInstalaciones.put("mapaDiasFranjasDia", mapaDiasFranjasDia);
			// guardamos id_fanja_fecha
			Map<String, Boolean> mapaPasado = new HashMap<String, Boolean>();
			List<FranjaDia> franjasPrimerDia = mapaDiasFranjasDia.get(proximosDias.get(0));
			for (FranjaDia franjaDia : franjasPrimerDia) {
				Date fechaFin = reservaService.getFechaRealReserva(proximosDias.get(0), franjaDia.getHoraFin());
				if (fechaFin.before(new Date())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String diaformateado = sdf.format(proximosDias.get(0));
					mapaPasado.put(
							franjaDia.getIdFranjaDia() + "_" + instalacion.getIdInstalacion() + "_" + diaformateado,
							new Boolean(true));
				}
			}
			mapaInstalaciones.put("mapaPasado", mapaPasado);

			listaMapasInstalaciones.add(mapaInstalaciones);
		}
		model.addAttribute("instalaciones", listaMapasInstalaciones);

		return "TablaFranjas";
	}

}
