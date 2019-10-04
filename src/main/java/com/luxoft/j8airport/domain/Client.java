package com.luxoft.j8airport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Client {

  @Id
  @GeneratedValue
  long id;

  String name;
  int age;
  Gender gender;
  Status status = Status.NONE;

  public enum Gender {
    MALE, FEMALE
  }
}
