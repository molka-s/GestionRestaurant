package service;

import model.Article;
import java.io.*;
import java.util.*;

public class ArticleService {
    private static final String FICHIER_ARTICLES = "data/articles.txt";
    private Map<Integer, Article> articles;
    private int dernierId;

    public ArticleService() {
        articles = new HashMap<>();
        chargerArticles();
        dernierId = articles.keySet().stream().max(Integer::compare).orElse(0);
    }

    public void chargerArticles() {
        File fichier = new File(FICHIER_ARTICLES);
        if (fichier.exists()) {
            try (Scanner scanner = new Scanner(fichier)) {
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    Article article = Article.fromString(ligne);
                    articles.put(article.getId(), article);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Erreur lors de la lecture du fichier articles: " + e.getMessage());
            }
        }
    }

    private void sauvegarderArticles() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FICHIER_ARTICLES))) {
            for (Article article : articles.values()) {
                writer.println(article.toString());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier articles: " + e.getMessage());
        }
    }

    public boolean ajouterArticle(Article article) {
        if (article.getId() == 0) {
            article.setId(++dernierId);
        }
        articles.put(article.getId(), article);
        sauvegarderArticles();
        return true;
    }

    public List<Article> listerArticles() {
        return new ArrayList<>(articles.values());
    }

    public Article trouverArticleParId(int id) {
        return articles.get(id);
    }

    public boolean modifierArticle(Article article) {
        if (!articles.containsKey(article.getId())) {
            return false;
        }
        articles.put(article.getId(), article);
        sauvegarderArticles();
        return true;
    }

    public boolean supprimerArticle(int id) {
        if (!articles.containsKey(id)) {
            return false;
        }
        articles.remove(id);
        sauvegarderArticles();
        return true;
    }

    public Map<Integer, Article> getArticlesMap() {
        Map<Integer, Article> articlesMap = new HashMap<>();
        for (Article article : listerArticles()) {
            articlesMap.put(article.getId(), article);
        }
        return articlesMap;
    }

    // Méthode optionnelle pour modifier uniquement l'URL de l'image
    public boolean modifierImageArticle(int id, String nouvelleImageUrl) {
        Article article = articles.get(id);
        if (article == null) return false;
        article.setImageUrl(nouvelleImageUrl);
        sauvegarderArticles();
        return true;
    }
}