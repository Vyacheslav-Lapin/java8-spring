package com.luxoft.j8airport.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Status {
  PLATINUM(100000),
  GOLD(10000),
  SILVER(5000),
  NONE(0);

  int moneySpentLimit;

}
