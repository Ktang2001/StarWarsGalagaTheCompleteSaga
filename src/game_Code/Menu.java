package game_Code;

import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;

    public Menu() {
        setTitle("Star Wars Attack - IntailMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel headerLabel = new JLabel("Star Wars Attack");
        headerLabel.setBounds(150, 10, 200, 30);
        panel.add(headerLabel);

        JTextArea controlsArea = new JTextArea("Controls:\n\n" +
                "Move: Arrow keys\n" +
                "Shoot: Space bar\n" +
                "Exit: Escape key\n\n" +
                "Press Space bar to start the game.");
        controlsArea.setBounds(50, 50, 300, 80);
        controlsArea.setEditable(false);
        panel.add(controlsArea);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(150, 140, 100, 30);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                startGame();
            }
        });
    }

    private void startGame() {
        SwingUtilities.invokeLater(() -> {
            GameLoop game = new GameLoop();
            game.setVisible(true);

            Timer timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.updateGame();
                    game.repaint();
                }
            });

            timer.start();
        });
    }


        public void showPostGameMenu(int score) {
            setTitle("Star Wars Attack - Game Over");
            setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

            JPanel panel = new JPanel();
            add(panel);
            placePostGameComponents(panel, score);

            setVisible(true);
        }

        private void placePostGameComponents(JPanel panel, int score) {
            panel.setLayout(null);

            JLabel gameOverLabel = new JLabel("Game Over!");
            gameOverLabel.setBounds(150, 10, 200, 30);
            panel.add(gameOverLabel);

            JLabel scoreLabel = new JLabel("Your Score: " + score);
            scoreLabel.setBounds(150, 50, 200, 30);
            panel.add(scoreLabel);

            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(150, 100, 100, 30);
            panel.add(exitButton);

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }

  
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu initialMenu = new Menu();
        });
    }
}
