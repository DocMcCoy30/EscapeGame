package fr.DocMcCoy30.EscapeGame;

public class Bornes {

    private int borneMin;
    private int borneMax;

    public Bornes (int borneMin, int borneMax) {
        this.borneMin=borneMin;
        this.borneMax=borneMax;
        System.out.println(borneMin);
        System.out.println(borneMax);
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
