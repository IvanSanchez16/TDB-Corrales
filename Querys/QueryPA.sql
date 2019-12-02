CREATE PROCEDURE SPInsertarCria @Id int,@Fecha date,@FechaActual date,@Estado varchar(30),@Peso int,@CMusculo varchar(20),@CGrasa tinyint,
@Corral int 
AS 
begin try
	BEGIN tran;
	Insert into CRIAS (Fecha_Entrada,Estado_Origen,Peso,Color_Musculo,Cant_Grasa,Corral_id)
	values(@Fecha,@Estado,@Peso,@CMusculo,@CGrasa,@Corral)
	Insert into LOGDIETAS values(1,@Id,@FechaActual)
	commit tran;
end try
begin catch
	rollback tran;
	declare @errornum int=100000,@errormen varchar(max)='Ocurrio un error durante la inserci�n',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPComprobarCorral @Corral_id int
as
IF @Corral_id not in (Select Corral_id from CORRALES where Corral_id=@Corral_id and Tipo='N')
begin
	select 0 as Band
	return
end
else
	select 1 as band

CREATE PROCEDURE SPInsertarCorral @Tipo char
AS
INSERT INTO CORRALES VALUES(@Tipo)

CREATE PROCEDURE SPActualizarCria @Id int,@Peso int,@Grasa int AS 
UPDATE CRIAS SET Peso=@Peso,Cant_Grasa=@Grasa,Clasificacion_id=null where Crias_id=@Id

CREATE PROCEDURE SPActualizarClasificacion @Id int,@Fecha date,@Clasificacion_id int AS
Begin try
	begin tran
		UPDATE CRIAS SET Clasificacion_id=@Clasificacion_id where Crias_id=@Id
		INSERT INTO LOGCLASIFICACIONES Values(@Id,@Clasificacion_id,@Fecha)
		if(@Clasificacion_id=3 and @Id not in (select Cria_id from SENSORES where Cria_id=@Id))
			Insert into SENSORES values (@Id,38.5)
	commit tran 
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error durante la clasificacion',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPActualizarTemperatura @Cria_id int,@Temperatura float AS
UPDATE SENSORES set Temperatura=@Temperatura where cria_id=@Cria_id


CREATE PROCEDURE SPAgregarACuarentena @Cria int,@Corral int,@Fecha date AS
Begin try
	begin tran
		INSERT INTO CUARENTENAS(Cria_id,Fecha_Inicio) values(@Cria,@Fecha)
		DECLARE @CorralOrg int
		Set @CorralOrg=(SELECT Corral_id from CRIAS where Crias_id=@Cria)
		INSERT INTO MOVIMIENTOS_CRIAS values (@CorralOrg,@Corral,@Cria,@Fecha)
		UPDATE CRIAS SET Corral_id=@Corral where Crias_id=@Cria
		INSERT INTO LOGDIETAS VALUES (4,@Cria,@Fecha)
	commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error en el proceso de cuarentena',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPSacrificar @Cria int,@Fecha date AS
Begin try
	begin tran
		INSERT INTO SALIDAS VALUES(@Cria,@Fecha,0)
		UPDATE CUARENTENAS set Fecha_Fin=@Fecha where Cria_id=@Cria
	commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error durante el sacrificio',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

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
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error durante el proceso',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPCambiarDieta @Cria int,@Dieta varchar(30),@Fecha date as
Begin try
	begin tran
		declare @DietaId int
		select @DietaId=Dieta_id from DIETAS where Descripcion=@Dieta
		INSERT INTO LOGDIETAS (Cria_id,Dieta_id,Fecha) VALUES (@Cria,@DietaId,@Fecha)
	commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error durante el cambio de dieta',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

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
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error al generar el informe',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

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
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error al generar el informe',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

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
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error al generar el informe',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPInformeCriaMovimientos @Cria int AS
Begin try
	begin tran
		select CorralOrg_id as [Corral origen],CorralDes_id as [Corral destino],Fecha from MOVIMIENTOS_CRIAS where Cria_id=@Cria
	commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurri� un error al generar el informe',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch