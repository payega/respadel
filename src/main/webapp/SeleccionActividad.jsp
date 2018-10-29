<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Selección Actividad" />
<%@include file="/includes/cabecera.jsp"%>

<tr>
	<td colspan="2" id="central">
	    <span id="subtitulo">Actividades</span>
	    <br />
	    <br />
		<table cellspacing="0" cellpadding="0" width="80%" align="center"
			class="tablacontenido">
			<thead>
				<tr>
					<th class="centradoAli">Actividad</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaActividades}" var="item">
					<tr class="centradoAli">
						<td class="centradoAli">
						<a href="<c:url value="/seleccionCentro.action">
							<c:param name="idActividad" value="${item.idActividad}" />
						</c:url>" class="linktabla">${item.nombre}</a></td>
					</tr>
				</c:forEach>
				<tr class="fin">
					<td class="centradoAli"></td>
				</tr>
			</tbody>
		</table> <br /></td>
</tr>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<%@include file="/includes/pie.jsp"%>
