package main.java;

import java.util.Random;
import java.util.Scanner;

public abstract class Jeu {

    private byte[] combinaisonSaisie = new byte[5];
    private byte[] combinaisonOrdinateur = new byte[5];
    private byte[] proposition = new byte[5];

    public Jeu() {
    }

    /**
     * saisieCombinaisonSecrete : Saisie manuel de l'utilisateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void saisieCombinaisonSecrete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre combinaison secrète :");
        String saisiCombi = scanner.nextLine();

        for (int i = 0; i <= 3; i++){
            char caracSaisie = saisiCombi.charAt(i);
            byte caracSaisieByte = (byte)(caracSaisie - 48);
            combinaisonSaisie[i] = caracSaisieByte;
            System.out.println(combinaisonSaisie[i]);
        }
        System.out.println("Votre combinaison est " + combinaisonSaisie[0] + "" + combinaisonSaisie[1] + "" +
                combinaisonSaisie[2] + "" + combinaisonSaisie[3]);
    }

    /**
     * combinaisonOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonOrdinateur(){
        Random r = new Random();

        for (int i = 0; i <= 3; i++){
            int chiffre = r.nextInt(9);
            byte chiffreByte = (byte)(chiffre);
            combinaisonOrdinateur[i] = chiffreByte;
            System.out.println(combinaisonOrdinateur[i]);
        }
        System.out.println("L'ordinateur a choisi la combinaison : " + combinaisonOrdinateur[0] + "" + combinaisonOrdinateur[1] + "" +
                combinaisonOrdinateur[2] + "" + combinaisonOrdinateur[3]);
    }

    /**
     * proposition : Génère la proposition qui doit être comparé aux combinaisons.
     */

    protected void proposition(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir votre proposition :");
        String propositionStr = scanner.nextLine();

        for (int i = 0; i <= 3; i++){
            char caracProposition = propositionStr.charAt(i);
            byte caracPropositionByte = (byte)(caracProposition - 48);
            proposition[i] = caracPropositionByte;
        }
    }

    /**
     * lancerMDJ : Methode pour lancer les différents mode de jeu dans le main.
     */
    protected abstract void lancerMDJ();

    /**
     * toString : Envoi le message de bienvenue a chaque début de partie.
     */
    public String toString(){
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }

    public byte[] getCombinaisonSaisie() {
        return combinaisonSaisie;
    }

    public byte[] getCombinaisonOrdinateur() {
        return combinaisonOrdinateur;
    }

    public byte[] getProposition() {
        return proposition;
    }
}
