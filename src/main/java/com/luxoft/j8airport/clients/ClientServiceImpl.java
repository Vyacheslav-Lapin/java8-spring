package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  ClientRepository clientRepository;

  @Override
  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public Client findById(Long clientId) {
    return null;
  }

  @Override
  public Map<Status, Set<Client>> getAllClientsGroupByStatus() {
    return null;
  }

  @Override
  public double getAverageAge() {
    return 0.0;
  }

  @Override
  public double getAverageAge(Status status) {
    return 0.0;
  }


}
