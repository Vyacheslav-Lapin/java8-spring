package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.tickets.TicketRepository;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDiscountService implements DiscountService {

  @NonFinal
  @Setter(onMethod_ = @Autowired)
  TicketRepository ticketRepository;

  @Override
  public double getPersonalPrice(Client client, double price) {
    return 0.0;
  }

  private double getDiscountMultiplier(double moneySpent) {
    double multiplier = 1.0;

    if (moneySpent >= Status.PLATINUM.getMoneySpentLimit()) {
      multiplier = 0.85;
    } else if (moneySpent >= Status.GOLD.getMoneySpentLimit()) {
      multiplier = 0.9;
    } else if (moneySpent >= Status.SILVER.getMoneySpentLimit()) {
      multiplier = 0.95;
    }

    return multiplier;
  }

}
