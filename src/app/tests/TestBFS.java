package app.tests;

import app.interfaces.ITest;
import app.structures.BFS;
import app.structures.Graph;
import app.structures.utils.Edge;
import app.structures.utils.Vertex;
import app.utils.GraphAdjacencePrinter;

public class TestBFS implements ITest {
    public static void run() {
        Graph graph = new Graph(new GraphAdjacencePrinter());
        Vertex v1 = graph.addVertex(new Vertex(6, "v1"));
        Vertex v2 = graph.addVertex(new Vertex(4, "v2"));
        Vertex v3 = graph.addVertex(new Vertex(3, "v3"));
        Vertex v4 = graph.addVertex(new Vertex(5, "v4"));
        Vertex v5 = graph.addVertex(new Vertex(1, "v5"));
        Vertex v6 = graph.addVertex(new Vertex(2, "v6"));

        Edge a1 = new Edge(1);
        Edge a2 = new Edge(1);
        Edge a3 = new Edge(1);
        Edge a4 = new Edge(1);
        Edge a5 = new Edge(1);
        Edge a6 = new Edge(1);
        Edge a7 = new Edge(1);

        graph.addEdge(v1, v2, a1, false);
        graph.addEdge(v2, v4, a2, false);
        graph.addEdge(v2, v3, a3, false);
        graph.addEdge(v3, v6, a5, false);
        graph.addEdge(v4, v5, a4, false);
        graph.addEdge(v4, v6, a7, false);
        graph.addEdge(v6, v5, a6, false);

        Vertex v = BFS.getBestLocation(graph);
        System.out.println(v.getLabel());
    }
}
