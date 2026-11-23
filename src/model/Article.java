package model;

public class Article {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private String categorie; // plat, sandwich, salade, dessert, viennoiserie, boisson
    private String imageUrl;

    public Article(int id, String nom, String description, double prix, String categorie, String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.imageUrl = imageUrl;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return id + ";" + nom + ";" + description + ";" + prix + ";" + categorie + ";" + imageUrl;
    }

    public static Article fromString(String str) {
        String[] parts = str.split(";");
        return new Article(
            Integer.parseInt(parts[0]), 
            parts[1], 
            parts[2], 
            Double.parseDouble(parts[3]), 
            parts[4],
            parts.length > 5 ? parts[5] : "" // Gère le cas où l'URL n'existe pas
        );
    }
}