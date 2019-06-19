package com.david.projetMVLALE;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu {
    private static final int NBR_POSITION = 4;

    protected byte[] combinaisonJoueur = new byte[NBR_POSITION];
    protected byte[] combinaisonOrdinateur = new byte[NBR_POSITION];
    protected byte[] propositionJoueur = new byte[NBR_POSITION];
    protected byte[] propositionOrdinateur = new byte[NBR_POSITION];
    private byte[] propositionHaute = {10, 10, 10, 10};
    private byte[] propositionBasse = {0, 0, 0, 0};
    protected int compteur = 0;

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
        for (int i = 0; i < NBR_POSITION; i++) {

            char caracSaisie = strSaisie.charAt(i);
            byte caracSaisieByte = (byte) (caracSaisie - 48);
            saisie[i] = caracSaisieByte;
        }

        System.out.print(reponse);
    }

    /**
     * combinaisonOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonOrdinateur() {
        Random rdmGenere = new Random();
        for (int i = 0; i < NBR_POSITION; i++) {
            int chiffreOrdinateur = rdmGenere.nextInt(10);
            combinaisonOrdinateur[i] = (byte) (chiffreOrdinateur);
        }
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
    }

    /**
     * jouerOrdinateur : Fais jouer l'intelligence artificielle de l'ordinateur.
     */

    protected void jouerOrdinateur() {
        Random rdmPropoOrdi = new Random();

        for (int i = 0; i < NBR_POSITION; i++) {
            int min = propositionBasse[i];
            int max = propositionHaute[i];

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
     * @return
     */
    protected String comparaison(byte[] proposition, byte[] combinaison) {
        String str = "";
        String ajout;
        for (int i = 0; i < NBR_POSITION; i++) {
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
     *
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi) {

        for (int i = 0; i < NBR_POSITION; i++) {
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
        String comparaison = "";
        for (int i = 0; i < NBR_POSITION; i++) {
            comparaison = comparaison + "C";
        }
        if (strSaisie.length() == comparaison.length()) {
            return true;
        } else
            System.err.println("Veuillez choisir une saisie de " + NBR_POSITION + " chiffres.");
        return false;
    }

    /**
     * contenuSaisie : Vérifie si le contenu de la saisie est correcte.
     *
     * @param strSaisie
     * @return
     */
    private boolean contenuSaisie(String strSaisie) {
        String comparer = "";
        String comparant = "";
        for (int i = 0; i < NBR_POSITION; i++) {
            comparant = comparant + "T";
        }

        for (int i = 0; i < NBR_POSITION; i++) {
            if (strSaisie.charAt(i) == '0') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '1') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '2') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '3') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '4') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '5') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '6') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '7') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '8') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '9') {
                comparer = comparer + "T";
            } else
                comparer = comparer + "F";
        }
        if (comparer.equals(comparant)) {
            return true;
        } else
            System.err.println("Veuillez choisir une saisie de " + NBR_POSITION + " chiffres.");
        return false;
    }

    /**
     * gagner : Boolean pour déterminer si le joueur ou l'ordinateur gagne.
     *
     * @return
     */
    protected boolean gagner() {
        if (comparaison(propositionJoueur, combinaisonOrdinateur).equals("====")) {
            System.out.println("Vous avez gagné !");
            return true;
        } else if (comparaison(propositionOrdinateur, combinaisonJoueur).equals("====")) {
            System.out.println("L'ordinateur a gagné !");
            return true;
        } else return false;
    }

    /**
     * initialisationCombi : Initialise les combinaisons du joueurs et de l'ordinateur dès le départ, afin
     * d'éviter les bug de code dans certaines situations.
     */
    protected void initialisationCombi() {
        for (int i = 0; i < NBR_POSITION; i++) {
            combinaisonOrdinateur[i] = -1;
            combinaisonJoueur[i] = -1;
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
