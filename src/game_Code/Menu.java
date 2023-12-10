package game_Code;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Menu-Background.png"); 
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
    	panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel headerLabel = new JLabel("Star Wars Attack");
        headerLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        headerLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headerLabel, gbc);

        JTextArea controlsArea = new JTextArea("Controls:\n\n" +
                "Move: Left and Right Arrow keys\n" +
                "Shoot: Space bar\n" +
                "Exit: Escape key\n\n" +
                "Press Space bar to start the game.");
        controlsArea.setFont(new Font("Courier New", Font.PLAIN, 20));
        controlsArea.setForeground(Color.WHITE); 
        controlsArea.setBackground(Color.BLACK);
        controlsArea.setPreferredSize(new Dimension(400, 120));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(controlsArea, gbc);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Courier New", Font.BOLD, 30));
        controlsArea.setForeground(Color.WHITE); 
        controlsArea.setBackground(Color.BLACK);
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(startButton, gbc);

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
