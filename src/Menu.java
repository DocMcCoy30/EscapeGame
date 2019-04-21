import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Configuration config;
    final static Logger log = LogManager.getLogger();

    private int choixMode, choixFin;

    public Menu() {
        try {
            config = new Configuration();
        } catch (IOException e){
            log.error("L'ouverture du Flux ne s'est pas déroulée comme prévu.");
        }
    }

    public void presentationJeux() {
        System.out.println();
        System.out.println("Bienvenue à Escape Game");
        System.out.println();
        System.out.println("Combinaison secrète de " + config.getNbCases() + " chiffres compris entre 0 et 9");
        System.out.println("Maximum de coups : " + config.getNbEssais() + ".");
        System.out.println();
    }

    public void modeMenu() {
        int i;
        String[] modes = {"Challenger", "Defenseur", "Duel", "Info Configuration",};
        System.out.println("ESCAPE GAME");
        System.out.println();
        for (i = 0; i < modes.length; i++) {
            System.out.println(i + 1 + " - " + modes[i]);
        }
    }

    public int modeChoix() {
        choixMode = 0;
        while (choixMode <= 0 || choixMode > 4) {
            modeMenu();
            System.out.println("Votre choix ?");
            try {
                choixMode = sc.nextInt();
                if (choixMode <= 4) {
                    runJeuSelectionne();
                }
            } catch (InputMismatchException e) {
                log.warn("Saisie choixMode non valide.");
                System.out.println("Votre saisie n'est pas un choix valide.");
                sc.nextLine();
            }
        }
        return choixMode;
    }


    public void finMenu() {
        System.out.println("Que souhaitez vous faire ?");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au Menu");
        System.out.println("3 - Quitter");
    }


    public int finChoix() {
        choixFin = 0;
        while (choixFin <= 0 || choixFin > 3) {
            finMenu();
            System.out.println("Votre choix (1, 2 ou 3)");
            try {
                choixFin = sc.nextInt();
                if (choixFin == 2) {
                    modeChoix();
                } else if (choixFin == 3) {
                    System.out.println();
                    System.out.println("Merci d'avoir joué. A bientôt!");
                }
            } catch (InputMismatchException e) {
                log.warn("Saisie choixFin non valide.");
                System.out.println("Votre saisie n'est pas un choix valide.");
                sc.nextLine();
            }
        }
        return choixFin;
    }


    public void runJeuSelectionne() {
        Jeu jeu = new Jeu(config);
        switch (choixMode) {
            case 1:
                do {
                    presentationJeux();
                    jeu.challenger();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 2:
                do {
                    presentationJeux();
                    jeu.defenseur();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 3:
                do {
                    presentationJeux();
                    jeu.duel();
                    finChoix();
                } while (choixFin == 1);
                break;
            case 4:
                config.affiche();
                modeChoix();
                break;
        }
    }
}