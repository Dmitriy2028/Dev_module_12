package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.services.ClientCrudService;
import org.example.services.PlanetCrudService;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession();
        ClientCrudService ccs = new ClientCrudService();
        PlanetCrudService pcs = new PlanetCrudService();
        clientCrud(ccs);
        planetCrud(pcs);
        session.close();
    }

    public static void clientCrud(ClientCrudService ccs) {

        //create new client
        Client newClient = new Client();
        newClient.setName("New Client");
        ccs.saveClient(newClient);
        System.out.printf("\nCreate new Client with id: %s and name: %s\n", newClient.getId(), newClient.getName());

        //get client by id
        Client client = ccs.findClientById(5L);
        System.out.println("Get client by id 5: " + client);

        //get all clients
        List<Client> clients = ccs.getAllClients();
        clients.forEach(allClient -> System.out.println("Client list ==> " + allClient));

        //update client
        client.setName("Updated Client");
        ccs.updateClient(client);
        System.out.println("Update Client by id 5 to " + client.getName());

        ccs.deleteClient(client);
        System.out.println("Delete client: " + client);

    }

    public static void planetCrud(PlanetCrudService pcs) {

        //create new planet
        Planet planet = new Planet();
        planet.setId("NEW");
        planet.setName("Mercury");
        pcs.savePlanet(planet);
        System.out.printf("\nCreated planet: %s\n", planet);

        //get planet by id
        planet = pcs.findPlanetById("NEW");
        System.out.println("Result by planet id NEW: " + planet.getName());

        //get all planets
        List<Planet> planets = pcs.getAllPlanets();
        planets.forEach(allPlanets -> System.out.println("Planets list ==> " + allPlanets));

        //update planet
        planet.setName("Pluto");
        pcs.updatePlanet(planet);
        System.out.println("Update planet by Id NEW to name Pluto");

        //delete planet
        pcs.deletePlanet(planet);
        System.out.println("Delete planet: " + planet);
    }

}