package main.java;

import java.util.Random;
import java.util.Scanner;

public abstract class Jeu {

    protected byte[] stockageCS = new byte[10];

    public Jeu() {
    }

    /**
     * @saisieManuelCombinaisonSecrete : Saisie manuel de l'utilisateur.
     */
    protected void saisieManuelCombinaisonSecrete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre combinaison secrète :");
        String saisiCombi = scanner.nextLine();

        char chU1 = saisiCombi.charAt(0);
        char chU2 = saisiCombi.charAt(1);
        char chU3 = saisiCombi.charAt(2);
        char chU4 = saisiCombi.charAt(3);

        byte premierCaracUtil = (byte) (chU1 - 48);
        byte deuxiemeCaracUtil = (byte) (chU2 - 48);
        byte troisiemeCaracUtil = (byte) (chU3 - 48);
        byte quatriemeCaracUtil = (byte) (chU4 - 48);

        stockageCS[0] = premierCaracUtil;
        stockageCS[1] = deuxiemeCaracUtil;
        stockageCS[2] = troisiemeCaracUtil;
        stockageCS[3] = quatriemeCaracUtil;

        System.out.println("Votre combinaison est " + saisiCombi);
        System.out.println(stockageCS[0]);
        System.out.println(stockageCS[1]);
        System.out.println(stockageCS[2]);
        System.out.println(stockageCS[3]);
    }

    /**
     * @combinaisonSecreteOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonSecreteOrdinateur(){
        Random r = new Random();
        int premierChiffre = r.nextInt(9);
        int deuxiemeChiffre = r.nextInt(9);
        int troisiemeChiffre = r.nextInt(9);
        int quatriemeChiffre = r.nextInt(9);

        String strPremierChiffre = Integer.toString(premierChiffre);
        String strDeuxiemeChiffre = Integer.toString(deuxiemeChiffre);
        String strTroisiemeChiffre = Integer.toString(troisiemeChiffre);
        String strQuatriemeChiffre = Integer.toString(quatriemeChiffre);

        String combiSecreteOrdi = strPremierChiffre + strDeuxiemeChiffre + strTroisiemeChiffre + strQuatriemeChiffre;

        char chO1 = combiSecreteOrdi.charAt(0);
        char chO2 = combiSecreteOrdi.charAt(1);
        char chO3 = combiSecreteOrdi.charAt(2);
        char chO4 = combiSecreteOrdi.charAt(3);

        byte premierCaracOrdi = (byte) (chO1 - 48);
        byte deuxiemeCaracOrdi = (byte) (chO2 - 48);
        byte troisiemeCaracOrdi = (byte) (chO3 - 48);
        byte quatriemeCaracOrdi = (byte) (chO4 - 48);

        stockageCS[6] = premierCaracOrdi;
        stockageCS[7] = deuxiemeCaracOrdi;
        stockageCS[8] = troisiemeCaracOrdi;
        stockageCS[9] = quatriemeCaracOrdi;

        System.out.println("L'ordinateur a choisi la combinaison : " + combiSecreteOrdi);
        System.out.println(stockageCS[6]);
        System.out.println(stockageCS[7]);
        System.out.println(stockageCS[8]);
        System.out.println(stockageCS[9]);
    }

    /**
     * @afficherChar : Test d'affichage des char de la combinaison.
     * @param saisiCombi
     */
    private void afficherChar(String saisiCombi){
        for (int i = 0; i < saisiCombi.length(); i++) {
            System.out.println(i + " - " + saisiCombi.charAt(i));
        }
    }

    /**
     * @lancerMDJ : Methode pour lancer les différents mode de jeu dans le main.
     */
    protected abstract void lancerMDJ();

    /**
     * @toString : Envoi le message de bienvenue a chaque début de partie.
     */
    public String toString(){
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }
}
