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
 * Controlador de la página de Mis Reservas
 * 
 * @author U247429
 */

@Controller
public class MisReservasController {

	private Properties props = null;

	private ReservaService reservaService;

	private AdministracionService administracionService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	public void setAdministracionService(AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	@RequestMapping("/misReservas.action")
	public String buscar(HttpServletRequest request,
			@RequestParam(value = "step", required = false, defaultValue = "1") Integer step,
			@RequestParam(value = "fechaDesde", required = false, defaultValue = "") String fechaDesde,
			@RequestParam(value = "fechaHasta", required = false, defaultValue = "") String fechaHasta, Model model)
			throws Exception {
		Long elementosPaso = Long.valueOf(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String usuarioBusqueda = request.getRemoteUser();

		Date fechaDesdeBusqueda = null;
		Date fechaHastaBusqueda = null;
		if (!"".equals(fechaHasta)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(fechaHasta));
			calendar.add(Calendar.DATE, 1); // un día más en el hasta
			fechaHastaBusqueda = calendar.getTime();
		} else {
			fechaHasta = sdf.format(new Date());
		}
		if (!"".equals(fechaDesde)) {
			fechaDesdeBusqueda = sdf.parse(fechaDesde);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -7); // siete días de busqueda por defecto
			fechaDesde = sdf.format(calendar.getTime());
		}
		List<Reserva> reservas = administracionService.getReservasFiltro(null, fechaDesdeBusqueda, fechaHastaBusqueda,
				usuarioBusqueda, step);
		model.addAttribute("listadoReservas", reservas);
		model.addAttribute("numeroReservas", administracionService.getCountReservasFiltro(null, fechaDesdeBusqueda,
				fechaHastaBusqueda, usuarioBusqueda));
		model.addAttribute("step", step);
		model.addAttribute("elementosPaso", elementosPaso);
		model.addAttribute("fechaDesde", fechaDesde);
		model.addAttribute("fechaHasta", fechaHasta);
		model.addAttribute("mapaCentros", administracionService.getMapaCentros());
		Map<Long, FranjaDia> mapaFranjasDia = administracionService.getMapaFranjas();
		model.addAttribute("mapaFranjas", mapaFranjasDia);
		model.addAttribute("mapaInstalaciones", administracionService.getMapaInstalaciones());
		model.addAttribute("mapaActividades", reservaService.getMapaActividades());
		Map<Long, Boolean> eliminables = new HashMap<Long, Boolean>();
		for (Reserva reserva : reservas) {
			eliminables.put(reserva.getIdReserva(), reservaService
					.getFechaRealReserva(reserva.getDia(), mapaFranjasDia.get(reserva.getFkFranjaDia()).getHoraInicio())
					.after(new Date()));
		}
		model.addAttribute("eliminables", eliminables);

		return "MisReservas";
	}

}
