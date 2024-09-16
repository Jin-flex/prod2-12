import java.util.HashMap;
import java.util.Map;

public class Stock <P extends Produit> extends Magasin {
    private final String adresse;
    private final int tailleMax;
    private final String nom;
    private final HashMap<P, Integer> listeProduits = new HashMap<>();

    //getter-setter
    public String getAdresse() {
        return adresse;
    }
    public int getTailleMax() {
        return tailleMax;
    }
    public String getNom() {
        return nom;
    }
    public HashMap<P, Integer> getListeProduits() {
        return new HashMap<>(listeProduits);
    }

    //Méthodes
    public void ajouterProduit (P produit, Integer qttProduit){
        if(listeProduits.size()!=tailleMax){
            if (!listeProduits.containsValue(produit)) {
                if((produit instanceof Refrigere && this.nom.equals("Réfrigéré"))||(produit instanceof Toxique && this.nom.equals("Toxique non réfrigéré"))
                        ||(produit instanceof RefrigereToxique && this.nom.equals("Toxique réfrigéré"))
                        ||(produit instanceof ProduitNormal && this.nom.equals("Non-toxique non réfrigéré")))
                {
                    listeProduits.put(produit, qttProduit);
                }
                else throw new RuntimeException("Il est impossible de placer des produits de types différents dans le même stock");
            }
            else throw new RuntimeException("Il est impossible d'ajouter un produit déjà existant au stock.");
        }
        else {
            throw new RuntimeException("Le stock est plein : impossible d'ajouter de nouveaux produits");
        }
    }

    public void retirerProduit (String nom, String marque){
        getListeProduits().forEach((key1, value) -> {
            Produit key = key1;
            if (key.getNom().equals(nom) && key.getMarque().equals(marque)) {
                listeProduits.remove(key);
            }
        });
    }

    public void modifierProduit (String nom, String marque, Integer quantite){
        getListeProduits().forEach((key, value) -> {
            if (key.getNom().equals(nom) && key.getMarque().equals(marque)) {
                listeProduits.replace(key, quantite);
            }
        });
    }

    //constructeur
    public Stock(String adresse, int tailleMax, String nom) {
        this.adresse = adresse;
        this.tailleMax = tailleMax;
        this.nom = nom;
    }
}