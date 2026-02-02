package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import engine.*;


public class CreditsScreen extends JFrame {

    private UserChecker userChecker;


    public CreditsScreen(UserChecker checker) {
        this.userChecker = checker;

        setTitle("Player Stats");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));

        JLabel titleLabel = new JLabel("PLAYER STATISTICS", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 215, 0));
        panel.add(titleLabel);

        String username = userChecker.getCurrentUser();
        String score = "0";
        String gameTime = "00:00";

        if (username != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("game_data.csv"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username)) {
                        if (parts.length >= 3) {
                            score = parts[2];
                        }
                        if (parts.length >= 5) {
                            gameTime = parts[4];
                        }
                        break;
                    }
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("no file");
            }
        }

        JLabel nameLabel = new JLabel("Player:", SwingConstants.RIGHT);
        nameLabel.setBounds(50, 90, 120, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(nameLabel);

        String displayName = "Guest";
        if (username != null) {
            displayName = username;
        }
        JLabel nameValue = new JLabel(displayName, SwingConstants.LEFT);
        nameValue.setBounds(180, 90, 170, 30);
        nameValue.setFont(new Font("Arial", Font.BOLD, 18));
        nameValue.setForeground(Color.WHITE);
        panel.add(nameValue);

        JLabel scoreLabel = new JLabel("Best Score:", SwingConstants.RIGHT);
        scoreLabel.setBounds(50, 140, 120, 30);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(scoreLabel);

        JLabel scoreValue = new JLabel(score, SwingConstants.LEFT);
        scoreValue.setBounds(180, 140, 170, 30);
        scoreValue.setFont(new Font("Arial", Font.BOLD, 18));
        scoreValue.setForeground(new Color(255, 215, 0));
        panel.add(scoreValue);

        JLabel timeLabel = new JLabel("Last Game:", SwingConstants.RIGHT);
        timeLabel.setBounds(50, 190, 120, 30);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(timeLabel);

        JLabel timeValue = new JLabel(gameTime, SwingConstants.LEFT);
        timeValue.setBounds(180, 190, 170, 30);
        timeValue.setFont(new Font("Arial", Font.BOLD, 18));
        timeValue.setForeground(Color.CYAN);
        panel.add(timeValue);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(125, 260, 150, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(backButton);

        add(panel);
    }
}
