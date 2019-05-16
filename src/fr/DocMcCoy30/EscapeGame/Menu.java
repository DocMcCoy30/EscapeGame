package fr.DocMcCoy30.EscapeGame;

import fr.DocMcCoy30.EscapeGame.jeuduplusmoins.Jeu;
import fr.DocMcCoy30.EscapeGame.beans.Configuration;
import fr.DocMcCoy30.EscapeGame.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Affiche les differents menus du jeu
 */
public class Menu {

    Scanner sc = new Scanner(System.in);
    Configuration config;
    final static Logger log = LogManager.getLogger();

    private int choixMode, choixFin;

    public Menu(boolean modedev) {
        try {
            config = new Configuration();
            if (modedev) {
                config.setModeDev(true);
            }
        } catch (IOException e) {
            log.error("L'ouverture du Flux ne s'est pas déroulée comme prévu.");
        }
    }

    /**
     * Affiche la presentation du jeu
     */
    public void presentationJeux() {
        System.out.println();
        System.out.println("Bienvenue à Escape Game");
        System.out.println();
        System.out.println("Combinaison secrète de " + config.getNbCases() + " chiffres compris entre 0 et 9");
        System.out.println("Maximum de coups : " + config.getNbEssais() + ".");
        System.out.println();
    }

    /**
     * Affiche le menu principal
     */
    public void modeMenu() {
        int i;
        //Utils.clearConsole();
        String[] modes = {"Challenger", "Defenseur", "Duel", "Info Configuration", "Quitter"};
        System.out.println("ESCAPE GAME");
        System.out.println();
        for (i = 0; i < modes.length; i++) {
            System.out.println(i + 1 + " - " + modes[i]);
        }
    }

    /**
     * Recupere le choix du joueur dans le menu principal et lance le jeu selectionne
     *
     * @return : chiffre correspondant au choix du joueur
     */
    public int modeChoix() {
        choixMode = 0;
        while (choixMode <= 0 || choixMode > 5) {
            Utils.clearConsole();
            modeMenu();
            System.out.println("Votre choix ?");
            try {
                choixMode = sc.nextInt();
                if (choixMode <= 5) {
                    runJeuSelectionne();
                }
            } catch (InputMismatchException e) {
                log.warn("Saisie choixMode non valide.");
                sc.nextLine();
            }
        }
        return choixMode;
    }

    /**
     * Affiche le menu de fin de jeu
     */
    public void finMenu() {
        System.out.println("Que souhaitez vous faire ?");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au Menu");
        System.out.println("3 - Quitter");

    }

    /**
     * Recupere le choix du joueur dans le menu de fin de jeu et lance l'action attendue
     *
     * @return : chiffre correspondant au choix du joueur
     */
    public int finChoix() {
        choixFin = 0;
        while (choixFin <= 0 || choixFin > 3) {
            finMenu();
            System.out.println("Votre choix ?");
            try {
                choixFin = sc.nextInt();
                if (choixFin == 2) {
                    modeChoix();
                } else if (choixFin == 3) {
                    System.out.println("Merci d'avoir jouer. A bientôt.");
                }
            } catch (InputMismatchException e) {
                log.warn("Saisie choixFin non valide.");
                sc.nextLine();
            }
        }
        return choixFin;
    }

    /**
     * Lance les actions attendues et selectionnees dans les differents menus
     */
    public void runJeuSelectionne() {
        Jeu jeu = new Jeu(config);
        switch (choixMode) {
            case 1:
                do {
                    Utils.clearConsole();
                    presentationJeux();
                    jeu.challenger();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 2:
                do {
                    Utils.clearConsole();
                    presentationJeux();
                    jeu.defenseur();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 3:
                do {
                    Utils.clearConsole();
                    presentationJeux();
                    jeu.duel();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 4:
                Utils.clearConsole();
                config.affiche();
                modeChoix();
                break;
            case 5:
                System.out.println("Merci d'avoir jouer. A bientôt.");
        }
    }
}