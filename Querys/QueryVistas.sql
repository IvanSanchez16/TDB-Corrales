USE GRANJA

CREATE VIEW NumeroCriasPorCorralView AS 
select C1.Corral_id,Tipo,[Numero de crias] from CORRALES C1 left join
(select C.Corral_id,count(*) [Numero de crias] from CORRALES C 
inner join CriasEnProcesoView V on C.Corral_id=V.Corral_id
group by C.Corral_id) T on C1.Corral_id=T.Corral_id

CREATE VIEW CriasClasificacionesView AS
select CR.Crias_id AS Id,Peso,Color_Musculo as [Color de músculo],Cant_Grasa as [Porcentaje de grasa],C.Nombre as Clasificación from CriasEnProcesoView CR left join
CLASIFICACIONES C on CR.Clasificacion_id=C.Clasificacion_id

CREATE VIEW CriasEnRiesgoGrasaCobertura2View AS
Select T.Id,T.Corral_id from
(SELECT CR.Crias_id as Id,CR.Corral_id from CriasEnProcesoView CR inner join
SENSORES S on S.Cria_id=CR.Crias_id where S.Temperatura>=40) T
left join CUARENTENAS C on C.Cria_id=T.Id where C.Fecha_Inicio is null

CREATE VIEW EstadoCriasG2View AS
Select C.crias_id as Cria,S.Temperatura,C.corral_id as Corral from CriasEnProcesoView C 
inner join SENSORES S on C.crias_id=S.cria_id
inner join CORRALES Co on C.corral_id=Co.corral_id
where Co.Tipo='N'

CREATE VIEW CriasG2SanasView as
select C.Crias_id from CriasEnProcesoView C 
inner join SENSORES S on C.Crias_id=S.Cria_id
where C.Corral_id in (Select Corral_id from CORRALES where Tipo='N')

CREATE VIEW CriasEnCuarentenaView as
select c.Crias_id as Cria,S.Temperatura,c.Corral_id as Corral,DATEDIFF(DAY,Fecha_Inicio,GETDATE()) as Días from CriasEnProcesoView c inner join
SENSORES S on S.Cria_id=c.Crias_id  inner join
CUARENTENAS Cu on c.Crias_id=Cu.Cria_id 
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

CREATE VIEW DietasView as
Select Descripcion from DIETAS where Descripcion != 'Bajo tratamiento'
