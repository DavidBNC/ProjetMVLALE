package com.david.projetMVLALE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public abstract class AbstractJeu {

    protected int nbrPosition;
    protected int compteur;
    protected int compteurMax;
    protected boolean gagner;
    protected byte[] combinaisonJoueur;
    protected byte[] combinaisonOrdinateur;
    protected byte[] propositionJoueur;
    protected byte[] propositionOrdinateur;
    protected byte[] propositionHaute;
    protected byte[] propositionBasse;

    public AbstractJeu() {
        try (InputStream input = new FileInputStream("src/com/david/projetMVLALE/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            nbrPosition = Integer.parseInt(prop.getProperty("nbr-Position"));
            combinaisonJoueur = new byte[nbrPosition];
            combinaisonOrdinateur = new byte[nbrPosition];
            propositionJoueur = new byte[nbrPosition];
            propositionOrdinateur = new byte[nbrPosition];
            propositionHaute = new byte[nbrPosition];
            propositionBasse = new byte[nbrPosition];
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Saisie manuel de la combinaison ou de la proposition du Joueur en passant par différentes conversions +
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
     * Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
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
     * Fais jouer l'intelligence artificielle de l'ordinateur.
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

                if (propositionOrdinateur[i] < combinaisonJoueur[i])
                {
                    if (propositionOrdinateur[i] == 0){
                        propositionBasse[i] = (byte) (propositionOrdinateur[i] + 1);
                    } else
                    propositionBasse[i] = propositionOrdinateur[i];
                } else {
                    propositionHaute[i] = propositionOrdinateur[i];
                }
            }
        }
    }

    /**
     * Renvoi la comparaison de chaque caractères de la proposition.
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
     * Methode du tour du Joueur.
     */
    protected void tourJoueur() {
        saisie(propositionJoueur, "Veuillez choisir une proposition :", "Votre proposition : ");
        afficherCombinaison(propositionJoueur);
        System.out.println(" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
    }

    /**
     * Methode du tour de l'ordinateur.
     */
    protected void tourOrdinateur() {
        jouerOrdinateur();
        System.out.print("Proposition : ");
        afficherCombinaison(propositionOrdinateur);
        System.out.println(" --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
    }

    /**
     * Affiche la combinaison.
     *
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi) {

        for (int i = 0; i < nbrPosition; i++) {
            System.out.print(combi[i]);
        }
    }

    /**
     * Vérifie la longueur d'une saisie.
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
     * Vérifie si le contenu de la saisie est correcte.
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
     * Initialise/réinitialise les combinaisons/propositions/compteur
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
     * Boolean renvoyant le nombre de tours Maximum dans une partie.
     *
     * @return
     */
    protected abstract boolean nbrToursMax();

    /**
     * Methode pour lancer les différents mode de jeu dans le projetMVLALE.
     */
    protected abstract void jouer();

    /**
     * Envoi le message de bienvenue a chaque début de mode de jeu.
     */
    public String toString() {
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }
}
