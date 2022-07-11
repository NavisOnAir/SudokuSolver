package ui;

import sudoku.Sudoku;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Ui {

    // font
    public FontRenderContext _frc = new FontRenderContext(null, true, true);
    public Font _defaultFond = new Font("Courier", Font.PLAIN, 20);

    // colors
    public Color _numberColor;
    public Color _startNumberColor;
    public Color _borderColor;
    public Color _thickBorderColor;
    public Color _highlightColor;
    public Color _defaultHighlightColor;
    public Color _falseHighlightColor;
    public Color _buttonColor;

    // ui
    public int _startButtonX;
    public int _startButtonY;

    public int _solveButtonX;
    public int _solveButtonY;

    public int _createButtonX;
    public int _createButtonY;

    public int _highlightedX;
    public int _highlightedY;
    public boolean _isHighlighted = false;

    // instances
    Sudoku _sudoku;

    public Ui(Sudoku sudoku) {
        // instances
        _sudoku = sudoku;

        _numberColor = Color.white;
        _startNumberColor = Color.green;
        _borderColor = Color.white;
        _thickBorderColor = Color.white;
        _highlightColor = new Color(0, 100, 0, 10);
        _defaultHighlightColor = _highlightColor;
        _falseHighlightColor = new Color(100, 0, 0, 10);
        _buttonColor = Color.white;
    }

    public void drawStart(Graphics2D g2d) {
        String str = "Start";
        float fontSize = 40f;
        int sX = getXForCenteredText(_defaultFond.deriveFont(fontSize), str);
        int sY = Math.round(_sudoku.getHeight() - _sudoku.getWidth() * .7f);

        _startButtonX = sX;
        _startButtonY = sY;

        g2d.setColor(_buttonColor);
        g2d.setFont(_defaultFond.deriveFont(fontSize));
        g2d.drawString(str, sX, sY);
    }

    public void drawGame(Graphics2D g2d) {
        // solve button
        String strSolveButton = "Solve";
        float fondSoziSolveButton = 10f * _sudoku._scale;
        int sbX = getXForCenteredText(_defaultFond.deriveFont(fondSoziSolveButton), strSolveButton);
        int sbY = (int) (_sudoku.getHeight() * 0.99);

        _solveButtonX = sbX;
        _solveButtonY = sbY;

        g2d.setColor(_buttonColor);
        g2d.setFont(_defaultFond.deriveFont(fondSoziSolveButton));
        g2d.drawString(strSolveButton, sbX, sbY);

        // create Button
        String strCreateButton = "Create";
        float fondSizeCreateButton = 10f * _sudoku._scale;
        int cbX = (int) (_sudoku.getWidth() * 0.01);
        int cbY = (int) (_sudoku.getHeight() * 0.99);

        _createButtonX = cbX;
        _createButtonY = cbY;

        g2d.setColor(_buttonColor);
        g2d.setFont(_defaultFond.deriveFont(fondSizeCreateButton));
        g2d.drawString(strCreateButton, cbX, cbY);

        // sudoku field
        for (int x = 0; x < _sudoku.get_sudokuWidth(); x += _sudoku._tileSize) {
            for (int y = 0; y < _sudoku.get_sudokuHeight(); y += _sudoku._tileSize) {

                // number coordinates
                int xCord = x / _sudoku._tileSize;
                int yCord = y / _sudoku._tileSize;

                int startNumber = _sudoku.get_startField()[yCord][xCord];

                // borders
                g2d.setColor(_borderColor);
                g2d.drawRect(x, y, _sudoku._tileSize, _sudoku._tileSize);

                // highlight tile
                if (_isHighlighted) {
                    int highX = _highlightedX * _sudoku._tileSize + 1;
                    int highY = _highlightedY * _sudoku._tileSize + 1;
                    g2d.setColor(_highlightColor);
                    g2d.fillRect(highX, highY, _sudoku._tileSize - 1, _sudoku._tileSize - 1);
                }

                // number
                if (startNumber != 0) {
                    g2d.setColor(_startNumberColor);
                } else {
                    g2d.setColor(_numberColor);
                }
                String str = "";
                int number = _sudoku.get_field()[yCord][xCord];
                if (number != 0) {
                    str = Integer.toString(number);
                }
                float fontSize = 10f * _sudoku._scale;
                g2d.setFont(_defaultFond.deriveFont(fontSize));
                int nX = getXForCenteredText(_defaultFond.deriveFont(fontSize), str, x, x + _sudoku._tileSize) + x;
                int nY = getYForCenteredText(_defaultFond.deriveFont(fontSize), str, y, y + _sudoku._tileSize) + y;
                g2d.drawString(str, nX, nY);
            }
        }

        // line borders for 3x3 rectangles with wider lines
        for (int x = 1; x < 3; x++) {
            g2d.setColor(_thickBorderColor);
            g2d.drawLine(x * _sudoku._tileSize * 3, 0, x * _sudoku._tileSize * 3, _sudoku.get_sudokuHeight());
            g2d.drawLine(x * _sudoku._tileSize * 3 + 1, 0, x * _sudoku._tileSize * 3 + 1, _sudoku.get_sudokuHeight());
            g2d.drawLine(x * _sudoku._tileSize * 3 - 1, 0, x * _sudoku._tileSize * 3 - 1, _sudoku.get_sudokuHeight());
        }
        for (int y = 1; y < 3; y++) {
            g2d.setColor(_thickBorderColor);
            g2d.drawLine(0, y * _sudoku._tileSize * 3, _sudoku.get_sudokuWidth(), y * _sudoku._tileSize * 3);
            g2d.drawLine(0, y * _sudoku._tileSize * 3 + 1, _sudoku.get_sudokuWidth(), y * _sudoku._tileSize * 3 + 1);
            g2d.drawLine(0, y * _sudoku._tileSize * 3 - 1, _sudoku.get_sudokuWidth(), y * _sudoku._tileSize * 3 - 1);
        }
    }

    public void highlight(int x, int y) {
        _highlightedX = x;
        _highlightedY = y;
        _isHighlighted = true;
    }

    // returns the centered x coordinate for a given string in given font to center text
    public int getXForCenteredText(Font font, String str) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D r2D = font.getStringBounds(str, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rX = (int) Math.round(r2D.getX());
        return(_sudoku.getWidth() / 2) - (rWidth / 2) - rX;
    }

    public int getXForCenteredText(Font font, String str, int leftBorder, int rightBorder) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D r2D = font.getStringBounds(str, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rX = (int) Math.round(r2D.getX());
        int spaceWidth = rightBorder - leftBorder;
        return(spaceWidth / 2) - (rWidth / 2) - rX;
    }

    public int getYForCenteredText(Font font, String str, int topBorder, int botBorder) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D r2D = font.getStringBounds(str, frc);
        int rHeight = (int) Math.round(r2D.getHeight());
        int rY = (int) Math.round(r2D.getY());
        int spaceHeight = botBorder - topBorder;
        return (spaceHeight / 2) - (rHeight / 2) - rY;
    }

    // return true if x and y are in rectangle
    public boolean checkRectangle(int x, int y, Rectangle2D reg2D, int regX, int regY) {
        int rWidth = (int) Math.round(reg2D.getWidth());
        int rHeight = (int) Math.round(reg2D.getHeight()  / 2);

        // comparing logic
        boolean boolX = true;
        boolean boolY = true;
        if (y > regY) boolY = false;
        if (y < regY - rHeight) boolY = false;
        if (x < regX) boolX = false;
        if (x > regX + rWidth) boolX = false;

        if (boolX && boolY) {
            return true;
        } else {
            return false;
        }
    }
}
