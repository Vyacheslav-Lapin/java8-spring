package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
  List<Ticket> findAllByClient(Client client);

  List<Ticket> findAllByFlight(Flight flight);
}
