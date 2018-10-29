<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/admin/includes/cabecera.inc" %>
<script>


function guardar(){
    if(document.formweb.usuario.value!='' && document.formweb.idFranjaDia.value!='' ){
        if(document.formweb.reservaPeriodica.checked && document.formweb.ultimodia.value==''){
           alert('Ha de rellenar los campos de la reserva periodica'); 
            
        }else{
           document.formweb.submit(); 
        }
    }else{
        alert('Ha de rellenar todos los campos');
    }
}


function verFranjas(){
  var select = $('#idFranjaDia');
  $('option', select).remove();
    if(select.prop) {
      var options = select.prop('options');
    }
    else {
      var options = select.attr('options');
    }
  options[options.length] = new Option('--Pulse ver Franjas--','');
  
  var select2 = $('#tipoReserva');
  $('option', select2).remove();
    if(select2.prop) {
      var options = select2.prop('options');
    }
    else {
      var options = select2.attr('options');
    }
  options[options.length] = new Option('--Seleccione una instalacion y un dia--','');
  
    if(document.formweb.dia.value!='' && document.formweb.idInstalacion.value!=''){
      $('#franjas_pendiente').hide("slow");
      $('#franjas_buscando').show("slow");
      $.getJSON("${pageContext.request.contextPath}/admin/servicios/franjasDisponibles.action?",
      {
        dia: document.formweb.dia.value,
        idInstalacion: document.formweb.idInstalacion.value
      },
      function(data) {
        var select = $('#idFranjaDia');
        if(select.prop) {
          var options = select.prop('options');
        }
        else {
          var options = select.attr('options');
        }
        $('option', select).remove();
        options[options.length] = new Option('--Seleccionar--','');
        $.each(data, function(i, obj) {
            options[options.length] = new Option(obj.horaInicio + ' - ' + obj.horaFin,obj.idFranjaDia);
        });
        $('#franjas_buscando').hide("slow");
      });
    }else{
      $('#franjas_pendiente').show("slow");
      $('#franjas_buscando').hide("slow");
    
    }
   
   if(document.formweb.dia.value!='' && document.formweb.idInstalacion.value!=''){
     $.getJSON("${pageContext.request.contextPath}/admin/servicios/comprobarFestivo.action?",
      {
        dia: document.formweb.dia.value,
        idInstalacion: document.formweb.idInstalacion.value
      },
      function(data) {
        
        var select = $('#tipoReserva');
        if(select.prop) {
          var options = select.prop('options');
        }
        else {
          var options = select.attr('options');
        }
        $('option', select).remove();
          if(data.festivo){
              options[options.length] = new Option('Todos los ' + data.dia + 's','1');
              options[options.length] = new Option('Todos los festivos','2');
              
          }else{
              options[options.length] = new Option('Todos los ' + data.dia,'1');
              options[options.length] = new Option('Todos los laborables','2');
          }
        });
    }
    
}

</script>

<form name="formweb" method="POST" action="${pageContext.request.contextPath}/admin/reservas/guardar.action">
    <div align="right" class="subtitulo">Gestión de Reservas</div>
    <br />
    <fieldset class="fieldset">
        <legend>Datos generales de la reserva</legend>
        
        <table cellspacing="2" cellpadding="2" width="90%" border="0" bgcolor="#ffffff" class="texto" border="0">
            <tr>
                <td>
                    <table border="0" width="100%" class="texto">
                        <tr>
                            <td width="30%" class="texto_negrita izquierdaAli">Usuario</td>
                            <td width="70%" class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="usuario" value="${usuario.login}">
                            </td>
                        </tr>
                        <tr>
                            <td width="20%" class="texto_negrita izquierdaAli">Instalacion</td>
                            <td class="izquierdaAli">
                                <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="idInstalacion" id="idInstalacion" onchange="javascript:verFranjas();">
                                        <option value="">--Seleccionar--</option>
                                        <c:forEach var="item1" items="${listaInstalacion}">
                                            <option value="${item1.idInstalacion}">${mapaCentros[item1.fkCentro].nombre}: ${item1.nombre}&nbsp;&nbsp;</option>
                                        </c:forEach>
                                    </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%" class="texto_negrita izquierdaAli">Dia</td>
                            <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="dia" id="dia" value="${usuario.email}" onchange="javascript:verFranjas();">&nbsp;<span id="franjas_pendiente">Seleccione un dia y una instalacion para ver las franjas</span><span style="display:none" id="franjas_buscando">Buscando franjas</span></td>
                        </tr>
                        <tr>
                            <td class="texto_negrita izquierdaAli">Franja horaria</td>
                            <td class="izquierdaAli">
                                <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="idFranjaDia" id="idFranjaDia">
                                        <option value="">--Pulse ver Franjas--</option>
                                    </select>                   
                            </td>
                        </tr>
                        <tr>
                            <td class="texto_negrita izquierdaAli">Reserva periodica</td>
                            <td class="izquierdaAli">
                                <input id="reservaPeriodica" name="reservaPeriodica" value="true" type="checkbox">              
                            </td>
                        </tr>
                        <tr id="file_udr" style="display:none">
                            <td width="20%" class="texto_negrita izquierdaAli">Ultimo día reserva</td>
                            <td class="izquierdaAli"><input class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="ultimodia" id="ultimodia" value=""></td>
                        </tr>
                        <tr  id="fila_tipores" style="display:none">
                            <td class="texto_negrita izquierdaAli">Tipo de reserva periodica</td>
                            <td class="izquierdaAli">
                                <select class="campos" onFocus="this.className='campos_sel';" onBlur="this.className='campos';" name="tipoReserva" id="tipoReserva">
                                        <option value="">--Seleccione una instalacion y un dia--</option>
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
    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
    <input type="hidden" name="administrador" value="0">
</form>
    <script>
    
    $(document).ready(function(){
        $( "#dia" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});
        
         $( "#ultimodia" ).datepicker({
            inline: true,
            dateFormat: 'dd/mm/yy',
            monthNames:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});
        
        
        $('#reservaPeriodica').change(function() {
            if($('#reservaPeriodica').attr("checked")){
                $('#file_udr').show();
                $('#fila_tipores').show();
                
            }else{
                $('#file_udr').hide();
                $('#fila_tipores').hide();
            }
        });
    });    
    
    document.formweb.idInstalacion.value='${reserva.fkInstalacion}';
    
    
</script>
<%@ include file="/admin/includes/pie.inc" %>