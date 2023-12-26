-- MarketingDirector Table Inserts
INSERT INTO MarketingDirector (execID, name, team) VALUES (1, 'John Doe', 'Marketing Team A');
INSERT INTO MarketingDirector (execID, name, team) VALUES (2, 'Jane Smith', 'Marketing Team B');
INSERT INTO MarketingDirector (execID, name, team) VALUES (3, 'Samuel Johnson', 'Marketing Team C');
INSERT INTO MarketingDirector (execID, name, team) VALUES (4, 'Emily White', 'Marketing Team A');
INSERT INTO MarketingDirector (execID, name, team) VALUES (5, 'Robert Green', 'Marketing Team B');

-- PartnershipDirector Table Inserts
INSERT INTO PartnershipDirector (execID, name, team) VALUES (1, 'Bob Johnson', 'Partnership Team X');
INSERT INTO PartnershipDirector (execID, name, team) VALUES (2, 'Alice Brown', 'Partnership Team Y');
INSERT INTO PartnershipDirector (execID, name, team) VALUES (3, 'Chris Lee', 'Partnership Team Z');
INSERT INTO PartnershipDirector (execID, name, team) VALUES (4, 'Diana Martinez', 'Partnership Team X');
INSERT INTO PartnershipDirector (execID, name, team) VALUES (5, 'George Miller', 'Partnership Team Y');

-- Venue Table Inserts
INSERT INTO Venue (venueID, address, roomNumber, capacity) VALUES (201, '123 Main Street', 101, 200);
INSERT INTO Venue (venueID, address, roomNumber, capacity) VALUES (202, '456 Oak Avenue', 202, 150);
INSERT INTO Venue (venueID, address, roomNumber, capacity) VALUES (203, '789 Pine Lane', 303, 180);
INSERT INTO Venue (venueID, address, roomNumber, capacity) VALUES (204, '987 Cedar Road', 404, 250);
INSERT INTO Venue (venueID, address, roomNumber, capacity) VALUES (205, '654 Elm Boulevard', 505, 300);
INSERT INTO VENUE (venueID, address, roomNumber, capacity) VALUES (269, 'YOYO Street', 133, 400);

