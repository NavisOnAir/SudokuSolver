package listener;

import sudoku.Sudoku;
import ui.Ui;

import java.awt.event.MouseEvent;

public class MouseHandler implements java.awt.event.MouseListener {

    // instances
    Sudoku _sudoku;
    Ui _ui;

    public MouseHandler(Sudoku sudoku) {
        _sudoku = sudoku;
        _ui = sudoku.get_ui();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // start menu
        if (_sudoku._gameState == _sudoku.STATE_START) {

            // start button
            if (_ui.checkRectangle(e.getX(), e.getY(), _ui._defaultFond.deriveFont(40f).getStringBounds("Start", _ui._frc), _ui._startButtonX, _ui._startButtonY)) {
                _sudoku.startGame();
            }
        }

        // in-game
        if (_sudoku._gameState == _sudoku.STATE_GAME) {

            // highlight tile
            int x = (int) e.getX() / _sudoku._tileSize;
            int y = (int) e.getY() / _sudoku._tileSize;
            _ui.highlight(x, y);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}