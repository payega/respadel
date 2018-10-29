<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Confirmar Reserva" />
<%@include file="/includes/cabecera.jsp" %>
   
<fmt:formatDate value="${diaformateado}" pattern="EEEE, d 'de' MMMM" var="fechaFormateada"/>   
        <tr>
                <td colspan="2" id="central">
                        <span id="subtitulo">Confirmación de la reserva</span>
                        <br />
						<br />
                        <br />
                        <br />
                        <form name="formulario" action="${pageContext.request.contextPath}/reservas/reservar.action" method="GET">
                            <input type="hidden" name="dia" value="${dia}" />
                            <input type="hidden" name="origen" value="${origen}" />
                            <input type="hidden" name="idCentro" value="${centro.idCentro}" />
                            <input type="hidden" name="idFranjaDia" value="${franjaDia.idFranjaDia}" />
                            <input type="hidden" name="idInstalacion" value="${instalacion.idInstalacion}" />
                            
							<table width="80%" align="center" class="tablaformulario">
								<tbody>
                         <tr class="centradoAli">
                           <td width="50%" class="derechaAli cabeceraLateralTabla">Actividad:</td>
                           <td width="1%"></td>
                           <td width="49%"class="izquierdaAli">${actividad.nombre}</td>
                         </tr>
                         <tr class="centradoAli">
                           <td width="50%" class="derechaAli cabeceraLateralTabla">Centro:</td>
                           <td width="1%"></td>
                           <td width="49%"class="izquierdaAli">${centro.nombre}</td>
                         </tr>
                         <tr class="centradoAli">
                           <td class="derechaAli cabeceraLateralTabla">Instalacion:</td>
                           <td width="2%">&nbsp;</td>
                           <td class="izquierdaAli">${instalacion.nombre}</td>
                         </tr>
                         <tr class="centradoAli">
                           <td class="derechaAli cabeceraLateralTabla">Fecha:</td>
                           <td width="2%">&nbsp;</td>
                           <fmt:formatDate value="${diaformateado}" pattern="EEEE, d 'de' MMMM" var="fechaFormateada"/>
                           <td class="izquierdaAli">${fechaFormateada}</td>
                         </tr>
                         <tr class="centradoAli">
                           <td class="derechaAli cabeceraLateralTabla">Franja Horaria:</td>
                           <td width="2%">&nbsp;</td>
                           <fmt:formatDate value="${franjaDia.horaInicio}" pattern="HH:mm" var="horaPistaInicio"/>
                           <fmt:formatDate value="${franjaDia.horaFin}" pattern="HH:mm" var="horaPistaFin"/>
                           <c:set var="nombre" value="${horaPistaInicio}-${horaPistaFin}" />
                           <td class="izquierdaAli">${nombre}</td>
                         </tr>
                         <tr class="centradoAli">
                             <td class="derechaAli">&nbsp;</td>
                             <td width="2%">&nbsp;</td>
                             <td class="izquierdaAli">&nbsp;</td>
                         </tr>
                         <tr class="centradoAli">
                             <td class="derechaAli"><input type="submit" value="Reservar"></td>
                             <td width="2%">&nbsp;</td>
                             <td class="izquierdaAli">
                             <a href="${pageContext.request.contextPath}/${origen}?idCentro=${centro.idCentro}&idActividad=${actividad.idActividad}" class="linkbutton">Cancelar</a>
                             </td>
                         </tr>
					</tbody>
				</table>
                        </form>
                        <br />
                        
                </td>
        </tr>

        <script>    
            $(document).ready(function(){

            });
        </script>
<%@include file="/includes/pie.jsp" %>
