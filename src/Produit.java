public abstract class Produit {

    private final int prix;
    private final String marque;
    private final String nom;
    private int tempConservation;

    public int getPrix() {
        return prix;
    }
    public String getMarque() {
        return marque;
    }
    public String getNom() {
        return nom;
    }
    public void setTempConservation(int tempConservation) {
        this.tempConservation = tempConservation;
    }
    public int getTempConservation() {
        return tempConservation;
    }

    public Produit(int prix, String marque, String nom) {
        this.prix = prix;
        this.marque = marque;
        this.nom = nom;
    }
}