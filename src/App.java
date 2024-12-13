import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class App extends JPanel implements ActionListener {

    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_TILES_X = WIDTH / TILE_SIZE;
    private static final int NUM_TILES_Y = HEIGHT / TILE_SIZE;
    
    private LinkedList<Point> snake;
    private Point food;
    private int direction; // 0 = haut, 1 = droite, 2 = bas, 3 = gauche
    private boolean gameOver;
    private Timer timer;

    public App() {
        this.snake = new LinkedList<>();
        this.snake.add(new Point(NUM_TILES_X / 2, NUM_TILES_Y / 2));
        this.direction = 1; // Commencer à se déplacer vers la droite
        this.gameOver = false;
        
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 2) direction = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 3) direction = 1;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 0) direction = 2;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 1) direction = 3;
                        break;
                }
            }
        });
        
        this.timer = new Timer(500, this);
        this.timer.start();
        
        spawnFood();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moveSnake();
            checkCollisions();
            checkFoodConsumption();
            repaint();
        }
    }

    private void moveSnake() {
        Point head = snake.getFirst();
        Point newHead = null;

        switch (direction) {
            case 0: // Haut
                newHead = new Point(head.x, head.y - 1);
                break;
            case 1: // Droite
                newHead = new Point(head.x + 1, head.y);
                break;
            case 2: // Bas
                newHead = new Point(head.x, head.y + 1);
                break;
            case 3: // Gauche
                newHead = new Point(head.x - 1, head.y);
                break;
        }

        if (!gameOver) {
            snake.addFirst(newHead);
            if (newHead.equals(food)) {
                spawnFood(); // Si la tête touche la nourriture, on génère une nouvelle nourriture
            } else {
                snake.removeLast(); // Sinon, on enlève la dernière partie du serpent
            }
        }
    }

    private void checkCollisions() {
        Point head = snake.getFirst();
        // Vérifier les collisions avec les bords
        if (head.x < 0 || head.x >= NUM_TILES_X || head.y < 0 || head.y >= NUM_TILES_Y) {
            gameOver = true;
        }
        // Vérifier les collisions avec le corps
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    private void checkFoodConsumption() {
        if (snake.getFirst().equals(food)) {
            spawnFood();
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
        while (snake.contains(food)) { // Éviter que la nourriture apparaisse sur le serpent
            food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", WIDTH / 4, HEIGHT / 2);
        } else {
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            g.setColor(Color.RED);
            g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu du Serpent");
        App game = new App();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
