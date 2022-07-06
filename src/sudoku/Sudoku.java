package sudoku;

import listener.KeyHandler;
import listener.MouseHandler;
import ui.Ui;

import javax.swing.JPanel;
import java.awt.*;

public class Sudoku extends JPanel implements Runnable {

    // game field
    private int[][] _field;
    private int[][] _startField;

    // game state
    public final int STATE_START = 0;
    public final int STATE_GAME = 1;

    public int _gameState;

    // ui size
    private final int _origTileSize = 16;
    public final int _scale = 4;
    public final int _tileSize = _origTileSize * _scale;
    private final int _screenWidth;
    private final int _screenHeight;
    private final int _sudokuWidth;
    private final int _sudokuHeight;

    // instances
    // game thread
    Thread _gameThread;

    // ui
    Ui _ui = new Ui(this);

    // listener
    MouseHandler _mouseHand = new MouseHandler(this);
    KeyHandler _keyHand = new KeyHandler();

    public Sudoku() {
        _field = new int[][]
                {
                        {0, 3, 0, 2, 8, 0, 0, 0, 0},
                        {6, 0, 0, 9, 5, 0, 0, 2, 0},
                        {8, 0, 0, 0, 0, 0, 5, 7, 0},
                        {0, 0, 5, 3, 0, 1, 0, 0, 0},
                        {0, 6, 0, 0, 9, 0, 0, 1, 0},
                        {0, 0, 0, 7, 0, 4, 9, 0, 0},
                        {0, 7, 2, 0, 0, 0, 0, 0, 5},
                        {0, 9, 0, 0, 7, 5, 0, 0, 6},
                        {0, 0, 0, 0, 1, 2, 0, 4, 0}
                };
        _startField = new int[_field.length][_field[0].length];
        for (int i = 0; i < _startField.length; i++) {
            for (int j = 0; j < _startField[0].length; j++) {
                _startField[i][j] = _field[i][j];
            }
        }

        // initialize screen
        _sudokuWidth = _tileSize * 9;
        _sudokuHeight = _tileSize * 9;
        _screenWidth = _sudokuWidth;
        _screenHeight = _sudokuHeight + 50;
        this.setPreferredSize(new Dimension(_screenWidth, _screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);

        _gameState = STATE_START;

        // listener
        addMouseListener(_mouseHand);
        addKeyListener(_keyHand);
    }

    // solver
    public void solve() {
        Solver sl = new Solver(this);
        System.out.println(sl.solve());
    }

    /**
     * return false if number is wrong on given x and y position row
     */
    private boolean testRow(int x, int y, int number) {
        for (int i = 0; i < 9; i++) {
            if (number == _field[y][i]) {
                System.out.println("number in row");
                return false;
            }
        }
        return true;
    }

    /**
     * return false if number is wrong on given x and y position column
     */
    private boolean testColumn(int x, int y, int number) {
        for (int i = 0; i < 9; i++) {
            if (number == _field[i][x]) {
                System.out.println("number in column");
                return false;
            }
        }
        return true;
    }

    /**
     * return false if number is wrong on given x and y position square
     */
    private boolean testSquare(int x, int y, int number) {
        int squareX;
        int squareY;

        if (x - 6 >= 0) { // right row
            squareX = 6;
        } else if (x - 3 >= 0) { // mod row
            squareX = 3;
        } else { // left row
            squareX = 0;
        }

        if (y - 6 >= 0) { // bottom column
            squareY = 6;
        } else if (y - 3 >= 0) { // mid column
            squareY = 3;
        } else { // top column
            squareY = 0;
        }

        // checks every position in the corresponding 3x3 square
        for (int _x = squareX; _x < squareX + 3; _x++) {
            for (int _y = squareY; _y < squareY + 3; _y++) {
                if (number == _field[_y][_x]) {
                    System.out.println("number in square");
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isOccupied(int x, int y) {
        return _startField[y][x] != 0;
    }

    // output
    public void print() {
        for (int[] row : _field) {
            for (int num : row) {
                System.out.print(Integer.toString(num) + "  ");
            }
            System.out.println();
        }
    }

    public void printStartField() {
        for (int[] row : _startField) {
            for (int num : row) {
                System.out.print(Integer.toString(num) + "  ");
            }
            System.out.println();
        }
    }

    // input
    public boolean input(int x, int y, int number) {
        if (_startField[y][x] != 0) {
            System.out.println("Occupied position");
            return false;
        }
        if (!testRow(x, y, number)) {
            return false;
        }
        if (!testColumn(x, y, number)) {
            return false;
        }
        if (!testSquare(x, y, number)) {
            return false;
        }

        System.out.println("insert number");
        _field[y][x] = number;
        return true;
    }

    public boolean insertNumber(int x, int y, int number) {
        return input(x, y, number);
    }

    public boolean deleteNum(int x, int y) {
        if (_startField[y][x] == 0) {
            _field[y][x] = 0;
            return true;
        } else {
            return false;
        }
    }

    public void startGame() {
        _gameState = STATE_GAME;
    }

    // game loop
    public void startGameThread() {
        _gameThread = new Thread(this);
        _gameThread.start();
    }

    @Override
    public void run() {
        while (_gameThread != null) {
            // physical
            update();

            // optical
            repaint();
        }
    }

    // physical update method
    public void update() {
        switch (_gameState) {
            case STATE_START:
                break;
            case STATE_GAME:
                if (_ui._isHighlighted) {
                    if (_keyHand._is1) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 1);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is2) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 2);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is3) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 3);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is4) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 4);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is5) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 5);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is6) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 6);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is7) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 7);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is8) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 8);
                    }
                }
                if (_ui._isHighlighted) {
                    if (_keyHand._is9) {
                        insertNumber(_ui._highlightedX, _ui._highlightedY, 9);
                    }
                }
                break;
        }
    }

    // optical repaint method
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        switch (_gameState) {
            case STATE_START:
                _ui.drawStart(g2d);
                break;
            case STATE_GAME:
                _ui.drawGame(g2d);
                break;
        }
    }

    // get
    public Ui get_ui() {
        return _ui;
    }

    public int[][] get_field() {
        return _field;
    }

    public int[][] get_startField() {
        return _startField;
    }

    public int get_sudokuWidth() {
        return _sudokuWidth;
    }

    public int get_sudokuHeight() {
        return _sudokuHeight;
    }
}
