package puzzles.strings;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class for the strings puzzle.
 *
 * @author Chase Killorin
 */
public class Strings {
    /**
     * Run an instance of the strings puzzle.
     *
     * @param args [0]: the starting string;
     *             [1]: the finish string.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Strings start finish"));
        } else {
            Solver solver = new Solver();
            StringsConfiguration config = new StringsConfiguration(args[0],args[1]);
            List<Configuration> path = (LinkedList<Configuration>) solver.solve(config);

            System.out.println("Start: "+args[0]+", End: "+args[1]);
            System.out.println("Total configs: "+solver.getTotConfig());
            System.out.println("Unique configs: "+solver.getUniqueConfig());

            if (path==null || path.size() <= 0){
                System.out.println("No solution");
                return;
            }
            for(int i = 0; i < path.size(); i++) {
                System.out.println("Step "+i+": "+path.get(i));
            }
        }
    }
}