import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; // 50px for the text panel on top

    JFrame frame = new JFrame("Triple Strike Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String userSymbol;
    String computerSymbol;
    String currentPlayer;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe(String userSymbol) {
        this.userSymbol = userSymbol;
        this.computerSymbol = userSymbol.equals("X") ? "O" : "X";
        this.currentPlayer = userSymbol;

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(0xA2B9BC));
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Garamond", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Triple Strike Game");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(0xA2B9BC));
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(new Color(0xA2B9BC));
                tile.setForeground(Color.white);
                tile.setFont(new Font("Garamond", Font.PLAIN, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(userSymbol) ? computerSymbol : userSymbol;
                                textLabel.setText(currentPlayer + "'s turn.");
                                if (currentPlayer.equals(computerSymbol) && !gameOver) {
                                    computerMove();
                                    checkWinner();
                                    if (!gameOver) {
                                        currentPlayer = userSymbol;
                                        textLabel.setText(currentPlayer + "'s turn.");
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    void computerMove() {
        Random rand = new Random();
        while (true) {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if (board[row][col].getText().equals("")) {
                board[row][col].setText(computerSymbol);
                turns++;
                break;
            }
        }
    }
