package com.luxoft.j8airport.tickets.discounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.tickets.TicketRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PersonalDiscountServiceTests {

  final ClientSupportService clientSupportService;

  final DiscountService discountService;

  @Mock
  TicketRepository ticketRepositoryMock;

  private Map<Status, Client> clientMap;

  @BeforeEach
  public void setup() {
    clientMap = new HashMap<>(4);

    clientMap.put(Status.NONE, clientSupportService.generateAndStoreClients(Status.NONE, 1).get(0));
    clientMap.put(Status.SILVER, clientSupportService.generateAndStoreClients(Status.SILVER, 1).get(0));
    clientMap.put(Status.GOLD, clientSupportService.generateAndStoreClients(Status.GOLD, 1).get(0));
    clientMap.put(Status.PLATINUM, clientSupportService.generateAndStoreClients(Status.PLATINUM, 1).get(0));

    ticketRepositoryMock = mock(TicketRepository.class);

    List<Ticket> noPrice = Arrays.asList(new Ticket[]{new Ticket()});

    Ticket silverPlus = mock(Ticket.class);
    when(silverPlus.getPrice()).thenReturn(Status.SILVER.getMoneySpentLimit() + 1.0);

    Ticket goldPlus = mock(Ticket.class);
    when(goldPlus.getPrice()).thenReturn(Status.GOLD.getMoneySpentLimit() + 1.0);

    Ticket platinumPlus = mock(Ticket.class);
    when(platinumPlus.getPrice()).thenReturn(Status.PLATINUM.getMoneySpentLimit() + 1.0);


    when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.NONE))).thenReturn(noPrice);

    when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.SILVER)))
        .thenReturn(Arrays.asList(new Ticket[]{silverPlus}));

    when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.GOLD)))
        .thenReturn(Arrays.asList(new Ticket[]{goldPlus}));

    when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.PLATINUM)))
        .thenReturn(Arrays.asList(new Ticket[]{platinumPlus}));

    discountService.setTicketRepository(ticketRepositoryMock);
  }

  public void clear() {
    clientSupportService.deleteAllGeneratedClients();
  }

  @Test
  @DisplayName("--> getPersonalPrice for client with Status.NONE")
  public void getPersonalPriceTest1() {
    double beforeDiscount = 10;

    double expected = 10;
    double actual = discountService.getPersonalPrice(clientMap.get(Status.NONE), beforeDiscount);

    assertEquals(expected, actual, 0);
  }

  @Test
  @DisplayName("--> getPersonalPrice for client with Status.SILVER")
  public void getPersonalPriceTest2() {
    double beforeDiscount = 10;

    double expected = 9.5;
    double actual = discountService.getPersonalPrice(clientMap.get(Status.SILVER), beforeDiscount);

    assertEquals(expected, actual, 0);
  }

  @Test
  @DisplayName("--> getPersonalPrice for client with Status.GOLD")
  public void getPersonalPriceTest3() {
    double beforeDiscount = 10;

    double expected = 9;
    double actual = discountService.getPersonalPrice(clientMap.get(Status.GOLD), beforeDiscount);

    assertEquals(expected, actual, 0);
  }

  @Test
  @DisplayName("--> getPersonalPrice for client with Status.PLATINUM")
  public void getPersonalPriceTest4() {
    double beforeDiscount = 10;

    double expected = 8.5;
    double actual = discountService.getPersonalPrice(clientMap.get(Status.PLATINUM), beforeDiscount);

    assertEquals(expected, actual, 0);
  }

}
