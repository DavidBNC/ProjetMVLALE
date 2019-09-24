package com.david.projetMVLALE;

public class Defenseur extends AbstractJeu {

    public Defenseur() {
        joueur = true;
        ordinateur = false;
        compteurOn = true;
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            return false;
    }
}
