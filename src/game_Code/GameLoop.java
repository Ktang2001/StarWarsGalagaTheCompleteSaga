package game_Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameLoop extends JFrame {
    private List<String> shipImages;
    private Player player;

    public GameLoop() {
        shipImages = new ArrayList<>();
       
        shipImages.add("N1_Starfigher.png"); // We will need to get some images of starwars ships to put in these array indexes  
        shipImages.add("");
        shipImages.add("");
        shipImages.add("");

        player = new Player(0, 0, 0, 0, null, 0, 0); 

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

    private void updateGame() {
        // Update game logic here
       
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        
        // We will need to set up the background first
        // Then we need to draw the player and oppents at their repective positions 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameLoop game = new GameLoop();
            game.setSize(800, 600);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setVisible(true);
        });
    }
}
