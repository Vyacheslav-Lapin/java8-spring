package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.tickets.discounts.DiscountService;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  DiscountService discountService;

  @NonFinal
  @Setter(onMethod_ = @Autowired)
  TicketRepository repository;

  @Override
  public Ticket calculateAndApplyDiscount(Ticket ticket) {
    return null;
  }

  @Override
  public Ticket createTicket(Client client, Flight flight) {
    return null;
  }

  @Override
  public Double getAverageMoneySpent() {

    return null;
  }

  @Override
  public Set<Client> getAllAboveAverageSpenders() {
    return null;
  }

  private Map<Client, Double> getClientToSpending() {
    return null;
  }


  @Override
  public Set<Client> getAllClientsAbove(int flightLimit) {
    return null;
  }

}
