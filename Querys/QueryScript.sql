CREATE DATABASE GRANJA

USE GRANJA

CREATE TABLE CORRALES (Corral_id int identity(1,1) Primary key,Tipo char(1) not null)

CREATE TABLE CLASIFICACIONES (Clasificacion_id int primary key,Nombre varchar(20) not null,PesoMax int not null,
GrasaMax int not null)

CREATE TABLE CRIAS (Crias_id int identity(1,1) primary key,Fecha_Entrada date not null,Estado_Origen varchar(30) not null,Peso int not null,Color_Musculo varchar(20) not null,
Cant_Grasa tinyint not null,Clasificacion_id int null,Corral_id int not null,
foreign key(Corral_id) references CORRALES(Corral_id),
foreign key(Clasificacion_id) references CLASIFICACIONES(Clasificacion_id))

CREATE TABLE SALIDAS(Cria_id int primary key,Fecha_Salida date not null,Razon_Salida bit not null,
foreign key(Cria_id) references CRIAS(Crias_id))

CREATE TABLE SENSORES (Sensor_id int identity primary key)

CREATE TABLE SENSOR_CRIA (Sensor_id int, Cria_id int,clave int identity,estatus bit,
primary key (Sensor_id,Cria_id),
foreign key (Sensor_id) references SENSORES(Sensor_id),
foreign key (Cria_id) references CRIAS(Crias_id))

CREATE TABLE DATOS_SENSOR(Sensor_id int,clave int identity,Temperatura float,Presion int,Ritmo int,
primary key(Sensor_id,clave),
foreign key(Sensor_id) references SENSORES(Sensor_id))

CREATE TABLE CUARENTENAS (Cuarentena_id int identity(1,1) primary key not null,Cria_id int,Fecha_Inicio date not null,Fecha_Fin date,
foreign key (Cria_id) references CRIAS(Crias_id))

CREATE TABLE MOVIMIENTOS_CRIAS (CorralOrg_id int, CorralDes_id int, Cria_id int, Fecha date,
PRIMARY KEY (Cria_id,CorralDes_id,Fecha),
FOREIGN KEY (Cria_id) references CRIAS(Crias_id),
FOREIGN KEY (CorralOrg_id) references CORRALES(Corral_id),
FOREIGN KEY (CorralDes_id) references CORRALES(Corral_id))

CREATE TABLE DIETAS (Dieta_id int identity(1,1) primary key,Descripcion varchar(30) unique,Tipo bit)

CREATE TABLE LOGDIETAS (Dieta_id int, Cria_id int,Clave int identity, Fecha date,
primary key (Cria_id,Dieta_id,Clave,Fecha),
foreign key (Cria_id) references CRIAS(Crias_id),
foreign key (Dieta_id) references DIETAS(Dieta_id))

INSERT INTO DIETAS VALUES ('Balanceada',0),('Subir grasa',0),('Bajar grasa',0),('Bajo tratamiento 1',1),('Bajo tratamiento 2',1),('Bajo tratamiento 3',1)

INSERT INTO CLASIFICACIONES VALUES (1,'Grasa de cobertura 0',90,10),(2,'Grasa de cobertura 1',130,19),(3,'Grasa de cobertura 2',170,26),(4,'Grasa de cobertura 3',250,40)
