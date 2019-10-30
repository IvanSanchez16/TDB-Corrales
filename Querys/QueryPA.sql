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

Select * from LOGDIETAS

Select * from CORRALES

Select * from CRIAS 
