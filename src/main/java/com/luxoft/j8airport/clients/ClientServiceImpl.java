package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long clientId)
    {
        return null;
    }

    @Override
    public Map<Status, Set<Client>> getAllClientsGroupByStatus()
    {
        return null;
    }

    @Override
    public double getAverageAge()
    {
        return 0.0;
    }

    @Override
    public double getAverageAge(Status status)
    {
        return 0.0;
    }


}
