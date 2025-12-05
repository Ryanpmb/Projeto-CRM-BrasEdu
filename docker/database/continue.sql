use crmdb;
GO

CREATE TRIGGER tg_store_sales
ON oportunities
AFTER UPDATE
AS
BEGIN
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
INNER JOIN oportunities as o on c.user_id  = o.customer_id 
INNER JOIN courses as co on o.course_id = co.id 
GO