package com.luxoft.j8airport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Airport {

  @Id
  @GeneratedValue
  Long id;

  String country;
  String city;
  String name;
  String zoneId;
}
