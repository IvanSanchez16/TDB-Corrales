sys.sp_addmessage
	@msgnum	= 50001,
	@severity = 12,
	@msgtext = 'El corral necesita no ser tipo cuarentena.'
GO

sys.sp_addmessage
	@msgnum	= 50002,
	@severity = 12,
	@msgtext = 'Algo falló durante la inserción.'
GO
