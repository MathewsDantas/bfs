package app.utils;

import java.util.Vector;

import app.interfaces.IGraphPrinter;
import app.structures.Graph;
import app.structures.utils.Vertex;
import app.structures.utils.Edge;

public class GraphAdjacencePrinter implements IGraphPrinter {
    public GraphAdjacencePrinter() {
    }

    public void print(Graph graph) {
        if (graph.getAdjacencies() == null) {
            System.out.println("Grafo vazio");
            return;
        }
        System.out.print(String.format("%10s", ""));
        for (Vertex edge : graph.getVertexs()) {
            System.out.print(String.format("%10s", edge.getLabel()+":"+edge.getCost()));
        }
        System.out.println();
        for (int i = 0; i < graph.getAdjacencies().size(); i++) {
            Vector<Vector<Edge>> column = graph.getAdjacencies().get(i);
            System.out.print(String.format("%10s", graph.getVertexs().get(i).getLabel()));
            for (Vector<Edge> line : column) {
                String lineString = "";
                lineString += "[";
                for (Edge vertex : line) {
                    lineString += vertex.getCost() + ",";
                }
                lineString += "]";
                System.out.print(String.format("%10s", lineString));
            }
            System.out.println();
        }
    }
}