package net.example.cebulasoft.graffile;

import org.jgrapht.graph.DefaultWeightedEdge;

public class FileWeightedEdge extends DefaultWeightedEdge {
    @Override
    public String toString() {
        return String.valueOf(new Double(getWeight()).intValue());
    }
}
