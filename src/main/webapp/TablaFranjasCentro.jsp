<%@include file="/includes/cabecera.jsp" %>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${siguienteDia}" pattern="yyyyMMdd" var="siguienteDiaFormateado"/>
<fmt:formatDate value="${diaAMostrar}" pattern="yyyyMMdd" var="fechaFormateada"/>
<fmt:formatDate value="${diaAMostrar}" pattern="yyyy-MM-dd" var="fechaFormateada2"/>
<fmt:formatDate value="${anteriorDia}" pattern="yyyyMMdd" var="anteriorDiaFormateado"/>

        <tr>
            <td width="40%" id="titulo">Reserva de pistas de padel</td>
            <td id="contentmigas">Usuario: ${pageContext.request.remoteUser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Estas en | <a href="${pageContext.request.contextPath}/index.action">Seleccion de Instalacion</a> | Horarios por centro</td>
        </tr>
        <tr>
            <td colspan="2" id="menu"><a href="#">Me apunto!</a> | <a href="#">Mis reservas</a></td>
        </tr>
        <tr>
            <td colspan="2" id="central">
                <span id="subtitulo">Reserva de hora</span>
                <br />
                <br />
                <table width="80%" align="center">
                    <tr>
                        <td colspan="${fn:length(listaInstalaciones)}" style="text-align: center">
                            <span id="subtitulo"> <c:if test="${anteriorDia!=null}"> <a href="${pageContext.request.contextPath}/tablaFranjasCentro.action?idCentro=${centro.idCentro}&dia=${anteriorDiaFormateado}" class="linktabla"><<&nbsp;</a></c:if> <fmt:formatDate value="${diaAMostrar}" pattern="EEEE,d 'de' MMMM"/><c:if test="${siguienteDia!=null}"> <a href="${pageContext.request.contextPath}/tablaFranjasCentro.action?idCentro=${centro.idCentro}&dia=${siguienteDiaFormateado}" class="linktabla">&nbsp;>></a></c:if></spam>
                        </td>
                    </tr>
                    <tr>
                        <c:forEach var="item" items="${listaInstalaciones}">
                            <td style="vertical-align: top">
                                <table cellspacing="0" cellpadding="0" width="80%" align="center" class="tablacontenido" border="0">
                                    <thead> 
                                        <tr>
                                            <th class="centradoAli">${item.nombre}</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item2" items="${mapaInstalacionFranjasDia[item.idInstalacion]}">
                                        <tr class="centradoAli">
                                            <td class="centradoAli">
                                                <c:set var="clave" value="${item2.idFranjaDia}_${item.idInstalacion}_${fechaFormateada2}" />
                                                <c:choose>
                                                    <c:when test="${mapaReservas[clave].ESTADO=='O'}">
                                                        <a href="#" class="linktabla_reserva_abierta">${item2.nombre}</a>
                                                    </c:when>
                                                    <c:when test="${mapaReservas[clave].USUARIO==pageContext.request.remoteUser}">
                                                        <a href="${pageContext.request.contextPath}/reservas/mostrarEliminar.action?idReserva=${mapaReservas[clave].ID_RESERVA}&idCentro=${item.fkCentro}&dia=${fechaFormateada}&idFranjaDia=${item2.idFranjaDia}&idInstalacion=${item.idInstalacion}" class="linktabla_reserva_propia">${item2.nombre}</a>
                                                    </c:when>
                                                    <c:when test="${mapaReservas[clave]!=null}">
                                                        <a href="#" class="linktabla_reserva">${item2.nombre}</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/reservas/mostrar.action?idCentro=${item.fkCentro}&dia=${fechaFormateada}&idFranjaDia=${item2.idFranjaDia}&idInstalacion=${item.idInstalacion}&origen=tablaFranjasCentro.action" class="linktabla_libre">${item2.nombre}</a>
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
           </td>
      </tr>
      	<tr><td>
		<br/>
		<%@include file="/includes/leyenda.jsp" %>
		<br/>
		</td>
		</tr>
      
      <script>    
            $(document).ready(function(){
            });
      </script>
<%@include file="/includes/pie.jsp" %>