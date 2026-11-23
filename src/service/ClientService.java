package service;

import model.Client;
import java.io.*;
import java.util.*;

public class ClientService {
    private static final String FICHIER_CLIENTS = "data/clients.txt";
    private Map<String, Client> clients;

    public ClientService() {
        clients = new HashMap<>();
        chargerClients();
        creerAdminParDefaut();
    }

    private void chargerClients() {
        File fichier = new File(FICHIER_CLIENTS);
        if (!fichier.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.trim().isEmpty()) {
                    try {
                        Client client = Client.fromString(ligne);
                        clients.put(client.getLogin(), client);
                    } catch (Exception e) {
                        System.err.println("Erreur lecture ligne client: " + ligne);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier clients: " + e.getMessage());
        }
    }

    private void sauvegarderClients() {
        File fichier = new File(FICHIER_CLIENTS);
        try {
            fichier.getParentFile().mkdirs(); // Crée le dossier si inexistant
        } catch (SecurityException e) {
            System.err.println("Erreur création dossier data: " + e.getMessage());
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fichier))) {
            for (Client client : clients.values()) {
                writer.println(client.toString());
            }
        } catch (IOException e) {
            System.err.println("Erreur écriture fichier clients: " + e.getMessage());
        }
    }

    private void creerAdminParDefaut() {
        if (!clients.containsKey("admin")) {
            Client admin = new Client("admin", "admin123", "Administrateur", "Systeme",
                    java.time.LocalDate.now(), "Siège", "0000000000");
            admin.setPremiereConnexion(false);
            clients.put(admin.getLogin(), admin);
            sauvegarderClients();
        }
    }

    public boolean creerClient(Client client) {
        if (client == null || clients.containsKey(client.getLogin())) {
            return false;
        }
        clients.put(client.getLogin(), client);
        sauvegarderClients();
        return true;
    }

    public List<Client> listerClients() {
        return new ArrayList<>(clients.values());
    }

    public Client trouverClientParLogin(String login) {
        return clients.get(login);
    }

    public boolean modifierClient(Client client) {
        if (client == null || !clients.containsKey(client.getLogin())) {
            return false;
        }
        clients.put(client.getLogin(), client);
        sauvegarderClients();
        return true;
    }

    public boolean supprimerClient(String login) {
        if (!clients.containsKey(login)) {
            return false;
        }
        clients.remove(login);
        sauvegarderClients();
        return true;
    }

    public Client authentifier(String login, String password) {
        Client client = clients.get(login);
        if (client == null) {
            return null;
        }
        return client.verifyPassword(password) ? client : null;
    }

    public int getTotalClients() {
        return clients.size();
    }

    // Méthode optionnelle pour migrer un client vers le nouveau format
    public boolean migrerClient(String login, String passwordClaire) {
        Client client = clients.get(login);
        if (client == null || client.getSalt() != null) {
            return false;
        }
       
        Client nouveauClient = new Client(
                client.getLogin(),
                passwordClaire,
                client.getNom(),
                client.getPrenom(),
                client.getDateNaissance(),
                client.getAdresse(),
                client.getTelephone()
        );
        nouveauClient.setPremiereConnexion(client.isPremiereConnexion());
       
        clients.put(login, nouveauClient);
        sauvegarderClients();
        return true;
    }
}