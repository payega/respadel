package com.iberdrola.respadel.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import com.iberdrola.respadel.dao.AdminMapper;
import com.iberdrola.respadel.dao.CentroMapper;
import com.iberdrola.respadel.dao.DiaFestivoMapper;
import com.iberdrola.respadel.dao.FranjaDiaMapper;
import com.iberdrola.respadel.dao.FranjaMapper;
import com.iberdrola.respadel.dao.InstalacionMapper;
import com.iberdrola.respadel.dao.MeapuntoMapper;
import com.iberdrola.respadel.dao.ReservaMapper;
import com.iberdrola.respadel.model.Centro;
import com.iberdrola.respadel.model.DiaFestivo;
import com.iberdrola.respadel.model.Franja;
import com.iberdrola.respadel.model.FranjaDia;
import com.iberdrola.respadel.model.Instalacion;
import com.iberdrola.respadel.model.Reserva;
import com.iberdrola.respadel.service.AdministracionService;

/**
 * Implementación de servicios de administración
 * 
 * @author U328075
 *
 */
public class AdministracionServiceImpl implements AdministracionService {

	private Properties props;
	private DiaFestivoMapper diaFestivoMapper;
	private CentroMapper centroMapper;
	private InstalacionMapper instalacionMapper;
	private ReservaMapper reservaMapper;
	private FranjaDiaMapper franjaDiaMapper;
	private FranjaMapper franjaMapper;
	private MeapuntoMapper meapuntoMapper;
	private AdminMapper adminMapper;

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	public MeapuntoMapper getMeapuntoMapper() {
		return meapuntoMapper;
	}

	public void setMeapuntoMapper(MeapuntoMapper meapuntoMapper) {
		this.meapuntoMapper = meapuntoMapper;
	}

	public void setProps(Properties props) {
		this.props = props;
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

	public List<Reserva> getReservasFiltro(Long fkInstalacion, Date fechaDesde, Date fechaHasta, String usuario,
			int step) {
		int elementsByStep = new Integer(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10")).intValue();
		int firstResult = elementsByStep * (step - 1);
		RowBounds rowBounds = new RowBounds(firstResult, elementsByStep);
		return reservaMapper.getReservasFiltro(fkInstalacion, fechaDesde, fechaHasta, usuario, rowBounds);
	}

	public int getCountReservasFiltro(Long fkInstalacion, Date fechaDesde, Date fechaHasta, String usuario) {
		return reservaMapper.getCountReservasFiltro(fkInstalacion, fechaDesde, fechaHasta, usuario);
	}

	public void crearReserva(Reserva reserva) {
		this.reservaMapper.crearReserva(reserva);
	}

	@Transactional
	public Long crearReservaPeriodica(Reserva reserva, Date diaFinal, Long tipoReserva) {
		Long reservasCreadas = 0l;
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(reserva.getDia());
		inicio.set(Calendar.MINUTE, 0);
		inicio.set(Calendar.SECOND, 0);
		inicio.set(Calendar.HOUR_OF_DAY, 0);
		inicio.set(Calendar.MILLISECOND, 0);
		Integer diaSemanaInicial = inicio.get(Calendar.DAY_OF_WEEK);
		Calendar fin = Calendar.getInstance();
		fin.setTime(diaFinal);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);
		fin.set(Calendar.HOUR_OF_DAY, 0);
		fin.set(Calendar.MILLISECOND, 0);
		Boolean diaInicioFestivo = isFestivoByInstalacion(reserva.getDia(), reserva.getFkInstalacion());
		for (; !inicio.after(fin); inicio.add(Calendar.DATE, 1)) {
			Date dia = inicio.getTime();
			Boolean diaActualFestivo = isFestivoByInstalacion(dia, reserva.getFkInstalacion());
			if (diaInicioFestivo == diaActualFestivo) {
				if (tipoReserva == 2 || inicio.get(Calendar.DAY_OF_WEEK) == diaSemanaInicial) {
					Reserva resExample = new Reserva();
					resExample.setDia(dia);
					resExample.setFkFranjaDia(reserva.getFkFranjaDia());
					resExample.setFkInstalacion(reserva.getFkInstalacion());
					resExample.setFecHora(null);
					if (reservaMapper.getByExample(resExample).isEmpty()) {
						reserva.setDia(dia);
						this.reservaMapper.crearReserva(reserva);
						reservasCreadas++;
					}
				}
			}
		}
		return reservasCreadas;
	}

	public boolean isFestivoByCentro(Date dia, Long idCentro) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dia);
		if (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			if (diaFestivoMapper.getDiaFestivoByDate(dia, idCentro).size() > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isFestivoByInstalacion(Date dia, Long idCentro) {
		Instalacion instalacion = instalacionMapper.getInstalacionById(idCentro);
		return isFestivoByCentro(dia, instalacion.getFkCentro());

	}

	public void crearDiaFestivo(DiaFestivo diaFestivo) {
		this.diaFestivoMapper.crearDiaFestivo(diaFestivo);
	}

	public void borrarReserva(Long idReserva, String usuario) {
		this.reservaMapper.eliminarReserva(idReserva, usuario);
	}

	public Map<Long, FranjaDia> getMapaFranjas() {
		return franjaDiaMapper.getMapaFranjas();
	}

	public Map<Long, Centro> getMapaCentros() {
		return centroMapper.getMapaCentros();
	}

	public List<Instalacion> getInstalacionByExample(Instalacion instalacion) {
		return instalacionMapper.getInstalacionByExample(instalacion);
	}

	public Map<Long, Instalacion> getMapaInstalaciones() {
		return instalacionMapper.getMapaInstalaciones();
	}

	public List<FranjaDia> getMapaDiasFranjasDia(Date dia, Long idInstalacion) {
		Instalacion instalacion = instalacionMapper.getInstalacionById(idInstalacion);
		List<FranjaDia> franjasDia = null;
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dia);
		long festivo = isFestivoByCentro(dia, instalacion.getFkCentro()) ? 1l : 0l;
		Franja franja = franjaMapper.getFranjaValida(dia, festivo, idInstalacion);
		if (franja != null) {
			franjasDia = franjaDiaMapper.getFranjaDiaByFranja(franja.getIdFranja());
			Map<String, Map<String, String>> mapaReservas = reservaMapper.getMapaReservasDia(dia);
			for (int i = franjasDia.size() - 1; i >= 0; i--) {
				if (mapaReservas.get(franjasDia.get(i).getIdFranjaDia() + "_" + idInstalacion) != null) {
					franjasDia.remove(i);
				}
			}
		}
		return franjasDia;
	}

	public List<DiaFestivo> getDiaFestivoByFiltro(Date fechaDesde, Date fechaHasta, Long idCentro, int step) {
		int elementsByStep = new Integer(props.getProperty("ELEMENTOS_PASO_PAGINACION", "10")).intValue();
		int firstResult = elementsByStep * (step - 1);
		RowBounds rowBounds = new RowBounds(firstResult, elementsByStep);
		return diaFestivoMapper.getDiaFestivoByFiltro(fechaDesde, fechaHasta, idCentro, rowBounds);
	}

	public int getCountFestivoByFiltro(Date fechaDesde, Date fechaHasta, Long idCentro) {
		return diaFestivoMapper.getCountFestivoByFiltro(fechaDesde, fechaHasta, idCentro);
	}

	public int eliminarDiaFestivo(Long idDiaFestivo) {
		return diaFestivoMapper.eliminarDiaFestivo(idDiaFestivo);
	}

	public List<Centro> getListaCentros() {
		return centroMapper.getListaCentros();
	}
}
