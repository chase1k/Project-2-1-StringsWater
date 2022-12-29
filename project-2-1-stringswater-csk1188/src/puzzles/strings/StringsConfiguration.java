package puzzles.strings;

import puzzles.common.solver.Configuration;

import java.util.*;

public class StringsConfiguration implements Configuration {
    private String start;
    private String finish;
    private String current;
    public StringsConfiguration(String st, String fn){
        start = st;
        finish = fn;
        current = st;
    }

    /**
     * Determines if string is correct string
     * @return whether string is solution
     */
    @Override
    public boolean isSolution() {
        return current.equals(finish);
    }

    /**
     * Try every combination of strings changing letters to get the solution
     * @return all neighbors of a certain string config
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        ArrayList<Configuration> neighbors = new ArrayList<>();
        String cur;

        for (int letter = 0; letter < current.length(); letter++) {
            //Moving backwards letter //If out of bounds clause
            if (((this.start.charAt(letter)) - (int) 'A' - 1 % 26 + (int) 'A') == (int) 'A' - 1) {cur = String.valueOf('Z');}
            else{cur = String.valueOf((char) ((int) (this.start.charAt(letter)) - (int) 'A' - 1 % 26 + (int) 'A'));}

            cur = current.substring(0,letter)+cur+current.substring(letter+1);
            neighbors.add(new StringsConfiguration(cur,finish));

            //Moving forwards letter //If out of bounds clause
            if (((this.start.charAt(letter)) - (int) 'A' + 1 % 26 + (int) 'A') == (int) 'Z' + 1) {cur = String.valueOf('A');}
            else{cur = String.valueOf((char) ((int) (this.start.charAt(letter)) - (int) 'A' + 1 % 26 + (int) 'A'));}

            cur = current.substring(0,letter)+cur+current.substring(letter+1);
            neighbors.add(new StringsConfiguration(cur,finish));
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return current;
    }

    @Override
    public int hashCode() {
        return this.current.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringsConfiguration other){
            return current.equals(other.current) /*&& finish.equals(other.finish)*/;
        }return false;
    }
}
