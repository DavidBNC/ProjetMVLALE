package com.david.projetMVLALE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;

public abstract class AbstractJeu {

    final protected static Logger logger = Logger.getLogger((AbstractJeu.class));
    protected static int nbrPosition;
    protected static int compteurMax;
    protected static boolean modeDev;
    protected static boolean confCharger;
    protected int compteur;
    protected boolean gagner;
    protected String retourComparaison;
    protected byte[] combinaisonJoueur;
    protected byte[] combinaisonOrdinateur;
    protected byte[] propositionJoueur;
    protected byte[] propositionOrdinateur;
    protected byte[] propositionHaute;
    protected byte[] propositionBasse;

    public AbstractJeu() {
        if (!confCharger) {
            try (InputStream input = new FileInputStream("src/main/java/com/david/ressources/config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                logger.info("Le fichier de configuration a été initialisé.");
                if (prop.getProperty("compteurMax").charAt(0) < '0' || prop.getProperty("compteurMax").charAt(0) > 9) {
                    compteurMax = Integer.parseInt(prop.getProperty("compteurMax", "5"));
                }
                if (prop.getProperty("nbrPosition").charAt(0) < '0' || prop.getProperty("nbrPosition").charAt(0) > 9) {
                    nbrPosition = Integer.parseInt(prop.getProperty("nbrPosition", "4"));
                }
                modeDev = Boolean.parseBoolean(prop.getProperty("modeDev", "false"));
            } catch (NumberFormatException | IOException ex) {
                compteurMax = 5;
                nbrPosition = 4;
                modeDev = false;
                logger.warn("Le fichier de configuration n'a pas été trouvé. Les valeurs sont donc initialisées par défaut.");
            }
            confCharger = true;
        }
        logger.info("Le nombre de tour maximum avant d'avoir perdu est de " + compteurMax);
        logger.info("Le nombre de postion de la combinaison est de " + nbrPosition);
        logger.info("Le mode développeur est initialisé à " + modeDev);
        combinaisonJoueur = new byte[nbrPosition];
        combinaisonOrdinateur = new byte[nbrPosition];
        propositionJoueur = new byte[nbrPosition];
        propositionOrdinateur = new byte[nbrPosition];
        propositionHaute = new byte[nbrPosition];
        propositionBasse = new byte[nbrPosition];
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

                if (propositionOrdinateur[i] < combinaisonJoueur[i]) {
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
        retourComparaison = "";

        for (int i = 0; i < nbrPosition; i++) {
            if (proposition[i] < combinaison[i]) {
                retourComparaison += '+';
            } else if (proposition[i] > combinaison[i]) {
                retourComparaison += '-';
            } else {
                nbPositionCorrecte++;
                retourComparaison += '=';
            }
        }
        if (nbPositionCorrecte == nbrPosition) {
            gagner = true;
        }
        return retourComparaison;
    }

    /**
     * Methode du tour du Joueur.
     */
    protected void tourJoueur() {
        saisie(propositionJoueur, "Veuillez choisir une proposition :", "Votre proposition : ");
        logger.info("Proposition de l'utilisateur : " + afficherCombinaison(propositionJoueur));
        System.out.println(afficherCombinaison(propositionJoueur) +" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
        logger.info("Retour de la comparaison du tour de l'utilisateur : " + retourComparaison);
    }

    /**
     * Methode du tour de l'ordinateur.
     */
    protected void tourOrdinateur() {
        jouerOrdinateur();
        logger.info("Proposition de l'ordinateur : " + afficherCombinaison(propositionJoueur));
        System.out.println("Proposition de l'ordinateur : " + afficherCombinaison(propositionOrdinateur) + " --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
        logger.info("Retour de la comparaison du tour de l'ordinateur : " + retourComparaison);
    }

    /**
     * Affiche la combinaison.
     *
     * @param combi
     */
    protected String afficherCombinaison(byte[] combi) {
        String strCombi = "";
        for (int i = 0; i < nbrPosition; i++) {
            strCombi += combi[i];
        }
        return strCombi;
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
        logger.warn("Longueur de saisie invalide. La saisie doit être de " + nbrPosition + " chiffres.");
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
                logger.warn("Contenu de la saisie invalide. La saisie doit contenir " + nbrPosition + "chiffres.");
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
