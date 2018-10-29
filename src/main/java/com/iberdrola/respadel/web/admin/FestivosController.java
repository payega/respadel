package com.iberdrola.respadel.web.admin;

import com.iberdrola.respadel.model.DiaFestivo;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.service.AdministracionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Mantenimiento de festivos
 * 
 * @author U247429
 */
@Controller
public class FestivosController {

	private Properties props = null;

	private AdministracionService administracionService;

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setAdministracionService(AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	@RequestMapping("/admin/festivos/mostrar.action")
	public String mostrar(Model model) throws Exception {
		model.addAttribute("listaCentros", administracionService.getListaCentros());
		return "admin/festivos/listado";
	}

	@RequestMapping("/admin/festivos/buscar.action")
	public String buscar(@RequestParam(value = "step", required = false, defaultValue = "1") Integer step,
			@RequestParam("fechaDesde") String fechaDesde, @RequestParam("fechaHasta") String fechaHasta,
			@RequestParam("idCentro") Long idCentro, Model model) throws Exception {
		Long elementosPaso = Long.valueOf(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDesdeBusqueda = null;
		Date fechaHastaBusqueda = null;
		if (!"".equals(fechaDesde)) {
			fechaDesdeBusqueda = sdf.parse(fechaDesde);
		}
		if (!"".equals(fechaHasta)) {
			fechaHastaBusqueda = sdf.parse(fechaHasta);
		}
		model.addAttribute("listadoFestivos",
				administracionService.getDiaFestivoByFiltro(fechaDesdeBusqueda, fechaHastaBusqueda, idCentro, step));
		model.addAttribute("numeroFestivos",
				administracionService.getCountFestivoByFiltro(fechaDesdeBusqueda, fechaHastaBusqueda, idCentro));
		model.addAttribute("step", step);
		model.addAttribute("elementosPaso", elementosPaso);
		model.addAttribute("fechaDesde", fechaDesde);
		model.addAttribute("fechaHasta", fechaHasta);
		model.addAttribute("idCentro", idCentro);
		model.addAttribute("listaInstalacion", administracionService.getInstalacionByExample(new Instalacion()));
		model.addAttribute("mapaCentros", administracionService.getMapaCentros());
		model.addAttribute("mapaFranjas", administracionService.getMapaFranjas());
		model.addAttribute("mapaInstalaciones", administracionService.getMapaInstalaciones());
		model.addAttribute("listaCentros", administracionService.getListaCentros());

		return "admin/festivos/listado";
	}

	@RequestMapping("/admin/festivos/eliminar.action")
	public String eliminar(@RequestParam(value = "step", required = false, defaultValue = "1") String step,
			@RequestParam(value = "fechaDesde", required = false, defaultValue = "") String fechaDesde,
			@RequestParam(value = "fechaHasta", required = false, defaultValue = "") String fechaHasta,
			@RequestParam("idDiaFestivo") Long idDiaFestivo, @RequestParam("idCentro") Long idCentro, Model model)
			throws Exception {
		administracionService.eliminarDiaFestivo(idDiaFestivo);
		return "redirect:/admin/festivos/buscar.action?step=" + step + "&fechaDesde=" + fechaDesde + "&fechaHasta="
				+ fechaHasta;
	}

	@RequestMapping("/admin/festivos/nueva.action")
	public String nueva(Model model) throws Exception {
		model.addAttribute("listaCentros", administracionService.getListaCentros());
		return "admin/festivos/detalle";
	}

	@RequestMapping(value = "/admin/festivos/guardar.action", method = RequestMethod.POST)
	public String guardar(@RequestParam("dia") String fecha, @RequestParam("idCentro") Long idCentro) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaParseada = sdf.parse(fecha);
		DiaFestivo diaFestivo = new DiaFestivo();
		diaFestivo.setDia(fechaParseada);
		diaFestivo.setFkCentro(idCentro);
		administracionService.crearDiaFestivo(diaFestivo);
		return "redirect:/admin/festivos/exito.action";
	}

	@RequestMapping(value = "/admin/festivos/exito.action")
	public String exito() throws Exception {
		return "admin/festivos/exito";
	}

}
