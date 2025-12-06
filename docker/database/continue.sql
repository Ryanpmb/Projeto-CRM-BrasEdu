use crmdb;
GO

CREATE TRIGGER tg_store_sales
ON oportunities
AFTER UPDATE
AS
BEGIN
    BEGIN TRY
        INSERT INTO sales (
            final_value, 
            payment_method, 
            sales_status, 
            sold_in, 
            oportunity_id, 
            salesman_id
        )
        SELECT 
            c.value,
            i.payment_method,
            i.status,
            GETDATE(),             
            i.id,
            i.salesman_id
        FROM inserted i
        INNER JOIN deleted d ON d.id = i.id
        INNER JOIN courses c ON c.id = i.course_id
        WHERE i.status = 'WON' 
        AND d.status <> 'WON';
    END TRY
    BEGIN CATCH
        THROW;
    END CATCH
END
GO

CREATE FUNCTION fn_opportunitiesWonFromSalesman
(
    @salesman_id VARCHAR(255)
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        u.name AS name, 
        o.*
    FROM oportunities o
    INNER JOIN salesmans s ON o.salesman_id = s.user_id
    INNER JOIN users u ON s.user_id = u.id
    WHERE o.[status] = 'WON'
      AND o.salesman_id = @salesman_id
);
GO

CREATE VIEW vw_customersAndCoursesOfInteress AS
SELECT c.user_id as 'Código do Cliente', u.name as Nome, u.birth_date as 'Data de Nascimento',co.name  as 'Nome do Curso', co.hourly_load 'Carga Horária do Curso', co.value 'Valor do Curso' FROM customers as c
INNER JOIN users as u on c.user_id = u.id
INNER JOIN oportunities as o on c.user_id = o.customer_id 
INNER JOIN courses as co on o.course_id = co.id 
GO

CREATE PROCEDURE sp_opportunitiesWithDetails
(
    @idOpportunity INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        SELECT 
            o.id AS Id_Oportunidade,
            c.name AS Nome_Cliente,
            sm.name AS Nome_Vendedor,
            co.name AS Nome_Curso,
            o.sales_status AS Status_Venda
        FROM oportunities AS o
        INNER JOIN customers AS c ON c.user_id = o.customer_id
        INNER JOIN salesmans AS sm ON sm.user_id = o.salesman_id
        INNER JOIN courses AS co ON co.id = o.course_id
        WHERE o.id = @idOpportunity
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END
GO

USE crmdb;
GO

-- Backup

-----------------------------------------------------
-- 1. Cria o Job
-----------------------------------------------------
EXEC sp_add_job 
    @job_name = N'BackupFullDiario';

-----------------------------------------------------
-- 2. Adiciona o Step (o comando de backup)
-----------------------------------------------------
EXEC sp_add_jobstep
    @job_name = N'BackupFullDiario',
    @step_name = N'Backup FULL',
    @subsystem = N'TSQL',
    @command = N'
        BACKUP DATABASE crmdb
        TO DISK = ''C:\Backups\MeuBanco_Full.bak''
        WITH INIT, COMPRESSION;
    ',
    @retry_attempts = 0,
    @retry_interval = 0;

-----------------------------------------------------
-- 3. Cria o agendamento (todo dia às 02:00)
-----------------------------------------------------
EXEC sp_add_schedule
    @schedule_name = N'BackupFull_02h',
    @freq_type = 4,          -- diário
    @freq_interval = 1,      -- todo dia
    @active_start_time = 020000; -- 02:00 AM

-----------------------------------------------------
-- 4. Liga o job ao agendamento
-----------------------------------------------------
EXEC sp_attach_schedule
    @job_name = N'BackupFullDiario',
    @schedule_name = N'BackupFull_02h';

-----------------------------------------------------
-- 5. Ativa o job
-----------------------------------------------------
EXEC sp_add_jobserver
    @job_name = N'BackupFullDiario';
GO

-- Restore

-----------------------------------------------------
-- 1. Se já existir e estiver em uso, entra em SINGLE_USER
-----------------------------------------------------
ALTER DATABASE crmdb
SET SINGLE_USER
WITH ROLLBACK IMMEDIATE;
GO

-----------------------------------------------------
-- 2. Executa o comando de RESTORE DATABASE
-----------------------------------------------------
RESTORE DATABASE crmdb
FROM DISK = 'C:\Backups\BackupCRMDB.bak'
WITH 
    FILE = 1,
    REPLACE,
    NOUNLOAD,
    STATS = 10;
GO

-----------------------------------------------------
-- 3. Retorna o banco de dados para o modo MULTI_USER (necessário após o restore)
-----------------------------------------------------
ALTER DATABASE crmdb
SET MULTI_USER;
GO