public class Sudoku {

    private int[][] _field;
    private int[][] _startField;

    private boolean testRow(int x, int y, int number) {
        return true;
    }

    private boolean testColum(int x, int y, int number) {
        return true;
    }

    private boolean testSquare(int x, int y, int number) {
        return true;
    }

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
        _startField = _field;
    }

    public boolean input(int x, int y, int number) {
        return true;
    }
}
