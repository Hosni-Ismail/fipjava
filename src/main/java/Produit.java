public class Produit {
    private String code;
    private String nom;
    private double prixUnitaire;
    private String unite;

    public Produit(String code, String nom, double prixUnitaire, String unite) {
        this.code = code;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.unite = unite;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public String getUnite() {
        return unite;
    }

    @Override
    public String toString() {
        return code + ":" + nom + ";" + prixUnitaire + ";" + unite;
    }
}