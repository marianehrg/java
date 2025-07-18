package mns.hrg.m2i2.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mns.hrg.m2i2.dao.entity.Ticket;
import mns.hrg.m2i2.security.AppUserDetails;
import mns.hrg.m2i2.security.IsAdministrator;
import mns.hrg.m2i2.service.TicketService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        try {
            ticketService.addTickets(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ticket created");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contrainte violée en base");
        }
    }

    @PutMapping("/{id}/solved")
    @IsAdministrator
    public ResponseEntity<String> markAsSolved(@PathVariable Long id,
                                               @AuthenticationPrincipal AppUserDetails userDetails) {
        if(userDetails.getUser().isAdmin()){
            try {
                ticketService.markAsSolved(id);
                return ResponseEntity.ok("Ticket updated");
            } catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket not found");
            }
        }
        return ResponseEntity.ok("Ticket not updated, you're not admin");
    }
}
