package fr.DocMcCoy30.EscapeGame.beans;

public class Bornes {

    private int borneMin;
    private int borneMax;

    public Bornes(int borneMin, int borneMax) {
        this.borneMin = borneMin;
        this.borneMax = borneMax;
    }


    public int getBorneMin() {
        return borneMin;
    }

    public void setBorneMin(int borneMin) {
        this.borneMin = borneMin;
    }

    public int getBorneMax() {
        return borneMax;
    }

    public void setBorneMax(int borneMax) {
        this.borneMax = borneMax;
    }

}
