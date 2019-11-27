CREATE FUNCTION dbo.FCorralConMenosCrias(@Tipo char)
returns int
as begin
	return (Select top(1) Corral_id from NumeroCriasPorCorralView where Tipo=@Tipo order by [Numero de crias])
end