package com.iberdrola.respadel.web.admin;

import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Reserva;
import com.iberdrola.respadel.service.AdministracionService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Mantenimiento de reservas
 * 
 * @author U247429
 */

@Controller
public class ReservasController {

	private Properties props = null;

	private AdministracionService administracionService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setAdministracionService(AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	@RequestMapping("/admin/reservas/mostrar.action")
	public String mostrar(Model model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		model.addAttribute("fechaDesde", sdf.format(calendar.getTime()));
		model.addAttribute("listaInstalacion", administracionService.getInstalacionByExample(new Instalacion()));
		return "admin/reservas/listado";
	}

	@RequestMapping("/admin/reservas/buscar.action")
	public String buscar(@RequestParam(value = "step", required = false, defaultValue = "1") Integer step,
			@RequestParam("usuario") String usuario, @RequestParam("fechaDesde") String fechaDesde,
			@RequestParam("fechaHasta") String fechaHasta, @RequestParam("idInstalacion") Long idInstalacion,
			Model model) throws Exception {
		Long elementosPaso = Long.valueOf(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String usuarioBusqueda = null;
		Date fechaDesdeBusqueda = null;
		Date fechaHastaBusqueda = null;
		if (!"".equals(usuario)) {
			usuarioBusqueda = usuario + "%";
		}
		if (!"".equals(fechaDesde)) {
			fechaDesdeBusqueda = sdf.parse(fechaDesde);
		}
		if (!"".equals(fechaHasta)) {
			fechaHastaBusqueda = sdf.parse(fechaHasta);
		}
		model.addAttribute("listadoReservas", administracionService.getReservasFiltro(idInstalacion, fechaDesdeBusqueda,
				fechaHastaBusqueda, usuarioBusqueda, step));
		model.addAttribute("numeroReservas", administracionService.getCountReservasFiltro(idInstalacion,
				fechaDesdeBusqueda, fechaHastaBusqueda, usuarioBusqueda));
		model.addAttribute("step", step);
		model.addAttribute("elementosPaso", elementosPaso);
		model.addAttribute("usuario", usuario);
		model.addAttribute("fechaDesde", fechaDesde);
		model.addAttribute("fechaHasta", fechaHasta);
		model.addAttribute("idInstalacion", idInstalacion);
		model.addAttribute("listaInstalacion", administracionService.getInstalacionByExample(new Instalacion()));
		model.addAttribute("mapaCentros", administracionService.getMapaCentros());
		model.addAttribute("mapaFranjas", administracionService.getMapaFranjas());
		model.addAttribute("mapaInstalaciones", administracionService.getMapaInstalaciones());
		return "admin/reservas/listado";
	}

	@RequestMapping("/admin/reservas/nueva.action")
	public String nueva(Model model) throws Exception {
		model.addAttribute("listaInstalacion", administracionService.getInstalacionByExample(new Instalacion()));
		model.addAttribute("mapaCentros", administracionService.getMapaCentros());
		model.addAttribute("mapaFranjas", administracionService.getMapaFranjas());
		model.addAttribute("mapaInstalaciones", administracionService.getMapaInstalaciones());
		return "admin/reservas/detalle";
	}

	@RequestMapping("/admin/reservas/eliminar.action")
	public String eliminar(@RequestParam(value = "step", required = false, defaultValue = "1") String step,
			@RequestParam("usuario") String usuario, @RequestParam("fechaDesde") String fechaDesde,
			@RequestParam("fechaHasta") String fechaHasta, @RequestParam("idInstalacion") String idInstalacion,
			@RequestParam("usuarioEliminar") String usuarioEliminar, @RequestParam("idReserva") Long idReserva,
			Model model) throws Exception {
		administracionService.borrarReserva(idReserva, usuarioEliminar);
		return "redirect:/admin/reservas/buscar.action?step=" + step + "&usuario=" + usuario + "&fechaDesde="
				+ fechaDesde + "&fechaHasta=" + fechaHasta + "&idInstalacion=" + idInstalacion;
	}

	@RequestMapping(value = "/admin/reservas/guardar.action", method = RequestMethod.POST)
	public String guardar(@RequestParam("usuario") String usuario, @RequestParam("dia") String fecha,
			@RequestParam("idInstalacion") Long idInstalacion, @RequestParam("idFranjaDia") Long idFranjaDia,
			@RequestParam(value = "reservaPeriodica", defaultValue = "false", required = false) Boolean reservaPeriodica,
			@RequestParam(value = "ultimodia", required = false) String ultimodia,
			@RequestParam(value = "tipoReserva", required = false) Long tipoReserva) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaParseada = sdf.parse(fecha);
		Reserva reserva = new Reserva();
		reserva.setDia(fechaParseada);
		reserva.setEstado("C");
		reserva.setUsuario(usuario);
		reserva.setFkInstalacion(idInstalacion);
		reserva.setFkFranjaDia(idFranjaDia);
		Long numeroReservasCreadas = 1l;
		if (reservaPeriodica) {
			Date ultimodiaParseada = sdf.parse(ultimodia);
			numeroReservasCreadas = administracionService.crearReservaPeriodica(reserva, ultimodiaParseada,
					tipoReserva);
		} else {
			administracionService.crearReserva(reserva);
		}
		return "redirect:/admin/reservas/exito.action?numeroReservasCreadas=" + numeroReservasCreadas;
	}

	@RequestMapping(value = "/admin/reservas/exito.action")
	public String exito() throws Exception {
		return "admin/reservas/exito";
	}

}
