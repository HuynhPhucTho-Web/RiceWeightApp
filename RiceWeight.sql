USE master
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = N'RiceWeight')
BEGIN
    ALTER DATABASE RiceWeight SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE RiceWeight;
END
GO

CREATE DATABASE RiceWeight
GO

USE RiceWeight
GO

CREATE TABLE Customer (
    customerID INT PRIMARY KEY IDENTITY(1,1),
    customerName NVARCHAR(100) NOT NULL,
    phone VARCHAR(15)
);


CREATE TABLE WeighingSession (
    sessionID INT PRIMARY KEY IDENTITY(1,1),
    customerID INT FOREIGN KEY REFERENCES Customer(customerID),
    weighingDate DATETIME DEFAULT GETDATE(),
    pricePerKg DECIMAL(10,2) NOT NULL,
    note NVARCHAR(255)
);


CREATE TABLE WeighingDetail (
    detailID INT PRIMARY KEY IDENTITY(1,1),
    sessionID INT NOT NULL FOREIGN KEY REFERENCES WeighingSession(sessionID),
    bagIndex INT NOT NULL,
    weightKg DECIMAL(10,2) NOT NULL
);

