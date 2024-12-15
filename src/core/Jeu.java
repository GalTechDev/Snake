package core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Jeu implements InterfaceDessin{
    private Grille grille;
    private Serpent serpent;
    private Grenouille grenouille;
    private Direction direction;

    private boolean partieFinie;

    public Jeu() {
        initialiserJeu();
    }

    private void initialiserJeu() {
        this.partieFinie = false;

        this.grille = new Grille();

        this.serpent = new Serpent();
        this.serpent.placer(new Case(0, 0), new Case(1, 0), new Case(2, 0));

        this.grenouille = new Grenouille();
        placerGrenouille();

        this.setDirection(Direction.DROITE);
    }

    private void placerGrenouille() {
        this.grenouille.setCaseGrenouille(getRandomValideCase());
    }

    public void setDirection(Direction direction) {
        if (this.direction==null || (this.direction.getDx() != -direction.getDx() & this.direction.getDy() != -direction.getDy())) {
            this.direction = direction;
            this.serpent.setDirection(direction);
        }
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void jouer() {
        if (this.serpent.peutAvancer()) {
            Case lacase = this.grille.getUneCase(this.serpent.getTete().getxGrille()+this.direction.getDx(), this.serpent.getTete().getyGrille()+this.direction.getDy());
            this.serpent.avancer(lacase);
            if (this.serpent.estDans(this.grenouille.getCaseGrenouille())) {
                this.serpent.mangerGrenouille(this.grenouille);
                this.serpent.getCasesOccupees().addLast(this.serpent.getCasesOccupees().getLast());
                placerGrenouille();
            }
        } else {
            this.partieFinie = true;
        }
    }

    public void dessiner(Graphics graphics) {
        this.grille.dessiner(graphics);
        this.grenouille.dessiner(graphics);
    	this.serpent.dessiner(graphics);
    }

    public Case getRandomValideCase() {
        ArrayList<Case> valideCase = new ArrayList<Case>();
        for (int i=0; i<Grille.NBRE_DE_COLONNES; i++) {
            for (int j=0; j<Grille.NBRE_DE_LIGNES; j++) {
                if (!this.serpent.estDans(this.grille.getUneCase(i, j))) {
                    valideCase.add(this.grille.getUneCase(i, j));
                }
                
            }
        }

        Random random = new Random();
        int i = random.nextInt(valideCase.size());
        return valideCase.get(i);
        
    }

    public boolean isPartieFinie() {
        return this.partieFinie;
    }
}
