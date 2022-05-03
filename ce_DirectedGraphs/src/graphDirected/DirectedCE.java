package graphDirected;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

import java.io.File;

public class DirectedCE {
    public static void main(String[] args) {
        Digraph graph = new Digraph(new In(new File("./src/graphDirected/resource/TopologicalOrderGraph.txt")));
        Topological topological = new Topological(graph);

        topological.order().forEach(vtx -> System.out.print(vtx + " "));
    }
}