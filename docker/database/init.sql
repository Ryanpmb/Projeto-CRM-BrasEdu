
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'crmdb')
BEGIN
    CREATE DATABASE [crmdb];
END;

CREATE FUNCTION fn_GetOportunityFullView()
RETURNS TABLE
AS
RETURN (
    SELECT 
        o.id AS oportunity_id,
        o.sales_status,
        o.initiated_at,
        o.finished_in,

        c.id AS customer_id,
        c.name AS customer_name,
        c.email AS customer_email,
        c.phone AS customer_phone,
        c.lead_status AS customer_lead_status,
        c.origin AS customer_origin,
        c.register_date AS customer_register_date,

        s.userId AS salesman_id,
        s.name AS salesman_name,
        s.departament AS salesman_departament,

        cr.id AS course_id,
        cr.name AS course_name,
        cr.value AS course_value,
        cr.hourly_load AS course_hourly_load,

        sl.final_value AS sale_value,
        sl.sold_in AS sold_in,
        sl.payment_method AS payment_method,

        (
            SELECT COUNT(*)
            FROM interations i
            WHERE i.oportunity_id = o.id
        ) AS interations_count,

        (
            SELECT MAX(i.interation_date)
            FROM interations i
            WHERE i.oportunity_id = o.id
        ) AS last_interation_date
    FROM oportunities o
        INNER JOIN customers c ON c.userId = o.customer_id
        INNER JOIN salesmans s ON s.userId = o.salesman_id
        INNER JOIN courses cr ON cr.id = o.course_id
        LEFT JOIN sales sl ON sl.oportunity_id = o.id
);


CREATE VIEW vw_FunnelSummary AS
SELECT
    (SELECT COUNT(*) FROM customers) AS total_leads,

    (SELECT COUNT(*) FROM customers WHERE lead_status = 'NEW') AS leads_new,
    (SELECT COUNT(*) FROM customers WHERE lead_status = 'IN_PROGRESS') AS leads_in_progress,
    (SELECT COUNT(*) FROM customers WHERE lead_status = 'QUALIFIED') AS leads_qualified,

    (SELECT COUNT(*) FROM oportunities WHERE status = 'OPEN') AS opp_open,
    (SELECT COUNT(*) FROM oportunities WHERE status = 'NEGOTIATION') AS opp_negotiation,
    (SELECT COUNT(*) FROM oportunities WHERE status = 'CLOSED_LOST') AS opp_lost,

    (SELECT COUNT(*) FROM sale WHERE sales_status = 'COMPLETED') AS sales_done,
    (SELECT COUNT(*) FROM sale WHERE sales_status = 'CANCELLED') AS sales_cancelled,

    (SELECT SUM(final_value) FROM sale WHERE sales_status = 'COMPLETED') AS total_revenue,

    (SELECT AVG(final_value) FROM sale WHERE sales_status = 'COMPLETED') AS avg_ticket;
