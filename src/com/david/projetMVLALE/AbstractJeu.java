package com.david.projetMVLALE;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu {
    private static final int NBR_POSITION = 4;

    protected byte[] combinaisonJoueur = new byte[NBR_POSITION];
    protected byte[] combinaisonOrdinateur = new byte[NBR_POSITION];
    protected byte[] propositionJoueur = new byte[NBR_POSITION];
    protected byte[] propositionOrdinateur = new byte[NBR_POSITION];
    protected byte[] propositionHaute = new byte[NBR_POSITION];
    protected byte[] propositionBasse = new byte[NBR_POSITION];
    protected int compteur = 0;
    protected boolean gagner;

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
    protected void combinaisonOrdinateur(byte[] tabl) {
        Random rdmGenere = new Random();
        for (int i = 0; i < NBR_POSITION; i++) {
            int chiffreOrdinateur = rdmGenere.nextInt(10);
            tabl[i] = (byte) (chiffreOrdinateur);
        }
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
        gagner = false;
        String str = "";
        String ajout;
        for (int i = 0; i < NBR_POSITION; i++) {
            if (proposition[i] < combinaison[i]) {
                ajout = "+";
                str += ajout;
            } else if (proposition[i] > combinaison[i]) {
                ajout = "-";
                str += ajout;
            } else {
                ajout = "=";
                str += ajout;
            }
        }
        if (compteurCaractere(str, '=') == 4) {
            gagner = true;
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
            comparaison += "C";
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
            comparant += "T";
        }

        for (int i = 0; i < NBR_POSITION; i++) {
            if (strSaisie.charAt(i) == '0') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '1') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '2') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '3') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '4') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '5') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '6') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '7') {
                comparer += "T";
            } else if (strSaisie.charAt(i) == '8') {
                comparer = comparer + "T";
            } else if (strSaisie.charAt(i) == '9') {
                comparer += "T";
            } else
                comparer += "F";
        }
        if (comparer.equals(comparant)) {
            return true;
        } else
            System.err.println("Veuillez choisir une saisie de " + NBR_POSITION + " chiffres.");
        return false;
    }

    /**
     * initialisationCombi : Initialise/réinitialise les combinaisons/propositions/compteur
     * du joueur et de l'ordinateur dès le départ du jeu.
     */
    protected void initialisationCombi() {
        for (int i = 0; i < NBR_POSITION; i++) {
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
     * compteurCaractere : Compte le nombre d'un caractère voulu dans un String.
     *
     * @param chaineCarac
     * @param caracRechercher
     * @return
     */
    private int compteurCaractere(String chaineCarac, char caracRechercher) {
        int nb = 0;
        for (int i = 0; i < chaineCarac.length(); i++) {
            if (chaineCarac.charAt(i) == caracRechercher)
                nb++;
        }
        return nb;
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
