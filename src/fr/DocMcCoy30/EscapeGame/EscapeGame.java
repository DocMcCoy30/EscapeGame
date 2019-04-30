package fr.DocMcCoy30.EscapeGame;

public class EscapeGame {

    /**
     * Attribut static pour pouvoir être utilisé dans une méthode static
     * @param args args[0] = moddev => mode developpeur activé
     * @author DocMcCoy30
     */
    public static void main(String[] args) {
        /**
         * Utilisation d'un tableau => Il faut préciser l'indice
         * On ouvre toujours les accolades pour indiquer un bloc d'instruction même si il n'y en a qu'une.
         */
        Boolean modeDev = false;
        try {
            if (args[0] != null && args[0].equals("modedev")) {
                modeDev = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Lancement de l'application sans paramètre d'entrée.");
        }
        Menu run = new Menu(modeDev);
        run.modeChoix();
    }
}
