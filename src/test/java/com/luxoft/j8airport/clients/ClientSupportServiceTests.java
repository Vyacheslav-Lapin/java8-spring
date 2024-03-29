package com.luxoft.j8airport.clients;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClientSupportServiceTests {

  ClientSupportService clientSupportService;

  ClientService clientService;

  @Test
  public void generateAndStoreClientTest() {
    String name = "Irina";
    int age = 18;
    Client.Gender gender = Client.Gender.FEMALE;

    Client expected = clientSupportService.generateAndStoreClient(name, age, gender);

    Client actual = clientService.findById(expected.getId());

    assertEquals("generated client should be stored to DB", expected, actual);
  }

  @Test
  public void generateAndStoreClientTests1() {
    Status expectedStatus = Status.PLATINUM;
    int count = 5;

    clientSupportService.deleteAllGeneratedClients();

    clientSupportService.generateAndStoreClients(expectedStatus, count);


    List<Client> clients = clientService.findAll()
                               .stream()
                               .filter(c -> c.getStatus() == expectedStatus)
                               .collect(Collectors.toList());

    assertEquals("clients should be generated and stored to DB", count, clients.size());
  }

  @Test
  public void generateAndStoreClientTests2() {
    int count = 5;

    clientSupportService.deleteAllGeneratedClients();

    clientSupportService.generateAndStoreClients(Status.PLATINUM, count);


    List<Client> clients = clientService.findAll()
                               .stream()
                               .filter(c -> c.getName().startsWith(ClientSupportService.DEFAULT_CLIENT_NAME_PREFIX))
                               .collect(Collectors.toList());

    assertEquals("generated client name should start with "
                     + ClientSupportService.DEFAULT_CLIENT_NAME_PREFIX, count, clients.size());
  }

  @Test
  public void deleteAllGeneratedClientsTest() {
    clientSupportService.generateAndStoreClients(Status.PLATINUM, 5);

    clientSupportService.deleteAllGeneratedClients();

    List<Client> clients = clientService.findAll()
                               .stream()
                               .filter(c -> c.getName().startsWith(ClientSupportService.DEFAULT_CLIENT_NAME_PREFIX))
                               .collect(Collectors.toList());

    assertEquals("DB should contains 0 generated clients "
                     + ClientSupportService.DEFAULT_CLIENT_NAME_PREFIX, 0, clients.size());
  }

  @Test
  public void deleteAllTest() {
    clientSupportService.generateAndStoreClients(Status.PLATINUM, 5);

    clientSupportService.deleteAll();

    List<Client> clients = clientService.findAll();

    assertEquals("all clients should be  deleted", 0, clients.size());
  }

}
