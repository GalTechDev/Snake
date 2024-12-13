package core;

import java.awt.Graphics;

public class Grenouille implements InterfaceDessin{
    private Case caseGrenouille;

    public Grenouille() {
    }

    public Case getCaseGrenouille() {
        return caseGrenouille;
    }

    public void setCaseGrenouille(Case caseGrenouille) {
        this.caseGrenouille = caseGrenouille;
    }

    public void dessiner(Graphics graphics) {
        //graphics.setColor(Color.RED);
        //graphics.fillOval(this.getCaseGrenouille().getxGrille()*Case.TAILLE, this.getCaseGrenouille().getyGrille()*Case.TAILLE, Case.TAILLE, Case.TAILLE);
        SpriteSheet.POMME.dessiner(graphics, this.getCaseGrenouille().getxGrille()*Case.TAILLE, this.getCaseGrenouille().getyGrille()*Case.TAILLE);
    }

    public String toString() {
		return String.format("Grenouille at : %s", this.getCaseGrenouille()); 
	}
}
