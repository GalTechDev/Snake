package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.Direction;
import core.Grille;
import core.Jeu;

public class PanneauControleur extends JPanel implements ActionListener, KeyListener {

	/**
	 * le bouton quitter
	 */
	private JButton quitter;
	/**
	 * le bouton pour demarrer le jeu
	 */
	private JButton jouer;
	/**
	 * le bouton pour mettre en pause
	 */
	private JButton pause;

	private Timer timer;
	private int delai=500;
	/**
	 * le "sequenceur" qui fait avancer le serpent et danser la grenouille
	 */

	private Jeu jeu;
	private PanneauJeu plateauJeu;

	public PanneauControleur(PanneauJeu plateauJeu) {
		super();
		this.jeu = new Jeu();
		this.plateauJeu = plateauJeu;
		this.plateauJeu.setJeu(jeu);
		this.setPreferredSize(new Dimension(Grille.NBRE_DE_COLONNES, 80));
		this.setBackground(Color.white);

		this.quitter = new JButton("Quitter");
		this.jouer = new JButton("Jouer");
		this.pause = new JButton("Pause");
		this.add(this.quitter);
		this.quitter.addActionListener(this);
		this.add(this.jouer);
		this.jouer.addActionListener(this);
		this.add(this.pause);
		this.pause.addActionListener(this);
		this.timer = new Timer(this.delai, this);
		this.addKeyListener(this);
		this.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e==null) {
			if (this.jeu.isPartieFinie()){
				System.out.println("Partie terminée...");
				this.jeu=new Jeu();
				this.plateauJeu.setJeu(jeu);
			}

		} else if (e.getSource() == this.quitter) {
			System.exit(ABORT);
			return;

		} else if (e.getSource() == this.jouer) {
			if (this.jeu.isPartieFinie()){
				System.out.println("Partie terminée...");
				this.jeu=new Jeu();
				this.plateauJeu.setJeu(jeu);
			}
			
			this.requestFocusInWindow();
			this.timer.restart();
			return;

		} else if (e.getSource() == this.pause) {
			if(this.timer.isRunning()) {
				this.timer.stop();
				this.pause.setText("CONTINUER");
				//this.timer.restart();
			}
			else {
				this.timer.restart();
				this.pause.setText("PAUSE");
				this.requestFocusInWindow();
				
			}

			this.plateauJeu.repaint();
			return;

		} else if (e.getSource() == this.timer) {
			if (this.jeu.isPartieFinie()){
				System.out.println("Partie terminée...");
				this.jeu=new Jeu();
				this.plateauJeu.setJeu(jeu);
			}
		}

		this.jeu.jouer();
		this.plateauJeu.repaint();
		this.timer.restart();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.jeu.setDirection(Direction.DROITE);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.jeu.setDirection(Direction.GAUCHE);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			this.jeu.setDirection(Direction.BAS);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.jeu.setDirection(Direction.HAUT);
		}

		this.actionPerformed(null);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
