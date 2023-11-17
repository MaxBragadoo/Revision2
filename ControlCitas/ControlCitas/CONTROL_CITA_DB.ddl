-- Generado por Oracle SQL Developer Data Modeler 21.4.2.059.0838
--   en:        2023-11-14 19:03:23 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE cita (
    id_cita     INTEGER NOT NULL,
    fechahora   DATE NOT NULL,
    descripcion VARCHAR2(200) NOT NULL,
    estatus     VARCHAR2(45) NOT NULL,
    fechaalta   DATE NOT NULL,
    id_vehiculo INTEGER NOT NULL,
    id_usuario  INTEGER NOT NULL
);

ALTER TABLE cita ADD CONSTRAINT cita_pk PRIMARY KEY ( id_cita );

CREATE TABLE cliente (
    id_cliente    INTEGER NOT NULL,
    codigocliente VARCHAR2(10) NOT NULL,
    nombre        VARCHAR2(45) NOT NULL,
    apellidop     VARCHAR2(45) NOT NULL,
    apellidom     VARCHAR2(45) NOT NULL,
    correo        VARCHAR2(100),
    telefono      VARCHAR2(20),
    estatus       VARCHAR2(45) NOT NULL,
    fechaalta     DATE NOT NULL
);

CREATE UNIQUE INDEX cliente__idx ON
    cliente (
        codigocliente
    ASC,
        correo
    ASC );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE permiso (
    id_permiso    INTEGER NOT NULL,
    codigopermiso VARCHAR2(10) NOT NULL,
    nombre        VARCHAR2(45) NOT NULL,
    descripcion   VARCHAR2(45),
    estatus       VARCHAR2(45) NOT NULL,
    fechaalta     DATE NOT NULL
);

CREATE UNIQUE INDEX permiso__idx ON
    permiso (
        codigopermiso
    ASC );

ALTER TABLE permiso ADD CONSTRAINT permiso_pk PRIMARY KEY ( id_permiso );

CREATE TABLE permisorol (
    id_permisorol INTEGER NOT NULL,
    id_permiso    INTEGER NOT NULL,
    id_rol        INTEGER NOT NULL
);

ALTER TABLE permisorol ADD CONSTRAINT permisorol_pk PRIMARY KEY ( id_permisorol );

CREATE TABLE rol (
    id_rol      INTEGER NOT NULL,
    codigorol   VARCHAR2(10) NOT NULL,
    nombre      VARCHAR2(45) NOT NULL,
    descripcion VARCHAR2(100),
    estatus     VARCHAR2(45) NOT NULL,
    fechaalta   DATE NOT NULL
);

CREATE UNIQUE INDEX rol__idx ON
    rol (
        codigorol
    ASC );

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE usuario (
    id_usuario    INTEGER NOT NULL,
    codigousuario VARCHAR2(10) NOT NULL,
    nombre        VARCHAR2(45) NOT NULL,
    apellidop     VARCHAR2(45) NOT NULL,
    apellidom     VARCHAR2(45) NOT NULL,
    telefono      VARCHAR2(45),
    username      VARCHAR2(45) NOT NULL,
    password      VARCHAR2(45) NOT NULL,
    id_rol        INTEGER NOT NULL,
    estatus       VARCHAR2(45) NOT NULL,
    fechaalta     DATE NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

CREATE TABLE vehiculo (
    id_vehiculo      INTEGER NOT NULL,
    vin              VARCHAR2(20) NOT NULL,
    marca            VARCHAR2(45) NOT NULL,
    modelo           VARCHAR2(45) NOT NULL,
    anio             INTEGER NOT NULL,
    color            VARCHAR2(45) NOT NULL,
    placa            VARCHAR2(7) NOT NULL,
    reparacionprevia VARCHAR2(200),
    estatus          VARCHAR2(45) NOT NULL,
    fechaalta        DATE NOT NULL,
    id_cliente       INTEGER NOT NULL
);

CREATE UNIQUE INDEX vehiculo__idx ON
    vehiculo (
        vin
    ASC );

ALTER TABLE vehiculo ADD CONSTRAINT vehiculo_pk PRIMARY KEY ( id_vehiculo );

ALTER TABLE cita
    ADD CONSTRAINT cita_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE cita
    ADD CONSTRAINT cita_vehiculo_fk FOREIGN KEY ( id_vehiculo )
        REFERENCES vehiculo ( id_vehiculo );

ALTER TABLE permisorol
    ADD CONSTRAINT permisorol_permiso_fk FOREIGN KEY ( id_permiso )
        REFERENCES permiso ( id_permiso );

ALTER TABLE permisorol
    ADD CONSTRAINT permisorol_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE vehiculo
    ADD CONSTRAINT vehiculo_cliente_fk FOREIGN KEY ( id_cliente )
        REFERENCES cliente ( id_cliente );


CREATE SEQUENCE SEQ_ROL
    INCREMENT BY 1
    START WITH 1;
    
CREATE SEQUENCE SEQ_PERMISO
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE SEQ_USUARIO
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE SEQ_VEHICULO
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE SEQ_CLIENTE
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE SEQ_CITA
    INCREMENT BY 1
    START WITH 1;