package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import core.Case;
import core.Direction;
import core.Grille;
import core.Jeu;

/**
 * Cette classe definit la zone de jeu.
 * 
 * @author claudine
 *
 */


public class PanneauJeu extends JPanel  {

	
	private Jeu jeu;
	
	public PanneauJeu() {
		super();
	
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Grille.NBRE_DE_COLONNES*Case.TAILLE,Grille.NBRE_DE_LIGNES*Case.TAILLE));
	}

	


	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.jeu.dessiner(g);
		
		
	}

	public void informerJeu(Direction direction) {
		
		this.jeu.setDirection(direction);
	
		this.repaint();
	}
	
	
}
