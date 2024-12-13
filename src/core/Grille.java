package core;

import java.awt.Graphics;
import java.util.ArrayList;

public class Grille implements InterfaceDessin{
    public static final int NBRE_DE_COLONNES = 10;
    public static final int NBRE_DE_LIGNES = 10;

    private ArrayList<ArrayList<Case>> lesCases = new ArrayList<ArrayList<Case>>();

    public Grille() {
        for (int i=0; i<NBRE_DE_COLONNES; i++) {
            this.lesCases.addLast(new ArrayList<Case>());
            for (int j=0; j<NBRE_DE_LIGNES; j++) {
                this.lesCases.get(i).addLast(new Case(i, j));
            }
        }
    }

    Case getUneCase(int x, int y) {
        return lesCases.get(x).get(y);
    }

    public void dessiner(Graphics graphics) {
        for (ArrayList<Case> uneColonne: this.lesCases) {
            for (Case uneCase: uneColonne) {
                uneCase.dessiner(graphics);
            }
        }
    }
}
