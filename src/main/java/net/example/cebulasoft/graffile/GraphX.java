
package net.example.cebulasoft.graffile;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import javax.swing.*;

/**
 * Klasa realizuje graficzną wersję grafu
 */
class GraphX {

    private JFrame frame;

    private JGraphXAdapter<String, FileWeightedEdge> graphAdapter;
    private mxIGraphLayout layout;


    GraphX(DirectedWeightedMultigraph<String, FileWeightedEdge> baseGraph) {
        this.frame = new JFrame("Graf");
        this.graphAdapter = new JGraphXAdapter<>(baseGraph);

        this.layout = new mxCircleLayout(graphAdapter);
    }

    void Exec() {
        SwingUtilities.invokeLater(() -> {
            layout.execute(graphAdapter.getDefaultParent());
            frame.add(new mxGraphComponent(graphAdapter));
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }
}
