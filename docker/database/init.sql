
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'crmdb')
BEGIN
    CREATE DATABASE [crmdb];
END;

