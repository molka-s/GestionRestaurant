package model;

import java.time.LocalDate;
import java.util.Objects;
import util.PasswordHasher;

public class Client {
    private String login;
    private String password;
    private String salt;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;
    private String telephone;
    private boolean premiereConnexion;

    // Constructeur pour nouveaux clients (avec hachage)
    public Client(String login, String password, String nom, String prenom,
                 LocalDate dateNaissance, String adresse, String telephone) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.premiereConnexion = true;
        this.salt = PasswordHasher.generateSalt();
        this.password = PasswordHasher.hashPassword(password, this.salt);
    }

    // Getters
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getSalt() { return salt; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }
    public boolean isPremiereConnexion() { return premiereConnexion; }

    // Setters
    public void setLogin(String login) { this.login = login; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setPremiereConnexion(boolean premiereConnexion) {
        this.premiereConnexion = premiereConnexion;
    }

    // Méthode pour mettre à jour le mot de passe (avec hachage)
    public void setPassword(String newPassword) {
        this.salt = PasswordHasher.generateSalt();
        this.password = PasswordHasher.hashPassword(newPassword, this.salt);
    }

    // Vérification du mot de passe (compatible anciens et nouveaux clients)
    public boolean verifyPassword(String inputPassword) {
        if (salt == null) {
            // Ancien client - vérification en clair
            return password.equals(inputPassword);
        } else {
            // Nouveau client - vérification hachée
            return PasswordHasher.verifyPassword(inputPassword, password, salt);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return login.equals(client.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    // Format pour sauvegarde (compatible anciens et nouveaux clients)
    @Override
    public String toString() {
        if (salt == null) {
            // Ancien format (sans sel)
            return String.join(";",
                    login, password, nom, prenom,
                    dateNaissance.toString(), adresse, telephone,
                    String.valueOf(premiereConnexion));
        } else {
            // Nouveau format (avec sel)
            return String.join(";",
                    login, password, salt, nom, prenom,
                    dateNaissance.toString(), adresse, telephone,
                    String.valueOf(premiereConnexion));
        }
    }

    // Méthode de création à partir d'une ligne de fichier
    public static Client fromString(String str) {
        String[] parts = str.split(";", -1); // -1 pour garder les champs vides

        if (parts.length == 8) {
            // Ancien format (sans sel)
            Client client = new Client(parts[0], parts[1], parts[2], parts[3],
                    LocalDate.parse(parts[4]), parts[5], parts[6]);
            client.premiereConnexion = Boolean.parseBoolean(parts[7]);
            client.salt = null;
            return client;
        } else if (parts.length >= 9) {
            // Nouveau format (avec sel)
            Client client = new Client(parts[0], "dummy", parts[3], parts[4],
                    LocalDate.parse(parts[5]), parts[6], parts[7]);
            client.password = parts[1];
            client.salt = parts[2];
            client.premiereConnexion = Boolean.parseBoolean(parts[8]);
            return client;
        } else {
            throw new IllegalArgumentException("Format de client invalide: " + str);
        }
    }
}