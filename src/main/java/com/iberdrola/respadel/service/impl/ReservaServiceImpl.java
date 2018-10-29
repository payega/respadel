package com.iberdrola.respadel.service.impl;

import com.iberdrola.respadel.dao.ActividadMapper;
import com.iberdrola.respadel.dao.AdminMapper;
import com.iberdrola.respadel.dao.CentroMapper;
import com.iberdrola.respadel.dao.DiaFestivoMapper;
import com.iberdrola.respadel.dao.FranjaDiaMapper;
import com.iberdrola.respadel.dao.FranjaMapper;
import com.iberdrola.respadel.dao.InstalacionMapper;
import com.iberdrola.respadel.dao.MeapuntoMapper;
import com.iberdrola.respadel.dao.ReservaMapper;
import com.iberdrola.respadel.model.Actividad;
import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.Franja;
import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Meapunto;
import com.iberdrola.respadel.model.Reserva;
import com.iberdrola.respadel.service.ReservaService;
import com.iberdrola.respadel.util.Utils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;

/**
 * Implementación de servicios de reserva
 * 
 * @author U328075
 */
public class ReservaServiceImpl implements ReservaService {

	private Properties props;
	private DiaFestivoMapper diaFestivoMapper;
	private CentroMapper centroMapper;
	private InstalacionMapper instalacionMapper;
	private ReservaMapper reservaMapper;
	private FranjaDiaMapper franjaDiaMapper;
	private FranjaMapper franjaMapper;
	private MeapuntoMapper meapuntoMapper;
	private ActividadMapper actividadMapper;
	private AdminMapper adminMapper;

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setActividadMapper(ActividadMapper actividadMapper) {
		this.actividadMapper = actividadMapper;
	}

	public void setMeapuntoMapper(MeapuntoMapper meapuntoMapper) {
		this.meapuntoMapper = meapuntoMapper;
	}

	public void setDiaFestivoMapper(DiaFestivoMapper diaFestivoMapper) {
		this.diaFestivoMapper = diaFestivoMapper;
	}

	public void setCentroMapper(CentroMapper centroMapper) {
		this.centroMapper = centroMapper;
	}

	public void setInstalacionMapper(InstalacionMapper instalacionMapper) {
		this.instalacionMapper = instalacionMapper;
	}

	public void setReservaMapper(ReservaMapper reservaMapper) {
		this.reservaMapper = reservaMapper;
	}

	public void setFranjaDiaMapper(FranjaDiaMapper franjaDiaMapper) {
		this.franjaDiaMapper = franjaDiaMapper;
	}

	public void setFranjaMapper(FranjaMapper franjaMapper) {
		this.franjaMapper = franjaMapper;
	}

	public List<Centro> getListaCentrosPorActividad(Long idActividad) {
		return instalacionMapper.getListaCentrosPorActividad(idActividad);
	}

	public Centro getCentroById(Long idCentro) {
		return centroMapper.getCentroById(idCentro);
	}

	public Map<Long, List<Instalacion>> getMapaListaInstalaciones(List<Centro> listaCentros) {
		Map<Long, List<Instalacion>> resultados = new HashMap<Long, List<Instalacion>>();
		for (int i = 0; listaCentros != null && i < listaCentros.size(); i++) {
			List<Instalacion> instalaciones = instalacionMapper
					.getInstalacionByFkCentro(listaCentros.get(i).getIdCentro());
			resultados.put(listaCentros.get(i).getIdCentro(), instalaciones);
		}
		return resultados;
	}

	public List<Instalacion> getInstalacionesByCentro(Long idCentro) {
		return instalacionMapper.getInstalacionByFkCentro(idCentro);
	}

	public List<Instalacion> getInstalacionesByCentroYActividad(Long idCentro, Long idActividad) {
		Instalacion instalacion = new Instalacion();
		instalacion.setFkCentro(idCentro);
		instalacion.setFkActividad(idActividad);
		return instalacionMapper.getInstalacionByExample(instalacion);
	}

	public Map<String, Map<String, String>> getMapaReservas(Date diaInicio, Date diaFin) {
		return reservaMapper.getMapaReservas(diaInicio, diaFin);
	}

