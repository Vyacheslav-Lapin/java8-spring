package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  List<Client> findByStatus(Status status);

  void deleteAllByNameStartsWith(String prefix);
}
