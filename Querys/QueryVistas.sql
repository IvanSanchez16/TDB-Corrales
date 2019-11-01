USE GRANJA

CREATE VIEW NumeroCriasPorCorralView AS 
select C1.Corral_id,Tipo,[Numero de crias] from CORRALES C1 left join
(select C.Corral_id,count(*) [Numero de crias] from CORRALES C 
inner join CRIAS V on C.Corral_id=V.Corral_id
group by C.Corral_id) T on C1.Corral_id=T.Corral_id


CREATE VIEW CriasClasificaciones AS
select CR.Crias_id AS Id,Peso,Color_Musculo as [Color de músculo],Cant_Grasa as [Porcentaje de grasa],C.Nombre as Clasificación from CRIAS CR left join
CLASIFICACIONES C on CR.Clasificacion_id=C.Clasificacion_id

CREATE VIEW CriasEnfermasGrasaCobertura2 AS
Select T.Id,T.Corral_id,C.Fecha_Inicio from
(SELECT CR.Crias_id as Id,CR.Corral_id from CRIAS CR inner join
SENSORES S on S.Cria_id=CR.Crias_id where S.Temperatura>=40) T
left join CUARENTENAS C on C.Cria_id=T.Id where C.Fecha_Inicio is null