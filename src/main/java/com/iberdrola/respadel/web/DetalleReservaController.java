package com.iberdrola.respadel.web;

import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Reserva;
import com.iberdrola.respadel.service.ReservaService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de la pagían de detalle de reservas
 * 
 * @author U328075
 */

@Controller
public class DetalleReservaController {

	private Properties props = null;

	private ReservaService reservaService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@RequestMapping("/reservas/mostrar.action")
	public String mostrar(HttpServletRequest request, @RequestParam("origen") String origen,
			@RequestParam("idFranjaDia") Long idFranjaDia, @RequestParam("idInstalacion") Long idInstalacion,
			@RequestParam("dia") String dia, Model model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date diaformateado = sdf.parse(dia);

		model.addAttribute("dia", dia);
		model.addAttribute("diaformateado", diaformateado);
		model.addAttribute("origen", origen);
		Instalacion instalacion = reservaService.getInstalacion(idInstalacion);
		model.addAttribute("centro", reservaService.getCentroById(instalacion.getFkCentro()));
		model.addAttribute("instalacion", instalacion);
		model.addAttribute("actividad", reservaService.getActividad(instalacion.getFkActividad()));
		model.addAttribute("franjaDia", reservaService.getFranjaDia(idFranjaDia));

		// Estas comprobaciones se repiten en el método de crear reserva
		if (!reservaService.isReservaAutorizada(request.getUserPrincipal().getName())) {
			model.addAttribute("titulo", "No autorizado");
			model.addAttribute("mensaje", "No está autorizado para realizar una reserva");
			return "Mensaje";
		}
		if (!reservaService.esAdmin(request.getUserPrincipal().getName())) {
			if (!reservaService.isReservaPosible(request.getUserPrincipal().getName(), instalacion.getFkActividad())) {
				model.addAttribute("titulo", "Limite de reservas excedido");
				model.addAttribute("mensaje", "Ha superado el límite permitido de reservas");
				return "Mensaje";
			} else if (!reservaService.isHorarioEnRango(diaformateado, idFranjaDia)) {
				model.addAttribute("titulo", "Horario no permitido");
				model.addAttribute("mensaje", "Horario de reserva no permitido");
				return "Mensaje";
			}
		}
		return "ConfirmacionReserva";
	}

	@RequestMapping("/reservas/reservar.action")
	public String reservar(HttpServletRequest request, @RequestParam("origen") String origen,
			@RequestParam("idFranjaDia") Long idFranjaDia, @RequestParam("idInstalacion") Long idInstalacion,
			@RequestParam("dia") String dia, Model model) throws Exception {
		Instalacion instalacion = reservaService.getInstalacion(idInstalacion);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date diaformateado = sdf.parse(dia);
		Reserva reserva = new Reserva();
		reserva.setDia(diaformateado);
		reserva.setFkFranjaDia(idFranjaDia);
		reserva.setFkInstalacion(idInstalacion);
		reserva.setUsuario(request.getUserPrincipal().getName());
		reserva.setEstado("C");
		reservaService.crearReserva(reserva);
		return "redirect:/" + origen + "?idCentro=" + instalacion.getFkCentro() + "&idActividad="
				+ instalacion.getFkActividad();
	}

	@RequestMapping("/reservas/mostrarEliminar.action")
	public String mostrarEliminar(HttpServletRequest request, @RequestParam("origen") String origen,
			@RequestParam("idReserva") Long idReserva, Model model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Reserva reserva = reservaService.getReserva(idReserva);
		Instalacion instalacion = reservaService.getInstalacion(reserva.getFkInstalacion());
		model.addAttribute("instalacion", instalacion);
		model.addAttribute("dia", sdf.format(reserva.getDia())); //
		model.addAttribute("diaformateado", reserva.getDia());
		model.addAttribute("origen", origen);
		model.addAttribute("reserva", reserva);
		model.addAttribute("centro", reservaService.getCentroById(instalacion.getFkCentro()));
		model.addAttribute("actividad", reservaService.getActividad(instalacion.getFkActividad()));
		model.addAttribute("franjaDia", reservaService.getFranjaDia(reserva.getFkFranjaDia()));

		return "EliminarReserva";
	}

	@RequestMapping("/reservas/eliminar.action")
	public String eliminar(HttpServletRequest request, @RequestParam("origen") String origen,
			@RequestParam("idReserva") Long idReserva, Model model) throws Exception {
		Reserva reserva = reservaService.getReserva(idReserva);
		Instalacion instalacion = reservaService.getInstalacion(reserva.getFkInstalacion());
		// ojo, eliminamos la reserva incluyendo al usuario en la where
		reservaService.eliminarReserva(idReserva, request.getUserPrincipal().getName());
		return "redirect:/" + origen + "?idCentro=" + instalacion.getFkCentro() + "&idActividad="
				+ instalacion.getFkActividad();
	}

}
