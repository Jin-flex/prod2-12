public class RefrigereToxique extends Produit {

    public RefrigereToxique(int prix, String marque, String nom, int tempConservation) {
        super(prix, marque, nom);
        setTempConservation(tempConservation);
    }
}
