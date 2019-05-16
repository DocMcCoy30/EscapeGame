package fr.DocMcCoy30.EscapeGame.jeuduplusmoins;

import fr.DocMcCoy30.EscapeGame.beans.Bornes;
import fr.DocMcCoy30.EscapeGame.beans.Configuration;
import fr.DocMcCoy30.EscapeGame.utils.Utils;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * Definit les methodes pour les trois différents modes de jeu
 */
public class Jeu {

    Configuration config;
    JoueurHumain jh;
    JoueurOrdi pc;

    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Character> tabIndice2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput2 = new ArrayList<>();
    protected List<Bornes> tabBornes = new ArrayList<>();
    protected int nbBienPlace, nbBienPlace1, nbBienPlace2;


    public Jeu(Configuration config) {
        this.config = config;
        jh = new JoueurHumain(config);
        pc = new JoueurOrdi(config);
    }

    /**
     * Initialise les variables nbBienPlaces
     */
    public void initNbBienPlace() {
        nbBienPlace = 0;
        nbBienPlace1 = 0;
        nbBienPlace2 = 0;
    }

    /**
     * Jeu en mode challenger
     */
    public void challenger() {
        initNbBienPlace();
        jh.initNbDeCoups();
        pc.tabCombiDejaJouees.clear();
        pc.tabIndicesDejaJoues.clear();
        pc.combi(tabCombiRandom);
        while (!jh.conditionsDeSortie(nbBienPlace,tabCombiRandom)) {
            pc.modeDev(tabCombiRandom);
            System.out.println("Proposition ?");
            jh.combi(tabCombiInput);
            tabIndice = pc.indicesResolution(tabCombiInput, tabCombiRandom);
            Utils.clearConsole();
            jh.displayResult(tabCombiInput, tabIndice);
            nbBienPlace = pc.getNbBienPlace(tabIndice, nbBienPlace);
        }
    }

    /**
     * Jeu en mode defenseur
     */
    public void defenseur() {
        initNbBienPlace();
        pc.initNbDeCoups();
        pc.tabCombiDejaJouees.clear();
        pc.tabIndicesDejaJoues.clear();

        jh.propositionInitiale();
        jh.combi(tabCombiInput);
        pc.initTabPc(tabCombiRandom);
        pc.tabBornesMinMax(tabBornes);
        while (!pc.conditionsDeSortie(nbBienPlace,tabCombiInput)) {
            tabIndice = jh.indicesResolution(tabCombiRandom, tabCombiInput);
            Utils.clearConsole();
            pc.displayResult(tabCombiRandom, tabIndice);
            jh.validationIndices();
            nbBienPlace = jh.getNbBienPlace(tabIndice, nbBienPlace);
            pc.proposition(tabCombiRandom, tabIndice, tabBornes);
        }
    }

    /**
     * Jeu en mode duel
     */
    public void duel() {
        initNbBienPlace();
        jh.initNbDeCoups();
        jh.tabCombiDejaJouees.clear();
        jh.tabIndicesDejaJoues.clear();
        pc.combi(tabCombiRandom);

        pc.initNbDeCoups();
        pc.tabCombiDejaJouees.clear();
        pc.tabIndicesDejaJoues.clear();
        jh.propositionInitiale();
        jh.combi(tabCombiInput2);
        pc.initTabPc(tabCombiRandom2);
        pc.tabBornesMinMax(tabBornes);


        while ((!jh.conditionsDeSortie(nbBienPlace1,tabCombiRandom))&(!pc.conditionsDeSortie(nbBienPlace2,tabCombiInput2)))  {

            pc.modeDev(tabCombiRandom);
            System.out.println("Challenger, votre proposition :");
            jh.combi(tabCombiInput);
            tabIndice = pc.indicesResolution(tabCombiInput, tabCombiRandom);
            Utils.clearConsole();
            jh.displayResult(tabCombiInput, tabIndice);
            nbBienPlace1 = pc.getNbBienPlace(tabIndice, nbBienPlace);
            System.out.println();

            System.out.println("Defenseur");
            tabIndice2 = jh.indicesResolution(tabCombiRandom2, tabCombiInput2);
            pc.displayResult(tabCombiRandom2, tabIndice2);
            jh.validationIndices();
            nbBienPlace2 = jh.getNbBienPlace(tabIndice2, nbBienPlace);
            pc.proposition(tabCombiRandom2, tabIndice2, tabBornes);
            System.out.println();
        }

    }
}

