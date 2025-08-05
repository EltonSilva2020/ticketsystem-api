package be.ticketsystem.api.repository;

import be.ticketsystem.api.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends AbstractRepository<Ticket> {}
