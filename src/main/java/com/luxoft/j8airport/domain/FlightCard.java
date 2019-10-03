package com.luxoft.j8airport.domain;

import java.time.Duration;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FlightCard {

  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  Airport from;

  @ManyToOne
  private Airport to;

  int distance;

  Duration flightTime;

  int maxPassengers;


}

