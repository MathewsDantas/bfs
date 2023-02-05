package app.structures;

import app.structures.utils.Edge;
import app.structures.utils.Vertex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static Vertex getBestLocation(Graph graph) {
        int n = graph.getVertexs().size();
        Queue<Vertex> queue = new LinkedList<>();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        Vertex bestLocation = null;
        double avgDistance = 0;
        double  minDistance = Integer.MAX_VALUE;

        for (Vertex vertex : graph.getVertexs()) {
            double  totalDistance = 0;
            queue.add(vertex);
            visited[graph.getVertexs().indexOf(vertex)] = true;
            distance[graph.getVertexs().indexOf(vertex)] = 0;
            System.out.println("-----------------------------------------------------------");
            System.out.println("Vértice origem = "+vertex.getLabel());
            while (!queue.isEmpty()) {
                Vertex currentVertex = queue.poll();
                System.out.println("--> Vértice atual = " + currentVertex.getLabel());
                for (Edge edge : graph.getIncidenceOfVertex(currentVertex)) {

                    Vertex adjacentVertex = graph.getOposite(currentVertex, edge);
                    int adjacentIndex = graph.getVertexs().indexOf(adjacentVertex);

                    if (!visited[adjacentIndex]) {
                        distance[adjacentIndex] = distance[graph.getVertexs().indexOf(currentVertex)] + 1;
                        totalDistance += distance[adjacentIndex];
                        System.out.println("Distancia para o "+adjacentVertex.getLabel()+" = " + distance[adjacentIndex]);
                        System.out.println("Distancia total = " + totalDistance);
                        visited[adjacentIndex] = true;
                        queue.add(adjacentVertex);
                    }
                }
                for (boolean v: visited) {
                    System.out.println(v);
                }
                System.out.println("");
            }

            avgDistance = (totalDistance/n);
            if (avgDistance < minDistance) {
                minDistance = avgDistance;
                bestLocation = vertex;
            }
            Arrays.fill(visited, false);

        }

        return bestLocation;
    }
}
