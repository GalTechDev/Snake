package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum SpriteSheet {

	TETE_HAUT 	(192,0),
	TETE_BAS 	(256,64),
	TETE_DROITE	(256,0),
	TETE_GAUCHE	(192,64),

	CORP_H		(64, 0),
	CORP_V		(128,64),

	CORP_C_BG	(128,0),
	CORP_C_BD	(0,0),
	CORP_C_HG	(128,128),
	CORP_C_HD	(0,64),

	QUEUE_HAUT	(192,128),
	QUEUE_BAS	(256,192),
	QUEUE_DROITE(256,128),
	QUEUE_GAUCHE(192,192),
	POMME		(0, 192);


	/*
	 * sprite size = 64px
	 */

	private int x;
	private int y;
	private BufferedImage sheet;
	
	SpriteSheet(int x, int y) {
		this.x=x;
		this.y=y;

		try {
			this.sheet = ImageIO.read(new File("src/gui/asset/snake-graphics.png"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void dessiner(Graphics graphics, int x, int y) {
		
		graphics.drawImage(
			this.sheet, 
			x, y, 
			x+Case.TAILLE, y+Case.TAILLE, 
			this.getX(), this.getY(), 
			this.getX()+64, this.getY()+64,
			null, null
		);
	}
	
	public String toString() {
		return String.format("Sprite at : x=%d, y=%d", this.x, this.y); 
	}
}
