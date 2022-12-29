package puzzles.common.solver;

import java.util.*;

public class Solver {
    HashMap<Configuration, Configuration> totPath = new HashMap<>();
    List<Configuration> path;
    List<Configuration> queue;
    private int totConfig;
    private int uniqueConfig;

    /**
     * Solver for all different configurations
     * @param config specific config to solve
     * @return LinkedList of the shortest possible path
     */
    public Collection<Configuration> solve(Configuration config){
        Configuration current = config;
        queue = new LinkedList<>();
        totPath.put(config, null);
        queue.add(config);
        totConfig++;
        uniqueConfig++;

        while (!queue.isEmpty()) {
            current = queue.remove(0);
            if(current.isSolution()){
                break;
            }

            for (Configuration neihbor : current.getNeighbors()) {
                totConfig++;
                if (!totPath.containsKey(neihbor)) {
                    queue.add(neihbor);
                    totPath.put(neihbor, current);
                    uniqueConfig++;
                }
            }
            if (queue.size() == 0) {return new LinkedList<>();}
        }
            path = new LinkedList<>();
            Configuration temp = current;
            while(temp != null){
                path.add(0,temp);
                temp = totPath.get(temp);
            }
            System.out.println("Path in Solver: "+path);
            return path;
    }

    /**
     * Getter for total configurations
     * @return total configurations
     */
    public int getTotConfig() {
        return totConfig;
    }

    /**
     * Getter for unique configurations
     * @return unique configurations
     */
    public int getUniqueConfig() {
        return uniqueConfig;
    }
}
