package com.david.projetMVLALE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Defenseur extends AbstractJeu {

    public Defenseur() {
        try (InputStream input = new FileInputStream("src/com/david/projetMVLALE/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            compteurMax = Integer.parseInt(prop.getProperty("CompteurPerdu"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Déroulement du jeu en mode Défenseur.
     */

    private void modeDefenseur() {
        do {
            compteur++;
            tourOrdinateur();
            System.out.println("----------------------------");
        } while (!gagner && nbrToursMax());
        if (gagner) {
            System.out.println("L'ordinateur a gagné !!");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
        modeDefenseur();
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            System.out.println("L'ordinateur a perdu.");
        return false;
    }
}
