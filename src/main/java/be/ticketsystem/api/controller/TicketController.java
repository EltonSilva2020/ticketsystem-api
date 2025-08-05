package be.ticketsystem.api.controller;

import be.ticketsystem.api.dto.TicketDTO;
import be.ticketsystem.api.dto.request.ReqTicketDTO;
import be.ticketsystem.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TicketController {

    private static final String TICKET_ENDPOINT = "/tickets";
    private static final String TICKET_ENDPOINT_ID = TICKET_ENDPOINT + "/{id}";

    @Autowired
    private TicketService ticketService;

    @GetMapping(path = TICKET_ENDPOINT_ID)
    public ResponseEntity<Object> getTicket(@PathVariable(value = "id") String id) {

        TicketDTO ticket = ticketService.get(id);

        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }


    @GetMapping(path = TICKET_ENDPOINT)
    public ResponseEntity<Page<TicketDTO>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String sort) {

        Page<TicketDTO> tickets = ticketService.getAll(page, pageSize, orderBy, sort);
        return ResponseEntity.ok(tickets);
    }


    @PostMapping(path = TICKET_ENDPOINT)
    public ResponseEntity createTicket(@RequestBody ReqTicketDTO reqTicketDTO) {

        //    ticketService.create(reqTicketDTO);

        return new ResponseEntity<>("The resource was successfully created.", HttpStatus.CREATED);
    }

    @PutMapping(path = TICKET_ENDPOINT_ID)
    public ResponseEntity updateTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.update(ticketDTO);

        return new ResponseEntity<>("The resource was successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = TICKET_ENDPOINT_ID)
    public ResponseEntity deleteTicket() {

        return new ResponseEntity<>("The resource was successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
