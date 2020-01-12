/*
 * Klasa realizuje strukture grafu
 * Brakuje krawedzi grafu.
 * */

package net.example.cebulasoft.graffile;

import lombok.Getter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

@Getter
class GraphB {

    private DirectedWeightedMultigraph graph;

    GraphB(FilesConnectionInfo filesConnectionInfo) {
        graph = buildGraph(filesConnectionInfo);
    }

    private static DirectedWeightedMultigraph<String, DefaultWeightedEdge> buildGraph(FilesConnectionInfo d) {
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

        for (String key : d.getKeySet()) {
            FileInfo extracted = d.get(key);
            graph.addVertex(extracted.getName());
        }

        /*
         * INFO:
         * Poniżej znajdują się przykładowe dane do grafu wraz z krawędziami,
         * to tak aby pokazać że sam graf działa, domyślnie zakomentowane.
         */

        //sample data - labels

//        String x1= "one";
//        String x2= "two";
//        String x3= "three";


//        graph.addVertex(x1);
//        graph.addVertex(x2);
//        graph.addVertex(x3);


        //sample data - edges

//        DefaultWeightedEdge e = graph.addEdge(x1,x2);
//        graph.setEdgeWeight(e,1);
//        e= graph.addEdge(x2,x3);
//        graph.setEdgeWeight(e,2);

        return graph;
    }
}
