package sudoku;

public class Solver implements Runnable {

    // instances
    Sudoku _sudoku;

    public Solver(Sudoku sudoku) {
        _sudoku = sudoku;
    }

    public boolean solve() {
        return bruteForce(0, 10000000);
    }

    private boolean bruteForce(int index, long speed) {
        if (speed > 0) {
            long currTime = System.nanoTime();
            long timeEnd = currTime + speed;
            while (System.nanoTime() < timeEnd) {

            }
        }
        int[] cords = convertCords(index, _sudoku.get_field());
        int x = cords[0];
        int y = cords[1];
        if (x >= 9 || y >= 9) {
            return true;
        }
        if (_sudoku.isOccupied(x, y)) {
            if(bruteForce(index + 1, speed)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (_sudoku.insertNumber(x, y, i)) {
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
        _sudoku.deleteNum(x, y);
        return false;
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
        System.out.println(solve());
    }
}
