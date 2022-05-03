
import java.lang.reflect.Array;
import java.util.*;

public class Problem {

    public static void main(String[] args) {
        int[][] input = new int[][]{{0,1}, {1,2}, {2,0}, {4}, {3}, {}};
        System.out.println(components(input));
    }

    public static int components(int[][] problem) {
        /**
         * If a node hasn't been seen before, put it into a new bag. If it has been seen before, add it to the bag with
         * it's neighbors.
         */
        Set<Integer> seen = new HashSet<>();
        List<List<Integer>> bags = new ArrayList<>();
        for (int[] connection : problem) {
            if(connection.length != 0) {
                if(connection.length > 1) {
                    if(seen.contains(connection[0]) || seen.contains(connection[1])) {
                        bags.forEach(bag -> {
                            if(bag.contains(connection[0])) {
                                bag.add(connection[1]);
                            }
                            else {
                                bag.add(connection[0]);
                            }
                        });
                    }
                    else {
                        seen.add(connection[0]);
                        seen.add(connection[1]);
                        bags.add(new ArrayList<>(Arrays.asList(connection[0], connection[1])));
                    }
                }
                else {
                    seen.add(connection[0]);
                    bags.add(new ArrayList<>(Collections.singletonList(connection[0])));
                }
            }
        }

        return bags.size();
    }

}
