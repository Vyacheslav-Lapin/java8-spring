package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.tickets.discounts.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService
{
    @Autowired
    private DiscountService discountService;

    private TicketRepository repository;

    @Override
    public Ticket calculateAndApplyDiscount(Ticket ticket)
    {
        return null;
    }

    @Override
    public Ticket createTicket(Client client, Flight flight)
    {
        return null;
    }

    @Override
    public Double getAverageMoneySpent()
    {

        return null;
    }

    @Override
    public Set<Client> getAllAboveAverageSpenders()
    {
        return null;
    }

    private Map<Client, Double> getClientToSpending()
    {
        return null;
    }


    @Override
    public Set<Client> getAllClientsAbove(int flightLimit)
    {
        return null;
    }

    @Autowired
    public void setRepository(TicketRepository repository)
    {
        this.repository = repository;
    }
}
