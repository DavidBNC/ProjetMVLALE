package com.david.projetMVLALE;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu {

    protected int nbrPosition;
    protected byte[] combinaisonJoueur = new byte[nbrPosition];
    protected byte[] combinaisonOrdinateur = new byte[nbrPosition];
    protected byte[] propositionJoueur = new byte[nbrPosition];
    protected byte[] propositionOrdinateur = new byte[nbrPosition];
    protected byte[] propositionHaute = new byte[nbrPosition];
    protected byte[] propositionBasse = new byte[nbrPosition];
    protected int compteur = 0;
    protected boolean gagner;

    public AbstractJeu(int nbrPosition) {
        this.nbrPosition = nbrPosition;
    }

    /**
     * saisieCombinaison : Saisie manuel de la combinaison ou de la proposition du Joueur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète ou proposition.
     */
    protected void saisie(byte[] saisie, String demande, String reponse) {
        String strSaisie;
        Scanner scanner = new Scanner(System.in);
        System.out.println(demande);
        do {
            strSaisie = scanner.nextLine();
        } while (!longeurSaisie(strSaisie) || !contenuSaisie(strSaisie));
        for (int i = 0; i < nbrPosition; i++) {

            char caracSaisie = strSaisie.charAt(i);
            saisie[i] = (byte) (caracSaisie - 48);
        }

        System.out.print(reponse);
    }

    /**
     * combinaisonOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonOrdinateur(byte[] tabl) {
        Random rdmGenere = new Random();
        for (int i = 0; i < nbrPosition; i++) {
            int chiffreOrdinateur = rdmGenere.nextInt(10);
            tabl[i] = (byte) (chiffreOrdinateur);
        }
    }

    /**
     * jouerOrdinateur : Fais jouer l'intelligence artificielle de l'ordinateur.
     */

    private void jouerOrdinateur() {
        Random rdmPropoOrdi = new Random();

        for (int i = 0; i < nbrPosition; i++) {
            int min = 0;
            int max = propositionHaute[i];
            if (propositionBasse[i] != 0) {
                min = propositionBasse[i] + 1;
            }

            if (propositionOrdinateur[i] != combinaisonJoueur[i]) {
                int propoOrdi = min + rdmPropoOrdi.nextInt(max - min);
                propositionOrdinateur[i] = (byte) (propoOrdi);

                if (propositionOrdinateur[i] < combinaisonJoueur[i]) {
                    propositionBasse[i] = propositionOrdinateur[i];
                } else {
                    propositionHaute[i] = propositionOrdinateur[i];
                }
            }
        }
    }

    /**
     * comparaison : Renvoi la comparaison de chaque caractères de la proposition.
     *
     * @return str
     */
    private String comparaison(byte[] proposition, byte[] combinaison) {
        int nbPositionCorrecte = 0;
        gagner = false;
        String str = "";

        for (int i = 0; i < nbrPosition; i++) {
            if (proposition[i] < combinaison[i]) {
                str += '+';
            } else if (proposition[i] > combinaison[i]) {
                str += '-';
            } else {
                nbPositionCorrecte++;
                str += '=';
            }
        }
        if (nbPositionCorrecte == nbrPosition) {
            gagner = true;
        }
        return str;
    }

    /**
     * tourJoueur : Methode du tour du Joueur.
     */
    protected void tourJoueur(){
        saisie(propositionJoueur, "Veuillez choisir une proposition :", "Votre proposition : ");
        afficherCombinaison(propositionJoueur);
        System.out.println(" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
    }

    /**
     * tourOrdinateur : Methode du tour de l'ordinateur.
     */
    protected void tourOrdinateur(){
        jouerOrdinateur();
        System.out.print("Proposition : ");
        afficherCombinaison(propositionOrdinateur);
        System.out.println(" --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
    }

    /**
     * afficherCombinaison : Affiche la combinaison.
     *
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi) {

        for (int i = 0; i < nbrPosition; i++) {
            System.out.print(combi[i]);
        }
    }

    /**
     * longeurSaisie : Vérifie la longueur d'une saisie.
     *
     * @param strSaisie
     * @return
     */
    private boolean longeurSaisie(String strSaisie) {

        if (strSaisie.length() == nbrPosition) {
            return true;
        } else
            System.err.println("Veuillez choisir une saisie de " + nbrPosition + " chiffres.");
        return false;
    }

    /**
     * contenuSaisie : Vérifie si le contenu de la saisie est correcte.
     *
     * @param strSaisie
     * @return
     */
    private boolean contenuSaisie(String strSaisie) {

        for (int i = 0; i < nbrPosition; i++) {
            if (strSaisie.charAt(i) < '0' || strSaisie.charAt(i) > '9') {
                System.err.println("Veuillez choisir une saisie de " + nbrPosition + " chiffres.");
                return false;
            }
        }
        return true;
    }

    /**
     * initialisationCombi : Initialise/réinitialise les combinaisons/propositions/compteur
     * du joueur et de l'ordinateur dès le départ du jeu.
     */
    protected void initialisationCombi() {
        for (int i = 0; i < nbrPosition; i++) {
            combinaisonOrdinateur[i] = 0;
            combinaisonJoueur[i] = 0;
            propositionJoueur[i] = -1;
            propositionOrdinateur[i] = -1;
            propositionHaute[i] = 10;
            propositionBasse[i] = 0;
            compteur = 0;
        }
    }

    /**
     * nbrToursMax : Boolean renvoyant le nombre de tours Maximum dans une partie.
     *
     * @return
     */
    protected abstract boolean nbrToursMax();

    /**
     * lancerMDJ : Methode pour lancer les différents mode de jeu dans le projetMVLALE.
     */
    protected abstract void jouer();

    /**
     * toString : Envoi le message de bienvenue a chaque début de mode de jeu.
     */
    public String toString() {
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }
}
