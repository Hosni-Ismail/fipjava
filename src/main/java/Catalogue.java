import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Catalogue {
    private Map<String, Produit> produits;

    public Catalogue() {
        this.produits = new HashMap<>();
    }

    public void ajouterProduit(Produit produit) {
        produits.put(produit.getCode(), produit);
    }

    public Produit chercherProduitParCode(String code) {
        return produits.get(code);
    }

    public void chargerCatalogueDepuisFichier(String cheminFichier) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            String[] parts = ligne.split(":");
            String code = parts[0];
            String[] details = parts[1].split(";");
            String nom = details[0];
            double prixUnitaire = Double.parseDouble(details[1]);
            String unite = details[2];
            Produit produit = new Produit(code, nom, prixUnitaire, unite);
            ajouterProduit(produit);
        }
        reader.close();
    }

    public void listerProduits() {
        for (Produit p : produits.values()) {
            System.out.println(p);
        }
    }
}

