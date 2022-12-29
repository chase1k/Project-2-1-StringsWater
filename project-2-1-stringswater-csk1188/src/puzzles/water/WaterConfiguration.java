package puzzles.water;

import puzzles.common.solver.Configuration;
import java.util.LinkedList;
import java.util.Collection;

public class WaterConfiguration implements Configuration {
    LinkedList<Integer> buckList;
    LinkedList<Integer> current ;
    private int amount;
    public WaterConfiguration(int amt, LinkedList<Integer> cur,LinkedList<Integer> buckets){
        amount = amt;
        current = cur;
        buckList = buckets;
    }

    /**
     * Try every combination of filling dumping and pouring buckets to get the solution
     * @return all neighbors of a certain water config
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        LinkedList<Configuration> neighbors = new LinkedList<>();
        //Filling
        for (int bucket = 0; bucket < current.size(); bucket++) {
            LinkedList<Integer> buckets = new LinkedList<>(buckList);
            buckets.set(bucket, this.current.get(bucket));
            neighbors.add(new WaterConfiguration(amount, current, buckets));
        }
        //Dumping
        for (int bucket = 0; bucket < buckList.size(); bucket++) {
            LinkedList<Integer> buckets = new LinkedList<>(buckList);
            buckets.set(bucket, 0);
            neighbors.add(new WaterConfiguration(amount, current, buckets));
        }
        //Pouring
        for (int dumper = 0;dumper < buckList.size();dumper++) {
            for(int filler = 0;filler < buckList.size();filler++) {
                if(filler != dumper) {
                    LinkedList<Integer> buckets = new LinkedList<>(buckList);
                    int amot = Math.min(buckList.get(dumper), current.get(filler) - buckList.get(filler));
                    buckets.set(dumper,(buckList.get(dumper)- amot));
                    buckets.set(filler,(buckList.get(filler)+ amot));
                    neighbors.add(new WaterConfiguration(amount,current, buckets));
                }
            }
        }
        return neighbors;
    }

    /**
     * Determines if bucket holds correct amount
     * @return whether bucket is solution
     */
    @Override
    public boolean isSolution() {
        for (Integer bucket : buckList) {
            if (bucket == amount) {return true;}
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash= 0;
        for (Integer buck : buckList) {hash += buck.hashCode();}
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WaterConfiguration other){
            return buckList.equals(other.buckList);
        }return false;
    }
    @Override
    public String toString() {return buckList.toString();}
}
