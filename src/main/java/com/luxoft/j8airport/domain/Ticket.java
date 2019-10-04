package com.luxoft.j8airport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Ticket {

  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  Flight flight;

  @ManyToOne
  Client client;

  double price;

  double discount;
}
