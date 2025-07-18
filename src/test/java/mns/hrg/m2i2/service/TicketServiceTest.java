package mns.hrg.m2i2.service;

import mns.hrg.m2i2.dao.entity.Ticket;
import mns.hrg.m2i2.dao.repository.TicketRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void cleanDB() {
        ticketRepository.deleteAll();
    }

    @Test
    void shouldSaveTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Title test");
        ticket.setDescription("Description test");
        ticket.setSolved(false);

        ticketService.addTickets(ticket);

        List<Ticket> all = ticketService.getAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getTitle()).isEqualTo("Title test");
    }

    @Test
    void shouldReturnTicketsBySolvedStatus() {
        Ticket solved = new Ticket();
        solved.setTitle("solved");
        solved.setDescription("Description test");
        solved.setSolved(true);

        Ticket notSolved = new Ticket();
        notSolved.setTitle("not solved");
        notSolved.setDescription("Description test");
        notSolved.setSolved(false);

        ticketRepository.saveAll(List.of(solved, notSolved));

        List<Ticket> found = ticketService.getAllBySolved(false);

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getTitle()).isEqualTo("not solved");
    }

}
