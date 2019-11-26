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
		if(@Clasificacion_id=3)
			Insert into SENSORES values (@Id,38)
	commit tran 
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurrio un error durante la clasificacion',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch



CREATE PROCEDURE SPAgregarACuarentena @Cria int,@Corral int,@Fecha date,@Medicamento varchar(20),@Enfermedad varchar(20) AS
Begin try
	begin tran
		Insert Into TRATAMIENTOS values(@Medicamento,@Enfermedad)
		Declare @Tratamiento int
		SET @Tratamiento=(SELECT max(Tratamiento_id) from TRATAMIENTOS)
		INSERT INTO CUARENTENAS(Tratamiento_id,Cria_id,Fecha_Inicio) values(@Tratamiento,@Cria,@Fecha)
		DECLARE @CorralOrg int
		Set @CorralOrg=(SELECT Corral_id from CRIAS where Crias_id=@Cria)
		INSERT INTO MOVIMIENTOS_CRIAS values (@CorralOrg,@Corral,@Cria,@Fecha)
		UPDATE CRIAS SET Corral_id=@Corral where Crias_id=@Cria
		commit tran
end try
begin catch
	rollback tran
	declare @errornum int=100000,@errormen varchar(max)='Ocurrio un error en el proceso de cuarentena',@errorest int=Error_State();
	throw @errornum,@errormen,@errorest;
end catch



Select * from LOGDIETAS

Select * from CORRALES	

Select * from CRIAS 

Select * from SENSORES

Select * from LOGCLASIFICACIONES

Select * from CLASIFICACIONES


