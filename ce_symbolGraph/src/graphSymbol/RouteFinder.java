package graphSymbol;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RouteFinder {

    public static void main(String[] args) {
        String filename  = "routes.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String departure = StdIn.readLine();
            if (sg.contains(departure)) {
                int s = sg.index(departure);
                BreadthFirstPaths bfs = new BreadthFirstPaths(sg.graph(), s);
                for (int v = 0; v < graph.V(); v++) {
                    StdOut.print(sg.name(v) + ": ");
                    bfs.pathTo(v).forEach(path -> System.out.print(sg.name(path) + " "));
                    System.out.println();
                }
            }
            else {
                StdOut.println("The departure " + departure + " could not be found.");
            }
        }
    }
}
