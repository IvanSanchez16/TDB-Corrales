CREATE PROCEDURE SPInsertarCria @Id int,@Fecha date,@FechaActual date,@Estado varchar(30),@Peso int,@CMusculo varchar(20),@CGrasa tinyint,
@Corral int 
AS 
IF @Corral not in (Select Corral_Id from CORRALES where Corral_id=@Corral and Tipo='N')
	RAISERROR(50001,12,1)
else
BEGIN tran
begin try
	Insert into CRIAS (Crias_id,Fecha_Entrada,Estado_Origen,Peso,Color_Musculo,Cant_Grasa,Corral_id) 
	values(@Id,@Fecha,@Estado,@Peso,@CMusculo,@CGrasa,@Corral)
	Insert into LOGDIETAS values(1,@Id,@FechaActual)
	commit tran
end try
begin catch
	rollback tran
end catch

CREATE PROCEDURE SPActualizarClasificacion @Id int,@Fecha date,@Clasificacion_id int AS
Begin try
	begin tran
		UPDATE CRIAS SET Clasificacion_id=@Clasificacion_id where Crias_id=@Id
		INSERT INTO LOGCLASIFICACIONES Values(@Id,@Clasificacion_id,@Fecha)
		if(@Clasificacion_id=3)
			Insert into SENSORES values (@Id,'S')
	commit tran 
end try
begin catch
	rollback tran
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
end catch

Select * from LOGDIETAS

Select * from CORRALES

Select * from CRIAS 

Select * from SENSORES

Select * from LOGCLASIFICACIONES

Select * from CLASIFICACIONES

truncate table SENSORES

UPDATE SENSORES SET Estado_Animal='E'
