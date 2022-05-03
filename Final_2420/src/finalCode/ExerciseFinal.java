package finalCode;

import edu.princeton.cs.algs4.*;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseFinal {
    public static void main(String[] args) {
        String filename = "src/finalCode/Resources/TrainConnections.csv";
        SymbolEdgeWeightedGraph sewg = new SymbolEdgeWeightedGraph(filename, ",");
        EdgeWeightedGraph graph = sewg.graph();
        Scanner sc = new Scanner(System.in);

        System.out.println("Part A:\n=======\nDeparture: ");
        String departure = sc.nextLine();
        System.out.print("Destination: ");
        String destination = sc.nextLine();

        if(!sewg.contains(departure) && !sewg.contains(destination)) {
            System.out.printf("There is neither a train to %s nor to %s.", departure, destination);
        }
        else if (!sewg.contains(departure)) {
            System.out.printf("There is no train connection %s.", departure);
        }
        else if (!sewg.contains(destination)) {
            System.out.printf("There is no train connection %s.", destination);
        }
        else {
            int s = sewg.indexOf(departure);
            DijkstraUndirectedSP dijkstraUndirectedSP = new DijkstraUndirectedSP(graph, s);
            System.out.println("\nPart B:\n=======\nFastest Connection:\n");
            var path = dijkstraUndirectedSP.pathTo(sewg.indexOf(destination));
            AtomicInteger lastVisited = new AtomicInteger(s);
            AtomicInteger totalTime = new AtomicInteger();
            path.forEach(action -> {
                var parts = action.toString().split(" ");
                var fromI = Integer.parseInt(parts[0].split("-")[0]);
                var from = sewg.nameOf(fromI);
                var toI = Integer.parseInt(parts[0].split("-")[1]);
                var to = sewg.nameOf(Integer.parseInt(parts[0].split("-")[1]));

                if (lastVisited.get() == fromI) {
                    System.out.println(from + " - " + to + " " + action.weight() + " min");
                    lastVisited.set(toI);
                }
                else {
                    System.out.println(to + " - " + from + " " + action.weight() + " min");
                    lastVisited.set(fromI);
                }
                totalTime.addAndGet((int) action.weight());
            });

            System.out.println("\nTotal amount of time from " + departure + " to " + destination + ": " + totalTime);
        }
    }
}
