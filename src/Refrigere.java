public class Refrigere extends Produit {

    private int tempConservation;
    public Refrigere(int prix, String marque, String nom, int tempConservation) {
        super(prix, marque, nom);
        setTempConservation(tempConservation);
    }
}
