package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // keys
    public boolean _is1 = false;
    public boolean _is2 = false;
    public boolean _is3 = false;
    public boolean _is4 = false;
    public boolean _is5 = false;
    public boolean _is6 = false;
    public boolean _is7 = false;
    public boolean _is8 = false;
    public boolean _is9 = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 49, 97 -> _is1 = true;
            case 50, 98 -> _is2 = true;
            case 51, 99 -> _is3 = true;
            case 52, 100 -> _is4 = true;
            case 53, 101 -> _is5 = true;
            case 54, 102 -> _is6 = true;
            case 55, 103 -> _is7 = true;
            case 56, 104 -> _is8 = true;
            case 57, 105 -> _is9 = true;
            default -> System.out.println(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 49, 97 -> _is1 = false;
            case 50, 98 -> _is2 = false;
            case 51, 99 -> _is3 = false;
            case 52, 100 -> _is4 = false;
            case 53, 101 -> _is5 = false;
            case 54, 102 -> _is6 = false;
            case 55, 103 -> _is7 = false;
            case 56, 104 -> _is8 = false;
            case 57, 105 -> _is9 = false;
        }
    }
}
