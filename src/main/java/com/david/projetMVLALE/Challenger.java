package com.david.projetMVLALE;

public class Challenger extends AbstractJeu {

    public Challenger() {
        joueur = false;
        ordinateur = true;
        compteurOn = true;
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            return false;

    }
}
