package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/flights")
public class FlightsController {

  FlightService flightService;

  ClientService clientService;

  @GetMapping
  public List<Flight> getAllAvailableFlights(String city) {
    return flightService.getAllAvailableFlights();
  }

  @GetMapping("/alive")
  public String alive() {
    return "FlightsController ready to service";
  }

  @GetMapping("buyTicket")
  public Ticket buyTicket(@RequestParam Long clientId,
                          @RequestParam Long flightId) {
    return null;
  }
}
