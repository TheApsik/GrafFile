/*
 * Klasa realizuje graficzną wersję grafu
 * */

package net.example.cebulasoft.graffile.GUI;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import javax.swing.*;
import java.awt.*;

public class GraphX {

	JFrame frame;

	JGraphXAdapter<String, DefaultWeightedEdge> gAdapter;
	mxIGraphLayout layout;


	public GraphX(DirectedWeightedMultigraph<String, DefaultWeightedEdge> baseGraph) {
		this.frame = new JFrame("Graf");
		this.gAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(baseGraph);
		frame.setPreferredSize(new Dimension(1200, 800));

		this.layout = new mxCircleLayout(gAdapter);
	}

	public void Exec() {
		SwingUtilities.invokeLater(() -> {
			layout.execute(gAdapter.getDefaultParent());
			frame.add(new mxGraphComponent(gAdapter));
			frame.pack();
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
		});
	}
}
