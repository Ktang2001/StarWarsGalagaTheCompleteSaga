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

	private static final long serialVersionUID = -602017958886020199L;
	private List<String> shipImages;
    private Player player;
    final int up = 5;
  	final int down = 5;
  	final int left = 5;
  	final int right = 5;
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
        playerShip.setImage(playerShip.getShipNum());
        player = new Player(100, 100, 400, 600, playerShip, 3, 3); 
        

  
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                updateGame(player,playerShip);
                repaint(); 
            }
        });
        timer.start();
        JPanel space = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		g.setColor(Color.BLACK);
        		g.fillRect(0, 0, 2000, 2000);
        		g.drawImage(playerShip.setImage(playerShip.getShipNum()),player.getX(),player.getY(),this);
        		
        		for(Object[] opponent : opponents) {
        			int opponentX = (int)opponent[0];
        			int opponentY = (int)opponent[1];
        			Ship opponentShip = (Ship) opponent[2];
        			g.drawImage(opponentShip.getImage(),opponentX,opponentY,this);
        			
        			
        		}
        		
        	}
        };
        setContentPane(space);
    }
    
    // Movement for Player
	private class DirectionListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			
			 switch (e.getKeyCode())
               {
                  case KeyEvent.VK_UP:
                     player.setY(player.getY() - up);
                     break;
                  case KeyEvent.VK_DOWN:
                      player.setY(player.getY() + down);
                     break;
                  case KeyEvent.VK_LEFT:
                      player.setX(player.getX()- left);
                     break;
                  case KeyEvent.VK_RIGHT:
                      player.setX(player.getX()+ right);
                     break;
               }
			 repaint();
		}
		@Override
		public void keyTyped (KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e) {}
	}

    public void updateGame(Player player ,Ship playerShip){
 
    	
       
    	Random rand = new Random();
        int num = rand.nextInt(1, 10);
        int randX, randShip;
        Ship opponentShip;
        
       
        Object[] playerObject = new Object[]{player.getX(), player.getY(), playerShip, 3, 3};
        opponents.add(playerObject);
        

        for (int i = 0; i < num; i++) {
            randX = rand.nextInt(0, 799);
            randShip = rand.nextInt(0, shipImages.size());
            opponentShip = new Ship(randShip, 2, shipImages.get(randShip));

            Object[] opponent = new Object[] {randX, 0, opponentShip, 3, 3}; 
            opponents.add(opponent);
        }
    }
    		 
    


    public static void main(String[] args) {
    	
    	GameLoop game = new GameLoop();
    	game.setSize(800,600);
    	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	game.setFocusable(true);
    	game.setVisible(true);
    	
    }
}
