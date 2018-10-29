create table centro(
    ID_CENTRO int not null,
    NOMBRE varchar(80) not null,
    constraint pk_centro primary key (ID_CENTRO)
);

create table actividad(
    ID_ACTIVIDAD int not null,
    NOMBRE varchar(80) not null,
    constraint pk_actividad primary key (ID_ACTIVIDAD)
);

create table instalacion(
    ID_INSTALACION int not null,
    FK_CENTRO int not null,
    FK_ACTIVIDAD int not null,
    NOMBRE varchar(80) not null,
    constraint pk_instalacion primary key (ID_INSTALACION)
);

create table franja(
    ID_FRANJA int not null,
    NOMBRE varchar(80) not null,
    FECHA_INICIO_VALIDEZ date,
    FECHA_FIN_VALIDEZ date,
    FRANJA_FESTIVOS int not null,
    constraint pk_franja primary key (ID_FRANJA)
);

create table instalacion_franja(
    ID_INSTALACION_FRANJA int not null,
    FK_INSTALACION int not null,
    FK_FRANJA int not null,
    constraint pk_instalacion_franja primary key (ID_INSTALACION_FRANJA)
);

create table franja_dia(
    ID_FRANJA_DIA int not null,
    FK_FRANJA int not null,
    HORA_INICIO time not null,
    HORA_FIN time not null,
    constraint pk_franja_dia primary key (ID_FRANJA_DIA)
);

create table reserva(
    ID_RESERVA int not null IDENTITY,
    FK_FRANJA_DIA int not null,
    FK_INSTALACION int not null,
    DIA date,
    FEC_HORA timestamp,
    USUARIO varchar(80) not null,
    NOMBRE_ORIGINAL varchar(80) null,
    ESTADO varchar(1) not null,
    constraint pk_reserva primary key (ID_RESERVA)
);

create table dia_festivo(
    ID_DIA_FESTIVO int not null IDENTITY,
    DIA date,
    FK_CENTRO int not null,
    constraint pk_dia_festivo primary key (ID_DIA_FESTIVO)
);


create table meapunto(
    ID_MEAPUNTO int not null IDENTITY,
    FK_RESERVA int not null,
    NIVEL_MINIMO decimal(3,2) not null,
    NIVEL_MAXIMO decimal(3,2) not null,
    NOTAS varchar(500) not null,
    DIA_LIMITE date not null,
    HORA_LIMITE time not null,
    INSCRITOS int not null,
    ESTADO varchar(1) not null,
    constraint pk_meapunto primary key (ID_MEAPUNTO)
);

create table inscripcion_meapunto(
    ID_INSCRIPCION_MEAPUNTO int not null IDENTITY,
    FK_MEAPUNTO int not null,
    USUARIO varchar(80) not null,
    EMAIL_USUARIO varchar(500) not null,
    constraint pk_inscripcion_meapunto primary key (ID_INSCRIPCION_MEAPUNTO)
);

create table admin(
    ID_ADMIN varchar(80) not null,
    constraint pk_admin primary key (ID_ADMIN)
);
