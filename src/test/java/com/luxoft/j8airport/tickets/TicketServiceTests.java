package com.luxoft.j8airport.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.flights.FlightService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketServiceTests {
  TicketService ticketService;

  TicketRepository ticketRepository;
  ClientService clientService;
  ClientSupportService clientSupportService;
  FlightService flightService;

  @Mock
  @NonFinal
  TicketRepository ticketRepositoryMock;

  @BeforeEach
  public void setup() {
    clientSupportService.generateAndStoreClient("Oleg", 34, Client.Gender.MALE);
    clientSupportService.generateAndStoreClient("Irina", 27, Client.Gender.FEMALE);
    flightService.loadFlights();

    ticketRepositoryMock = mock(TicketRepository.class);

    ticketService.setRepository(ticketRepositoryMock);
  }

  @AfterEach
  public void cleanDB() {
    clientSupportService.deleteAllGeneratedClients();
  }


  @Test
  public void createTicketTest() {
    Client client = clientService.findAll().get(0);
    Flight flight = flightService.getAllAvailableFlights().get(0);

    ticketService.setRepository(ticketRepository);
    Ticket ticket = ticketService.createTicket(client, flight);

    assertNotNull(ticket.getId(), "--> ticket should be stored and contain id");
  }

  @Test
  @DisplayName("--> average price of 100 + 400 expected")
  public void getAverageMoneySpentTest1() {
    Client client = clientService.findAll().get(0);

    Ticket cheap = mock(Ticket.class);
    when(cheap.getPrice()).thenReturn(100.0);
    when(cheap.getClient()).thenReturn(client);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getPrice()).thenReturn(400.0);
    when(expensive.getClient()).thenReturn(client);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap, expensive));

    double expected = 250.0;

    assertEquals(expected, ticketService.getAverageMoneySpent(), 0);
  }

  @Test
  @DisplayName("--> average price of 275.0 expected with 2 clients")
  public void getAverageMoneySpentTest2() {
    Client client0 = clientService.findAll().get(0);
    Client client1 = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getPrice()).thenReturn(100.0);
    when(cheap0.getClient()).thenReturn(client0);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getPrice()).thenReturn(200.0);
    when(cheap1.getClient()).thenReturn(client0);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getPrice()).thenReturn(400.0);
    when(expensive.getClient()).thenReturn(client1);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    double expected = ((100 + 200) / 2 + 400) / 2; // 275.0

    assertEquals(expected, ticketService.getAverageMoneySpent(), 0);
  }

  @Test
  public void getAllAboveAverageSpenders1() {
    Client poor = clientService.findAll().get(0);
    Client rich = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getPrice()).thenReturn(100.0);
    when(cheap0.getClient()).thenReturn(poor);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getPrice()).thenReturn(200.0);
    when(cheap1.getClient()).thenReturn(poor);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getPrice()).thenReturn(400.0);
    when(expensive.getClient()).thenReturn(rich);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    assertTrue(ticketService.getAllAboveAverageSpenders().contains(rich), "--> rich client expected");
  }

  @Test
  public void getAllAboveAverageSpenders2() {
    Client poor = clientService.findAll().get(0);
    Client rich = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getPrice()).thenReturn(100.0);
    when(cheap0.getClient()).thenReturn(poor);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getPrice()).thenReturn(200.0);
    when(cheap1.getClient()).thenReturn(poor);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getPrice()).thenReturn(150.0);
    when(expensive.getClient()).thenReturn(rich);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    assertEquals(0, ticketService.getAllAboveAverageSpenders().size(), "--> no clients expected (average - $150)");
  }

  @Test
  public void getAllClientsAbove1() {
    Client poor = clientService.findAll().get(0);
    Client rich = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getClient()).thenReturn(poor);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getClient()).thenReturn(poor);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getClient()).thenReturn(rich);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    assertEquals(0, ticketService.getAllClientsAbove(3).size(), "--> no clients with 3+ flights expected");
  }

  @Test
  public void getAllClientsAbove2() {
    Client poor = clientService.findAll().get(0);
    Client rich = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getClient()).thenReturn(poor);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getClient()).thenReturn(poor);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getClient()).thenReturn(rich);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    assertTrue(ticketService.getAllClientsAbove(1).contains(poor), "--> poor client with 2 flights expected");
  }

  @Test
  public void getAllClientsAbove3() {
    Client poor = clientService.findAll().get(0);
    Client rich = clientService.findAll().get(1);

    Ticket cheap0 = mock(Ticket.class);
    when(cheap0.getClient()).thenReturn(poor);

    Ticket cheap1 = mock(Ticket.class);
    when(cheap1.getClient()).thenReturn(poor);

    Ticket expensive = mock(Ticket.class);
    when(expensive.getClient()).thenReturn(rich);

    when(ticketRepositoryMock.findAll()).thenReturn(Arrays.asList(cheap0, cheap1, expensive));

    assertEquals(2, ticketService.getAllClientsAbove(0).size(), "--> 2 clients expected");
  }

}
