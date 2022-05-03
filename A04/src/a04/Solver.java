package a04;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Solver {
    MinPQ<SearchNode> minPQ = new MinPQ<>();
    Stack<SearchNode> solution;
    int moves;
    public Solver(Board initial) {
        if(initial == null) {
            throw new NullPointerException("Board is null.");
        }
        if(initial.isSolvable()) {
            minPQ.insert(new SearchNode(initial, 0, initial.manhattan(), null));
            while(!minPQ.isEmpty()) {
                SearchNode min = minPQ.delMin();
                if(min.getBoard().isGoal()) {
                    solution = new Stack<>();
                    solution.push(min);
                    SearchNode current = min;
                    moves = min.getMoves();
                    while(current.getPrevious() != null) {
                        solution.push(current.getPrevious());
                        current = current.getPrevious();
                    }
                    return;
                }
                for (Board neighbor : min.getBoard().neighbors()) {
                    if(!neighbor.equals(initial)) {
                        minPQ.insert(new SearchNode(neighbor, min.getMoves() + 1, min.getMoves() + 1 + neighbor.manhattan(), min));
                    }
                }
            }

        }
        else {
            throw new IllegalArgumentException("This board is not solvable.");
        }
    }
    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        Queue<Board> boards = new Queue<>();
        for (SearchNode searchNode : solution) {
            boards.enqueue(searchNode.getBoard());
        }
        return boards;
    }

    public static void main(String[] args) {
        int[][] test = new int[3][3];

        test[0][0] = 8;
        test[0][1] = 1;
        test[0][2] = 3;
        test[1][0] = 4;
        test[1][1] = 0;
        test[1][2] = 2;
        test[2][0] = 7;
        test[2][1] = 6;
        test[2][2] = 5;

        Board board = new Board(test);
        Solver solver = new Solver(board);
        solver.solution().forEach(System.out::println);
        System.out.println(solver.moves());
    }

}
