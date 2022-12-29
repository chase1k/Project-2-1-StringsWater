package puzzles.water;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class for the water buckets puzzle.
 *
 * @author YOUR NAME HERE
 */
public class Water {

    /**
     * Run an instance of the water buckets puzzle.
     *
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    ("Usage: java Water amount bucket1 bucket2 ...")
            );
        } else {
            LinkedList<Integer> buckets = new LinkedList<>();
            for (int i = 1; i < args.length; i++) {
                buckets.add(Integer.parseInt(args[i]));
            }
            Solver solver = new Solver();
            LinkedList<Integer> starter = new LinkedList<>();
            for (int i = 0; i < buckets.size(); i++) starter.add(0);
            WaterConfiguration config = new WaterConfiguration(Integer.parseInt(args[0]),buckets,starter);
            List<Configuration> path = (LinkedList<Configuration>) solver.solve(config);

            System.out.println("Amount: "+args[0]+", Buckets: "+buckets);
            System.out.println("Total configs: "+solver.getTotConfig());
            System.out.println("Unique configs: "+solver.getUniqueConfig());

            if (path==null || path.size() == 0){
                System.out.println("No solution");
                return;
            }
            for(int i = 0; i < path.size(); i++) {
                System.out.println("Step "+i+": "+path.get(i));
            }

        }
    }
}
