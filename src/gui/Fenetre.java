package gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import javax.swing.JFrame;

import core.Case;
import core.Grille;
import core.Jeu;


/**
 * Cette classe definit la fenetre principale de l'application
 * 
 * @author claudine
 *
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame  {

	private PanneauJeu plateauJeu;
	private PanneauControleur panneauControleur;
	private Jeu jeu=new Jeu();
	
	/**
	 * Le constructeur
	 * 
	 * @param title
	 *            Le titre qui s'affiche dans le bandeau du haut de la fenètre
	 * @throws HeadlessException
	 */
	public Fenetre(String title) {
		super("Serpent");
		
		this.plateauJeu = new PanneauJeu();	
		this.panneauControleur = new PanneauControleur(plateauJeu);
					
		
		this.requestFocus();
		this.getContentPane().setPreferredSize(new Dimension(new Dimension((Grille.NBRE_DE_COLONNES*Case.TAILLE)+5,
				(Grille.NBRE_DE_LIGNES*Case.TAILLE)+100)));
		this.getContentPane().add(this.plateauJeu,BorderLayout.CENTER);
		this.getContentPane().add(panneauControleur,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(300, 200);
				
		this.pack();
		this.setVisible(true);
		
	}

	
	
  
	
}
