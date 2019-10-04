package com.luxoft.j8airport.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Flight {

  @Id
  @GeneratedValue
  Long id;

  @NonNull
  @ManyToOne
  FlightCard flightCard;

  ZonedDateTime departure;
  ZonedDateTime arrive;

  State state = State.NONE;

  @OneToMany
  @JoinColumn(name = "FK_FLIGHT_ID")
  List<Ticket> ticketsBought = new ArrayList<>();

  public void addTicket(Ticket ticket) {
    ticketsBought.add(ticket);
  }

  public Flight startBoarding() {
    state = State.BOARDING;
    return this;
  }

  public void takeOff() {
    state = State.IN_THE_SKY;
  }

  public List<Ticket> getTicketsBought() {
    return new ArrayList<>(ticketsBought);
  }

  public void setTicketsBought(List<Ticket> ticketsBought) {
    this.ticketsBought = new ArrayList<>(ticketsBought);
  }

  public enum State {
    NONE, BOARDING, IN_THE_SKY, LANDED
  }
}
