package graphDFSvsBFS;

import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DFSvsBFS {

    public static void main(String[] args) {
        In input = new In(new File("./src/graphDFSvsBFS/SimpleGraph.txt"));
        Graph graph = new Graph(input);

        var source = 1;

        System.out.println("Adjacency List:\n---------------");
        for (int i = 0; i < graph.V(); i++) {
            System.out.print(i + ": ");
            List<Integer> sorted = new ArrayList<>();
            for (Integer adj : graph.adj(i)) {
               sorted.add(adj);
            }
            sorted.sort(Comparator.reverseOrder());
            var it = sorted.iterator();
            if(it.hasNext()) {
                System.out.print(it.next());
            }
            while(it.hasNext()) {
                System.out.print("->" + it.next());
            }
            System.out.println();
        }

        DepthFirstPaths dfs = new DepthFirstPaths(graph, source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, source);

        System.out.println();
        System.out.printf("%-20s%-20s", "Paths DFS:", "Shortest Paths BFS:\n");
        System.out.printf("%-20s%-20s", "--------------", "--------------");
        for (int i = 0; i < graph.V(); i++) {
            if(i != 1) System.out.printf("\n%-20s%-20s", dfs.pathTo(i), bfs.pathTo(i));
        }
    }
}
