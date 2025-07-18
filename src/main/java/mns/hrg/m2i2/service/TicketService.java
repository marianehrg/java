package mns.hrg.m2i2.service;

import lombok.AllArgsConstructor;
import mns.hrg.m2i2.dao.entity.Ticket;
import mns.hrg.m2i2.dao.repository.TicketRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;

    public void addTickets(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllBySolved(boolean isSolved) {
        return ticketRepository.findAllByIsSolved(isSolved);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    public void markAsSolved(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setSolved(true);
        ticketRepository.save(ticket);
    }
}
