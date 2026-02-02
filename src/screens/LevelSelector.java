package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import engine.*;


public class LevelSelector extends JFrame {

    private UserChecker userChecker;


    public LevelSelector(UserChecker checker) {
        this.userChecker = checker;

        setTitle("Select Level");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));

        JLabel titleLabel = new JLabel("SELECT LEVEL", SwingConstants.CENTER);
        titleLabel.setBounds(150, 30, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 215, 0));
        panel.add(titleLabel);

        createLevelButton(panel, "Level 1", "Easy - Wave 1 Start", 50, 100, 1);
        createLevelButton(panel, "Level 2", "Medium - Wave 3 Start", 225, 100, 2);
        createLevelButton(panel, "Level 3", "Hard - Wave 5 Start", 400, 100, 3);

        JButton backButton = new JButton("BACK TO MENU");
        backButton.setBounds(200, 300, 200, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(100, 100, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu(userChecker);
                mainMenu.setVisible(true);
                dispose();
            }
        });
        panel.add(backButton);

        add(panel);
    }


    private void createLevelButton(JPanel panel, String title, String desc, int x, int y, int level) {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(null);
        btnPanel.setBounds(x, y, 150, 150);
        btnPanel.setBackground(new Color(70, 70, 70));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

        JLabel titleLbl = new JLabel(title, SwingConstants.CENTER);
        titleLbl.setBounds(0, 10, 150, 20);
        titleLbl.setFont(new Font("Arial", Font.BOLD, 18));
        titleLbl.setForeground(Color.WHITE);
        btnPanel.add(titleLbl);

        JTextArea descArea = new JTextArea(desc);
        descArea.setBounds(10, 40, 130, 40);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setForeground(Color.LIGHT_GRAY);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        btnPanel.add(descArea);

        JButton selectBtn = new JButton("PLAY");
        selectBtn.setBounds(25, 100, 100, 30);
        selectBtn.setBackground(new Color(70, 130, 180));
        selectBtn.setForeground(Color.WHITE);
        selectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        selectBtn.setFocusPainted(false);
        selectBtn.setOpaque(true);
        selectBtn.setBorderPainted(false);

        selectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame(level);
            }
        });

        btnPanel.add(selectBtn);
        panel.add(btnPanel);
    }


    private void startGame(int level) {
        GameScreen gameScreen = new GameScreen(userChecker, level);
        gameScreen.setVisible(true);
        dispose();
    }
}
