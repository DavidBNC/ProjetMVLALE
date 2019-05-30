package main.java;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu {
    private final int NBR_POSITION = 4;

    private byte[] combinaisonJoueur = new byte[NBR_POSITION];
    private byte[] combinaisonOrdinateur = new byte[NBR_POSITION];
    private byte[] propositionJoueur = new byte[NBR_POSITION];
    private byte[] propositionOrdinateur = new byte[NBR_POSITION];
    private byte[] positionHaute = {10, 10, 10, 10};
    private byte[] positionBasse = {0, 0, 0, 0};

    public AbstractJeu() {
    }

    /**
     * saisieCombinaison : Saisie manuel de la combinaison ou de la proposition du Joueur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète ou proposition.
     */
    protected void saisie(byte[] saisie, String demande, String reponse) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(demande);
        String strSaisie = scanner.nextLine();
        for (int i = 0; i <= NBR_POSITION - 1; i++) {
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
        Random rdmCombi = new Random();

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            int chiffreOrdinateur = rdmCombi.nextInt(10);
            combinaisonOrdinateur[i] = (byte) (chiffreOrdinateur);
        }
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
    }

    /**
     * propositionOrdinateur : Génère la proposition de l'ordinateur qui doit être comparé aux combinaisons.
     */

    protected void propositionOrdinateur() {
        Random rdmPropoOrdi = new Random();

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            int min = positionBasse[i];
            int max = positionHaute[i];

            if (propositionOrdinateur[i] != combinaisonJoueur[i]) {
                int propoOrdi = min + rdmPropoOrdi.nextInt(max - min);
                propositionOrdinateur[i] = (byte) (propoOrdi);

                if (propositionOrdinateur[i] < combinaisonJoueur[i]) {
                    positionBasse[i] = propositionOrdinateur[i];
                } else {
                    positionHaute[i] = propositionOrdinateur[i];
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
     *
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi) {

        for (int i = 0; i <= NBR_POSITION - 1; i++) {
            System.out.print(combi[i]);
        }
    }

    /**
     * Boolean pour déterminer si le joueur a gagné.
     *
     * @return
     */
    protected boolean joueurGagne() {
        if (!comparaison(getPropositionJoueur(), getCombinaisonOrdinateur()).equals("====")) {
            return true;
        } else
            return false;
    }

    /**
     * Boolean pour déterminer si l'ordinateur a gagné.
     *
     * @return
     */
    protected boolean ordinateurGagne() {
        if (!comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()).equals("====")) {
            return true;
        } else
            return false;
    }

    /**
     * lancerMDJ : Methode pour lancer les différents mode de jeu dans le main.
     */
    protected abstract void jouer();

    /**
     * toString : Envoi le message de bienvenue a chaque début de mode de jeu.
     */
    public String toString() {
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
