-- Table for Marketing Director
CREATE TABLE MarketingDirector (
    execID INT PRIMARY KEY NOT NULL,
    name VARCHAR(60),
    team VARCHAR(60)
);

-- Table for Partnership Director
CREATE TABLE PartnershipDirector (
    execID INT PRIMARY KEY NOT NULL,
    name VARCHAR(60),
    team VARCHAR(60)
);

-- Table for Venue
CREATE TABLE Venue (
    venueID INT PRIMARY KEY,
    address VARCHAR(255),
    roomNumber INT,
    capacity INT
);

-- Table for Event
CREATE TABLE Event (
    eventID INT PRIMARY KEY,
    eventName VARCHAR(60),
    eventDate DATE,
    eventTime TIMESTAMP,
    venueID INT UNIQUE,
    FOREIGN KEY (venueID) REFERENCES Venue(venueID)
);

-- Table for Proposal
CREATE TABLE Proposal (
    proposalID INT PRIMARY KEY NOT NULL,
    proposalText VARCHAR(800),
    execID INT,
    FOREIGN KEY (execID) REFERENCES PartnershipDirector(execID)
);

-- Table for Event Director
CREATE TABLE EventDirector (
    execID INT PRIMARY KEY NOT NULL,
    name VARCHAR(60),
    team VARCHAR(60),
    eventID INT UNIQUE NOT NULL,
    venueID INT UNIQUE,
    FOREIGN KEY (eventID) REFERENCES Event(eventID),
    FOREIGN KEY (venueID) REFERENCES Venue(venueID)
);

-- Table for Infographic
CREATE TABLE Infographic (
    filename VARCHAR(255) PRIMARY KEY,
    editingSoftware VARCHAR(255),
    execID INT,
    eventID INT,
    FOREIGN KEY (execID) REFERENCES MarketingDirector(execID),
    FOREIGN KEY (eventID) REFERENCES Event(eventID)
);

-- Table for Ticket
CREATE TABLE Ticket (
    ticketID INT NOT NULL,
    eventID INT NOT NULL,
    PRIMARY KEY (ticketID, eventID),
    price NUMBER(4, 2),
    FOREIGN KEY (eventID) REFERENCES Event(eventID)
);

-- Table for Feedback Form
CREATE TABLE FeedbackForm (
    formID INT PRIMARY KEY
);

-- Table for Student
CREATE TABLE Student (
    studentID INT PRIMARY KEY,
    yearLevel INT,
    faculty VARCHAR(60),
    membership CHAR(1),
    name VARCHAR(60),
    email VARCHAR(75),
    dietaryRestrictions VARCHAR(255),
    eventID INT,
    ticketID INT,
    formID INT UNIQUE,
    FOREIGN KEY (eventID) REFERENCES Event(eventID),
    FOREIGN KEY (ticketID, eventID) REFERENCES Ticket,
    FOREIGN KEY (formID) REFERENCES FeedbackForm(formID)
);

-- Table for Delegate
CREATE TABLE Delegate (
    employeeID INT PRIMARY KEY,
    company VARCHAR(60),
    position VARCHAR(60),
    name VARCHAR(60),
    email VARCHAR(75),
    dietaryRestrictions VARCHAR(255),
    LinkedIn VARCHAR(255),
    eventID INT,
    execID INT UNIQUE,
    FOREIGN KEY (eventID) REFERENCES Event(eventID),
    FOREIGN KEY (execID) REFERENCES PartnershipDirector(execID)
);



