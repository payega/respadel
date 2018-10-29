<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/admin/includes/cabecera.inc" %>
<script>
    
    function buscar(){
       document.formweb.method.value='buscar';
       document.formweb.step.value='1';
       document.formweb.submit();   
    }
    
    function irAPaso(paso){
       document.formweb.step.value=paso;
       document.formweb.method.value='buscar';
       document.formweb.submit();
    }
    
    function eliminar(idDiaFestivo){
       if(confirm('¿Estas seguro de querer eliminar el día festivo?')){
           document.formweb.idDiaFestivo.value=idDiaFestivo;
           document.formweb.action='${pageContext.request.contextPath}/admin/festivos/eliminar.action';
           document.formweb.submit();
       }
    }
    
    function limpiarCampos(){
        document.formweb.fechaDesde.value='';
        document.formweb.fechaHasta.value='';
   }
    

</script>

<form name="formweb" id="formweb" method="GET" action="${pageContext.request.contextPath}/admin/festivos/buscar.action">
    <div align="right" class="subtitulo">Gestión de días festivos</div>
    <br />
    <fieldset class="fieldset">
        <legend>Filtro</legend>
        <table cellspacing="2" cellpadding="2" width="90%" border="0" bgcolor="#ffffff" class="texto">
           <tr>
                <td>Fecha desde</td>
                <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="fechaDesde" id="fechaDesde" value="${fechaDesde}"></td>
                <td>Fecha hasta</td>
                <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="fechaHasta" id="fechaHasta" value="${fechaHasta}" readonly="true"></td>
           </tr>
            <tr>
                <td>Instalacion</td>
                <td colspan="3" class="izquierdaAli">
                    <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="idCentro" value="${idCentro}">
                        <option value="">--Seleccionar--</option>
                        <c:forEach var="item1" items="${listaCentros}">
                            <option value="${item1.idCentro}">${item1.nombre}</option>
                        </c:forEach>
                    </select>
                </td>
           </tr>
           <tr align="right">
                <td colspan="4"><a href="javascript:buscar();" class="link">Buscar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/festivos/nueva.action" class="link">Crear día festivo</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:limpiarCampos();" class="link">Limpiar campos</a></td>
           </tr>
        </table>
    </fieldset>
    <c:if test="${numeroFestivos>0}">
    <hr>
    <fieldset>
        <legend>Listado de reservas</legend>
        <table border="0" width="90%" border="0">
            <tr>
                <td class="entradilla" align="right">
                <strong>${numeroFestivos}</strong> reservas encontradas. Se muestran las reservas de la <strong>${((step-1)*elementosPaso)+1}</strong> al <strong><c:choose><c:when test="${(step*elementosPaso)<numeroFestivos}">${step*elementosPaso}</c:when><c:otherwise>${numeroFestivos}</c:otherwise></c:choose></strong>
                </td>
            </tr>
        </table>
        <br>
            <table cellspacing="1" cellpadding="1" width="40%" border="0" bgcolor="#000000">
            <tr>
               <td class="tituloTabla" width="45%">Dia</td>
               <td class="tituloTabla" width="80%">Centro</td>
               <td class="tituloTabla" width="15%">Accion</td>
            </tr>
            <c:forEach items="${listadoFestivos}" var="item1" varStatus="status1">
            <tr>
               <td class="textoTabla" valign="top"><fmt:formatDate pattern="EEEEEEEE  dd/MM/yyyy" value="${item1.dia}"/></td>
               <td class="textoTabla" valign="top">${mapaCentros[item1.fkCentro].nombre}</td>
               <td class="textoTabla" valign="top"><a href="javascript:eliminar('${item1.idDiaFestivo}');"><img src="${pageContext.request.contextPath}/admin/images/borrar.gif" border="0" /></td>
            </tr>
            </c:forEach>
        </table>
   
           <table>
            <tr>
            <td align="center">
            <c:if test="${step>1}">
                    <a href="javascript:irAPaso(${step-1})" class="link"><<</a>
                </c:if>
                <c:set var="stepNumber" value="1" />
                <c:forEach begin="1" end="${numeroFestivos}" step="${elementosPaso}" varStatus="status1">
                    <c:choose>
                        <c:when test="${step==stepNumber}">
                        &nbsp;<a href="javascript:irAPaso(${stepNumber})" class="linkGrandeGranate">${stepNumber}</a>&nbsp;
                        </c:when>
                        <c:otherwise>
                        &nbsp;<a href="javascript:irAPaso(${stepNumber})" class="link">${stepNumber}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                    <c:set var="stepNumber" value="${stepNumber+1}" />
                </c:forEach>
                <c:if test="${step*elementosPaso<numeroFestivos}">
                    <a href="javascript:irAPaso(${step+1})" class="link">>></a>
                </c:if>

            </td>
          </tr>
          </table>
           </fieldset>
          </c:if>
    <input type="hidden" name="step" value="${step}">
    <input type="hidden" name="idDiaFestivo" value="">
 </form>
<script>
    
    $(document).ready(function(){
        $( "#fechaDesde" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});
        
        $( "#fechaHasta" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});
       
        document.formweb.idCentro.value='${idCentro}';
    });    
  
    
</script>
<%@ include file="/admin/includes/pie.inc" %>