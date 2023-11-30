package game_Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop extends JFrame {
    private List<String> shipImages;
    private Player player;
    final int up = 5;
  	final int down = 5;
  	final int left = 5;
  	final int right = 5;
    int y = player.getY();
    int x = player.getX();
    private List<Object[]> opponents;

    public GameLoop() {
    	addKeyListener (new DirectionListener());
        shipImages = new ArrayList<>();
       
        shipImages.add("N1_Starfigher.png");   
        shipImages.add("JediStarfighter.png");
        shipImages.add("ARC-170.png");
        shipImages.add("X-Wing.png");
        shipImages.add("Y-Wing.png");
        shipImages.add("falcon.png");
        shipImages.add("VultureDroid.png");
        shipImages.add("Tri-Fighter.png");
        shipImages.add("battle-station.png");
        shipImages.add("Grievous-Ship.png");
        shipImages.add("malevolence-starship.png");
        shipImages.add("Tie-Fighter.png");
        shipImages.add("star-destroyer.png");
        shipImages.add("executor-star-destroyer.png");
        shipImages.add("death-star.png");
        shipImages.add("death-star-2.png");

        opponents = new ArrayList<>();
        

        Ship playerShip = new Ship (1,2,shipImages.get(1));
        player = new Player(100, 0, 200, 200, playerShip, 0, 0); 

        // Set up the game loop timer
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform game logic here
                updateGame();
                repaint(); 
            }
        });
        timer.start();
    }
    
    // Movement for Player
	private class DirectionListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			 switch (e.getKeyCode())
               {
                  case KeyEvent.VK_UP:
                     y += up;
                     player.setY(y);
                     break;
                  case KeyEvent.VK_DOWN:
                      y -= down;
                      player.setY(y);
                     break;
                  case KeyEvent.VK_LEFT:
                      x += left;
                      player.setX(x);
                     break;
                  case KeyEvent.VK_RIGHT:
                      x -= right;
                      player.setX(x);
                     break;
               }
			 repaint();
		}
		@Override
		public void keyTyped (KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e) {}
	}

    public void updateGame(){
        // Update game logic here
    	
        // Spawn new enemies or power-ups
    	Random rand = new Random();
        int num = rand.nextInt(1, 10);
        int randX, randShip;
        Ship opponentShip;

        for (int i = 0; i < num; i++) {
            randX = rand.nextInt(0, 799);
            randShip = rand.nextInt(0, shipImages.size()); // Assuming 0 to size of shipImages
            opponentShip = new Ship(randShip, 2, shipImages.get(randShip));

            Object[] opponent = new Object[] {randX, 0, opponentShip, 25, 25}; // Example properties
            opponents.add(opponent);
        }
    }
    		 
    @Override	
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        
        // We will need to set up the background first
        // Then we need to draw the player and oppents at their repective positions 
    }

    public static void main(String[] args) {
    	GameLoop game = new GameLoop();
    	game.setSize(800,600);
    	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	game.setVisible(true);
    }
}
