package fr.DocMcCoy30.EscapeGame.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 *
 */
public class Configuration {

    private ResourceBundle rb;
    private FileInputStream fis;
    private Integer nbCases;
    private Integer nbDigits;
    private Integer nbEssais;
    private Boolean modedev;

    /**
     * ouvre un flux, récupère les données du fichier config.properties et ferme le flux
     *
     * @throws IOException
     */
    public Configuration() throws IOException {
        try {
            fis = new FileInputStream("./resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            rb = new PropertyResourceBundle(fis);
            this.nbCases = Integer.parseInt(rb.getString("nbCases"));
            this.nbDigits = Integer.parseInt(rb.getString("nbDigits"));
            this.nbEssais = Integer.parseInt(rb.getString("nbEssais"));
            this.modedev = Boolean.parseBoolean(rb.getString("modedev"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }

    public int getNbCases() {
        return this.nbCases;
    }

    public int getNbDigits() { return this.nbDigits;}

    public int getNbEssais() {
        return this.nbEssais;
    }

    public boolean getModeDev() {
        return this.modedev;
    }

    public void setModeDev(Boolean modeDev) {
        this.modedev = modeDev;
    }

    /**
     * affiche les données de configuration du jeu
     */
    public void affiche() {
        System.out.println("Nombre de cases = " + getNbCases());
        System.out.println("Nombre de chiffres = " + getNbDigits());
        System.out.println("Nombre d'essais = " + getNbEssais());
        if (getModeDev()) {
            System.out.println("Mode Developpeur activé");
        } else {
            System.out.println("Mode Developpeur désactivé");
        }
        System.out.println();//
    }

}


