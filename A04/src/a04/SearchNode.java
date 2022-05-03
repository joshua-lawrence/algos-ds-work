package a04;

import java.util.Comparator;

public class SearchNode implements Comparable {
    private final Board board;
    private final int moves;
    private final int priority;
    private final SearchNode previous;

    SearchNode(Board board, int moves, int priority, SearchNode previous) {
        this.board = board;
        this.moves = moves;
        this.priority = priority;
        this.previous = previous;
    }

    public SearchNode getPrevious() {
        return previous;
    }

    public int getPriority() {
        return priority;
    }

    public int getMoves() {
        return moves;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public int compareTo(Object o) {
        SearchNode that = (SearchNode) o;
        return Integer.compare(this.getPriority(), that.getPriority());
    }
}