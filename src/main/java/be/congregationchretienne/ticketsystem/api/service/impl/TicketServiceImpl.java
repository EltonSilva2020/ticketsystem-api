package be.congregationchretienne.ticketsystem.api.service.impl;

import static be.congregationchretienne.ticketsystem.api.mapping.TicketMapping.INSTANCE_TICKET;

import be.congregationchretienne.ticketsystem.api.dto.TicketDTO;
import be.congregationchretienne.ticketsystem.api.exception.NotFoundException;
import be.congregationchretienne.ticketsystem.api.mapping.CycleAvoidingMappingContext;
import be.congregationchretienne.ticketsystem.api.model.Ticket;
import be.congregationchretienne.ticketsystem.api.repository.TicketRepository;
import be.congregationchretienne.ticketsystem.api.service.TicketService;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Getter
@Transactional
public class TicketServiceImpl extends AbstractServiceImpl implements TicketService {

  @Autowired private TicketRepository repository;

  public TicketServiceImpl(TicketRepository repository) {
    super(repository);
  }

  @Override
  public TicketDTO get(String id) {
    var uuid = checkAndConvertID(id);

    Optional<Ticket> ticket = repository.findById(uuid);

    ticket.orElseThrow(
        () ->
            new NotFoundException(
                String.format("The ticket with reference [%s] was not found.", id)));

    return INSTANCE_TICKET.entityToDTO(ticket.get(), new CycleAvoidingMappingContext());
  }

  @Override
  public Page<TicketDTO> getAll(Integer page, Integer pageSize, String orderBy, String sort) {

    Pageable pageable = null;

    if (pageSize <= 0 || pageSize > 50) {
      throw new IllegalArgumentException("The number items per page should be between 1 and 50.");
    }
    if (null == orderBy || orderBy.isEmpty()) {
      pageable = PageRequest.of(page, pageSize);

    } else {

      try {
        pageable = PageRequest.of(page, pageSize).withSort(Sort.by(orderBy));

      } catch (Exception e) {
        throw new IllegalArgumentException(
            String.format("The parameter %s is invalid.", orderBy.toString()));
      }
    }

    Page<Ticket> ticket = repository.findAll(pageable);

    return ticket.map(
        currentTicket ->
            (INSTANCE_TICKET.entityToDTO(currentTicket, new CycleAvoidingMappingContext())));
  }

  @Transactional
  @Override
  public void create(TicketDTO bean) {
    Ticket ticket = INSTANCE_TICKET.dtoToEntity(bean);

    repository.save(ticket);
  }

  @Override
  public void update(TicketDTO ticketDTO) {
    checkAndConvertID(ticketDTO.id());

    repository.save(INSTANCE_TICKET.dtoToEntity(ticketDTO));
  }

  @Override
  public void delete(String id) {
    var uuid = checkAndConvertID(id);

    repository.deleteById(uuid);
  }
}
