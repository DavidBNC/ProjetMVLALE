package com.david.projetMVLALE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Challenger extends AbstractJeu {

    public Challenger() {
        try (InputStream input = new FileInputStream("src/com/david/projetMVLALE/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            compteurMax = Integer.parseInt(prop.getProperty("CompteurPerdu"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Déroulement du jeu en mode challenger.
     */
    private void modeChallenger() {
        do {
            compteur++;
            tourJoueur();
            System.out.println("----------------------------");
        } while (!gagner && nbrToursMax());
        if (gagner) {
            System.out.println("Vous avez gagné !!");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
        modeChallenger();
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            System.out.println("Vous avez perdu.");
        return false;
    }
}
