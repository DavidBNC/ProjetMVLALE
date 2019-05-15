package main.java;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu{
    private final int NBR_POSITION = 4;

    private byte[] combinaisonJoueur = new byte[NBR_POSITION];
    private byte[] combinaisonOrdinateur = new byte[NBR_POSITION];
    private byte[] propositionJoueur = new byte[NBR_POSITION];
    private byte[] propositionOrdinateur = new byte[NBR_POSITION];

    public AbstractJeu(){
    }

    /**
     * saisieCombinaison : Saisie manuel du Joueur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void saisieCombinaison(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre combinaison secrète :");
        String strCombi = scanner.nextLine();
        factorisationJoueur(strCombi, combinaisonJoueur);
        System.out.print("Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
    }

    /**
     * combinaisonOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonOrdinateur(){
        Random rdmCombi = new Random();

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            int chiffreOrdinateur = rdmCombi.nextInt(10);
            byte byteChiffreOrdinateur = (byte) (chiffreOrdinateur);
            combinaisonOrdinateur[i] = byteChiffreOrdinateur;
        }
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
    }

    /**
     * propositionJoueur : Génère la proposition qui doit être comparé aux combinaisons.
     */

    protected void propositionJoueur(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir une proposition :");
        String strProposition = scanner.nextLine();
        factorisationJoueur(strProposition, propositionJoueur);
    }

    /**
     * propositionOrdinateur : Génère la proposition qui doit être comparé aux combinaisons.
     */

    protected void propositionOrdinateur(){
        Random rdmPropoOrdi = new Random();

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            int min = propositionOrdinateur[i];
            int max = 10;

            if (propositionOrdinateur[i] < combinaisonJoueur[i]){
                int propoOrdi = min + rdmPropoOrdi.nextInt(max - min);
                byte bytePropoOrdi = (byte) (propoOrdi);
                propositionOrdinateur[i] = bytePropoOrdi;

            } else if (propositionOrdinateur[i] > combinaisonJoueur[i]){
                int propoOrdi = rdmPropoOrdi.nextInt(min);
                byte bytePropoOrdi = (byte)(propoOrdi);
                propositionOrdinateur[i] = bytePropoOrdi;
            } else;
        }
    }

    /**
     * factorisationJoueur : Factorisation de la saisie Joueur pour la saisie de la combinaison + proposition.
     * @param strSaisie
     * @param saisie
     */
    private void factorisationJoueur(String strSaisie, byte[] saisie){

        for (int i = 0; i <= NBR_POSITION - 1; i++){
            char caracSaisie = strSaisie.charAt(i);
            byte caracSaisieByte = (byte)(caracSaisie - 48);
            saisie[i] = caracSaisieByte;
        }
    }

    /**
     * comparaison : Renvoi la comparaison de chaque caractères de la proposition.
     * @return
     */
    public String comparaison(byte[] proposition,byte[] combinaison) {
        String str = "";
        String ajout;
        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            if (proposition[i] < combinaison[i]) {
                ajout = "+";
                str = str + ajout;
            } else if (proposition[i] > combinaison[i]) {
                ajout = "-";
                str = str + ajout;
            } else {
                ajout = "=";
                str = str + ajout;
            }
        }
        return str;
    }

    /**
     * afficherCombinaison : Affiche la combinaison.
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi){

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            System.out.print(combi[i]);
        }
    }

    /**
     * lancerMDJ : Methode pour lancer les différents mode de jeu dans le main.
     */
    protected abstract void jouer();

    /**
     * toString : Envoi le message de bienvenue a chaque début de partie.
     */
    public String toString(){
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }

    public byte[] getCombinaisonJoueur() {
        return combinaisonJoueur;
    }

    public byte[] getCombinaisonOrdinateur() {
        return combinaisonOrdinateur;
    }

    public byte[] getPropositionJoueur() {
        return propositionJoueur;
    }

    public byte[] getPropositionOrdinateur() {
        return propositionOrdinateur;
    }
}
