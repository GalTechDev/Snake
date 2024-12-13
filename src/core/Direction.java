package core;

public enum Direction {

	HAUT (0,-1),
	BAS (0,1),
	DROITE(1,0),
	GAUCHE(-1,0);

	private int dx;
	private int dy;
	
	Direction(int dx, int dy) {
		this.dx=dx;
		this.dy=dy;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public String toString() {
		return String.format("dx : %d | dy : %d", dx, dy); 
	}
}
