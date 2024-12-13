package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Case implements InterfaceDessin{
    private int xGrille;
    private int yGrille;

    public static final int TAILLE = 40;

    public Case(int x, int y) {
        this.xGrille = x;
        this.yGrille = y;
    }

    public int getxGrille() {
        return xGrille;
    }

    public int getyGrille() {
        return yGrille;
    }

    public void dessiner(Graphics graphics) {
        graphics.setColor(new Color(34, 107, 74));
        graphics.fillRect(this.getxGrille()*TAILLE, this.getyGrille()*TAILLE, TAILLE, TAILLE);

    }

    public String toString() {
        return String.format("x : %d | y : %d", this.xGrille, this.yGrille); 
    }
}
