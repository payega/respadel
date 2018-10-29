package com.iberdrola.respadel.service;

import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.iberdrola.respadel.dao.AdminMapper;
import com.iberdrola.respadel.service.impl.ReservaServiceImpl;

/**
 * Test para la clase de servicios de reservas
 * 
 * @author u247429
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ReservaServiceTest {

	@Mock
	private AdminMapper adminMapper;

	@InjectMocks
	private ReservaServiceImpl reservaServiceImpl;

	@Test
	public void shouldCallEsAdmin() {

		// when
		when(adminMapper.getAdmin("1")).thenReturn("1");
		boolean r = reservaServiceImpl.esAdmin("1");

		// then
		Assert.assertEquals(r, true);
	}
	
	@Test
	public void  shouldCalculateFechaRealReserva() {
	
		Date d = new Date(0);
		Time t = new Time(0);
		Date d2 = reservaServiceImpl.getFechaRealReserva(d, t);
		
		Assert.assertEquals(d, d2);
	}

}
