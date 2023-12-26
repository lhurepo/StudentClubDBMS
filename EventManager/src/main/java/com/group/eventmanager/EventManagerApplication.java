package com.group.eventmanager;

import com.group.eventmanager.database.*;
import com.group.eventmanager.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/event")
public class EventManagerApplication {

    private String username;
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(EventManagerApplication.class, args);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        this.username = loginRequest.getUsername();
        this.password = loginRequest.getPassword();

        AuthService authService = new AuthService();

        // Use the AuthService to authenticate
        boolean isAuthenticated;
        try (Connection ignored = authService.authenticate(username, password)) {
            isAuthenticated = true;
        } catch (Exception e) {
            isAuthenticated = false;
            e.printStackTrace();
        }

        if (isAuthenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    @PostMapping(value = "/insert-event", consumes = "application/json")
    public ResponseEntity<String> insertEventHandler(@RequestBody Event event) {
        databaseConnectHandlerEvent eventHandler = new databaseConnectHandlerEvent(this.username, this.password);
        boolean isSuccess = eventHandler.insertEvent(event);

        if (isSuccess) {
            return ResponseEntity.ok("Event insertion successful");
        } else {
            return ResponseEntity.status(500).body("Event insertion failed");
        }
    }

    @PostMapping(value = "/delete-event", consumes = "application/json")
    public ResponseEntity<String> deleteEventHandler(@RequestBody int eventID) {
        databaseConnectHandlerEvent eventHandler = new databaseConnectHandlerEvent(this.username, this.password);
        boolean isSuccess = eventHandler.deleteEvent(eventID);

        if (isSuccess) {
            return ResponseEntity.ok("Event deletion successful");
        } else {
            return ResponseEntity.status(500).body("Event deletion failed");
        }
    }

    @PostMapping(value = "/update-event", consumes = "application/json")
    public ResponseEntity<String> updateEventHandler(@RequestBody Event event) {
        databaseConnectHandlerEvent eventHandler = new databaseConnectHandlerEvent(this.username, this.password);
        boolean isSuccess = eventHandler.updateEvent(event);

        if (isSuccess) {
            return ResponseEntity.ok("Event update successful");
        } else {
            return ResponseEntity.status(500).body("Event update failed");
        }
    }

    @GetMapping("/get-event-info")
    public ResponseEntity<Event[]> getEventInfoHandler() {
        databaseConnectHandlerEvent getInfoHandler = new databaseConnectHandlerEvent(this.username, this.password);
        Event[] events = getInfoHandler.getEventInfo();

        if (events.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(events);
        }
    }
    //---------------------------------------------------------------------------------------
    @PostMapping(value = "/insert-venue", consumes = "application/json")
    public ResponseEntity<String> insertVenueHandler(@RequestBody Venue venue) {
        databaseConnectHandlerVenue venueHandler = new databaseConnectHandlerVenue(this.username, this.password);
        boolean isSuccess = venueHandler.insertVenue(venue);

        if (isSuccess) {
            return ResponseEntity.ok("Venue insertion successful");
        } else {
            return ResponseEntity.status(500).body("Venue insertion failed");
        }
    }

    @PostMapping(value = "/delete-venue", consumes = "application/json")
    public ResponseEntity<String> deleteVenueHandler(@RequestBody int venueID) {
        databaseConnectHandlerVenue venueHandler = new databaseConnectHandlerVenue(this.username, this.password);
        boolean isSuccess = venueHandler.deleteVenue(venueID);

        if (isSuccess) {
            return ResponseEntity.ok("Venue deletion successful");
        } else {
            return ResponseEntity.status(500).body("Venue deletion failed");
        }
    }

    @PostMapping(value = "/update-venue", consumes = "application/json")
    public ResponseEntity<String> updateVenueHandler(@RequestBody Venue venue) {
        databaseConnectHandlerVenue venueHandler = new databaseConnectHandlerVenue(this.username, this.password);
        boolean isSuccess = venueHandler.updateVenue(venue);

        if (isSuccess) {
            return ResponseEntity.ok("Venue update successful");
        } else {
            return ResponseEntity.status(500).body("Venue update failed");
        }
    }

    @GetMapping("/get-venue-info")
    public ResponseEntity<Venue[]> getVenueInfoHandler() {
        databaseConnectHandlerVenue getInfoHandler = new databaseConnectHandlerVenue(this.username, this.password);
        Venue[] venues = getInfoHandler.getVenueInfo();

        if (venues.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(venues);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-marketing-director", consumes = "application/json")
    public ResponseEntity<String> insertMarketingDirectorHandler(@RequestBody MarketingDirector marketingDirector) {
        databaseConnectHandlerMarketingDirector mdHandler = new databaseConnectHandlerMarketingDirector(this.username, this.password);
        boolean isSuccess = mdHandler.insertMarketingDirector(marketingDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Marketing Director insertion successful");
        } else {
            return ResponseEntity.status(500).body("Marketing Director insertion failed");
        }
    }

    @PostMapping(value = "/delete-marketing-director", consumes = "application/json")
    public ResponseEntity<String> deleteMarketingDirectorHandler(@RequestBody int execID) {
        databaseConnectHandlerMarketingDirector mdHandler = new databaseConnectHandlerMarketingDirector(this.username, this.password);
        boolean isSuccess = mdHandler.deleteMarketingDirector(execID);

        if (isSuccess) {
            return ResponseEntity.ok("Marketing Director deletion successful");
        } else {
            return ResponseEntity.status(500).body("Marketing Director deletion failed");
        }
    }

    @PostMapping(value = "/update-marketing-director", consumes = "application/json")
    public ResponseEntity<String> updateMarketingDirectorHandler(@RequestBody MarketingDirector marketingDirector) {
        databaseConnectHandlerMarketingDirector mdHandler = new databaseConnectHandlerMarketingDirector(this.username, this.password);
        boolean isSuccess = mdHandler.updateMarketingDirector(marketingDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Marketing Director update successful");
        } else {
            return ResponseEntity.status(500).body("Marketing Director update failed");
        }
    }

    @GetMapping("/get-marketing-director-info")
    public ResponseEntity<MarketingDirector[]> getMarketingDirectorInfoHandler() {
        databaseConnectHandlerMarketingDirector getInfoHandler = new databaseConnectHandlerMarketingDirector(this.username, this.password);
        MarketingDirector[] marketingDirectors = getInfoHandler.getMarketingDirectorInfo();

        if (marketingDirectors.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(marketingDirectors);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-partnership-director", consumes = "application/json")
    public ResponseEntity<String> insertPartnershipDirectorHandler(@RequestBody PartnershipDirector partnershipDirector) {
        databaseConnectHandlerPartnershipDirector pdHandler = new databaseConnectHandlerPartnershipDirector(username, password);
        boolean isSuccess = pdHandler.insertPartnershipDirector(partnershipDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Partnership Director insertion successful");
        } else {
            return ResponseEntity.status(500).body("Partnership Director insertion failed");
        }
    }

    @PostMapping(value = "/delete-partnership-director", consumes = "application/json")
    public ResponseEntity<String> deletePartnershipDirectorHandler(@RequestBody int execID) {
        databaseConnectHandlerPartnershipDirector pdHandler = new databaseConnectHandlerPartnershipDirector(username, password);
        boolean isSuccess = pdHandler.deletePartnershipDirector(execID);

        if (isSuccess) {
            return ResponseEntity.ok("Partnership Director deletion successful");
        } else {
            return ResponseEntity.status(500).body("Partnership Director deletion failed");
        }
    }

    @PostMapping(value = "/update-partnership-director", consumes = "application/json")
    public ResponseEntity<String> updatePartnershipDirectorHandler(@RequestBody PartnershipDirector partnershipDirector) {
        databaseConnectHandlerPartnershipDirector pdHandler = new databaseConnectHandlerPartnershipDirector(username, password);
        boolean isSuccess = pdHandler.updatePartnershipDirector(partnershipDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Partnership Director update successful");
        } else {
            return ResponseEntity.status(500).body("Partnership Director update failed");
        }
    }

    @GetMapping("/get-partnership-director-info")
    public ResponseEntity<PartnershipDirector[]> getPartnershipDirectorInfoHandler() {
        databaseConnectHandlerPartnershipDirector getInfoHandler = new databaseConnectHandlerPartnershipDirector(username, password);
        PartnershipDirector[] partnershipDirectors = getInfoHandler.getPartnershipDirectorInfo();

        if (partnershipDirectors.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partnershipDirectors);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-feedback-form", consumes = "application/json")
    public ResponseEntity<String> insertFeedbackFormHandler(@RequestBody FeedbackForm feedbackForm) {
        databaseConnectHandlerFeedbackForm formHandler = new databaseConnectHandlerFeedbackForm(this.username, this.password);
        boolean isSuccess = formHandler.insertFeedbackForm(feedbackForm);

        if (isSuccess) {
            return ResponseEntity.ok("FeedbackForm insertion successful");
        } else {
            return ResponseEntity.status(500).body("FeedbackForm insertion failed");
        }
    }

    @PostMapping(value = "/delete-feedback-form", consumes = "application/json")
    public ResponseEntity<String> deleteFeedbackFormHandler(@RequestBody int formID) {
        databaseConnectHandlerFeedbackForm formHandler = new databaseConnectHandlerFeedbackForm(this.username, this.password);
        boolean isSuccess = formHandler.deleteFeedbackForm(formID);

        if (isSuccess) {
            return ResponseEntity.ok("FeedbackForm deletion successful");
        } else {
            return ResponseEntity.status(500).body("FeedbackForm deletion failed");
        }
    }

    @PostMapping(value = "/update-feedback-form", consumes = "application/json")
    public ResponseEntity<String> updateFeedbackFormHandler(@RequestBody FeedbackForm feedbackForm) {
        databaseConnectHandlerFeedbackForm formHandler = new databaseConnectHandlerFeedbackForm(this.username, this.password);
        boolean isSuccess = formHandler.updateFeedbackForm(feedbackForm);

        if (isSuccess) {
            return ResponseEntity.ok("FeedbackForm update successful");
        } else {
            return ResponseEntity.status(500).body("FeedbackForm update failed");
        }
    }

    @GetMapping("/get-feedback-form-info")
    public ResponseEntity<FeedbackForm[]> getFeedbackFormInfoHandler() {
        databaseConnectHandlerFeedbackForm getInfoHandler = new databaseConnectHandlerFeedbackForm(this.username, this.password);
        FeedbackForm[] forms = getInfoHandler.getFeedbackFormInfo();

        if (forms.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(forms);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-infographic", consumes = "application/json")
    public ResponseEntity<String> insertInfographicHandler(@RequestBody Infographic infographic) {
        databaseConnectHandlerInfographic infographicHandler = new databaseConnectHandlerInfographic(this.username, this.password);
        boolean isSuccess = infographicHandler.insertInfographic(infographic);

        if (isSuccess) {
            return ResponseEntity.ok("Infographic insertion successful");
        } else {
            return ResponseEntity.status(500).body("Infographic insertion failed");
        }
    }

    @PostMapping(value = "/delete-infographic", consumes = "application/json")
    public ResponseEntity<String> deleteInfographicHandler(@RequestBody String filename) {
        databaseConnectHandlerInfographic infographicHandler = new databaseConnectHandlerInfographic(this.username, this.password);
        boolean isSuccess = infographicHandler.deleteInfographic(filename);

        if (isSuccess) {
            return ResponseEntity.ok("Infographic deletion successful");
        } else {
            return ResponseEntity.status(500).body("Infographic deletion failed");
        }
    }

    @PostMapping(value = "/update-infographic", consumes = "application/json")
    public ResponseEntity<String> updateInfographicHandler(@RequestBody Infographic infographic) {
        databaseConnectHandlerInfographic infographicHandler = new databaseConnectHandlerInfographic(this.username, this.password);
        boolean isSuccess = infographicHandler.updateInfographic(infographic);

        if (isSuccess) {
            return ResponseEntity.ok("Infographic update successful");
        } else {
            return ResponseEntity.status(500).body("Infographic update failed");
        }
    }

    @GetMapping("/get-infographic-info")
    public ResponseEntity<Infographic[]> getInfographicInfoHandler() {
        databaseConnectHandlerInfographic getInfoHandler = new databaseConnectHandlerInfographic(this.username, this.password);
        Infographic[] infographics = getInfoHandler.getInfographicInfo();

        if (infographics.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(infographics);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-proposal", consumes = "application/json")
    public ResponseEntity<String> insertProposalHandler(@RequestBody Proposal proposal) {
        databaseConnectHandlerProposal proposalHandler = new databaseConnectHandlerProposal(this.username, this.password);
        boolean isSuccess = proposalHandler.insertProposal(proposal);

        if (isSuccess) {
            return ResponseEntity.ok("Proposal insertion successful");
        } else {
            return ResponseEntity.status(500).body("Proposal insertion failed");
        }
    }

    @PostMapping(value = "/delete-proposal", consumes = "application/json")
    public ResponseEntity<String> deleteProposalHandler(@RequestBody int proposalID) {
        databaseConnectHandlerProposal proposalHandler = new databaseConnectHandlerProposal(this.username, this.password);
        boolean isSuccess = proposalHandler.deleteProposal(proposalID);

        if (isSuccess) {
            return ResponseEntity.ok("Proposal deletion successful");
        } else {
            return ResponseEntity.status(500).body("Proposal deletion failed");
        }
    }

    @PostMapping(value = "/update-proposal", consumes = "application/json")
    public ResponseEntity<String> updateProposalHandler(@RequestBody Proposal proposal) {
        databaseConnectHandlerProposal proposalHandler = new databaseConnectHandlerProposal(this.username, this.password);
        boolean isSuccess = proposalHandler.updateProposal(proposal);

        if (isSuccess) {
            return ResponseEntity.ok("Proposal update successful");
        } else {
            return ResponseEntity.status(500).body("Proposal update failed");
        }
    }

    @GetMapping("/get-proposal-info")
    public ResponseEntity<Proposal[]> getProposalInfoHandler() {
        databaseConnectHandlerProposal getInfoHandler = new databaseConnectHandlerProposal(this.username, this.password);
        Proposal[] proposals = getInfoHandler.getProposalInfo();

        if (proposals.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(proposals);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-ticket", consumes = "application/json")
    public ResponseEntity<String> insertTicketHandler(@RequestBody Ticket ticket) {
        databaseConnectHandlerTicket ticketHandler = new databaseConnectHandlerTicket(this.username, this.password);
        boolean isSuccess = ticketHandler.insertTicket(ticket);

        if (isSuccess) {
            return ResponseEntity.ok("Ticket insertion successful");
        } else {
            return ResponseEntity.status(500).body("Ticket insertion failed");
        }
    }

    @PostMapping(value = "/delete-ticket", consumes = "application/json")
    public ResponseEntity<String> deleteTicketHandler(@RequestBody Map<String, Integer> payload) {
        int ticketID = payload.get("ticketID");
        int eventID = payload.get("eventID");

        databaseConnectHandlerTicket ticketHandler = new databaseConnectHandlerTicket(this.username, this.password);
        boolean isSuccess = ticketHandler.deleteTicket(ticketID, eventID);

        if (isSuccess) {
            return ResponseEntity.ok("Ticket deletion successful");
        } else {
            return ResponseEntity.status(500).body("Ticket deletion failed");
        }
    }

    @PostMapping(value = "/update-ticket", consumes = "application/json")
    public ResponseEntity<String> updateTicketHandler(@RequestBody Ticket ticket) {
        databaseConnectHandlerTicket ticketHandler = new databaseConnectHandlerTicket(this.username, this.password);
        boolean isSuccess = ticketHandler.updateTicket(ticket);

        if (isSuccess) {
            return ResponseEntity.ok("Ticket update successful");
        } else {
            return ResponseEntity.status(500).body("Ticket update failed");
        }
    }

    @GetMapping("/get-ticket-info")
    public ResponseEntity<Ticket[]> getTicketInfoHandler() {
        databaseConnectHandlerTicket getInfoHandler = new databaseConnectHandlerTicket(this.username, this.password);
        Ticket[] tickets = getInfoHandler.getTicketInfo();

        if (tickets.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tickets);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-event-director", consumes = "application/json")
    public ResponseEntity<String> insertEventDirectorHandler(@RequestBody EventDirector eventDirector) {
        databaseConnectHandlerEventDirector eventDirectorHandler = new databaseConnectHandlerEventDirector(username, password);
        boolean isSuccess = eventDirectorHandler.insertEventDirector(eventDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Event Director insertion successful");
        } else {
            return ResponseEntity.status(500).body("Event Director insertion failed");
        }
    }

    @PostMapping(value = "/delete-event-director", consumes = "application/json")
    public ResponseEntity<String> deleteEventDirectorHandler(@RequestBody int execID) {
        databaseConnectHandlerEventDirector eventDirectorHandler = new databaseConnectHandlerEventDirector(username, password);
        boolean isSuccess = eventDirectorHandler.deleteEventDirector(execID);

        if (isSuccess) {
            return ResponseEntity.ok("Event Director deletion successful");
        } else {
            return ResponseEntity.status(500).body("Event Director deletion failed");
        }
    }

    @PostMapping(value = "/update-event-director", consumes = "application/json")
    public ResponseEntity<String> updateEventDirectorHandler(@RequestBody EventDirector eventDirector) {
        databaseConnectHandlerEventDirector eventDirectorHandler = new databaseConnectHandlerEventDirector(username, password);
        boolean isSuccess = eventDirectorHandler.updateEventDirector(eventDirector);

        if (isSuccess) {
            return ResponseEntity.ok("Event Director update successful");
        } else {
            return ResponseEntity.status(500).body("Event Director update failed");
        }
    }

    @GetMapping("/get-event-director-info")
    public ResponseEntity<EventDirector[]> getEventDirectorInfoHandler() {
        databaseConnectHandlerEventDirector eventDirectorHandler = new databaseConnectHandlerEventDirector(username, password);
        EventDirector[] eventDirectors = eventDirectorHandler.getEventDirectorInfo();

        if (eventDirectors.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(eventDirectors);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-student", consumes = "application/json")
    public ResponseEntity<String> insertStudentHandler(@RequestBody Student student) {
        databaseConnectHandlerStudent studentHandler = new databaseConnectHandlerStudent(this.username, this.password);
        boolean isSuccess = studentHandler.insertStudent(student);

        if (isSuccess) {
            return ResponseEntity.ok("Student insertion successful");
        } else {
            return ResponseEntity.status(500).body("Student insertion failed");
        }
    }

    @PostMapping(value = "/delete-student", consumes = "application/json")
    public ResponseEntity<String> deleteStudentHandler(@RequestBody int studentID) {
        databaseConnectHandlerStudent studentHandler = new databaseConnectHandlerStudent(this.username, this.password);
        boolean isSuccess = studentHandler.deleteStudent(studentID);

        if (isSuccess) {
            return ResponseEntity.ok("Student deletion successful");
        } else {
            return ResponseEntity.status(500).body("Student deletion failed");
        }
    }

    @PostMapping(value = "/update-student", consumes = "application/json")
    public ResponseEntity<String> updateStudentHandler(@RequestBody Student student) {
        databaseConnectHandlerStudent studentHandler = new databaseConnectHandlerStudent(this.username, this.password);
        boolean isSuccess = studentHandler.updateStudent(student);

        if (isSuccess) {
            return ResponseEntity.ok("Student update successful");
        } else {
            return ResponseEntity.status(500).body("Student update failed");
        }
    }

    @GetMapping("/get-student-info")
    public ResponseEntity<Student[]> getStudentInfoHandler() {
        databaseConnectHandlerStudent getInfoHandler = new databaseConnectHandlerStudent(this.username, this.password);
        Student[] students = getInfoHandler.getStudentInfo();

        if (students.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    //---------------------------------------------------------------------------------------

    @PostMapping(value = "/insert-delegate", consumes = "application/json")
    public ResponseEntity<String> insertDelegateHandler(@RequestBody Delegate delegate) {
        databaseConnectHandlerDelegate delegateHandler = new databaseConnectHandlerDelegate(this.username, this.password);
        boolean isSuccess = delegateHandler.insertDelegate(delegate);

        if (isSuccess) {
            return ResponseEntity.ok("Delegate insertion successful");
        } else {
            return ResponseEntity.status(500).body("Delegate insertion failed");
        }
    }

    @PostMapping(value = "/delete-delegate", consumes = "application/json")
    public ResponseEntity<String> deleteDelegateHandler(@RequestBody int employeeID) {
        databaseConnectHandlerDelegate delegateHandler = new databaseConnectHandlerDelegate(this.username, this.password);
        boolean isSuccess = delegateHandler.deleteDelegate(employeeID);

        if (isSuccess) {
            return ResponseEntity.ok("Delegate deletion successful");
        } else {
            return ResponseEntity.status(500).body("Delegate deletion failed");
        }
    }

    @PostMapping(value = "/update-delegate", consumes = "application/json")
    public ResponseEntity<String> updateDelegateHandler(@RequestBody Delegate delegate) {
        databaseConnectHandlerDelegate delegateHandler = new databaseConnectHandlerDelegate(this.username, this.password);
        boolean isSuccess = delegateHandler.updateDelegate(delegate);

        if (isSuccess) {
            return ResponseEntity.ok("Delegate update successful");
        } else {
            return ResponseEntity.status(500).body("Delegate update failed");
        }
    }

    @GetMapping("/get-delegate-info")
    public ResponseEntity<Delegate[]> getDelegateInfoHandler() {
        databaseConnectHandlerDelegate getInfoHandler = new databaseConnectHandlerDelegate(this.username, this.password);
        Delegate[] delegates = getInfoHandler.getDelegateInfo();

        if (delegates.length == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(delegates);
        }
    }

}
