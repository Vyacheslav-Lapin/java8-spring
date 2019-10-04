package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/clients")
public class ClientController {

  ClientService clientService;

  @GetMapping
  public List<Client> findAll() {
    return clientService.findAll();
  }

  @GetMapping("/alive")
  public String alive() {
    return "ClientController ready to service";
  }
}
