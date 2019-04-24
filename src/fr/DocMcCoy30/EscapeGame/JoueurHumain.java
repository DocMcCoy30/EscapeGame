package fr.DocMcCoy30.EscapeGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Scanner;

public class JoueurHumain extends Joueurs {

    private final static Logger log = LogManager.getLogger();
    Scanner sc = new Scanner(System.in);


    public JoueurHumain(Configuration config) {
        super(config);
    }
    
    @Override
    public ArrayList<Integer> combi(ArrayList<Integer> tabCombiInput) {
        this.tabCombiInput = tabCombiInput;
        String combinaison;
        tabCombiInput.clear();
        try{
        combinaison = sc.nextLine();
        if (combinaison.equals("")) {
            System.out.println("Votre combinaison doit contenir " + config.getNbCases() + " chiffres");
            tabCombiInput.clear();
            combi(tabCombiInput);
            throw new IllegalArgumentException();
        }
        char[] combiChar = combinaison.toCharArray();
        for (int i = 0; i < combiChar.length; i++) {
            int j = Character.getNumericValue(combiChar[i]);
            tabCombiInput.add(j);
        }
            for (int i = 0; i < tabCombiInput.size(); i++) {
                if (tabCombiInput.get(i) > config.getNbDigits()) {
                    System.out.println("Les chiffres de votre combinaison doivent etre compris entre 0 et " + config.getNbDigits());
                    tabCombiInput.clear();
                    combi(tabCombiInput);
                    throw new IllegalArgumentException();
                } else if (tabCombiInput.size() != config.getNbCases()) {
                    System.out.println("Votre combinaison doit contenir " + config.getNbCases() + " chiffres");
                    tabCombiInput.clear();
                    combi(tabCombiInput);
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException e) {
            log.error("Combinaison saisie Invalide");
        }
        return tabCombiInput;
    }

    public void propositionInitiale (){
        System.out.println("Votre combinaison de " + config.getNbCases() + " chiffres compris entre 0 et " + config.getNbDigits() + " ?");
    }


    /**
     * affiche le code secret et la proposition du PC pour analyse
     * @param tabCombiInput : code secret Joueur Humain
     * @param tabCombiRandom : proposition PC
     */
    public void affichePropositions(ArrayList<Integer> tabCombiInput, ArrayList<Integer> tabCombiRandom) {
        this.tabCombiInput = tabCombiInput;
        this.tabCombiRandom = tabCombiRandom;
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < tabCombiInput.size(); i++) {
            int nums = tabCombiInput.get(i);
            str1 += nums;
        }
        System.out.println( "Votre combinaison -> "+str1);
        for (int i = 0; i < tabCombiRandom.size(); i++) {
            int nums2 = tabCombiRandom.get(i);
            str2 += nums2;
        }
        System.out.println("Ma proposition    -> " + str2);
        System.out.print("Réponse           -> ");
    }

    /**
     * Demande de validation après analyse automatisée
     */
    public void validationIndices() {
        try {
            System.out.println("Tapez enter pour valider");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            log.error("Entrée Invalide");
        }
    }

}