	public Boolean isReservaPosible(String usuario, Long idActividad) {
		Long numeroMaximoReservas = new Long(props.getProperty("NUMERO_MAXIMO_RESERVAS"));
		Calendar calendario = Calendar.getInstance();
		List<Reserva> proximasReservas = reservaMapper.getProximasReservas(calendario.getTime(), usuario, idActividad);
		if (proximasReservas.size() < numeroMaximoReservas) {
			return true;
		} else {
			return false;
		}
	}

	public List<Date> getProximosDias() {
		Long numeroDiasAMostrar = new Long(props.getProperty("NUMERO_DIAS_A_MOSTRAR"));
		List<Date> proximosDias = new ArrayList<Date>();
		Calendar calendario = Calendar.getInstance();
		for (int i = 0; i < numeroDiasAMostrar; i++) {
			proximosDias.add(calendario.getTime());
			calendario.add(Calendar.DATE, 1);
		}

		return proximosDias;
	}

	public Map<Date, List<FranjaDia>> getMapaDiasFranjasDia(List<Date> listaDias, Long idInstalacion, Long idCentro) {
		Map<Date, List<FranjaDia>> mapaDiasFranjaDia = new HashMap<Date, List<FranjaDia>>();
		for (int i = 0; i < listaDias.size(); i++) {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(listaDias.get(i));
			long festivo = 1l;
			if (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				festivo = 0l;
			} else {
				if (diaFestivoMapper.getDiaFestivoByDate(listaDias.get(i), idCentro).size() > 0) {
					festivo = 0l;
				}
			}
			Franja franja = franjaMapper.getFranjaValida(listaDias.get(i), festivo, idInstalacion);
			if (franja != null) {
				List<FranjaDia> franjasDia = franjaDiaMapper.getFranjaDiaByFranja(franja.getIdFranja());
				mapaDiasFranjaDia.put(listaDias.get(i), franjasDia);
			}

		}

		return mapaDiasFranjaDia;
	}