-- Event Table Inserts
INSERT INTO Event (eventID, eventName, eventDate, eventTime, venueID)
VALUES (101, 'Event 1', TO_DATE('2023-12-01', 'YYYY-MM-DD'), TO_TIMESTAMP('2023-12-01 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 201);
INSERT INTO Event (eventID, eventName, eventDate, eventTime, venueID)
VALUES (102, 'Event 2', TO_DATE('2023-12-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2023-12-15 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), 202);
INSERT INTO Event (eventID, eventName, eventDate, eventTime, venueID)
VALUES (103, 'Event 3', TO_DATE('2024-01-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-10 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 203);
INSERT INTO Event (eventID, eventName, eventDate, eventTime, venueID)
VALUES (104, 'Event 4', TO_DATE('2024-02-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-05 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 204);
INSERT INTO Event (eventID, eventName, eventDate, eventTime, venueID)
VALUES (105, 'Event 5', TO_DATE('2024-02-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-20 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 205);

-- Proposal Table Inserts
INSERT INTO Proposal (proposalID, proposalText, execID) VALUES (1, 'Proposal 1 text', 1);
INSERT INTO Proposal (proposalID, proposalText, execID) VALUES (2, 'Proposal 2 text', 2);
INSERT INTO Proposal (proposalID, proposalText, execID) VALUES (3, 'Proposal 3 text', 3);
INSERT INTO Proposal (proposalID, proposalText, execID) VALUES (4, 'Proposal 4 text', 4);
INSERT INTO Proposal (proposalID, proposalText, execID) VALUES (5, 'Proposal 5 text', 5);

-- EventDirector Table Inserts
INSERT INTO EventDirector (execID, name, team, eventID, venueID) VALUES (1, 'Mark Wilson', 'Event Team Alpha', 101, 201);
INSERT INTO EventDirector (execID, name, team, eventID, venueID) VALUES (2, 'Sara Davis', 'Event Team Beta', 102, 202);
INSERT INTO EventDirector (execID, name, team, eventID, venueID) VALUES (3, 'Alex Turner', 'Event Team Gamma', 103, 203);
INSERT INTO EventDirector (execID, name, team, eventID, venueID) VALUES (4, 'Laura Johnson', 'Event Team Alpha', 104, 204);
INSERT INTO EventDirector (execID, name, team, eventID, venueID) VALUES (5, 'Michael Smith', 'Event Team Beta', 105, 205);

-- Infographic Table Inserts
INSERT INTO Infographic (filename, editingSoftware, execID, eventID) VALUES ('infographic1.jpg', 'Illustrator', 1, 101);
INSERT INTO Infographic (filename, editingSoftware, execID, eventID) VALUES ('infographic2.jpg', 'Photoshop', 2, 102);
INSERT INTO Infographic (filename, editingSoftware, execID, eventID) VALUES ('infographic3.jpg', 'Canva', 3, 103);
INSERT INTO Infographic (filename, editingSoftware, execID, eventID) VALUES ('infographic4.jpg', 'InDesign', 4, 104);
INSERT INTO Infographic (filename, editingSoftware, execID, eventID) VALUES ('infographic5.jpg', 'Sketch', 5, 105);

-- Ticket Table Inserts
INSERT INTO Ticket (ticketID, eventID, price) VALUES (1, 101, 25.00);
INSERT INTO Ticket (ticketID, eventID, price) VALUES (2, 102, 30.00);
INSERT INTO Ticket (ticketID, eventID, price) VALUES (3, 103, 20.00);
INSERT INTO Ticket (ticketID, eventID, price) VALUES (4, 104, 35.00);
INSERT INTO Ticket (ticketID, eventID, price) VALUES (5, 105, 40.00);

-- FeedbackForm Table Inserts
INSERT INTO FeedbackForm (formID) VALUES (1);
INSERT INTO FeedbackForm (formID) VALUES (2);
INSERT INTO FeedbackForm (formID) VALUES (3);
INSERT INTO FeedbackForm (formID) VALUES (4);
INSERT INTO FeedbackForm (formID) VALUES (5);

-- Student Table Inserts
INSERT INTO Student (studentID, yearLevel, faculty, membership, name, email, dietaryRestrictions, eventID, ticketID, formID) VALUES (1, 2, 'Engineering', 'Y', 'Alice Johnson', 'alice@email.com', 'None', 101, 1, 1);
INSERT INTO Student (studentID, yearLevel, faculty, membership, name, email, dietaryRestrictions, eventID, ticketID, formID) VALUES (2, 3, 'Business', 'N', 'Bob Smith', 'bob@email.com', 'Vegetarian', 102, 2, 2);
INSERT INTO Student (studentID, yearLevel, faculty, membership, name, email, dietaryRestrictions, eventID, ticketID, formID) VALUES (3, 1, 'Arts', 'Y', 'Charlie Davis', 'charlie@email.com', 'Vegan', 103, 3, 3);
INSERT INTO Student (studentID, yearLevel, faculty, membership, name, email, dietaryRestrictions, eventID, ticketID, formID) VALUES (4, 4, 'Science', 'N', 'Diana Wilson', 'diana@email.com', 'Gluten-free', 104, 4, 4);
INSERT INTO Student (studentID, yearLevel, faculty, membership, name, email, dietaryRestrictions, eventID, ticketID, formID) VALUES (5, 2, 'Engineering', 'Y', 'Edward Brown', 'edward@email.com', 'None', 105, 5, 5);

-- Delegate Table Inserts
INSERT INTO Delegate (employeeID, company, position, name, email, dietaryRestrictions, LinkedIn, eventID, execID) VALUES (1, 'ABC Corp', 'Manager', 'Alex Turner', 'alex@email.com', 'None', 'linkedin.com/alex', 101, 1);
INSERT INTO Delegate (employeeID, company, position, name, email, dietaryRestrictions, LinkedIn, eventID, execID) VALUES (2, 'XYZ Ltd', 'Director', 'Emma White', 'emma@email.com', 'Vegetarian', 'linkedin.com/emma', 102, 2);
INSERT INTO Delegate (employeeID, company, position, name, email, dietaryRestrictions, LinkedIn, eventID, execID) VALUES (3, '123 Inc', 'CEO', 'Gary Davis', 'gary@email.com', 'None', 'linkedin.com/gary', 103, 3);
INSERT INTO Delegate (employeeID, company, position, name, email, dietaryRestrictions, LinkedIn, eventID, execID) VALUES (4, '456 Co', 'Engineer', 'Helen Smith', 'helen@email.com', 'Gluten-free', 'linkedin.com/helen', 104, 4);
INSERT INTO Delegate (employeeID, company, position, name, email, dietaryRestrictions, LinkedIn, eventID, execID) VALUES (5, '789 Tech', 'Analyst', 'Ivan Johnson', 'ivan@email.com', 'Vegan', 'linkedin.com/ivan', 105, 5);
