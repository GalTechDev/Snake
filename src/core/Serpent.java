package core;

import java.awt.Graphics;
import java.util.ArrayList;

public class Serpent implements InterfaceDessin{
    private ArrayList<Case> casesOccupees;
    private Direction direction;
    private int nbGrenouillesMangees;

    public Serpent() {
        this.casesOccupees = new ArrayList<Case>();
        this.nbGrenouillesMangees = 0;
    }

    public void placer(Case case1, Case case2, Case case3) {
        this.casesOccupees.clear();
        this.casesOccupees.addFirst(case1);
        this.casesOccupees.addFirst(case2);
        this.casesOccupees.addFirst(case3);
    }

    public void avancer(Case lacase) {
        this.casesOccupees.addFirst(lacase);
        this.casesOccupees.removeLast();

    }

    public Case getTete() {
        return this.casesOccupees.get(0);
    }

    public boolean mangerGrenouille(Grenouille grenouille) {
        boolean manger = (grenouille.getCaseGrenouille() == this.getTete());
        if (manger) {
            this.nbGrenouillesMangees++;
        }
        return manger;
    }

    public boolean seMordLaQueue() {
        int i = 0;
        for (Case lacase : this.casesOccupees) {
            if (i > 0) {
                if (lacase == this.getTete()) {
                    return true;
                }
            }
            i++;
        }

        return false;

    }

    public boolean estDans(Case lacase) {
        for (Case caseCorp : this.casesOccupees) {
            if (caseCorp == lacase) {
                return true;
            }
        }
        return false;

    }

    public boolean peutAvancer() {
        if (this.seMordLaQueue()) {
            return false;
        } else if (this.getDirection().getDx() == Direction.GAUCHE.getDx()) {
            if (this.getTete().getxGrille()==0) {
                return false;
            }
        } else if (this.getDirection().getDy() == Direction.HAUT.getDy()) {
            if (this.getTete().getyGrille()==0) {
                return false;
            }
        } else if (this.getDirection().getDx() == Direction.DROITE.getDx()) {
            if (this.getTete().getxGrille()==Grille.NBRE_DE_COLONNES-1) {
                return false;
            }
        } else if (this.getDirection().getDy() == Direction.BAS.getDy()) {
            if (this.getTete().getyGrille()==Grille.NBRE_DE_LIGNES-1) {
                return false;
            }
        } 
        return true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public ArrayList<Case> getCasesOccupees() {
        return casesOccupees;
    }

    private SpriteSheet getImage(int index) {

        if (index==0) {
            //afficher tete
            if (this.getDirection().getDx() == Direction.GAUCHE.getDx()) {
                return SpriteSheet.TETE_GAUCHE;

             } else if (this.getDirection().getDx() == Direction.DROITE.getDx()) {
                return SpriteSheet.TETE_DROITE;

             } else if (this.getDirection().getDy() == Direction.HAUT.getDy()) {
               return SpriteSheet.TETE_HAUT;

             } else {
                return SpriteSheet.TETE_BAS;
             }            
            
        } else if (index == this.casesOccupees.size()-1) {
            Case lacase = this.getCasesOccupees().get(index);
            Case prevCase;
            if (this.getCasesOccupees().get(index).getxGrille() == this.getCasesOccupees().get(index-1).getxGrille() && this.getCasesOccupees().get(index-1).getyGrille()  == this.getCasesOccupees().get(index).getyGrille()) {
                prevCase = this.getCasesOccupees().get(index-2);
            } else {
                prevCase = this.getCasesOccupees().get(index-1);
            }
            
            //afficher queue
            if (prevCase.getxGrille() < lacase.getxGrille()) { //droite
                return SpriteSheet.QUEUE_GAUCHE;
            
            } else if (prevCase.getxGrille() > lacase.getxGrille()) { //gauche
                return SpriteSheet.QUEUE_DROITE;

            } else if (prevCase.getyGrille() > lacase.getyGrille()) { //haut
                return SpriteSheet.QUEUE_BAS;

            } else { //bas
                return SpriteSheet.QUEUE_HAUT;

            }
            
        } else {
            Case nextCase = this.getCasesOccupees().get(index+1);
            Case prevCase = this.getCasesOccupees().get(index-1);
            //afficher corp
            if (nextCase.getxGrille() == prevCase.getxGrille())  {
                return SpriteSheet.CORP_V;

             } else if (nextCase.getyGrille() == prevCase.getyGrille()){
                return SpriteSheet.CORP_H;

            } else { //corner 
                Case lacase = this.getCasesOccupees().get(index);
                
                if (nextCase.getxGrille() < lacase.getxGrille() || prevCase.getxGrille() < lacase.getxGrille()) { //Gauche
                    if (nextCase.getyGrille() < lacase.getyGrille() || prevCase.getyGrille() < lacase.getyGrille()){ //haut
                        return SpriteSheet.CORP_C_HG;

                    } else { //bas
                        return SpriteSheet.CORP_C_BG;

                    }
                } else { //Droite
                    if (nextCase.getyGrille() < lacase.getyGrille() || prevCase.getyGrille() < lacase.getyGrille()){ //haut
                        return SpriteSheet.CORP_C_HD;

                    } else { //bas
                        return SpriteSheet.CORP_C_BD;

                    }
                }
            }
        }
    }

    public void dessiner(Graphics graphics) {
        
        for  (int i = 0 ; i < this.casesOccupees.size(); ++i ) {
            if (i == this.casesOccupees.size()-2) {
                if (this.getCasesOccupees().get(i).getxGrille() == this.getCasesOccupees().get(i+1).getxGrille() && this.getCasesOccupees().get(i+1).getyGrille()  == this.getCasesOccupees().get(i).getyGrille()) {
                    continue;
                }
            }
            getImage(i).dessiner(graphics, this.getCasesOccupees().get(i).getxGrille()*Case.TAILLE, this.getCasesOccupees().get(i).getyGrille()*Case.TAILLE);
        }
        
    }

    public String toString() {
        return String.format("Serpent : mangé=%d", this.nbGrenouillesMangees);
    }
}
