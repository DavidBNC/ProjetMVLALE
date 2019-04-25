package main.java;

import java.util.Scanner;

public class Challenger extends Jeu {

    /**
     * @modeChallenger : Methode du mode de jeu en Challenger.
     */
    private void modeChallenger(){
        System.out.println(toString());
        combinaisonSecreteOrdinateur();
        jouerChall();
    }

    /**
     * @jouerChall : Déroulement du jeu en mode challenger.
     */
    private void jouerChall(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Le jeu du + ou - commence.");
        System.out.println("Veuillez choisir votre proposition :");
        String proposition = scanner.nextLine();

        char chP1 = proposition.charAt(0);
        char chP2 = proposition.charAt(1);
        char chP3 = proposition.charAt(2);
        char chP4 = proposition.charAt(3);

        byte premierCaracPropo = (byte) (chP1 - 48);
        byte deuxiemeCaracPropo = (byte) (chP2 - 48);
        byte troisiemeCaracPropo = (byte) (chP3 - 48);
        byte quatriemeCaracPropo = (byte) (chP4 - 48);

        String reponseComparaison = comparaisonPremierChar(premierCaracPropo) + comparaisonDeuxiemeChar(deuxiemeCaracPropo) +
                comparaisonTroisiemeChar(troisiemeCaracPropo) + comparaisonQuatriemeChar(quatriemeCaracPropo);

        System.out.println("Proposition : " + proposition + " --> Proposition : " + reponseComparaison);
        }

    /**
     * @comparaisonPremierChar : Renvoi la comparaison du premier caractère de la proposition.
     * @param caracPropo
     * @return
     */
    public String comparaisonPremierChar(byte caracPropo){
        String reponseChar;

        if(caracPropo < stockageCS[6]){
            reponseChar = "+";
        } else if (caracPropo > stockageCS[6]){
            reponseChar = "-";
        } else{
            reponseChar = "=";
        }
        return reponseChar;
    }

    /**
     * @comparaisonDeuxiemeChar : Renvoi la comparaison du deuxième caractère de la proposition.
     * @param caracPropo
     * @return
     */
    public String comparaisonDeuxiemeChar(byte caracPropo) {
        String reponseChar;

        if (caracPropo < stockageCS[7]) {
            reponseChar = "+";
        } else if (caracPropo > stockageCS[7]) {
            reponseChar = "-";
        } else {
            reponseChar = "=";
        }
        return reponseChar;
    }

    /**
     * @comparaisonTroisiemeChar : Renvoi la comparaison du troisième caractère de la proposition.
     * @param caracPropo
     * @return
     */
    public String comparaisonTroisiemeChar(byte caracPropo) {
        String reponseChar;

        if (caracPropo < stockageCS[8]) {
            reponseChar = "+";
        } else if (caracPropo > stockageCS[8]) {
            reponseChar = "-";
        } else {
            reponseChar = "=";
        }
        return reponseChar;
    }

    /**
     * @comparaisonQuatriemeChar : Renvoi la comparaison du quatrième caractère de la proposition.
     * @param caracPropo
     * @return
     */
    public String comparaisonQuatriemeChar(byte caracPropo) {
        String reponseChar;

        if (caracPropo < stockageCS[9]) {
            reponseChar = "+";
        } else if (caracPropo > stockageCS[9]) {
            reponseChar = "-";
        } else {
            reponseChar = "=";
        }
        return reponseChar;
    }

    protected void lancerMDJ(){
        modeChallenger();
    }
}
