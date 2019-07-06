package com.david.projetMVLALE;

public enum Joueur {

    JOUEUR("Tu as", "Tu commences"),
    ORDINATEUR("L'ordinateur a","L'ordinateur commence");

    private String debutPhrase = "";
    private String debutDuel = "";

    Joueur(String debutPhrase, String debutDuel) {
        this.debutPhrase = debutPhrase;
        this.debutDuel = debutDuel;
    }

    public String toString(){
        return debutPhrase + " gagné la partie !";
}

    public String lancementDuel(){ return debutDuel + " à jouer.";}
}
