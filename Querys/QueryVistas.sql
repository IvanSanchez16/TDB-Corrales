USE GRANJA

CREATE VIEW NumeroCriasPorCorralView AS 
select C1.Corral_id,
Case 
when Tipo='N' then 'Normal'
else 'Cuarentena'
end as Tipo
,[Numero de crias] from CORRALES C1 left join
(select C.Corral_id,count(*) [Numero de crias] from CORRALES C 
inner join CriasEnProcesoView V on C.Corral_id=V.Corral_id
group by C.Corral_id) T on C1.Corral_id=T.Corral_id

CREATE VIEW CriasClasificacionesView AS
select CR.Crias_id AS Id,Peso,Color_Musculo as [Color de m�sculo],Cant_Grasa as [Porcentaje de grasa],C.Nombre as Clasificaci�n from CriasEnProcesoView CR left join
CLASIFICACIONES C on CR.Clasificacion_id=C.Clasificacion_id

CREATE VIEW CriasG2SanasView as
select C.Crias_id from CriasEnProcesoView C 
where C.Corral_id in (Select Corral_id from CORRALES where Tipo='N') and C.Clasificacion_id=3

CREATE VIEW CriasEnCuarentenaView as
select c.Crias_id as Cria,c.Corral_id as Corral,DATEDIFF(DAY,Fecha_Inicio,GETDATE()) as D�as from CriasEnProcesoView c 
inner join CUARENTENAS Cu on c.Crias_id=Cu.Cria_id 
where Cu.Fecha_Fin is null

CREATE VIEW CriasEnProcesoView AS
Select C.* from CRIAS C
left join SALIDAS S on C.Crias_id=S.Cria_id
where S.Cria_id is null

CREATE VIEW InfDietasxCriaView AS
Select C.Crias_id,D.Descripcion as [Dieta actual],C.Peso,C.Cant_Grasa as Grasa,DATEDIFF(DAY,C.Fecha_Entrada,GETDATE()) as [Dias en el proceso] from 	
(select C.Crias_id,MAX(Ld.Clave) Clave from CriasEnProcesoView C
inner join LOGDIETAS Ld on Ld.Cria_id=C.Crias_id
group by C.Crias_id) Cl
inner join CriasEnProcesoView C on C.Crias_id=Cl.Crias_id
inner join LOGDIETAS Ld on Ld.Cria_id=C.Crias_id
inner join DIETAS D on D.Dieta_id=Ld.Dieta_id
where Ld.Clave=Cl.Clave

CREATE VIEW DietasSanasView as
Select Descripcion from DIETAS where Tipo=0

CREATE VIEW DietasEnfermasView AS
select Descripcion from DIETAS where tipo=1

CREATE VIEW EstadoCriasView AS
select C.Crias_id,CL.Nombre as Clasificacion,C.Corral_id as Corral,
CASE
	when CU.Fecha_Inicio is null or CU.Fecha_Fin is not null then 'Sana'
	else 'Bajo cuarentena'
end
as [Estado actual],DATEDIFF(DAY,C.Fecha_Entrada,GETDATE()) [Dias en el proceso] from CriasEnProcesoView C
inner join CLASIFICACIONES CL on C.Clasificacion_id=CL.Clasificacion_id
left join CUARENTENAS CU on C.Crias_id=CU.Cria_id

CREATE VIEW CriasFueraView AS	
Select * from SALIDAS

CREATE VIEW EstatusSensoresView AS
select S.Sensor_id,
case
	when C.estatus=1 then 'En uso'
	else 'Sin usarse'
end as Estatus
from SENSORES S 
left join SENSOR_CRIA C on S.Sensor_id=C.Sensor_id

CREATE VIEW CriasConSensorView AS
Select S.Sensor_id,C.Crias_id from CriasEnProcesoView C
inner join SENSOR_CRIA S on C.Crias_id=S.Cria_id 
where S.Estatus=1

CREATE VIEW CriasSinSensorView as
select C.Crias_id,C.Corral_id from CriasEnProcesoView C
left join SENSOR_CRIA S on C.Crias_id=S.Cria_id
where S.Cria_id is null and C.Clasificacion_id=3



