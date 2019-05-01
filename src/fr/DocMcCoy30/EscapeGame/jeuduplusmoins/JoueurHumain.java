package fr.DocMcCoy30.EscapeGame.jeuduplusmoins;

import fr.DocMcCoy30.EscapeGame.beans.Configuration;
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
        try {
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

    @Override
    public boolean conditionsDeSortie(int nbBienPlace, ArrayList<Integer>tabCombiRandom) {
        this.nbBienPlace = nbBienPlace;
        this.tabCombiRandom=tabCombiRandom;
        endOfGame = false;
        if ((nbBienPlace != config.getNbCases()) && (nbDeCoups < config.getNbEssais())) {
            nbDeCoups++;
        } else if ((nbBienPlace == config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Victoire du joueur en " + nbDeCoups + " coups !");
            System.out.println();
            endOfGame = true;
        } else if ((nbBienPlace != config.getNbCases()) && (nbDeCoups >= config.getNbEssais())) {
            System.out.println("Nombre maximum d'essais atteints.");
            System.out.println("Perdu : la solution était : " + intToString(tabCombiRandom));
            System.out.println();
            endOfGame = true;
        }
        return endOfGame;
    }

    /**
     * Affiche l'invite d'entrée du code secret du joueur
     */
    public void propositionInitiale() {
        System.out.println("Votre code secret de " + config.getNbCases() + " chiffres compris entre 0 et " + config.getNbDigits() + " ?");
    }

    /**
     * Demande de validation par enter après affichage réponse
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
