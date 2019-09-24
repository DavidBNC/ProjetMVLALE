package com.david.projetMVLALE;

public class Duel extends AbstractJeu {

    public Duel() {
        joueur = true;
        ordinateur = true;
        compteurOn = false;
    }

    protected boolean nbrToursMax() {
        return true;
    }
}
