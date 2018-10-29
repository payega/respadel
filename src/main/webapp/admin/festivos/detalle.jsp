<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/admin/includes/cabecera.inc" %>
<script>


function guardar(){
    if(document.formweb.dia.value!=''){
        document.formweb.submit();
    }else{
        alert('Ha de rellenar todos los campos');
    }
}

</script>

<form name="formweb" method="POST" action="${pageContext.request.contextPath}/admin/festivos/guardar.action">
    <div align="right" class="subtitulo">Gestión de Días Festivos</div>
    <br />
    <fieldset class="fieldset">
        <legend>Datos generales del día festívo</legend>
        
        <table cellspacing="2" cellpadding="2" width="90%" border="0" bgcolor="#ffffff" class="texto" border="0">
            <tr>
                <td>
                    <table border="0" width="100%" class="texto">
                        
                        <tr>
                            <td width="20%" class="texto_negrita izquierdaAli">Dia</td>
                            <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="dia" id="dia" value=""></td>
                        </tr>
                       <tr align="right">
                       		<td width="20%" class="texto_negrita izquierdaAli">Centro</td>
                       		<td class="izquierdaAli">
	                        <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="idCentro">
		                        <option value="">--Seleccionar--</option>
		                        <c:forEach var="item1" items="${listaCentros}">
		                            <option value="${item1.idCentro}">${item1.nombre}</option>
		                        </c:forEach>
		                    </select>
		                    </td>
	                    </tr>
                         <tr align="right">
                            <td colspan="2"><a href="javascript:guardar();" class="link">Guardar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/reservas/mostrar.action">Volver</a></td>
                        </tr>
                        
                    </table>
                </td>
            </tr>
        </table>
    </fieldset>
    
    <input type="hidden" name="method" value="guardar">

</form>
    <script>
    
    $(document).ready(function(){
        $( "#dia" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});  
    });    
   
    
    
</script>
<%@ include file="/admin/includes/pie.inc" %>