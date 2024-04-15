package org.example.services;

import org.example.dao.ClientDaoImpl;
import org.example.entities.Client;

import java.util.List;

public class ClientCrudService {
    private ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

    public void saveClient(Client client) {
        clientDaoImpl.save(client);
    }

    public Client findClientById(Long id) {
        return clientDaoImpl.findById(id);
    }

    public List<Client> getAllClients () {return clientDaoImpl.getAllClients();}

    public void updateClient(Client client) {
        clientDaoImpl.update(client);
    }

    public void deleteClient(Client client) {
        clientDaoImpl.delete(client);
    }
}
