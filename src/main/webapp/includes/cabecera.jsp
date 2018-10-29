<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<title>:::Iberdrola::: Reserva de Instalaciones Deportivas </title>
        <script src="${pageContext.request.contextPath}/includes/js/jquery-1.6.4.min.js" type="text/javascript"></script>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
        <link type="text/css" href="${pageContext.request.contextPath}/includes/css/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />	
        <script type="text/javascript" src="${pageContext.request.contextPath}/includes/js/jquery-ui-1.8.16.custom.min.js"></script>
	<style type="text/css">
		body{
			margin:0px;
			padding:0px;
			font-family: Trebuchet MS;
		}
		#tablacontenedor{
			width:1000px;
			margin-left:auto;
			margin-right:auto;		
		}
		#central{
			height:300px;
			padding-top:2px;
			vertical-align:top;
		}
		#logoIberdrola{
			background:#66AB05 url(${pageContext.request.contextPath}/images/menu_opcion.gif);
		}
		#separacionTitulo{
			height:20px;
		}
		#lineaPie{
			background-image:url(${pageContext.request.contextPath}/images/br_pieCen.jpg);
			background-repeat:repeat-x;
			height:10px;
		}
		
		#titulo{
			color: #66ab05;
			font-family: "Trebuchet MS", verdana, sans-serif;
			text-decoration:none; 
			font-size: 1.5em;
			line-height: 25px;
			margin-top: 0px;
			margin-bottom: 0px;
			font-weight: normal;
			text-align:left;	
		}

		#subtitulo{
			color:#276015;
			font-family: "Trebuchet MS", verdana, sans-serif;
			text-decoration:none; 
			font-size: 1.4em;
			line-height: 25px;
			margin-left:20px;
			font-weight: normal;
			text-align:left;	
		}
                
		.notificacion{
        	color:red;
			font-family: "Trebuchet MS", verdana, sans-serif;
			text-decoration:none; 
			font-size: 1.3em;
			line-height: 25px;
			margin-left:20px;
			font-weight: normal;
			text-align:center;	
        }

		#contentmigas{
			background-image:url(${pageContext.request.contextPath}/images/fondomigas.gif);
			background-repeat:repeat-x;
			height:2em;
			text-align:right;
			font-family: "Trebuchet MS", verdana, sans-serif;
			color:#ff7800;
			font-size:0.688em;
			margin:0px;
		}
		
		#contentmigas a{
			color:#707070;
			text-decoration:none;
			font-family: "Trebuchet MS", verdana, sans-serif;
		}
                
        #contentmigas a:hover{
			color:#ff7800;
			text-decoration:none;
			font-family: "Trebuchet MS", verdana, sans-serif;
        	text-decoration: underline;
		}
                
		#menu{
			height:2em;
			text-align:right;
			font-family: "Trebuchet MS", verdana, sans-serif;
			color:#ff7800;
			font-size: 1.2em;
		}
                
		#menu a{
			color:#707070;
			text-decoration:none;
			font-family: "Trebuchet MS", verdana, sans-serif;
		}
                
        #menu a:hover{
			color:#ff7800;
			text-decoration:none;
			font-family: "Trebuchet MS", verdana, sans-serif;
        	text-decoration: underline;
		}

		.tablacontenido th{
			color:#276015; 
			font-weight:normal; 
			text-transform:uppercase;
		}
			
		.tablacontenido th,.tablacontenido td{	
			vertical-align:top;
			margin:0px 0px 0px 0px;
			padding:8px 30px 8px 30px;
			border-top:3px solid #FFFFFF;
			background-image:url(${pageContext.request.contextPath}/images/entabla.jpg);
			background-position:bottom; 
			background-repeat:repeat-x;	
			font-size:0.85em;
		}
        
        .linktabla{
              text-decoration: none;
              color:#707070;
        }
                
        .linktabla:hover{
              text-decoration: underline;
              color:#ff7800;
        }
        
        .linktabla_reserva_propia{
              text-decoration: none;
              color:#000000;
              background-color:#83ff62; 
        }
        
         .linktabla_reserva_propia:hover{
              text-decoration: none;
              color:#000000;
              background-color:#83ff62;
              text-decoration: underline;
        }
        
         .linktabla_reserva_abierta{
              text-decoration: none;
              color:#000000;
              background-color:#ff9b2f; 
        }
        
         .linktabla_reserva_abierta:hover{
              text-decoration: none;
              color:#000000;
              background-color:#ff9b2f;
              text-decoration: underline;
        }
        
        .linktabla_reserva{
              text-decoration: none;
              color:#000000;
              background-color:#ff7575; 
        }
                                        
        .linktabla_libre{
              text-decoration: none;
              color:#707070;
        }

        .linktabla_pasado{
              text-decoration: none;
              color:#C0C0C0;
        }
        
        .linktabla_libre:hover{
              text-decoration: none;
              color:#707070;
              text-decoration: underline;
        }
                
		.tablacontenido thead th, .tablacontenido thead td{
			background-color:#dee7dc; 	
			padding:18px 0px 18px 0px;
		}
		
		.tablacontenido tbody{
			color:#707070;
			border-bottom:#707070 1px solid;
		}
                
		.tablaformulario thead th, .tablaformulario thead td{
			background-color:#dee7dc; 	
			padding:18px 0px 18px 0px;
		}
		.tablaformulario tbody{
			color:#707070;
			border-bottom:#707070 1px solid;
		}
                
		.fin th, .fin td{
			background-attachment:inherit; 
			background-image:url(${pageContext.request.contextPath}/images/tabla.jpg);
			background-repeat:repeat-x;
			padding:8px 30px 44px 30px;
			background-position:bottom center; 
		}
                
		.cabeceraLateralTabla{
        	color:#276015;
		}
                
        .titularTabla{
        	font-weight: bold;
            color:#276015;
		}

		.centradoAli{
			text-align:center;
		}
                
		.derechaAli{
			text-align:right;
		}
                
        .izquierdaAli{
			text-align:left;
		}
                
        .imagelink{
        	border: 0;
		}
                
        input, select, textarea {
            background-color: #fcfcfc;
            border: 1px solid #ccc;
            font: 11px verdana, arial, helvetica, sans-serif;
            margin: 2px 0;
            padding: 2px 4px;
        }
        
        .linkbutton {
            background-color: #fcfcfc;
            border: 1px solid #ccc;
            font: 11px verdana, arial, helvetica, sans-serif;
            margin: 2px 0;
            padding: 2.5px 5px 2.5px 5px;
            text-decoration: none;
            color: black;
        }
        
        .linkverde{
            text-decoration: none;
            color:#276015;
            background-color:#FFFFFF; 
            font-weight: bold;
            
        }
        
        .linkverdegrande{
            text-decoration: none;
            color:#276015;
            background-color:#FFFFFF; 
            font-weight: bold;
            font-size:1.25em;            
        }

	</style>
</head>
<body>
    <table id="tablacontenedor" border="0">
		<tr>
            <td colspan="2" id="logoIberdrola"><a href="${pageContext.request.contextPath}/index.action"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Logotipo de Iberdrola" class="imagelink"/></a></td>
		</tr>
		<tr>
			<td colspan="2" id="separacionTitulo"></td>
		</tr>
		<tr>
			<jsp:useBean id="date" class="java.util.Date" />
			<td width="40%" id="titulo">Reserva de instalaciones deportivas</td>
			<td id="contentmigas">Usuario: ${pageContext.request.remoteUser} | Estas en ${ubicacion} | <fmt:formatDate value="${date}" pattern="EEEE, d 'de' MMMM"/></td>
		</tr>
		<tr>
			<td colspan="2" id="menu">
				<a href="${pageContext.request.contextPath}/index.action">Reservar</a> |
			<!--  temporalmente <a href="${pageContext.request.contextPath}/meapunto.action">Me apunto!</a> | -->	 
				<a href="${pageContext.request.contextPath}/misReservas.action">Mis reservas</a>
			</td>
		</tr>
		
