import java.util.*;
import java.util.stream.IntStream;

public class Magasin {
    private final List<Stock> stockList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    //try-catch
    private int estUnInt (int valeur1, int valeur2){
        boolean valeurOk;
        int element = 0;
        do{
            try{
                element =Integer.parseInt(sc.next());
                if (element>=valeur1 && element<=valeur2){
                    valeurOk = true;
                }else{
                    System.out.println("\u001b[31m    Le chiffre doit être compris entre " + (valeur1+1) + " et " + valeur2 + "\u001b[0m");
                    valeurOk = false;
                }
            }catch (NumberFormatException ex){
                System.out.println("\u001b[31m    Entrez un chiffre ! \u001b[0m");
                valeurOk = false;
            }
        }while (!valeurOk);
        return element;
    }
    private String estUnStringValide  (int valeur1, int valeur2){
        boolean valeurOk;
        String element = null;
        do{
            try{
                element =sc.next();
                if (element.length() > valeur1 && element.length() <= valeur2){
                    valeurOk = true;
                }else{
                    System.out.println("\u001b[31m    Il faut entre " + (valeur1+1) + " et " + valeur2 + " caractères !\u001b[0m");
                    valeurOk = false;
                }
            }catch (NumberFormatException ex){
                System.out.println("\u001b[31m    Entrez une chaîne de caractères valide ! \u001b[0m");
                valeurOk = false;
            }
        }while (!valeurOk);
        return element;
    }

    //Méthodes
    private void ajouterProduitStock (Produit p, int quantiteProd){
        affichageListeStock();
        System.out.print("  Dans quel stock voulez-vous l'ajouter ? :");
        int choixStock = estUnInt(1, stockList.size());
        stockList.get(choixStock-1).ajouterProduit(p,quantiteProd);
    }

