import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Configuration {

    static ResourceBundle rb;
    FileInputStream fis;

    {
        try {
            fis = new FileInputStream("./resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            rb = new PropertyResourceBundle(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNbCases() {
        String nbCases = rb.getString("nbCases");
        return Integer.parseInt(nbCases);
    }

    public static int getNbDigits() {
        String nbDigits = rb.getString("nbDigits");
        return Integer.parseInt(nbDigits);
    }

    public static int getNbEssais() {
        String nbEssais = rb.getString("nbEssais");
        return Integer.parseInt(nbEssais);
    }

    public void affiche() {
        System.out.println("Nombre de cases = " + getNbCases());
        System.out.println("Nombre de chiffres = " + getNbDigits());
        System.out.println("Nombre d'essais = " + getNbEssais());
    }

}


