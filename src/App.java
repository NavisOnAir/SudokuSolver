import sudoku.Solver;
import sudoku.Sudoku;

import javax.swing.JFrame;
import java.awt.Color;

public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        // default window settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("sudoku.Sudoku");
        window.setBackground(Color.black);

        Sudoku sudoku = new Sudoku();

        window.add(sudoku);

        // window fitting
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        sudoku.startGameThread();
    }
}
