import java.awt.*;
import javax.swing.*;
import java.io.IOException;

public class CaisseUI extends JFrame {
    private Catalogue catalogue;
    private Facture facture;

    private JTextField codeField = new JTextField(10);
    private JButton chercherButton = new JButton("chercher");
    private JButton ajouterButton = new JButton("ajouter");
    private JButton nouveauClientButton = new JButton("nouveau client");
    private JTextField produitField = new JTextField(10);
    private JTextField prixUnitaireField = new JTextField(10);
    private JTextField uniteField = new JTextField(10);
    private JTextField quantiteField = new JTextField(10);
    private JTextField totalField = new JTextField(10);
    private JTable achatsTables = new JTable();

    public CaisseUI(Catalogue catalogue) {
        this.catalogue = catalogue;
        this.facture = new Facture();
        JTextField lectureSeule[] = {
                produitField, prixUnitaireField, uniteField, totalField
        };
        for (JTextField tf: lectureSeule) {
            tf.setEditable(false);
        }
        mettreEnPage();
        configurerActions();
    }

    private void configurerActions() {
        chercherButton.addActionListener(e -> chercherProduit());
        ajouterButton.addActionListener(e -> ajouterProduit());
        nouveauClientButton.addActionListener(e -> nouveauClient());
    }

    private void chercherProduit() {
        String code = codeField.getText();
        Produit produit = catalogue.chercherProduitParCode(code);
        if (produit != null) {
            produitField.setText(produit.getNom());
            prixUnitaireField.setText(String.valueOf(produit.getPrixUnitaire()));
            uniteField.setText(produit.getUnite());
        } else {
            produitField.setText("Inconnu");
            prixUnitaireField.setText("");
            uniteField.setText("");
        }
    }

    private void ajouterProduit() {
        try {
            String code = codeField.getText();
            Produit produit = catalogue.chercherProduitParCode(code);
            if (produit != null) {
                int quantite = Integer.parseInt(quantiteField.getText());
                facture.ajouter(produit, quantite);
                actualiserAffichageFacture();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Quantité invalide.");
        }
    }

    private void actualiserAffichageFacture() {
        String[][] data = new String[facture.getLignes().size()][4];
        int index = 0;
        for (LigneFacture ligne : facture.getLignes()) {
            data[index][0] = ligne.getProduit().getNom();
            data[index][1] = String.valueOf(ligne.getProduit().getPrixUnitaire());
            data[index][2] = String.valueOf(ligne.getQuantite());
            data[index][3] = String.valueOf(ligne.getProduit().getPrixUnitaire() * ligne.getQuantite());
            index++;
        }
        achatsTables.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"Produit", "Prix Unitaire", "Quantité", "Total"}
        ));
        totalField.setText(String.valueOf(facture.total()));
    }

    private void nouveauClient() {
        facture = new Facture();
        actualiserAffichageFacture();
        codeField.setText("");
        produitField.setText("");
        prixUnitaireField.setText("");
        uniteField.setText("");
        quantiteField.setText("");
        totalField.setText("");
    }

    private void mettreEnPage() {
        setLayout(new GridBagLayout());
        GridBagConstraints cc = new GridBagConstraints();
        cc.insets = new Insets(3, 3, 3, 3);

        // Ligne 1
        add(new JLabel("Code"), cc);
        cc.gridx = 1;
        cc.gridwidth = 4;
        cc.fill = GridBagConstraints.HORIZONTAL;
        add(codeField, cc);
        cc.fill = GridBagConstraints.NONE;
        cc.gridx = 5;
        cc.gridwidth = 1;
        add(chercherButton, cc);

        // Ligne 2
        cc.gridx = 0;
        cc.gridy = 1;
        add(new JLabel("Produit"), cc);
        cc.gridx = 1;
        add(produitField, cc);
        cc.gridx++;
        add(new JLabel("Prix Unitaire"), cc);
        cc.gridx++;
        add(prixUnitaireField, cc);
        cc.gridx++;
        add(new JLabel("Unité"), cc);
        cc.gridx++;
        add(uniteField, cc);

        // Ligne 3
        cc.gridx = 0;
        cc.gridy = 2;
        add(new JLabel("Quantité"), cc);
        cc.gridx = 1;
        add(quantiteField, cc);
        cc.gridx = 5;
        add(ajouterButton, cc);

        // Ligne 4
        cc.gridx = 0;
        cc.gridy = 3;
        cc.gridwidth = 6;
        cc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(achatsTables), cc);

        // Ligne 5
        cc.gridwidth = 1;
        cc.gridx = 0;
        cc.gridy = 4;
        cc.fill = GridBagConstraints.NONE;
        add(new JLabel("Total"), cc);
        cc.gridx = 1;
        cc.gridwidth = 4;
        cc.fill = GridBagConstraints.HORIZONTAL;
        add(totalField, cc);
        cc.gridwidth = 1;
        cc.fill = GridBagConstraints.NONE;
        cc.gridx = 5;
        add(nouveauClientButton, cc);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Catalogue catalogue = new Catalogue();
                catalogue.chargerCatalogueDepuisFichier("src/main/produits.txt");
                CaisseUI ui = new CaisseUI(catalogue);
                ui.setVisible(true);
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}