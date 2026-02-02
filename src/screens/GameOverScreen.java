package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import engine.*;


public class GameOverScreen extends JFrame {

    private UserChecker userChecker;


    public GameOverScreen(UserChecker checker, int score, int enemiesDefeated, int wavesCompleted, int difficulty) {
        this.userChecker = checker;

        setTitle("Game Over");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));

        JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setBounds(100, 30, 300, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.RED);
        panel.add(titleLabel);

        createStatLabel(panel, "Final Score:", String.valueOf(score), 100);
        createStatLabel(panel, "Enemies Defeated:", String.valueOf(enemiesDefeated), 140);
        createStatLabel(panel, "Waves Reached:", String.valueOf(wavesCompleted), 180);

        JButton playAgainBtn = new JButton("PLAY AGAIN");
        playAgainBtn.setBounds(150, 250, 200, 40);
        playAgainBtn.setBackground(new Color(34, 139, 34));
        playAgainBtn.setForeground(Color.WHITE);
        playAgainBtn.setFont(new Font("Arial", Font.BOLD, 20));
        playAgainBtn.setFocusPainted(false);
        playAgainBtn.setOpaque(true);
        playAgainBtn.setBorderPainted(false);
        playAgainBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameScreen gameScreen = new GameScreen(userChecker, difficulty);
                gameScreen.setVisible(true);
                dispose();
            }
        });
        panel.add(playAgainBtn);

        JButton menuBtn = new JButton("MAIN MENU");
        menuBtn.setBounds(150, 310, 200, 40);
        menuBtn.setBackground(new Color(178, 34, 34));
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setFont(new Font("Arial", Font.BOLD, 20));
        menuBtn.setFocusPainted(false);
        menuBtn.setOpaque(true);
        menuBtn.setBorderPainted(false);
        menuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu(userChecker);
                mainMenu.setVisible(true);
                dispose();
            }
        });
        panel.add(menuBtn);

        add(panel);
    }


    private void createStatLabel(JPanel panel, String label, String value, int y) {
        JLabel l = new JLabel(label, SwingConstants.RIGHT);
        l.setBounds(50, y, 200, 30);
        l.setFont(new Font("Arial", Font.PLAIN, 18));
        l.setForeground(Color.LIGHT_GRAY);
        panel.add(l);

        JLabel v = new JLabel(value, SwingConstants.LEFT);
        v.setBounds(260, y, 200, 30);
        v.setFont(new Font("Arial", Font.BOLD, 18));
        v.setForeground(Color.WHITE);
        panel.add(v);
    }
}
