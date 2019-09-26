package com.david.projetMVLALE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

public abstract class AbstractJeu {

    final protected static Logger logger = Logger.getLogger((AbstractJeu.class));
    final private static int COMPTEUR_MAX = 5;
    final private static int NBR_POSITION = 4;
    protected static int nbrPosition;
    protected static int compteurMax;
    protected static boolean modeDev;
    protected static boolean confCharger;
    protected int compteur;
    protected boolean compteurOn;
    protected boolean gagner;
    protected boolean joueur;
    protected boolean ordinateur;
    protected String retourComparaison;
    protected byte[] combinaisonJoueur;
    protected byte[] combinaisonOrdinateur;
    protected byte[] propositionJoueur;
    protected byte[] propositionOrdinateur;
    protected byte[] propositionHaute;
    protected byte[] propositionBasse;

    public AbstractJeu() {
        if (!confCharger) {
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                logger.info("Le fichier de configuration a été initialisé.");
                try {
                    compteurMax = Integer.parseInt(prop.getProperty("compteurMax", Integer.toString(COMPTEUR_MAX)));
                    if (compteurMax <= 0) {
                        compteurMax = COMPTEUR_MAX;
                        logger.warn("La valeur trouvée dans le fichier de configuration n'est pas correcte. Elle sera donc initialisée par défaut.");
                    }
                } catch (NumberFormatException nfex) {
                    compteurMax = COMPTEUR_MAX;
                }
                try {
                    nbrPosition = Integer.parseInt(prop.getProperty("nbrPosition", Integer.toString(NBR_POSITION)));
                    if (nbrPosition <= 0) {
                        nbrPosition = NBR_POSITION;
                        logger.warn("La valeur trouvée dans le fichier de configuration n'est pas correcte. Elle sera donc initialisée par défaut.");

                    }
                } catch (NumberFormatException nfex) {
                    nbrPosition = NBR_POSITION;
                }

                modeDev = Boolean.parseBoolean(prop.getProperty("modeDev", "false"));

            } catch (IOException | NullPointerException ex) {
                compteurMax = COMPTEUR_MAX;
                nbrPosition = NBR_POSITION;
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
    protected void saisie(byte[] saisie, String strSaisie) {
        for (int i = 0; i < nbrPosition; i++) {
            char caracSaisie = strSaisie.charAt(i);
            saisie[i] = (byte) (caracSaisie - 48);
        }
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

    protected void jouerOrdinateur() {
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
    protected String comparaison(byte[] proposition, byte[] combinaison) {
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
    protected boolean longeurSaisie(String strSaisie) {

        if (strSaisie.length() == nbrPosition) {
            return true;
        } else
            return false;
    }

    /**
     * Vérifie si le contenu de la saisie est correcte.
     *
     * @param strSaisie
     * @return
     */
    protected boolean contenuSaisie(String strSaisie) {

        for (int i = 0; i < nbrPosition; i++) {
            if (strSaisie.charAt(i) < '0' || strSaisie.charAt(i) > '9') {
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
     * Envoi le message de bienvenue a chaque début de mode de jeu.
     */
    public String toString() {
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }
}
