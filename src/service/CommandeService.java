package service;

import model.Commande;
import model.Article;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CommandeService {
    private static final String FICHIER_COMMANDES = "data/commandes.txt";
    private Map<Integer, Commande> commandes;
    private int dernierId;
    private ArticleService articleService;

    public CommandeService(ArticleService articleService) {
        this.articleService = articleService;
        commandes = new HashMap<>();
        chargerCommandes();
        dernierId = commandes.keySet().stream().max(Integer::compare).orElse(0);
    }

    private void chargerCommandes() {
        File fichier = new File(FICHIER_COMMANDES);
        if (fichier.exists()) {
            try (Scanner scanner = new Scanner(fichier)) {
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    Commande commande = Commande.fromString(ligne, articleService.getArticlesMap());
                    commandes.put(commande.getId(), commande);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Erreur lors de la lecture du fichier commandes: " + e.getMessage());
            }
        }
    }

    private void sauvegarderCommandes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FICHIER_COMMANDES))) {
            for (Commande commande : commandes.values()) {
                writer.println(commande.toString());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier commandes: " + e.getMessage());
        }
    }

    public boolean creerCommande(Commande commande) {
        if (commande.getId() == 0) {
            commande.setId(++dernierId);
        }
        commandes.put(commande.getId(), commande);
        sauvegarderCommandes();
        return true;
    }

    public List<Commande> listerCommandes() {
        return new ArrayList<>(commandes.values());
    }

    public Commande trouverCommandeParId(int id) {
        return commandes.get(id);
    }

    public boolean modifierCommande(Commande commande) {
        if (!commandes.containsKey(commande.getId())) {
            return false;
        }
        commandes.put(commande.getId(), commande);
        sauvegarderCommandes();
        return true;
    }

    public boolean marquerCommandeRecuperee(int id) {
        Commande commande = trouverCommandeParId(id);
        if (commande == null || !commande.getStatut().equals("Prête")) {
            return false;
        }
        commande.setRecuperee(true);
        return modifierCommande(commande);
    }

    public boolean modifierStatutCommande(int id, String nouveauStatut) {
        Commande commande = trouverCommandeParId(id);
        if (commande == null) {
            return false;
        }
        
        List<String> statutsValides = Arrays.asList(
            "Non traitée", 
            "En préparation", 
            "Prête", 
            "Livrée",
            "Annulée"
        );
        
        if (!statutsValides.contains(nouveauStatut)) {
            return false;
        }
        
        commande.setStatut(nouveauStatut);
        return modifierCommande(commande);
    }

    public boolean supprimerCommande(int id) {
        if (!commandes.containsKey(id)) {
            return false;
        }
        commandes.remove(id);
        sauvegarderCommandes();
        return true;
    }
    
    
    public boolean annulerCommande(int id) {
        Commande commande = commandes.get(id);
        if (commande == null) {
            return false;
        }

        if (!commande.getStatut().equalsIgnoreCase("Non traitée")) {
            return false; // Ne peut annuler que les non traitées
        }

        commande.setStatut("Annulée");
        sauvegarderCommandes(); // Enregistre les changements
        return true;
    }


    public List<Commande> listerCommandesParClient(String clientLogin) {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes.values()) {
            if (commande.getClientLogin().equals(clientLogin)) {
                result.add(commande);
            }
        }
        return result;
    }

    public List<Commande> listerCommandesRecuperees() {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes.values()) {
            if (commande.isRecuperee()) {
                result.add(commande);
            }
        }
        return result;
    }

    public List<Commande> listerCommandesNonRecuperees() {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes.values()) {
            if (!commande.isRecuperee() && commande.getStatut().equals("Prête")) {
                result.add(commande);
            }
        }
        return result;
    }

    public Map<Integer, Article> getArticlesMap() {
        Map<Integer, Article> articlesMap = new HashMap<>();
        for (Article article : articleService.listerArticles()) {
            articlesMap.put(article.getId(), article);
        }
        return articlesMap;
    }

    public List<String> getStatutsPossibles() {
        return Arrays.asList(
            "Non traitée",
            "En préparation",
            "Prête",
            "Livrée",
            "Annulée"
        );
    }

    public int getTotalOrders() {
        return commandes.size();
    }

    public double getTotalSales() {
        double totalSales = 0.0;
        for (Commande commande : commandes.values()) {
            totalSales += commande.calculerTotal(articleService.getArticlesMap());
        }
        return totalSales;
    }

    public int getNombreCommandesRecuperees() {
        return listerCommandesRecuperees().size();
    }

    public int getNombreCommandesNonRecuperees() {
        return listerCommandesNonRecuperees().size();
    }

    public Map<String, Integer> getCommandesParStatut() {
        Map<String, Integer> stats = new HashMap<>();
        for (Commande commande : commandes.values()) {
            stats.put(commande.getStatut(), stats.getOrDefault(commande.getStatut(), 0) + 1);
        }
        return stats;
    }

    public Map<String, Integer> getCommandesParType() {
        Map<String, Integer> stats = new HashMap<>();
        for (Commande commande : commandes.values()) {
            stats.put(commande.getType(), stats.getOrDefault(commande.getType(), 0) + 1);
        }
        return stats;
    }
    
    
    public Map<LocalDate, Double> getVentesJournalieres() {
        Map<LocalDate, Double> ventesParJour = new HashMap<>();
        for (Commande commande : commandes.values()) {
            LocalDate date = commande.getDateHeure().toLocalDate();
            double total = commande.calculerTotal(getArticlesMap());
            ventesParJour.merge(date, total, Double::sum);
        }
        return ventesParJour;
    }

    public Map<String, Double> getVentesParCategorie() {
        Map<String, Double> ventesParCategorie = new HashMap<>();
        for (Commande commande : commandes.values()) {
            for (Map.Entry<Integer, Integer> entry : commande.getArticlesQuantites().entrySet()) {
                Article article = articleService.trouverArticleParId(entry.getKey());
                if (article != null) {
                    double montant = article.getPrix() * entry.getValue();
                    ventesParCategorie.merge(article.getCategorie(), montant, Double::sum);
                }
            }
        }
        return ventesParCategorie;
    }
    
    public boolean mettreAJourCommande(Commande nouvelleCommande) {
        if (nouvelleCommande == null || !commandes.containsKey(nouvelleCommande.getId())) {
            return false;
        }

        // Récupérer l'ancienne commande existante
        Commande ancienneCommande = commandes.get(nouvelleCommande.getId());

        // Mettre à jour uniquement les champs modifiables
        ancienneCommande.setType(nouvelleCommande.getType());
        ancienneCommande.setStatut(nouvelleCommande.getStatut());
        ancienneCommande.setArticlesQuantites(nouvelleCommande.getArticlesQuantites());
        ancienneCommande.setDateHeure(nouvelleCommande.getDateHeure());
        ancienneCommande.setRecuperee(nouvelleCommande.isRecuperee());
        // Ajouter d'autres setters si besoin

        // Sauvegarder les changements
        sauvegarderCommandes();

        return true;
    }


    
    
}