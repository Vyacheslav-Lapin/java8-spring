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
import lombok.With;

@Data
@With
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
  Airport to;

  int distance;
  Duration flightTime;
  int maxPassengers;
}

