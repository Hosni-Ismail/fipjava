import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class CatalogueTest {

    private Catalogue catalogue;

    @BeforeEach
    void setUp() {
        catalogue = new Catalogue();
    }

    @Test
    void chargerCatalogueDepuisFichier() throws IOException {
        // Charger un fichier de test (vérifie que le chemin d'accès est correct)
        catalogue.chargerCatalogueDepuisFichier("src/test/java/ressources/produits_test.txt");

        // Vérifier que les produits ont été correctement ajoutés
        Produit stylo = catalogue.chercherProduitParCode("A001");
        assertNotNull(stylo);
        assertEquals("stylo", stylo.getNom());
        assertEquals(3.5, stylo.getPrixUnitaire());
        assertEquals("unité", stylo.getUnite());

        Produit tomate = catalogue.chercherProduitParCode("L010");
        assertNotNull(tomate);
        assertEquals("tomate", tomate.getNom());
        assertEquals(2.5, tomate.getPrixUnitaire());
        assertEquals("kg", tomate.getUnite());
    }

    @Test
    void chargerCatalogueFichierInexistant() {
        // Vérifier qu'une exception est levée si le fichier n'existe pas
        assertThrows(IOException.class, () -> {
            catalogue.chargerCatalogueDepuisFichier("fichier_inexistant.txt");
        });
    }
}
