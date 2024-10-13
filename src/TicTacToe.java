import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
    // Dimensiune grilă (3x3)
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;  // Alternarea între jucătorul X și O
    private int moves = 0;  // Numără mișcările

    public TicTacToe() {
        // Configurare fereastră
        setTitle("X și O");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Inițializare butoane
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();  // Butonul apăsat

        // Dacă butonul are deja un X sau O, nu facem nimic
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        // Setăm textul în funcție de jucătorul curent
        if (playerX) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        // Verificăm dacă avem un câștigător sau un joc încheiat la egalitate
        moves++;
        if (checkForWinner()) {
            JOptionPane.showMessageDialog(this, "Jucătorul " + (playerX ? "X" : "O") + " a câștigat!");
            resetBoard();
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "Jocul s-a încheiat la egalitate!");
            resetBoard();
        }

        // Alternăm între jucători
        playerX = !playerX;
    }

    // Verificare câștigător
    private boolean checkForWinner() {
        // Verificăm rânduri și coloane
        for (int i = 0; i < 3; i++) {
            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkThree(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }

        // Verificăm diagonale
        if (checkThree(buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (checkThree(buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    // Verificăm dacă trei butoane au același text (X sau O) și nu sunt goale
    private boolean checkThree(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    // Resetare tabelă
    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        moves = 0;
        playerX = true;  // Resetăm la jucătorul X
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
