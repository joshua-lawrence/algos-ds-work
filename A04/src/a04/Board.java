package a04;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Board {

    private final int[][] blocks;
    private final int size;
    private int hamming;
    private int manhattan;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        AtomicBoolean containsZero = new AtomicBoolean(false);
        Arrays.stream(blocks).forEach(block -> Arrays.stream(block).forEach(val -> {
            if(val == 0) {
                containsZero.set(true);
            }
            if(val >= Math.pow(blocks.length, 2)) {
                throw new IllegalArgumentException();
            }
        }));
        if(!containsZero.get()) {
            throw new IllegalArgumentException("Must contain a 0.");
        }
        this.blocks = blocks;
        this.size = blocks.length;
        this.manhattan = calcManhattan();
    }

    // board size N
    public int size() {
        return size;
    }

    // number of blocks out of place
    public int hamming() {
        int position = 1;
        for(int[] row : blocks) {
            for(int block : row) {
                if(block != position && block != 0) {
                    hamming++;
                }
                position++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    private int calcManhattan() {
        int total = 0;
        for(int x = 0; x < size(); x++) {
            for(int y = 0; y < size(); y++) {
                if(blocks[x][y] != 0) {
                    // Where it should be:
                    var posX = (blocks[x][y] - 1) / size();
                    var posY= (blocks[x][y] - 1) % size();
                    // x and y are where it is.
                    total += Math.abs(x - posX) + Math.abs(y - posY);
                }
            }
        }
        return total;
    }

    public int manhattan() {
        return manhattan;
    }


    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }

    // is this board solvable?
    public boolean isSolvable() {
        boolean odd = size() % 2 != 0;
        int inversions = 0;
        int blankRow = 0;
        for(int i = 0; i < (size() * size()); i++) {
            for(int x=i; x < (size() * size()); x++) {
                if(blocks[i / size()][i % size()] > blocks[x / size()][x % size()]  && blocks[i / size()][i % size()] != 0 && blocks[x / size()][x % size()] != 0) {
                    inversions++;
                }
            }
        }
        if(odd) {
            return inversions % 2 == 0;
        }
        else {
            for(int i = 0; i < size(); i++) {
                for(int j = 0; j < size(); j++) {
                    if(blocks[i][j] == 0) {
                        blankRow = i;
                    }
                }
            }
            return (inversions + blankRow) % 2 != 0;
        }
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if(this.size() != that.size()) return false;
        int nonMatches = 0;
        for(int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if(this.blocks[i][j] != that.blocks[i][j]) {
                    nonMatches++;
                }
            }
        }
        if(nonMatches == 0) return true;
        else return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();
        int[] zeroPos = new int[2];
        for(int x = 0; x < size(); x++) {
            for(int y = 0; y < size(); y++) {
                if(blocks[x][y] == 0) {
                    zeroPos[0] = x;
                    zeroPos[1] = y;
                }
            }
        }

        if(zeroPos[1] != size() - 1) {
            int[][] newBlocks = Arrays.stream(blocks)
                    .map(int[]::clone)
                    .toArray(int[][]::new);
            newBlocks[zeroPos[0]][zeroPos[1]] = newBlocks[zeroPos[0]][zeroPos[1] + 1];
            newBlocks[zeroPos[0]][zeroPos[1] + 1] = 0;
            Board rightSwap = new Board(newBlocks);
            stack.add(rightSwap);
        }

        if(zeroPos[1] != 0) {
            int[][] newBlocks = Arrays.stream(blocks)
                    .map(int[]::clone)
                    .toArray(int[][]::new);
            newBlocks[zeroPos[0]][zeroPos[1]] = newBlocks[zeroPos[0]][zeroPos[1] - 1];
            newBlocks[zeroPos[0]][zeroPos[1] - 1] = 0;
            Board leftSwap = new Board(newBlocks);
            stack.add(leftSwap);
        }

        if(zeroPos[0] != size() - 1) {
            int[][] newBlocks = Arrays.stream(blocks)
                    .map(int[]::clone)
                    .toArray(int[][]::new);
            newBlocks[zeroPos[0]][zeroPos[1]] = newBlocks[zeroPos[0] + 1][zeroPos[1]];
            newBlocks[zeroPos[0] + 1][zeroPos[1]] = 0;
            Board botSwap = new Board(newBlocks);
            stack.add(botSwap);
        }

        if(zeroPos[0] != 0) {
            int[][] newBlocks = Arrays.stream(blocks)
                    .map(int[]::clone)
                    .toArray(int[][]::new);
            newBlocks[zeroPos[0]][zeroPos[1]] = newBlocks[zeroPos[0] - 1][zeroPos[1]];
            newBlocks[zeroPos[0] - 1][zeroPos[1]] = 0;
            Board topSwap = new Board(newBlocks);
            stack.add(topSwap);
        }

        return stack;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size() + "\n");
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        //2x2 test
//        int[][] test1 = new int[4][4];
//        test1[0][0] = 3;
//        test1[0][1] = 1;
//        test1[1][0] = 2;
//        test1[1][1] = 0;
//        var board1 = new Board(test1);
//        assert(board1.isSolvable());
        //4x4 test
        int[][] test2 = new int[4][4];
        test2[0][0] = 1;
        test2[0][1] = 2;
        test2[0][2] = 3;
        test2[0][3] = 4;
        test2[1][0] = 5;
        test2[1][1] = 0;
        test2[1][2] = 6;
        test2[1][3] = 8;
        test2[2][0] = 9;
        test2[2][1] = 10;
        test2[2][2] = 7;
        test2[2][3] = 11;
        test2[3][0] = 13;
        test2[3][1] = 14;
        test2[3][2] = 15;
        test2[3][3] = 12;
        var board2 = new Board(test2);
        assert(board2.isSolvable());
    }

}
