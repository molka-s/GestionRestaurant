package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Commande {
    private int id;
    private String clientLogin;
    private LocalDateTime dateHeure;
    private Map<Integer, Integer> articlesQuantites;
    private String statut; // "Non traitée", "En préparation", "Prête", "Livrée", "Annulée"
    private String type;  
    private boolean recuperee; // Nouvel attribut

    public Commande(int id, String clientLogin, LocalDateTime dateHeure, 
                   Map<Integer, Integer> articlesQuantites, String type) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.dateHeure = dateHeure;
        this.articlesQuantites = new HashMap<>(articlesQuantites);
        this.statut = "Non traitée";
        this.type = type;
        this.recuperee = false;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClientLogin() { return clientLogin; }
    public void setClientLogin(String clientLogin) { this.clientLogin = clientLogin; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public Map<Integer, Integer> getArticlesQuantites() { return new HashMap<>(articlesQuantites); }
    public void setArticlesQuantites(Map<Integer, Integer> articlesQuantites) {
        this.articlesQuantites = new HashMap<>(articlesQuantites);
    }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isRecuperee() { return recuperee; }
    public void setRecuperee(boolean recuperee) { this.recuperee = recuperee; }

    public double calculerTotal(Map<Integer, Article> articles) {
        double total = 0;
        for (Map.Entry<Integer, Integer> entry : articlesQuantites.entrySet()) {
            Article article = articles.get(entry.getKey());
            if (article != null) {
                total += article.getPrix() * entry.getValue();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(";")
          .append(clientLogin).append(";")
          .append(dateHeure).append(";")
          .append(statut).append(";")
          .append(type).append(";")
          .append(recuperee ? "1" : "0").append(";");

        for (Map.Entry<Integer, Integer> entry : articlesQuantites.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }

        if (!articlesQuantites.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static Commande fromString(String str, Map<Integer, Article> articles) {
        String[] parts = str.split(";");
        int id = Integer.parseInt(parts[0]);
        String clientLogin = parts[1];
        LocalDateTime dateHeure = LocalDateTime.parse(parts[2]);
        String statut = parts[3];
        String type = parts.length > 4 ? parts[4] : "Sur place";
        boolean recuperee = parts.length > 5 && parts[5].equals("1");

        Map<Integer, Integer> articlesQuantites = new HashMap<>();
        if (parts.length > 6 && !parts[6].isEmpty()) {
            String[] articlesParts = parts[6].split(",");
            for (String articlePart : articlesParts) {
                String[] keyValue = articlePart.split(":");
                int articleId = Integer.parseInt(keyValue[0]);
                int quantite = Integer.parseInt(keyValue[1]);
                articlesQuantites.put(articleId, quantite);
            }
        }

        Commande commande = new Commande(id, clientLogin, dateHeure, articlesQuantites, type);
        commande.setStatut(statut);
        commande.setRecuperee(recuperee);
        return commande;
    }
}