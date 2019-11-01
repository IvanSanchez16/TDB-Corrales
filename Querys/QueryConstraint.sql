ALTER TABLE CRIAS add CONSTRAINT Peso_Check CHECK (Peso between 50 and 250)

ALTER TABLE CRIAS ADD CONSTRAINT Grasa_Check CHECK (Cant_Grasa between 0 and 100)

