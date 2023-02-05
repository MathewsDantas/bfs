package app.tests;

import java.util.Vector;

import app.interfaces.ITest;
import app.structures.Graph;
import app.structures.utils.Vertex;
import app.structures.utils.Edge;
import app.utils.GraphAdjacencePrinter;

public class TestGraph implements ITest {
    public static void run() {
        Graph graph = new Graph(new GraphAdjacencePrinter());
        Vertex e1 = graph.addVertex(new Vertex(2, "e1"));
        Vertex e2 = graph.addVertex(new Vertex(3, "e2"));
        Vertex e3 = graph.addVertex(new Vertex(4, "e3"));
        Vertex e34 = graph.addVertex(new Vertex(5, "e4"));

        Edge v1 = new Edge(1);
        Edge v2 = new Edge(2);
        graph.addEdge(e1, e2, v1, false);
        graph.addEdge(e1, e3, v2, true);
        graph.addEdge(e3, e3, new Edge(3), false);
        graph.print();
        System.out.println("----");

        // arestas do vertice
        Vector<Edge> edgesOfVertex = graph.getIncidenceOfVertex(e3);
        System.out.print("ARESTAS INCIDENTES EM e3: ");
        for (Edge vertex : edgesOfVertex) {
            System.out.print(vertex.getCost() + " ");
        }
        System.out.println("");

        // arestas do vertice
        edgesOfVertex = graph.getIncidenceOfVertex(e1);
        System.out.print("ARESTAS INCIDENTES EM e1: ");
        for (Edge vertex : edgesOfVertex) {
            System.out.print(vertex.getCost() + " ");
        }
        System.out.println("");

        // vertices da aresta
        Vector<Vertex> vertexsOfEdge = graph.finalsVertex(v1);
        System.out.print("VERTICES FINAIS DE v1: ");
        for (Vertex vertex : vertexsOfEdge) {
            System.out.print(vertex.getLabel() + " ");
        }
        System.out.println("");

        System.out.println("ARESTA v1 é direcionada: " + graph.isDirected(v1));
        System.out.println("ARESTA v2 é direcionada: " + graph.isDirected(v2));

        // se sao adjancentes
        System.out.println("ARESTA ADJACENTE e2 & e3: " + graph.areAdjacent(e2, e3));
        System.out.print("ARESTAS ADJACENTES A e1: ");
        for (Vertex v : graph.getAdjacencies(e1)) {
            System.out.print(v.getLabel() + " ");
        }
        System.out.println();
        // aresta oposta
        System.out.println("ARESTA OPOSTA e2->v1: " + graph.getOposite(e2, v1).getLabel());

        // testando se é completo
        System.out.println("É COMPLETO: " + graph.isComplete());
        System.out.println("É EULERIANO: " + graph.isEulerian());
//        System.out.println("É CONEXO: " + graph.isConnected());
        System.out.println("TEM CAMINHO EULERIANO: " + graph.hasEulerianPath());
        if (graph.hasEulerianPath()) {
            for (Vertex edge : graph.getEulerianPath()) {
                System.out.print(edge.getLabel() + "->");
            }
            System.out.println("");
        }

        // CRIANDO GRAFO COMPLETO
        Graph graph2 = new Graph(new GraphAdjacencePrinter());
        Vertex e4 = graph2.addVertex(new Vertex(2, "e4"));
        Vertex e5 = graph2.addVertex(new Vertex(3, "e5"));
        Vertex e6 = graph2.addVertex(new Vertex(4, "e6"));
        graph2.addEdge(e4, e5, new Edge(1), false);
        graph2.addEdge(e4, e6, new Edge(2), false);
        graph2.addEdge(e5, e6, new Edge(3), false);
        graph2.print();
        System.out.println("É COMPLETO: " + graph2.isComplete());
//        System.out.println("É CONEXO: " + graph2.isConnected());
        System.out.println("É EULERIANO: " + graph2.isEulerian());
        System.out.println("TEM CAMINHO EULERIANO: " + graph2.hasEulerianPath());
        if (graph2.hasEulerianPath()) {
            for (Vertex edge : graph2.getEulerianPath()) {
                System.out.print(edge.getLabel() + "->");
            }
            System.out.println("");
        }
    }
}