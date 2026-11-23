package model;



public enum EtatCommande {
    NON_TRAITEE("Non traitée"),
    EN_COURS_DE_PREPARATION("En cours de préparation"),
    PRETE("Prête"),
    EN_ROUTE("En route");

    private String libelle;

    EtatCommande(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}