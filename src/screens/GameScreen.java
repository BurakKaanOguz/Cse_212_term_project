package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import engine.*;
import gameobjects.towers.*;
import maps.*;


public class GameScreen extends JFrame {

    private UserChecker userChecker;
    private GameEngine gameEngine;
    private JPanel topPanel;
    private JPanel gamePanel;
    private JLabel moneyLabel;
    private JLabel healthLabel;
    private JLabel statusLabel;
    private JButton pauseButton;

    private int selectedTowerType = -1;
    private boolean sellMode = false;
    private boolean upgradeMode = false;
    private int highlightRow = -1;
    private int highlightCol = -1;
    private int difficulty;
    private AudioManager audioManager;


    public GameScreen(UserChecker checker, int difficulty) {
        this.userChecker = checker;
        this.difficulty = difficulty;

        Map map;
        if (difficulty == 2) {
            map = Level2.createMap();
        } else if (difficulty == 3) {
            map = Level3.createMap();
        } else {
            map = Level1.createMap();
        }

        this.gameEngine = new GameEngine(map, difficulty);

        setTitle("Tower Defense - Playing as: " + userChecker.getCurrentUser() + " (Level " + difficulty + ")");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        createTopPanel();
        createGamePanel();

        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                handleLeave();
            }
        });

        audioManager = new AudioManager();
        audioManager.playBackgroundMusic("src/assets/sound/background.wav");
        audioManager.setVolume(0.5f);

        startGameUpdates();
    }


    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1280, 80));
        topPanel.setBackground(new Color(60, 60, 60));

        moneyLabel = new JLabel("Money: $500");
        moneyLabel.setBounds(10, 10, 150, 25);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moneyLabel.setForeground(Color.YELLOW);
        topPanel.add(moneyLabel);

        healthLabel = new JLabel("Health: 100%");
        healthLabel.setBounds(10, 40, 150, 25);
        healthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        healthLabel.setForeground(Color.RED);
        topPanel.add(healthLabel);

        statusLabel = new JLabel("Select a tower to place");
        statusLabel.setBounds(200, 25, 250, 30);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(Color.WHITE);
        topPanel.add(statusLabel);

        createTowerButtons();
        createActionButtons();
    }


    private void createTowerButtons() {
        String[] towerNames = { "Tower 1", "Tower 2", "Tower 3", "Plane" };
        int[] towerCosts = { 100, 150, 200, 300 };
        String[] towerImages = { "tower11.png", "tower21.png", "tower31.png", "plane1.png" };

        int startX = 400;
        for (int i = 0; i < 4; i++) {
            JLabel priceLabel = new JLabel("$" + towerCosts[i], SwingConstants.CENTER);
            priceLabel.setBounds(startX + (i * 110), 60, 80, 20);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            priceLabel.setForeground(Color.YELLOW);
            topPanel.add(priceLabel);

            JButton towerBtn = new JButton();
            towerBtn.setBounds(startX + (i * 110) + 10, 5, 60, 60);
            towerBtn.setContentAreaFilled(false);
            towerBtn.setBorderPainted(false);
            towerBtn.setFocusPainted(false);

            try {
                ImageIcon icon = new ImageIcon("src/assets/tower_assets/" + towerImages[i]);
                Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                towerBtn.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                System.out.println("no tower img");
            }

            final int type = i;
            final String tName = towerNames[i];
            final int tCost = towerCosts[i];
            towerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedTowerType = type;
                    sellMode = false;
                    upgradeMode = false;
                    statusLabel.setText("Place " + tName + " ($" + tCost + ")");
                    statusLabel.setForeground(Color.GREEN);
                }
            });

            topPanel.add(towerBtn);
        }
    }


    private void createActionButtons() {
        JLabel sellLabel = new JLabel("Sell", SwingConstants.CENTER);
        sellLabel.setBounds(850, 60, 80, 20);
        sellLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sellLabel.setForeground(Color.YELLOW);
        topPanel.add(sellLabel);

        JButton sellButton = new JButton();
        sellButton.setBounds(860, 5, 60, 60);
        sellButton.setContentAreaFilled(false);
        sellButton.setBorderPainted(false);
        sellButton.setFocusPainted(false);

        try {
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/sell_icon.png");
            sellButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            System.out.println("no sell icon");
        }

        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellMode = true;
                selectedTowerType = -1;
                upgradeMode = false;
                statusLabel.setText("Click tower to sell");
                statusLabel.setForeground(Color.ORANGE);
            }
        });
        topPanel.add(sellButton);

        JLabel upgradeLabel = new JLabel("Upgrade", SwingConstants.CENTER);
        upgradeLabel.setBounds(940, 60, 80, 20);
        upgradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        upgradeLabel.setForeground(Color.YELLOW);
        topPanel.add(upgradeLabel);

        JButton upgradeButton = new JButton();
        upgradeButton.setBounds(950, 5, 60, 60);
        upgradeButton.setContentAreaFilled(false);
        upgradeButton.setBorderPainted(false);
        upgradeButton.setFocusPainted(false);

        try {
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/upgrade_icon.png");
            upgradeButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            upgradeButton.setText("UP");
            upgradeButton.setFont(new Font("Arial", Font.BOLD, 30));
            upgradeButton.setForeground(Color.GREEN);
        }

        upgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeMode = true;
                selectedTowerType = -1;
                sellMode = false;
                statusLabel.setText("Click tower to upgrade (50% cost)");
                statusLabel.setForeground(Color.CYAN);
            }
        });
        topPanel.add(upgradeButton);

        pauseButton = new JButton();
        pauseButton.setBounds(1100, 10, 60, 60);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);

        try {
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/stop_icon.png");
            pauseButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            System.out.println("no pause icon");
        }

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        });
        topPanel.add(pauseButton);

        JButton leaveButton = new JButton();
        leaveButton.setBounds(1180, 10, 60, 60);
        leaveButton.setContentAreaFilled(false);
        leaveButton.setBorderPainted(false);
        leaveButton.setFocusPainted(false);

        try {
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/close_icon.png");
            leaveButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            System.out.println("no leave icon");
        }

        leaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLeave();
            }
        });
        topPanel.add(leaveButton);
    }


    private void createGamePanel() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gameEngine.render(g);

                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.BLACK);
                g.drawString("Wave: " + gameEngine.getCurrentWave(), 1100, 20);
                g.setColor(Color.BLACK);
                g.drawString("Score: " + gameEngine.getCurrentScore(), 1100, 45);

                if (highlightRow >= 0 && highlightCol >= 0) {
                    boolean canPlace = gameEngine.canPlaceTowerAt(highlightCol * 64, highlightRow * 64);
                    if (canPlace) {
                        g.setColor(new Color(0, 255, 0, 100));
                    } else {
                        g.setColor(new Color(255, 0, 0, 100));
                    }
                    g.fillRect(highlightCol * 64, highlightRow * 64, 64, 64);

                    g.setColor(Color.YELLOW);
                    ((Graphics2D) g).setStroke(new BasicStroke(3));
                    g.drawRect(highlightCol * 64, highlightRow * 64, 64, 64);

                    if (selectedTowerType >= 0 && canPlace) {
                        int range = 0;
                        if (selectedTowerType == 0) {
                            range = 150;
                        } else if (selectedTowerType == 1) {
                            range = 120;
                        } else if (selectedTowerType == 2) {
                            range = 200;
                        } else if (selectedTowerType == 3) {
                            range = 999;
                        }

                        int cx = highlightCol * 64 + 32;
                        int cy = highlightRow * 64 + 32;
                        g.setColor(new Color(255, 255, 255, 50));
                        g.fillOval(cx - range, cy - range, range * 2, range * 2);
                        g.setColor(new Color(255, 255, 255, 150));
                        ((Graphics2D) g).setStroke(new BasicStroke(1));
                        g.drawOval(cx - range, cy - range, range * 2, range * 2);
                    }
                }
            }
        };
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(null);

        JButton doomButton = new JButton();
        doomButton.setBounds(10, 550, 30, 30);
        doomButton.setContentAreaFilled(false);
        doomButton.setBorderPainted(false);
        doomButton.setFocusPainted(false);

        try {
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/doomface.jpg");
            doomButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            System.out.println("no doom img");
        }

        doomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameEngine.isDoomMode()) {
                    gameEngine.activateDoomMode();
                    audioManager.stopMusic();
                    audioManager.playBackgroundMusic("src/assets/sound/doom.wav");
                    doomButton.setEnabled(false);
                }
            }
        });

        gamePanel.add(doomButton);

        gamePanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }

            public void mouseExited(MouseEvent e) {
                highlightCol = -1;
                highlightRow = -1;
                gamePanel.repaint();
            }
        });

        gamePanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                updateHighlight(e.getX(), e.getY());
            }
        });
    }


    private void handleClick(int x, int y) {
        if (gameEngine.isPaused()) {
            return;
        }

        int gridX = (x / 64) * 64;
        int gridY = (y / 64) * 64;

        if (sellMode) {
            if (gameEngine.sellTowerAt(gridX, gridY)) {
                statusLabel.setText("Tower sold!");
                statusLabel.setForeground(Color.YELLOW);
                sellMode = false;
                resetStatusLabelAfterDelay();
            } else {
                statusLabel.setText("No tower here!");
                statusLabel.setForeground(Color.RED);
            }
        } else if (upgradeMode) {
            if (gameEngine.upgradeTowerAt(gridX, gridY)) {
                statusLabel.setText("Tower upgraded!");
                statusLabel.setForeground(Color.GREEN);
                upgradeMode = false;
                resetStatusLabelAfterDelay();
            } else {
                statusLabel.setText("Can't upgrade!");
                statusLabel.setForeground(Color.RED);
            }
        } else if (selectedTowerType >= 0) {
            if (gameEngine.placeTower(selectedTowerType, gridX, gridY)) {
                statusLabel.setText("Tower placed!");
                statusLabel.setForeground(Color.GREEN);
                selectedTowerType = -1;
                resetStatusLabelAfterDelay();
            } else if (!gameEngine.canPlaceTowerAt(gridX, gridY)) {
                statusLabel.setText("Can't place here!");
                statusLabel.setForeground(Color.RED);
            } else {
                statusLabel.setText("Not enough money!");
                statusLabel.setForeground(Color.RED);
            }
        }
        gamePanel.repaint();
    }


    private void resetStatusLabelAfterDelay() {
        Timer resetTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Select a tower to place");
                statusLabel.setForeground(Color.WHITE);
                ((Timer) e.getSource()).stop();
            }
        });
        resetTimer.setRepeats(false);
        resetTimer.start();
    }


    private void updateHighlight(int x, int y) {
        int col = x / 64;
        int row = y / 64;

        if (col != highlightCol || row != highlightRow) {
            highlightCol = col;
            highlightRow = row;

            int towerX = col * 64;
            int towerY = row * 64;

            if (sellMode) {
                Tower tower = gameEngine.getTowerAt(towerX, towerY);
                if (tower != null) {
                    statusLabel.setText("Sell for $" + tower.getSellPrice());
                    statusLabel.setForeground(Color.YELLOW);
                } else {
                    statusLabel.setText("Click tower to sell");
                    statusLabel.setForeground(Color.ORANGE);
                }
            } else if (upgradeMode) {
                Tower tower = gameEngine.getTowerAt(towerX, towerY);
                if (tower != null) {
                    int price = tower.getUpgradePrice();
                    if (price == -1) {
                        statusLabel.setText("Already max level!");
                        statusLabel.setForeground(Color.GRAY);
                    } else {
                        statusLabel.setText("Upgrade for $" + price);
                        statusLabel.setForeground(Color.CYAN);
                    }
                } else {
                    statusLabel.setText("Click tower to upgrade");
                    statusLabel.setForeground(Color.CYAN);
                }
            }
            gamePanel.repaint();
        }
    }


    private void togglePause() {
        gameEngine.togglePause();
        try {
            String iconName;
            if (gameEngine.isPaused()) {
                iconName = "continue_icon.png";
            } else {
                iconName = "stop_icon.png";
            }
            ImageIcon icon = new ImageIcon("src/assets/icon_assets/" + iconName);
            pauseButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            System.out.println("no icon");
        }
    }


    private void handleLeave() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to leave? Your progress will be saved.",
                "Leave Game", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            gameEngine.stop();
            audioManager.stopMusic();
            int finalScore = gameEngine.calculateFinalScore();
            String gameTime = gameEngine.getGameTimeFormatted();
            userChecker.updateScoreAndSession(finalScore, gameTime);

            JOptionPane.showMessageDialog(this,
                    "Score saved: " + finalScore, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }


    private void startGameUpdates() {
        Timer uiTimer = new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameEngine.isGameOver()) {
                    ((Timer) e.getSource()).stop();
                    audioManager.stopMusic();
                    GameOverScreen gameOverScreen = new GameOverScreen(
                            userChecker,
                            gameEngine.calculateFinalScore(),
                            gameEngine.getEnemiesDefeated(),
                            gameEngine.getCurrentWave(),
                            difficulty);
                    gameOverScreen.setVisible(true);
                    dispose();
                    return;
                }

                moneyLabel.setText("Money: $" + gameEngine.getMoney());
                healthLabel.setText("Health: " + gameEngine.getHealth() + "%");
                gamePanel.repaint();
            }
        });
        uiTimer.start();
    }
}