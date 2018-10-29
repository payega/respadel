<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/admin/includes/cabecera.inc" %>
<script>
    /*function buscar(){
        var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;  
        var errorMessage = 'Formato de fecha incorrecto';  
        if ((document.formweb.fechaDesde.value.match(RegExPattern)) || (document.formweb.fechaDesde.value=='')) {  
           document.formweb.method.value='buscar';
           document.formweb.step.value='1';
           document.formweb.submit();
        } else {  
            alert(errorMessage);  
            document.formweb.fechaDesde.focus();  
        }   
    }*/
    
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
    
    function eliminar(usuario,idReserva){
       if(confirm('¿Estas seguro de querer borrar la reserva?')){
           document.formweb.usuarioEliminar.value=usuario;
           document.formweb.idReserva.value=idReserva;
           document.formweb.action='${pageContext.request.contextPath}/admin/reservas/eliminar.action';
           document.formweb.submit();
       }
    }
    
    function limpiarCampos(){
        document.formweb.usuario.value='';
        document.formweb.fechaDesde.value='';
        document.formweb.fechaHasta.value='';
        document.formweb.idInstalacion.value='';
    }
    

</script>

<form name="formweb" id="formweb" method="GET" action="${pageContext.request.contextPath}/admin/reservas/buscar.action">
    <div align="right" class="subtitulo">Gestión de Reservas</div>
    <br />
    <fieldset class="fieldset">
        <legend>Filtro</legend>
        <table cellspacing="2" cellpadding="2" width="90%" border="0" bgcolor="#ffffff" class="texto">
            <tr>
                <td>Usuario</td>
                <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="usuario" value="${usuario}"></td>
                <td>Instalacion</td>
                <td class="izquierdaAli">
                    <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="idInstalacion" value="${idInstalacion}">
                        <option value="">--Seleccionar--</option>
                        <c:forEach var="item1" items="${listaInstalacion}">
                            <option value="${item1.idInstalacion}">${mapaCentros[item1.fkCentro].nombre}: ${item1.nombre}</option>
                        </c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td>Fecha desde</td>
                <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="fechaDesde" id="fechaDesde" value="${fechaDesde}"></td>
                <td>Fecha hasta</td>
                <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="fechaHasta" id="fechaHasta" value="${fechaHasta}" readonly="true"></td>
           </tr>
           <tr align="right">
                <td colspan="4"><a href="javascript:buscar();" class="link">Buscar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/reservas/nueva.action" class="link">Crear reserva</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:limpiarCampos();" class="link">Limpiar campos</a></td>
           </tr>
        </table>
    </fieldset>
    <c:if test="${numeroReservas>0}">
    <hr>
    <fieldset>
        <legend>Listado de reservas</legend>
        <table border="0" width="90%" border="0">
            <tr>
                <td class="entradilla" align="right">
                <strong>${numeroReservas}</strong> reservas encontradas. Se muestran las reservas de la <strong>${((step-1)*elementosPaso)+1}</strong> al <strong><c:choose><c:when test="${(step*elementosPaso)<numeroReservas}">${step*elementosPaso}</c:when><c:otherwise>${numeroReservas}</c:otherwise></c:choose></strong>
                </td>
            </tr>
        </table>
        <br>
            <table cellspacing="1" cellpadding="1" width="90%" border="0" bgcolor="#000000">
            <tr>
               <td class="tituloTabla" width="20%">Usuario</td>
               <td class="tituloTabla" width="25%">Instalacion</td>
               <td class="tituloTabla" width="25%">Fecha</td>
               <td class="tituloTabla" width="15%">Franja horaria</td>
               <td class="tituloTabla" width="15%">Accion</td>
            </tr>
            <c:forEach items="${listadoReservas}" var="item1" varStatus="status1">
            <tr>
               <td class="textoTabla">${item1.usuario}</td>
               <td class="textoTabla" valign="top">${mapaCentros[mapaInstalaciones[item1.fkInstalacion].fkCentro].nombre}: ${mapaInstalaciones[item1.fkInstalacion].nombre}</td>
               <td class="textoTabla" valign="top"><fmt:formatDate pattern="EEEEEEEE  dd/MM/yyyy" value="${item1.dia}"/></td>
               <fmt:formatDate value="${mapaFranjas[item1.fkFranjaDia].horaInicio}" pattern="HH:mm" var="horaPistaInicio" /> 
               <fmt:formatDate value="${mapaFranjas[item1.fkFranjaDia].horaFin}" pattern="HH:mm" var="horaPistaFin" />
               <td class="textoTabla" valign="top">${horaPistaInicio}-${horaPistaFin}</td>
               <td class="textoTabla" valign="top"><a href="javascript:eliminar('${item1.usuario}','${item1.idReserva}');"><img src="${pageContext.request.contextPath}/admin/images/borrar.gif" border="0" /></td>
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
                <c:forEach begin="1" end="${numeroReservas}" step="${elementosPaso}" varStatus="status1">
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
                <c:if test="${step*elementosPaso<numeroReservas}">
                    <a href="javascript:irAPaso(${step+1})" class="link">>></a>
                </c:if>

            </td>
          </tr>
          </table>
           </fieldset>
          </c:if>
    <input type="hidden" name="step" value="${step}">
    <input type="hidden" name="usuarioEliminar" value="">
    <input type="hidden" name="idReserva" value="">
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
       
    });    
   
       document.formweb.idInstalacion.value='${idInstalacion}';
    
</script>
<%@ include file="/admin/includes/pie.inc" %>