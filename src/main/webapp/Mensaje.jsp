<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ubicacion" value="Mensaje" />
<%@include file="/includes/cabecera.jsp"%>

<tr>
	<td colspan="2" id="central"><span id="subtitulo">${tituto}</span> <br /> <br /> <br />
		<table cellspacing="0" cellpadding="0" width="80%" align="center"
			class="tablaformulario" border="0">
			<tr>
				<td class="notificacion">${mensaje}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="centradoAli"><a
					href="${pageContext.request.contextPath}/${origen}?idCentro=${centro.idCentro}&idActividad=${actividad.idActividad}"
					class="linkbutton">Volver</a></td>
			</tr>

		</table> <br /> <br /></td>
</tr>

<script>
	$(document).ready(function() {
	});
</script>
<%@include file="/includes/pie.jsp"%>
