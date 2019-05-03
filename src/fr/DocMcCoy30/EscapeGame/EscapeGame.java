package fr.DocMcCoy30.EscapeGame;

public class EscapeGame {

    /**
     * Attribut static pour pouvoir être utilisé dans une méthode static
     *
     * @param args args[0] = moddev => mode developpeur activé
     * @author DocMcCoy30
     */
    public static void main(String[] args) {

        Boolean modedev = false;

        try {
            if (args[0] != null) {
                args[0] = args[0].toLowerCase();
                if (args[0].equals("modedev")) {
                    modedev = true;
                    System.out.println("Lancement de l'application en mode developpeur");
                }
            }
            } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Lancement de l'application sans paramètre d'entrée.");
            }
            Menu run = new Menu(modedev);
            run.modeChoix();
        }
    }
