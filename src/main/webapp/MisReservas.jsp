<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Mis Reservas" />
<%@include file="/includes/cabecera.jsp"%>

<tr>
	<td colspan="2" id="central"><span id="subtitulo">
	Mis Reservas</span> <br /> <br /> 
	<script>
				function buscar() {
					document.formweb.method.value = 'buscar';
					document.formweb.step.value = '1';
					document.formweb.submit();
				}

				function irAPaso(paso) {
					document.formweb.step.value = paso;
					document.formweb.method.value = 'buscar';
					document.formweb.submit();
				}

				function eliminar(usuario, idReserva) {
					if (confirm('¿Estas seguro de querer borrar la reserva?')) {
						document.formweb.usuarioEliminar.value = usuario;
						document.formweb.idReserva.value = idReserva;
						document.formweb.action = '${pageContext.request.contextPath}/admin/reservas/eliminar.action';
						document.formweb.submit();
					}
				}

				function limpiarCampos() {
					document.formweb.usuario.value = '';
					document.formweb.fechaDesde.value = '';
					document.formweb.fechaHasta.value = '';
					document.formweb.idInstalacion.value = '';
				}
			</script>

		<form name="formweb" id="formweb" method="GET"
			action="${pageContext.request.contextPath}/misReservas.action">
			<br />
			
			<table cellspacing="0" cellpadding="0" width="80%" align="center" class="tablaformulario" border="0">
			<tbody>
              <tr class="centradoAli">
                <td class="derechaAli cabeceraLateralTabla">Fecha desde:</td>
                <td class="izquierdaAli">&nbsp;</td>
                <td class="izquierdaAli">
                <input onFocus="this.className='campos_sel';"
							onBlur="this.className='campos';" name="fechaDesde"
							id="fechaDesde" value="${fechaDesde}"></td>
                <td class="derechaAli cabeceraLateralTabla">Fecha hasta:</td>
                <td class="izquierdaAli">&nbsp;</td>
                <td class="izquierdaAli">
                <input onFocus="this.className='campos_sel';"
							onBlur="this.className='campos';" name="fechaHasta"
							id="fechaHasta" value="${fechaHasta}"></td>
                <td class="derechaAli"><input type="submit" value="Buscar"></td>
                <td class="izquierdaAli">&nbsp;</td>
              </tr>
              </tbody>
              </table>
				<br>
					<c:if test="${numeroReservas > 0}">
					<table width="100%" border="0">
						<tr>
							<td class="linktabla" align="right"><strong>${numeroReservas}</strong>
								reservas encontradas. Se muestran las reservas de la <strong>${((step-1)*elementosPaso)+1}</strong>
								al <strong><c:choose>
										<c:when test="${(step*elementosPaso)<numeroReservas}">${step*elementosPaso}</c:when>
										<c:otherwise>${numeroReservas}</c:otherwise>
									</c:choose></strong></td>
						</tr>
					</table>
					</c:if>
					<br>
					<table width="100%" border="0" class="tablacontenido">
						<thead>
							<tr>
								<th>Fecha</th>
								<th>Actividad</th>
								<th>Centro</th>
								<th>Instalacion</th>
								<th>Fecha reservada</th>
								<th>Hora reservada</th>
								<th>Eliminar?</th>
							</tr>
						</thead>
						<tbody>							
							<c:if test="${empty listadoReservas}">
								<tr>
									<td colspan="7" class="centradoAli">No se han encontrado datos</td>
								</tr>
							</c:if>
							<c:forEach items="${listadoReservas}" var="item1" varStatus="status1">
								<tr>
									<td class="centradoAli"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
											value="${item1.fecHora}" /></td>
									<td class="centradoAli">${mapaActividades[mapaInstalaciones[item1.fkInstalacion].fkActividad].nombre}</td>
									<td class="centradoAli">${mapaCentros[mapaInstalaciones[item1.fkInstalacion].fkCentro].nombre}</td>
									<td class="centradoAli">${mapaInstalaciones[item1.fkInstalacion].nombre}</td>
									<td><fmt:formatDate pattern="EEEEEEEE, dd/MM/yyyy" value="${item1.dia}" /></td>
									<fmt:formatDate value="${mapaFranjas[item1.fkFranjaDia].horaInicio}" pattern="HH:mm" var="horaPistaInicio" />
									<fmt:formatDate value="${mapaFranjas[item1.fkFranjaDia].horaFin}" pattern="HH:mm" var="horaPistaFin" />
									<c:set var="nombre" value="${horaPistaInicio}-${horaPistaFin}" />
									<td class="centradoAli">${nombre}</td>
									<td class="centradoAli"> 
									    <fmt:formatDate value="${item1.dia}" pattern="yyyyMMdd" var="fechaFormateada" />
									<c:if test="${eliminables[item1.idReserva]}">
											<a href="${pageContext.request.contextPath}/reservas/mostrarEliminar.action?idReserva=${item1.idReserva}&amp;idCentro=${mapaCentros[mapaInstalaciones[item1.fkInstalacion].fkCentro].idCentro}&amp;dia=${fechaFormateada}&amp;idFranjaDia=${item1.fkFranjaDia}&amp;idInstalacion=${item1.fkInstalacion}&amp;origen=misReservas.action">
											<img src="${pageContext.request.contextPath}/admin/images/borrar.gif"
												border="0" /></a>								
										</c:if>
									</td>
								</tr>
							</c:forEach>
							<tr class="fin">
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
								<td class="centradoAli"></td>
							</tr>
						</tbody>
					</table>

					<table width="80%" align="center">
						<tr>
							<td align="center"><c:if test="${step>1}">
									<a href="javascript:irAPaso(${step-1})" class="linkverde"><<</a>
								</c:if> <c:set var="stepNumber" value="1" /> <c:forEach begin="1"
									end="${numeroReservas}" step="${elementosPaso}"
									varStatus="status1">
									<c:choose>
										<c:when test="${step==stepNumber}">
                        &nbsp;<a
												href="javascript:irAPaso(${stepNumber})"
												class="linkverdegrande">${stepNumber}</a>&nbsp;
                        </c:when>
						<c:otherwise>
                        &nbsp;<a href="javascript:irAPaso(${stepNumber})" class="linkverde">${stepNumber}</a>&nbsp;
                        </c:otherwise>
						</c:choose>
							<c:set var="stepNumber" value="${stepNumber+1}" />
								</c:forEach> <c:if test="${step*elementosPaso<numeroReservas}">
									<a href="javascript:irAPaso(${step+1})" class="linkverde">>></a>
								</c:if></td>
						</tr>
					</table>
			<input type="hidden" name="step" value="${step}"> 
			<input type="hidden" name="usuarioEliminar" value=""> 
			<input type="hidden" name="idReserva" value="">
			</form> 
			<script>
			$(document).ready(
					function() {
						$("#fechaDesde").datepicker(
								{
									inline : true,
									dateFormat : 'dd/mm/yy',
									monthNames : [ 'Enero', 'Febrero', 'Marzo',
											'Abril', 'Mayo', 'Junio', 'Julio',
											'Agosto', 'Septiembre', 'Octubre',
											'Noviembre', 'Diciembre' ]
								});

						$("#fechaHasta").datepicker(
								{
									inline : true,
									dateFormat : 'dd/mm/yy',
									monthNames : [ 'Enero', 'Febrero', 'Marzo',
											'Abril', 'Mayo', 'Junio', 'Julio',
											'Agosto', 'Septiembre', 'Octubre',
											'Noviembre', 'Diciembre' ]
								});

					});

			document.formweb.idInstalacion.value = '${idInstalacion}';
		</script></td>
</tr>
<%@include file="/includes/pie.jsp"%>