ALTER TABLE CRIAS add CONSTRAINT Peso_Check CHECK (Peso between 50 and 250)

ALTER TABLE CRIAS add CONSTRAINT Grasa_Check CHECK (Cant_Grasa between 1 and 40)

Select * from CRIAS
