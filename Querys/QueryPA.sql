CREATE PROCEDURE SPInsertarCria @Fecha date,@FechaActual date,@Estado varchar(30),@Peso int,@CMusculo varchar(20),@CGrasa tinyint,
@Corral int 
AS 
begin try
	BEGIN tran;
	Insert into CRIAS (Fecha_Entrada,Estado_Origen,Peso,Color_Musculo,Cant_Grasa,Corral_id)
	values(@Fecha,@Estado,@Peso,@CMusculo,@CGrasa,@Corral)
	declare @Cria int=@@IDENTITY
	Insert into LOGDIETAS values(1,@Cria,@FechaActual)
	commit tran;
	return @Cria
end try
begin catch
	rollback tran;
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrio un error durante la inserción',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPComprobarCorral @Corral_id int
as
IF @Corral_id not in (Select Corral_id from CORRALES where Corral_id=@Corral_id and Tipo='N')
begin
	select 0 as Band
	return
end
else
	select 1 as Band

CREATE PROCEDURE SPInsertarCorral @Tipo char
AS
INSERT INTO CORRALES VALUES(@Tipo)
return @@Identity

CREATE PROCEDURE SPActualizarClasificacion @Id int,@Fecha date,@Clasificacion_id int AS
UPDATE CRIAS SET Clasificacion_id=@Clasificacion_id where Crias_id=@Id
if(@Clasificacion_id=3)
begin
	return 1
end

ALTER PROCEDURE SPActualizarDatos @Cria_id int,@Sensor int,@Temperatura float,@Presion int,@Ritmo int AS
Insert into DATOS_SENSOR values(@Sensor,@Temperatura,@Presion,@Ritmo)

ALTER PROCEDURE SPAgregarACuarentena @Cria int,@Corral int,@Fecha date,@Dieta varchar(30) AS
Begin try
	begin tran
		if(@Cria in (select Cria from CriasEnCuarentenaView with(updlock) where Cria=@Cria))
		begin
			rollback tran
			return 1
		end
		INSERT INTO CUARENTENAS(Cria_id,Fecha_Inicio) values(@Cria,@Fecha)
		DECLARE @CorralOrg int
		Set @CorralOrg=(SELECT Corral_id from CRIAS where Crias_id=@Cria)
		INSERT INTO MOVIMIENTOS_CRIAS values (@CorralOrg,@Corral,@Cria,@Fecha)
		UPDATE CRIAS SET Corral_id=@Corral where Crias_id=@Cria
		declare @Dietaid int =(Select Dieta_id from DIETAS where Descripcion=@Dieta)
		INSERT INTO LOGDIETAS VALUES (@Dietaid,@Cria,@Fecha)
	commit tran
	return;
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error en el proceso de cuarentena',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

exec SPAgregarACuarentena 2,2,'20191206','Bajo tratamiento 1'

CREATE PROCEDURE SPSacrificar @Cria int,@Fecha date AS
Begin try
	begin tran
		INSERT INTO SALIDAS VALUES(@Cria,@Fecha,0)
		UPDATE CUARENTENAS set Fecha_Fin=@Fecha where Cria_id=@Cria
	commit tran
	return;
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el sacrificio',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPDarDeAltaCria @Cria int,@Fecha date AS
Begin try
	begin tran
		UPDATE CUARENTENAS set Fecha_Fin=@Fecha where Cria_id=@Cria
		INSERT INTO LOGDIETAS (Dieta_id,Cria_id,Fecha) values (1,@Cria,@Fecha)
		Declare @CorralDest int,@CorralIni int
		Select top(1) @CorralDest=Corral_id from NumeroCriasPorCorralView where Tipo='Normal' order by [Numero de crias]
		select @CorralIni=corral_id from CRIAS where Crias_id=@Cria
		INSERT INTO MOVIMIENTOS_CRIAS values (@CorralIni,@CorralDest,@Cria,@Fecha)
		UPDATE CRIAS set Corral_id=@CorralDest where Crias_id=@Cria
	commit tran
	return;
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el proceso',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPCambiarDieta @Cria int,@Dieta varchar(30),@Fecha date as
Begin try
	begin tran
		declare @DietaId int
		select @DietaId=Dieta_id from DIETAS where Descripcion=@Dieta
		INSERT INTO LOGDIETAS (Cria_id,Dieta_id,Fecha) VALUES (@Cria,@DietaId,@Fecha)
	commit tran
	return;
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el cambio de dieta',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;



CREATE PROCEDURE SPSiguienteProceso @Cria int,@Fecha date AS
INSERT INTO SALIDAS VALUES (@Cria,@Fecha,1)

CREATE PROCEDURE SPInformeCriaDatos @Cria int AS
Begin try
	begin tran
		select Fecha_Entrada,Fecha_Salida,
		case
		when Razon_Salida=1 then 'Enviada al siguiente proceso'
		else 'Sacrificada por enfermedad prolongada'
		end [Razon de salida],
		C.Peso,C.Cant_Grasa,C.Color_Musculo,CL.Nombre from CRIAS C
		inner join SALIDAS S on C.Crias_id=S.Cria_id
		inner join CLASIFICACIONES CL on CL.Clasificacion_id=C.Clasificacion_id
		where C.Crias_id=@Cria
	commit tran
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error al generar el informe',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPInformeCriaDietas @Cria int AS
Begin try
	begin tran
		select D.Descripcion,LD.Fecha as [Fecha de cambio] from LOGDIETAS LD
		inner join DIETAS D on LD.Dieta_id=D.Dieta_id
		where Cria_id=@Cria
	commit tran
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error al generar el informe',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPInformeCriaCuarentenas @Cria int AS
Begin try
	begin tran
		select C.Fecha_Inicio,C.Fecha_Fin,M.CorralDes_id as [Corral durante cuarentena] from CUARENTENAS C
		inner join MOVIMIENTOS_CRIAS M on C.Cria_id=M.Cria_id
		where C.Cria_id=@Cria and C.Fecha_Inicio=M.Fecha
	commit tran
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error al generar el informe',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPInformeCriaMovimientos @Cria int AS
Begin try
	begin tran
		select CorralOrg_id as [Corral origen],CorralDes_id as [Corral destino],Fecha from MOVIMIENTOS_CRIAS where Cria_id=@Cria
	commit tran
end try
begin catch
	rollback tran
end catch
declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error al generar el informe',@errorest int=Error_State();
throw @errornum,@errormen,@errorest;

CREATE PROCEDURE SPRegistrarSensor AS
INSERT INTO SENSORES DEFAULT VALUES
return @@IDENTITY

CREATE PROCEDURE SPAsignarSensor @Cria int,@Sensor int as
insert into SENSOR_CRIA (Cria_id,Sensor_id,Estatus) values(@Cria,@Sensor,1)

CREATE PROCEDURE SPInsertarDieta @Descripcion varchar(30),@Tipo bit as
Insert into DIETAS (Descripcion,Tipo) values (@Descripcion,@Tipo)

select * from DATOS_SENSOR
