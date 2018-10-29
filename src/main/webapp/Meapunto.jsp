<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Me Apunto!" />
<%@include file="/includes/cabecera.jsp" %>

<tr>
	<td colspan="2" id="central">
	<span id="subtitulo">Partidos Me Apunto!<span> <br /> <br /> 

                    <table align="center" class="tablacontenido" border="0">
                            <thead>
                                <tr>
                                    <th>Pista</th>
                                    <th>Fecha reserva</th>
                                    <th>Fecha cierre</th>
                                    <th>Notas</th>
                                    <th>Nivel</th>
                                    <th>Inscritos</th>
                                    <th>Accion</th>
                                </tr>
                            </thead>
                            <tbody>
                                    <c:forEach items="${proximosMeapunto}" var="item1"
                                            varStatus="status1">
                                        <tr>
                                            <td class="centradoAli">
                                                ${item1.NOMBREINSTALACION} en ${item1.NOMBRECENTRO}
                                            </td>
                                            <td class="centradoAli">
                                                <fmt:formatDate pattern="EEEEEEEE, dd/MM/yyyy" value="${item1.FECHARESERVA}" />
                                                &nbsp;
                                                <fmt:formatDate pattern="hh:mm" value="${item1.RESERVAHORAINICIO}" /> - <fmt:formatDate pattern="hh:mm" value="${item1.RESERVAHORAFIN}" />
                                            </td>
                                            
                                            <td class="centradoAli">
                                                <fmt:formatDate pattern="EEEEEEEE, dd/MM/yyyy" value="${item1.DIALIMITE}" />
                                                <fmt:formatDate pattern="hh:mm" value="${item1.HORALIMITE}" />
                                            </td>
                                            <td class="centradoAli">${item1.NOTAS}</td>
                                            <td class="centradoAli">${item1.NIVELMINIMO} - ${item1.NIVELMAXIMO}</td> 
                                            <td class="centradoAli">${item1.INSCRITOS}</td>
                                            <td class="centradoAli">
                                                <c:choose>
                                                    <c:when test="${proximosMeapuntoUsuario[item1.IDMEAPUNTO]!=null}">
                                                    Ya estas apuntado
                                                    </c:when>
                                                    <c:otherwise>
                                                    Apuntarse
                                                    </c:otherwise>
                                                </c:choose>
                                                
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
              
           </td>
      </tr>
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