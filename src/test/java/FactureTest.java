import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class FactureTest {

    private Facture facture;
    private Produit stylo;
    private Produit tomate;

    @BeforeEach
    void setUp() {
        // Initialiser la facture et quelques produits pour les tests
        facture = new Facture();
        stylo = new Produit("A001", "stylo", 3.5, "unité");
        tomate = new Produit("L010", "tomate", 2.5, "kg");
    }

    @Test
    void ajouter() {
        // Ajouter un produit à la facture
        facture.ajouter(stylo, 2);

        // Vérifier que la ligne de facture est bien ajoutée
        List<LigneFacture> lignes = facture.getLignes();
        assertEquals(1, lignes.size());
        assertEquals(stylo, lignes.get(0).getProduit());
        assertEquals(2, lignes.get(0).getQuantite());
    }

    @Test
    void getLignes() {
        // Ajouter des produits à la facture
        facture.ajouter(stylo, 2);
        facture.ajouter(tomate, 3);

        // Vérifier que les lignes de facture sont bien enregistrées
        List<LigneFacture> lignes = facture.getLignes();
        assertEquals(2, lignes.size());
        assertEquals(stylo, lignes.get(0).getProduit());
        assertEquals(2, lignes.get(0).getQuantite());
        assertEquals(tomate, lignes.get(1).getProduit());
        assertEquals(3, lignes.get(1).getQuantite());
    }

    @Test
    void total() {
        // Ajouter des produits à la facture
        facture.ajouter(stylo, 2); // 3.5 * 2 = 7.0
        facture.ajouter(tomate, 3); // 2.5 * 3 = 7.5

        // Vérifier que le total est correctement calculé
        double total = facture.total();
        assertEquals(14.5, total, 0.01); // Le total devrait être 7.0 + 7.5 = 14.5
    }
}
