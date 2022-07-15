package sudoku;

import java.util.Arrays;
import java.util.Vector;

public class Solver implements Runnable {

    // instances
    Sudoku _sudoku;
    private int[][] _field;
    private int[][] _startField;
    private long _speed;
    private boolean _solveAll;

    public Solver(Sudoku sudoku, int[][] field, int[][] startField, long speed) {
        _sudoku = sudoku;
        _field = field;
        _startField = startField;
        _speed = speed;
        _solveAll = false;
    }

    public boolean solve() {
        return bruteForce(0, _speed);
    }

    public Vector<int[][]> getSudokus() {
        Vector<int[][]> sudokus = new Vector<int[][]>();
        int maxSudokus = 9*9;
        for (int startIndex = 0; startIndex < (int) maxSudokus / 9; startIndex++) {
            for (int startNum = 1; startNum <= maxSudokus % 10; startNum++) {
                // creating template
                int[][] field = new int[9][9];
                int[] cords = convertCords(startIndex, field);
                int y = cords[0];
                int x = cords[1];
                field[y][x] = startNum;
                int[][] startField = new int[9][9];
                for (int i = 0; i < startField.length; i++) {
                    System.arraycopy(field[i], 0, startField[i], 0, startField[0].length);
                }

                // solving
                _field = field;
                _startField = startField;
                _speed = 0;
                if(solve()) {
                    sudokus.addElement(field);
                }
            }
        }

        // print solutions
        for (int[][] sud : sudokus) {
            for (int[] row : sud) {
                for (int num : row) {
                    System.out.print(Integer.toString(num) + "  ");
                }
                System.out.println();
            }
            System.out.println("----------------------------------");
        }

        return sudokus;
    }

    public int[][] getSudoku() {
        // creating template
        int[][] field = new int[9][9];
        int[][] startField = new int[9][9];
        int index = (int) (Math.random() * 9);
        field[index] = getOneLinePermutation();
        for (int i = 0; i < startField.length; i++) {
            System.arraycopy(field[i], 0, startField[i], 0, startField[0].length);
        }
        // solving
        _field = field;
        _startField = startField;
        _speed = 0;
        if(solve()) {
            return field;
        } else {
            return getSudoku();
        }
    }

    private boolean bruteForce(int index, long speed) {
        if (speed > 0) {
            long currTime = System.nanoTime();
            long timeEnd = currTime + speed;
            while (System.nanoTime() < timeEnd) {

            }
        }
        int[] cords = convertCords(index, _field);
        int x = cords[0];
        int y = cords[1];
        if (x >= 9 || y >= 9) {
            return true;
        }
        if (_sudoku.isOccupied(x, y, _startField)) {
            if(bruteForce(index + 1, speed)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (_sudoku.insertNumber(x, y, i, _field, _startField)) {
                    if (bruteForce(index + 1, speed)) {
                        return true;
                    }
                }
            }
            /*
            if (_sudoku.insertNumber(x, y, 1)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 2)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 3)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 4)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 5)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 6)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 7)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 8)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 9)) {
                if (bruteForce(index + 1, speed)) {
                    return true;
                }
            }
            */
        }
        System.out.println("Deleted num");
        _sudoku.deleteNum(x, y, _field, _startField);
        return false;
    }

    private int[] getOneLinePermutation() {
        int[] perm = new int[9];
        int num = 1;
        for (int i = 1; i <= 9; i++) {
            int index = (int) (Math.random() * 9);
            if (perm[index] != 0) {
                i--;
            } else {
                perm[index] = i;
            }
        }
        return perm;
    }

    /*
     * return the values of a int[][] as a int[]
     */
    public int[] convertField(int[][] field) {
        int[] fieldArrOneDim = new int[field.length * field[0].length];
        int index = 0;
        for (int[] row : field) {
            for (int num : row) {
                fieldArrOneDim[index] = num;
                index++;
            }
        }
        return fieldArrOneDim;
    }

    public int[] convertCords(int index, int[][] field) {
        int xWidth = field[0].length;
        int yHeight = field.length;
        int x = index % xWidth;
        int y = index / yHeight;
        int[] intArr = new int[2];
        intArr[0] = x;
        intArr[1] = y;
        return  intArr;
    }

    @Override
    public void run() {
        if (_solveAll) {
            getSudokus();
        } else {
            System.out.println(solve());
        }
    }

    public void set_solveAll(boolean b) {
        _solveAll = b;
    }
}