	public Map<Long, List<FranjaDia>> getMapaInstalacionFranjasDia(Date dia, List<Instalacion> listaInstalaciones,
			Long idCentro) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dia);
		long festivo = 1l;
		if (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			festivo = 0l;
		} else {
			if (diaFestivoMapper.getDiaFestivoByDate(dia, idCentro).size() > 0) {
				festivo = 0l;
			}
		}
		Map<Long, List<FranjaDia>> mapaInstalacionFranjasDia = new HashMap<Long, List<FranjaDia>>();
		for (int i = 0; i < listaInstalaciones.size(); i++) {
			Franja franja = franjaMapper.getFranjaValida(dia, festivo, listaInstalaciones.get(i).getIdInstalacion());
			if (franja != null) {
				List<FranjaDia> franjasDia = franjaDiaMapper.getFranjaDiaByFranja(franja.getIdFranja());
				mapaInstalacionFranjasDia.put(listaInstalaciones.get(i).getIdInstalacion(), franjasDia);
			}
		}

		return mapaInstalacionFranjasDia;
	}

	public FranjaDia getFranjaDia(Long idFranjaDia) {
		return franjaDiaMapper.getFranjaDiaById(idFranjaDia);
	}

	public void crearReserva(Reserva reserva) {
		// validamos autorización, número de reservas y horario
		if (!isReservaAutorizada(reserva.getUsuario())) {
			throw new RuntimeException("No autorizado!");
		}
		if (!esAdmin(reserva.getUsuario())) {
			if (!isHorarioEnRango(reserva.getDia(), reserva.getFkFranjaDia())) {
				throw new RuntimeException("El horario no se encuentra en el rango autorizado.");
			}
			Instalacion instalacion = getInstalacion(reserva.getFkInstalacion());
			if (!isReservaPosible(reserva.getUsuario(), instalacion.getFkActividad())) {
				throw new RuntimeException("Número de reservas excedidas!");
			}
		}

		this.reservaMapper.crearReserva(reserva);
	}

	public boolean isHorarioEnRango(Date date, Long idFranjaDia) {
		FranjaDia franjaDia = franjaDiaMapper.getFranjaDiaById(idFranjaDia);
		Time timeInicio = franjaDia.getHoraInicio();
		Time timeFin = franjaDia.getHoraFin();

		Date dateFin = getFechaRealReserva(date, timeFin);
		Date dateInicio = getFechaRealReserva(date, timeInicio);

		// podemos reservar una pista cuya fecha de fin esté en el futuro
		if (dateFin.before(new Date())) {
			return false;
		}

		// el inicio debe estar en los proximos X días (incluido hoy)
		// es decir, si es lunes, podemos reservar hasta el proximo lunes
		// calculamos la fecha/hora máxima aceptable
		Calendar calendarMaximo = Calendar.getInstance();
		calendarMaximo.set(Calendar.MINUTE, 0);
		calendarMaximo.set(Calendar.SECOND, 0);
		calendarMaximo.set(Calendar.HOUR_OF_DAY, 0);
		calendarMaximo.set(Calendar.MILLISECOND, 0);
		calendarMaximo.add(Calendar.DATE, Integer.valueOf(props.getProperty("NUMERO_DIAS_A_MOSTRAR")));
		Date fechaMaxima = calendarMaximo.getTime();

		if (dateInicio.after(fechaMaxima)) {
			return false;
		}

		return true;
	}

	public Reserva getReserva(Long idReserva) {
		return this.reservaMapper.getReservaPorId(idReserva);
	}

	public int eliminarReserva(Long idReserva, String usuario) {
		return this.reservaMapper.eliminarReserva(idReserva, usuario);
	}

	// por ahora las sacamos todas, luego filtraremos
	public List<Reserva> getHistoricoReservas(String usuario) {
		return this.reservaMapper.getReservasPorUsuario(usuario);
	}

	public Instalacion getInstalacion(Long idInstalacion) {
		return instalacionMapper.getInstalacionById(idInstalacion);
	}

	public boolean isReservaAutorizada(String usu) {
		String usuariosAutorizados = this.props.getProperty("USUARIOS_AUTORIZADOS", "");
		return Utils.wildCardListMatch(usu, usuariosAutorizados);
	}

	public List<Reserva> getReservasFiltro(Date fechaDesde, Date fechaHasta, String usuario, int step) {
		int elementsByStep = new Integer(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10")).intValue();
		int firstResult = elementsByStep * (step - 1);
		RowBounds rowBounds = new RowBounds(firstResult, elementsByStep);
		return reservaMapper.getReservasFiltro(null, fechaDesde, fechaHasta, usuario, rowBounds);
	}

	public int getCountReservasFiltro(Date fechaDesde, Date fechaHasta, String usuario) {
		return reservaMapper.getCountReservasFiltro(null, fechaDesde, fechaHasta, usuario);
	}

	public Date getFechaRealReserva(Date date, Time time) {
		Calendar dCal = Calendar.getInstance();
		dCal.setTime(date);
		Calendar tCal = Calendar.getInstance();
		tCal.setTime(time);
		dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));
		dCal.set(Calendar.MILLISECOND, tCal.get(Calendar.MILLISECOND));
		return dCal.getTime();
	}

	public Meapunto getMeapuntoPorId(Long idMeapunto) {
		return meapuntoMapper.getMeapuntoPorId(idMeapunto);
	}

	public Map<Long, Map> getProximosMeapuntoUsuario(String usuario) {
		return meapuntoMapper.getMapaProximosMeapuntoUsuario(new Date(), usuario);
	}

	public List<Map> getProximosMeapuntoNoUsuario(String usuario) {
		return meapuntoMapper.getMapaProximosMeapunto(new Date(), usuario);
	}

	public List<Actividad> getListaActividades() {
		return actividadMapper.getListaActividades();
	}

	public Actividad getActividad(Long idActividad) {
		return actividadMapper.getActividad(idActividad);
	}

	public Map<Long, Actividad> getMapaActividades() {
		List<Actividad> actividades = getListaActividades();
		Map<Long, Actividad> map = new HashMap<Long, Actividad>();
		for (Actividad actividad : actividades) {
			map.put(actividad.getIdActividad(), actividad);
		}
		return map;
	}

	public boolean esAdmin(String idUsuario) {
		return !(adminMapper.getAdmin(idUsuario) == null);
	}

}
