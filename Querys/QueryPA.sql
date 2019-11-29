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
	declare @errornum int=100000,@errormen varchar(max)='Ocurrio un error durante la inserción',@errorest int=Error_State();
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
	declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante la clasificacion',@errorest int=Error_State();
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
	declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error en el proceso de cuarentena',@errorest int=Error_State();
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
	declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el sacrificio',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

CREATE PROCEDURE SPDarDeAltaCria @Cria int,@Fecha date AS
Begin try
	begin tran
		UPDATE CUARENTENAS set Fecha_Fin=@Fecha where Cria_id=@Cria
		INSERT INTO LOGDIETAS (Dieta_id,Cria_id,Fecha) values (1,@Cria,@Fecha)
		Declare @CorralDest int = (select dbo.FCorralConMenosCrias('N')),@CorralIni int=(select corral_id from CRIAS where Crias_id=@Cria)
		INSERT INTO MOVIMIENTOS_CRIAS values (@CorralIni,@CorralDest,@Cria,@Fecha)
		UPDATE CRIAS set Corral_id=@CorralDest where Crias_id=@Cria
	commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el proceso',@errorest int=Error_State();
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
	declare @errornum int=100000,@errormen varchar(max)='Ocurrió un error durante el cambio de dieta',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch

UPDATE CRIAS SET Fecha_Entrada='20190620' where Crias_id=1002


Select * from LOGDIETAS

Select * from CORRALES	

Select * from CRIAS 

SELECT * from MOVIMIENTOS_CRIAS

SELECT * FROM DIETAS

select * from CUARENTENAS

Select * from SALIDAS

Select * from SENSORES

Select * from LOGCLASIFICACIONES

Select * from CLASIFICACIONES

