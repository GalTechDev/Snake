package gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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

	private Clip clip;
	
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
		
		this.playMusic("asset/ost.wav"); 
		
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

	/**
     * Le constructeur
     * 
     * @param title Le titre qui s'affiche dans le bandeau du haut de la fenêtre
     * @throws HeadlessException
     */
	private void playMusic(String chemin) {
        try {
            // Charger l'audio depuis le fichier
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(chemin));
            clip = AudioSystem.getClip();  // Création d'un clip audio
            clip.open(audioInputStream);  // Ouverture du flux audio dans le clip
            
            // Démarrer la lecture de la musique
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // La musique sera jouée en boucle (en continu)
        } catch (Exception e) {
            // Gestion des erreurs si le fichier n'est pas trouvé ou si un problème survient
            JOptionPane.showMessageDialog(this, "Erreur lors de la lecture de la musique : " + e.getMessage());
        }
    }
}
