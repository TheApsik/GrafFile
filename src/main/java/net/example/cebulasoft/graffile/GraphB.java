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

    private static DirectedWeightedMultigraph<String, DefaultWeightedEdge> buildGraph(FilesConnectionInfo filesConnectionInfo) {
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(FileWeightedEdge.class);

        for (FileInfo key : filesConnectionInfo.getValues()) {
            String name = key.getName();
            graph.addVertex(name);
        }

        for (FileInfo key : filesConnectionInfo.getValues()) {
            key.getReferences().forEach((s, integer) -> {
                graph.addEdge(key.getName(), s);
                graph.setEdgeWeight(key.getName(), s, integer);
            });
        }


        /*
         * INFO:
         * Poniżej znajdują się przykładowe dane do grafu wraz z krawędziami,
         * to tak aby pokazać że sam graf działa, domyślnie zakomentowane.
         */

        //sample data - labels

        //String x1 = "one";
        //String x2 = "two";
        //String x3 = "three";


        //graph.addVertex(x1);
        //graph.addVertex(x2);
        //graph.addVertex(x3);


        //sample data - edges

//        DefaultWeightedEdge e = graph.addEdge(x1, x2);
//        graph.setEdgeWeight(e, 1);
//        e = graph.addEdge(x2, x3);
//        graph.setEdgeWeight(e, 2);

        return graph;
    }
}
