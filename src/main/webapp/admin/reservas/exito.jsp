<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/admin/includes/cabecera.inc" %>


    <div align="right" class="subtitulo">Gestión de Reservas</div>
    <br />
    <fieldset class="fieldset">
        <legend>Datos generales de la reserva</legend>
        
        <table cellspacing="2" cellpadding="2" width="90%" border="0" bgcolor="#ffffff" class="texto" border="0">
            <tr>
                <td>
                    <table border="0" width="100%" class="texto">
                       <tr>
                            <td width="100%%" class="texto_negrita centradoAli">${param.numeroReservasCreadas} reserva creada con exito</td>
                       </tr>
                       <tr align="right">
                            <td><a href="${pageContext.request.contextPath}/admin/reservas/mostrar.action">Volver</a></td>
                       </tr>
                    </table>
                </td>
            </tr>
        </table>
    </fieldset>
   
    
    <script>
    
    $(document).ready(function(){
        $( "#dia" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});  
    });    
   
       document.formweb.idInstalacion.value='${reserva.fkInstalacion}';
    
</script>
<%@ include file="/admin/includes/pie.inc" %>