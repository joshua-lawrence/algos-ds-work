package graphInternet;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KruskalMST;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InternetCE {
    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(new File("GraphInternet.txt")));
        List<String> routers = new ArrayList<>();
        KruskalMST kruskalMST = new KruskalMST(graph);

        System.out.print("Offices needing to be connected: ");
        kruskalMST.edges().forEach(edge -> {
            var vw = edge.toString().split(" ");
            if(vw[0].charAt(0) - '0' != (graph.V() - 1)) {
                System.out.print(vw[0] + " ");
            }
            else {
                routers.add(vw[0].split("-")[1]);
            }
        });

        System.out.println();
        System.out.print("Offices needing a router: ");
        routers.forEach(router -> System.out.print(router + " "));

        System.out.println();
        System.out.println("Total cost: $" + kruskalMST.weight());
    }
}
