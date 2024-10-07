import java.util.ArrayList;
import java.util.List;

public class Facture {

    private List<LigneFacture> lignes;

    public Facture() {
        this.lignes = new ArrayList<>();
    }

    public void ajouter(Produit produit, int quantite) {
        lignes.add(new LigneFacture(produit, quantite));
    }

    public List<LigneFacture> getLignes() {
        return lignes;
    }

    public double total() {
        return lignes.stream().mapToDouble(ligne -> ligne.getProduit().getPrixUnitaire() * ligne.getQuantite()).sum();
    }
}

class LigneFacture {
    private Produit produit;
    private int quantite;

    public LigneFacture(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return produit.getNom() + " x" + quantite + " (" + produit.getUnite() + ") - " + produit.getPrixUnitaire() * quantite + " €";
    }
}