    //Affichage
    public void menuPrincipal() {
        int choix = 0;
        boolean quitter = false;
        do  {
            System.out.println("**SELECTION**");
            System.out.println("1 pour créer un stock");
            System.out.println("2 pour créer un nouvelle article");
            System.out.println("3 pour afficher les stocks et les articles");
            System.out.println("4 pour modifier la quantité d'un article");
            System.out.println("5 pour retirer un article");
            System.out.println("6 pour fermer le programme");
            System.out.print("Votre séléction : ");
            choix = estUnInt(1,7);
            if (choix == 6){
                quitter =true;
            }
            selection(choix);
        } while (!quitter);
    }
    private void selection(int choix){
        switch (choix){
            case 1 :
                //créer un stock
                System.out.print("  Quelle est l'adresse du stock ? : ");
                String adresse = estUnStringValide(1,100);
                System.out.print("  Quelle est la taille maximum du stock ? : ");
                int tailleMax = estUnInt(1,2147483647);
                //quel est le type > 1-2
                // refirigere 1-2
                //toxique 1-2
                System.out.print("  De quel type est le stock ? 1 toxique, 2 non-toxique : ");
                int type = estUnInt(1,2);
                switch (type){
                    case 1 :
                        System.out.print("  Est-ce un stock réfrigéré ? 1 oui 2 non : ");
                        int toxiqueRefrigere = estUnInt(1,2);
                        if (toxiqueRefrigere == 1){
                            String toxiqueRefrigereType = "Toxique réfrigéré";
                            Stock <RefrigereToxique> stockToxiqueRefrigere = new Stock<>(adresse, tailleMax, toxiqueRefrigereType);
                            stockList.add(stockToxiqueRefrigere);
                        }else{
                            String toxiqueNoRefrigereType = "Toxique non réfrigéré";
                            Stock <Toxique> stockToxiqueNoRefrigere = new Stock<>(adresse, tailleMax, toxiqueNoRefrigereType);
                            stockList.add(stockToxiqueNoRefrigere);
                        }
                        break;
                    case 2 :
                        System.out.print("  Est-ce un stock réfrigéré ? 1 oui 2 non : ");
                        int noToxiqueRefrigere = estUnInt(1,2);
                        if (noToxiqueRefrigere == 1){
                            String refrigereType = "Réfrigéré";
                            Stock <Refrigere> stock3 = new Stock(adresse, tailleMax, refrigereType);
                            stockList.add(stock3);
                        }else{
                            String noToxiqueRefrigereType = "Non-toxique non réfrigéré";
                            Stock <ProduitNormal> stockNoToxNoRefrigere = new Stock(adresse, tailleMax, noToxiqueRefrigereType);
                            stockList.add(stockNoToxNoRefrigere);
                        }
                        break;
                    default:
                        throw new RuntimeException("  Mauvais choix");
                }
                break;
            case 2 :
                //créer-ajouter un article
                System.out.print("  Quel est le nom de l'article ? : ");
                String nom = estUnStringValide(1,100);
                System.out.print("  Quelle est la marque de l'article ? : ");
                String marque = estUnStringValide(1,100);
                System.out.print("  Quel est le prix de l'article ? : ");
                int prix = estUnInt(1,2147483647);
                System.out.print("  Quelle est la quantité de l'article ? : ");
                int quantite = estUnInt(1,2147483647);

                //quel est le type > 1-2
                // refirigere 1-2
                //toxique 1-2
                System.out.print("  Quel est le type de l'article ? 1 toxique, 2 non-toxique : ");
                int typeArticle = estUnInt(1,2);
                switch (typeArticle){
                    case 1 :
                        System.out.print("  Est-ce un article réfrigéré ? 1 oui 2 non : ");
                        int toxiqueRefrigere = estUnInt(1,2);
                        if (toxiqueRefrigere == 1){
                            System.out.print("  Quelle est la température de conservation ? : ");
                            int tempConservation = estUnInt(-273,2147483647);
                            RefrigereToxique rt = new RefrigereToxique(prix, marque, nom, tempConservation);
                            ajouterProduitStock(rt,quantite);
                        }else{
                            Toxique t = new Toxique(prix,marque,nom);
                            ajouterProduitStock(t,quantite);
                        }
                        break;
                    case 2 :
                        System.out.print("  Est-ce un article réfrigéré ? 1 oui 2 non : ");
                        int noToxiqueRefrigere = estUnInt(1,2);
                        if (noToxiqueRefrigere == 1){
                            System.out.print("  Quelle est la température de conservation ? : ");
                            int tempConservation = estUnInt(-273,2147483647);
                            Refrigere r = new Refrigere(prix, marque,nom, tempConservation);
                            ajouterProduitStock(r,quantite);
                        }else{
                            ProduitNormal p = new ProduitNormal(prix, marque, nom);
                            ajouterProduitStock(p,quantite);
                        }
                        break;
                    default:
                        throw new RuntimeException("  Mauvais choix");
                }
                break;
            case 3 :
                afficherStockArticle();
                break;
            case 4 :
                //modifier la quantité d'un article
                affichageListeStock();
                System.out.print("  Dans quel stock se trouve l'article ? :");
                int choixStockArticle = estUnInt(1,stockList.size());
                afficheHashMap(stockList.get(choixStockArticle-1).getListeProduits());
                System.out.print("  Quel nom d'article voulez vous modifier ? :");
                String nomModif = estUnStringValide(1,100);
                System.out.print("  Quel marque d'article voulez vous modifier ? :");
                String marqueModif = estUnStringValide(1,100);
                System.out.print("  Quelle est la nouvelle quantité ? :");
                Integer quantiteModif = estUnInt(1,2147483647);
                stockList.get(choixStockArticle-1).modifierProduit(nomModif,marqueModif, quantiteModif);
                break;
            case 5 :
                //supprimer un article
                affichageListeStock();
                System.out.print("  Dans quel stock se trouve l'article ? :");
                int choixStockSupp = estUnInt(1,stockList.size());
                afficheHashMap(stockList.get(choixStockSupp-1).getListeProduits());
                System.out.print("  Quel nom d'article voulez vous supprimer ? :");
                String nomSupp = estUnStringValide(1,100);
                System.out.print("  Quel marque d'article voulez vous supprimer ? :");
                String marqueSupp = estUnStringValide(1,100);
                stockList.get(choixStockSupp-1).retirerProduit(nomSupp, marqueSupp);
                break;
            case  6:
                System.exit(0);
                break;
        }
    }
    private void afficheHashMap(HashMap<Produit,Integer> liste) {
        int i = 0;
        for (Map.Entry<Produit, Integer> entry : liste.entrySet()) {
            Produit key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("|-------------------------------------------------------------------------------------------------------|");
            System.out.println("|\033[0;36m         " + (i+1) + " =>  nom : " + key.getNom() + "   marque : " + key.getMarque() + "   prix : " + key.getPrix() + "€   quantité : " + value.toString() + " \033[0m|");
            i++;
        }
        System.out.println("|-------------------------------------------------------------------------------------------------------|");
    }

    private void affichageListeStock (){
        if (stockList.size()>0){
            IntStream.range(0, stockList.size()).forEach(j -> {
                Stock i = stockList.get(j);
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                System.out.println("\033[0;34m   " + (j+1) + " =>  adresse : " + i.getAdresse() + "    taille max : " + i.getTailleMax() + "   type : "+ i.getNom() + " \033[0m");
            });
            System.out.println("|-------------------------------------------------------------------------------------------------------|");
        }else {
            System.out.println("Il n'y a aucun stock disponnible !");
        }
    }

    private void afficherStockArticle (){
        if (stockList.size()>0){
            IntStream.range(0, stockList.size()).forEach(j -> {
                Stock i = stockList.get(j);
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                System.out.println("\033[0;34m   " + (j+1) + " =>  adresse : " + i.getAdresse() + "    taille max : " + i.getTailleMax() + "   type : "+ i.getNom() + " \033[0m");
                afficheHashMap(i.getListeProduits());
            });
            System.out.println("|-------------------------------------------------------------------------------------------------------|");
        }else {
            System.out.println("Il n'y a aucun stock disponnible !");
        }
    }
}