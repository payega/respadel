Insert into ACTIVIDAD (ID_ACTIVIDAD,NOMBRE) values ('1','Padel');
Insert into ACTIVIDAD (ID_ACTIVIDAD,NOMBRE) values ('2','Tenis');
Insert into ACTIVIDAD (ID_ACTIVIDAD,NOMBRE) values ('3','Fronton');
Insert into ADMIN (ID_ADMIN) values ('admin');
Insert into CENTRO (ID_CENTRO,NOMBRE) values ('1','Larraskitu');
Insert into CENTRO (ID_CENTRO,NOMBRE) values ('2','Madrid');
Insert into CENTRO (ID_CENTRO,NOMBRE) values ('3','La Ola');
Insert into FRANJA (ID_FRANJA,NOMBRE,FECHA_INICIO_VALIDEZ,FECHA_FIN_VALIDEZ,FRANJA_FESTIVOS) values ('1','Festivos',to_timestamp('01/01/00 00:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/01/99 23:59:59,000000000','DD/MM/RR HH24:MI:SS,FF'),'0');
Insert into FRANJA (ID_FRANJA,NOMBRE,FECHA_INICIO_VALIDEZ,FECHA_FIN_VALIDEZ,FRANJA_FESTIVOS) values ('2','Laborables',to_timestamp('01/01/00 00:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/01/99 23:59:59,000000000','DD/MM/RR HH24:MI:SS,FF'),'1');
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('1','1',to_timestamp('01/02/13 09:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 10:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('2','1',to_timestamp('01/02/13 10:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 12:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('3','1',to_timestamp('01/02/13 12:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 13:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('4','1',to_timestamp('01/02/13 13:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 15:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('5','1',to_timestamp('01/02/13 15:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 16:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('6','1',to_timestamp('01/02/13 16:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 18:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('7','1',to_timestamp('01/02/13 18:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 19:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('8','1',to_timestamp('01/02/13 19:30:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 21:00:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('11','2',to_timestamp('01/02/13 15:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 16:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('12','2',to_timestamp('01/02/13 16:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 17:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('13','2',to_timestamp('01/02/13 17:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 18:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('14','2',to_timestamp('01/02/13 18:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 19:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('15','2',to_timestamp('01/02/13 19:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 20:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into FRANJA_DIA (ID_FRANJA_DIA,FK_FRANJA,HORA_INICIO,HORA_FIN) values ('16','2',to_timestamp('01/02/13 20:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'),to_timestamp('01/02/13 21:15:00,000000000','DD/MM/RR HH24:MI:SS,FF'));
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('1','1','1','Pista 1');
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('2','1','1','Pista 2');
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('3','2','1','Pista 1');
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('4','2','1','Pista 2');
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('5','3','2','Pista 1');
Insert into INSTALACION (ID_INSTALACION,FK_CENTRO,FK_ACTIVIDAD,NOMBRE) values ('6','3','3','Fronton 1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('1','1','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('2','1','2');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('3','2','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('4','2','2');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('5','3','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('6','3','2');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('7','4','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('8','4','2');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('9','5','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('10','5','2');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('11','6','1');
Insert into INSTALACION_FRANJA (ID_INSTALACION_FRANJA,FK_INSTALACION,FK_FRANJA) values ('12','6','2');