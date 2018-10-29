<%@ page isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>:::Iberdrola::: Reserva de pistas de padel</title>
<link type="text/css" href="${pageContext.request.contextPath}/admin/includes/css/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/includes/js/jquery-1.6.2.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/includes/js/jquery-ui-1.8.16.custom.min.js"></script>
<link href="${pageContext.request.contextPath}/admin/includes/css/estilos.css" rel="stylesheet" type="text/css">
<script>
<!--
color_original='#f0ffd1';
color_seleccionado='#66ab05';

function entrar(src) {
      src.bgColor=color_seleccionado;
}

function salir(src) {
    src.bgColor=color_original;
}

-->
</script>
<style type="text/css">
<!--

.menu {
	font-family: Arial, Helvetica, sans-serif;
	height: auto;
	width: auto;
}

#tablacontenedor{
			width:100%;
			margin-left:auto;
			margin-right:auto;
                        border-spacing: 0;
		}
                
.logoIberdrola{
        background:#66AB05 url(${pageContext.request.contextPath}/images/menu_opcion.gif);
}
-->
</style>
</head>

<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<table width="100%" border="0" id="tablacontenedor">
    <tr>
      <td class="logoIberdrola"><a href="${pageContext.request.contextPath}/admin/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Logotipo de Iberdrola" class="imagelink" border="0"/></a></td>
      <td class="logoIberdrola titulo"> Reserva de Pistas de Padel / Administracion&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
  <tr>
    <td width="18%" align="center" valign="top">
        <table bgcolor="#f0ffd1" border="0" width="100%">
      <tbody>
         
        <tr>
          <td class="titulo_menu" background="${pageContext.request.contextPath}/admin/images/fondo_titulo.gif">MENU</td>
        </tr>

         <tr onmouseover="entrar(this)" onmouseout="salir(this)" id="menu1">
          <td class="elementos_menu"><a href="${pageContext.request.contextPath}/admin/reservas/mostrar.action" class="elementos_menu">Reservas</a></td>
         </tr> 
        <!--tr onmouseover="entrar(this)" onmouseout="salir(this)" id="menu2">
          <td class="elementos_menu"><a href="${pageContext.request.contextPath}/admin/meapunto/mostrar.action" class="elementos_menu">Partidos Me Apunto!</a></td>
        </tr-->
        <tr onmouseover="entrar(this)" onmouseout="salir(this)" id="menu3">
          <td class="elementos_menu"><a  href="${pageContext.request.contextPath}/admin/festivos/mostrar.action" class="elementos_menu">Festivos</a></td>
        </tr>
        <tr onmouseover="entrar(this)" onmouseout="salir(this)" id="menu3">
          <td class="elementos_menu"><a  href="${pageContext.request.contextPath}/index.action" class="elementos_menu">Salir</a></td>
        </tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu"><hr></td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>
        <tr><td class="elementos_menu">&nbsp;</td></tr>

        
      </tbody>
    </table>

<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b>
</td>
    <td width="82%"  valign="top">
        <table width="95%" border="0" align="center"><tr><td>
                    <div id="nifty" align="center">
                        <b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b> <b class="r4"></b></b>