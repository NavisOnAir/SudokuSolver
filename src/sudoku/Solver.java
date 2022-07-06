package sudoku;

public class Solver {

    // instances
    Sudoku _sudoku;

    public Solver(Sudoku sudoku) {
        _sudoku = sudoku;
    }

    public boolean bruteForce(int index) {
        int[] cords = convertCords(index, _sudoku.get_field());
        int x = cords[0];
        int y = cords[1];
        if (x >= 9 || y >= 9) {
            return true;
        }
        if (_sudoku.isOccupied(x, y)) {
            if(bruteForce(index + 1)) {
                return true;
            }
        } else {
            if (_sudoku.insertNumber(x, y, 1)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 2)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 3)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 4)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 5)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 6)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 7)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 8)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
            if (_sudoku.insertNumber(x, y, 9)) {
                if (bruteForce(index + 1)) {
                    return true;
                }
            }
        }
        _sudoku.deleteNum(x, y);
        return false;
    }

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
        int x = (int) index % xWidth;
        int y = (int) index / yHeight;
        int[] intArr = new int[2];
        intArr[0] = x;
        intArr[1] = y;
        return  intArr;
    }
}
