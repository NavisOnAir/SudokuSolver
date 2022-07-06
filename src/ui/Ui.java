package ui;

import sudoku.Sudoku;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Ui {

    // font
    public FontRenderContext _frc = new FontRenderContext(null, true, true);
    public Font _defaultFond = new Font("Courier", Font.PLAIN, 20);

    // ui
    public int _startButtonX;
    public int _startButtonY;

    public int _highlightedX;
    public int _highlightedY;
    public boolean _isHighlighted = false;

    // instances
    Sudoku _sudoku;

    public Ui(Sudoku sudoku) {
        // instances
        _sudoku = sudoku;
    }

    public void drawStart(Graphics2D g2d) {
        String str = "Start";
        float fontSize = 40f;
        int sX = getXForCenteredText(_defaultFond.deriveFont(fontSize), str);
        int sY = Math.round(_sudoku.getHeight() - _sudoku.getWidth() * .7f);

        _startButtonX = sX;
        _startButtonY = sY;

        g2d.setColor(Color.white);
        g2d.setFont(_defaultFond.deriveFont(fontSize));
        g2d.drawString(str, sX, sY);
    }

    public void drawGame(Graphics2D g2d) {
        for (int x = 0; x < _sudoku.getWidth(); x += _sudoku._tileSize) {
            for (int y = 0; y < _sudoku.getHeight(); y += _sudoku._tileSize) {

                // number coordinates
                int xCord = x / _sudoku._tileSize;
                int yCord = y / _sudoku._tileSize;

                int startNumber = _sudoku.get_startField()[yCord][xCord];

                // borders
                g2d.setColor(Color.white);
                g2d.drawRect(x, y, _sudoku._tileSize - 1, _sudoku._tileSize - 1);

                // highlight tile
                if (_isHighlighted) {
                    int highX = _highlightedX * _sudoku._tileSize;
                    int highY = _highlightedY * _sudoku._tileSize;
                    Color c = new Color(0, 100, 0, 10);
                    g2d.setColor(c);
                    g2d.fillRect(highX, highY, _sudoku._tileSize, _sudoku._tileSize);
                }

                // number
                if (startNumber != 0) {
                    g2d.setColor(Color.green);
                } else {
                    g2d.setColor(Color.white);
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
