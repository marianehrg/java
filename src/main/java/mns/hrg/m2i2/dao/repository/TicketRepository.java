package mns.hrg.m2i2.dao.repository;

import mns.hrg.m2i2.dao.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

    List<Ticket> findAllByIsSolved(boolean isSolved);
}
