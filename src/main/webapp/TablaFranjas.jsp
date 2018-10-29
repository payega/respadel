<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Selección Instalación" />
<%@include file="/includes/cabecera.jsp"%>

<tr>
	<td colspan="2" id="central">
		<span id="subtitulo">Instalaciones de ${actividad.nombre} en ${centro.nombre}</span> 
		<br />
		<br /> 
		<c:forEach var="mapaInstalacion" items="${instalaciones}">
			<c:set var="instalacion" value="${mapaInstalacion['instalacion']}" />
			<c:set var="mapaDiasFranjasDia" value="${mapaInstalacion['mapaDiasFranjasDia']}" />
			<c:set var="mapaPasado" value="${mapaInstalacion['mapaPasado']}" />
			<table width="80%" align="center" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="${fn:length(proximosDias)}" style="text-align: center">
						<span id="subtitulo">${instalacion.nombre}</span>
					</td>
				</tr>
				<tr>
					<c:forEach var="item" items="${proximosDias}">
						<td style="vertical-align: top">
							<table cellspacing="0" cellpadding="0" width="100%"
								align="center" class="tablacontenido" border="0">
								<thead>
									<tr>
										<th class="centradoAli" nowrap>
											<fmt:formatDate pattern="EEE dd 'de' MMM" value="${item}" />
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item2" items="${mapaDiasFranjasDia[item]}">
										<tr class="centradoAli">
											<td class="centradoAli" nowrap>
												<fmt:formatDate value="${item}" pattern="yyyy-MM-dd" var="fechaFormateada2" />
												<fmt:formatDate value="${item}" pattern="yyyyMMdd" var="fechaFormateada" /> 
												<c:set var="clave" value="${item2.idFranjaDia}_${instalacion.idInstalacion}_${fechaFormateada2}" />
												<fmt:formatDate value="${item2.horaInicio}" pattern="HH:mm" var="horaPistaInicio" /> 
												<fmt:formatDate value="${item2.horaFin}" pattern="HH:mm" var="horaPistaFin" />
												<c:set var="nombre" value="${horaPistaInicio}-${horaPistaFin}" />
												<c:choose>
													<c:when test="${mapaPasado[clave]!=null}">
														<font class="linktabla_pasado">${nombre}</font>														
													</c:when>
													<c:otherwise>
													<c:choose>												
														<c:when test="${mapaReservas[clave].estado=='O'}">
															<a href="#" class="linktabla_reserva_abierta">${nombre}</a>
														</c:when>
														<c:when test="${mapaReservas[clave].usuario==pageContext.request.remoteUser}">
															<a href="${pageContext.request.contextPath}/reservas/mostrarEliminar.action?idReserva=${mapaReservas[clave].ID_RESERVA}&amp;origen=tablaFranjas.action"
																class="linktabla_reserva_propia">${nombre}</a>
														</c:when>
														<c:when test="${mapaReservas[clave]!=null}">
															<a href="#" class="linktabla_reserva">${nombre}</a>
														</c:when>													
														<c:otherwise>
															<a href="${pageContext.request.contextPath}/reservas/mostrar.action?dia=${fechaFormateada}&amp;idFranjaDia=${item2.idFranjaDia}&amp;idInstalacion=${instalacion.idInstalacion}&amp;origen=tablaFranjas.action"
																class="linktabla_libre">${nombre}</a>
														</c:otherwise>
													</c:choose>
													</c:otherwise>
												</c:choose>												
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</c:forEach>
				</tr>
			</table>
			<br />
			<br />

		</c:forEach></td>
</tr>
<tr>
	<td><br /> <%@include file="/includes/leyenda.jsp"%>
		<br /></td>
</tr>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<%@include file="/includes/pie.jsp"%>