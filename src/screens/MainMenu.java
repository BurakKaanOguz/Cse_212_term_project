package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import engine.*;


public class MainMenu extends JFrame {

    private UserChecker userChecker;
    private JButton startButton;
    private JButton loginButton;
    private JButton exitButton;


    public MainMenu(UserChecker checker) {
        this.userChecker = checker;

        setTitle("Tower Defense Game");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));

        JLabel titleLabel = new JLabel("TOWER DEFENSE", SwingConstants.CENTER);
        titleLabel.setBounds(50, 80, 300, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 215, 0));
        panel.add(titleLabel);

        startButton = new JButton("START");
        startButton.setBounds(100, 200, 200, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(new Color(34, 139, 34));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        panel.add(startButton);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(100, 270, 200, 50);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        panel.add(loginButton);

        JButton creditsButton = new JButton("CREDITS");
        creditsButton.setBounds(100, 340, 200, 50);
        creditsButton.setFont(new Font("Arial", Font.BOLD, 20));
        creditsButton.setBackground(new Color(255, 140, 0));
        creditsButton.setForeground(Color.WHITE);
        creditsButton.setFocusPainted(false);
        creditsButton.setOpaque(true);
        creditsButton.setBorderPainted(false);
        panel.add(creditsButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(100, 410, 200, 50);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setBackground(new Color(178, 34, 34));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        panel.add(exitButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleStart();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCredits();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(panel);
    }


    private void handleStart() {
        if (!userChecker.isLoggedIn()) {
            JOptionPane.showMessageDialog(this,
                    "Please login first!",
                    "Not Logged In",
                    JOptionPane.WARNING_MESSAGE);
            handleLogin();
        } else {
            LevelSelector levelSelector = new LevelSelector(userChecker);
            levelSelector.setVisible(true);
        }
    }


    private void handleLogin() {
        LoginMenu loginMenu = new LoginMenu(userChecker);
        loginMenu.setVisible(true);
    }


    private void handleCredits() {
        CreditsScreen creditsScreen = new CreditsScreen(userChecker);
        creditsScreen.setVisible(true);
    }
}