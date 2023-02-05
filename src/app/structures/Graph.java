package app.structures;

import java.util.Vector;

import app.structures.utils.Vertex;
import app.structures.utils.Edge;
import app.interfaces.IGraphPrinter;

public class Graph {
    Vector<Vector<Vector<Edge>>> adjacencies;
    Vector<Vertex> vertexs;
    IGraphPrinter printer;

    public Graph(IGraphPrinter printer) {
        this.printer = printer;
        this.vertexs = new Vector<Vertex>();
        this.adjacencies = new Vector<Vector<Vector<Edge>>>();
    }

    public Graph(Graph graph) {
        this.adjacencies = graph.getAdjacencies();
        this.vertexs = graph.getVertexs();
        this.printer = graph.getPrinter();
    }

    public Vertex addVertex(Vertex vertex) {
        this.vertexs.add(vertex);

        Vector<Vector<Edge>> line = new Vector<Vector<Edge>>();
        this.adjacencies.add(line);
        this.updateColumnsSize();

        return vertex;
    }

    public Vector<Vertex> getVertexs() {
        return this.vertexs;
    }

    public void removeVertex(Vertex vertex) throws IllegalArgumentException {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        this.vertexs.remove(vertexPosition);
        this.adjacencies.remove(vertexPosition);
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            line.remove(vertexPosition);
        }
    }

    public Edge addEdge(Vertex from, Vertex to, Edge edge, boolean isDirect) {
        int x = this.vertexs.indexOf(from);
        int y = this.vertexs.indexOf(to);
        this.adjacencies.get(x).get(y).add(edge);
        if (!isDirect && x != y) {
            this.adjacencies.get(y).get(x).add(edge);
        }
        return edge;
    }

    public void removeEdge(Vertex from, Vertex to, Edge edge) throws IllegalArgumentException {
        int x = this.vertexs.indexOf(from);
        int y = this.vertexs.indexOf(to);
        boolean removed = this.adjacencies.get(x).get(y).remove(edge);
        if (!removed) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        if (x != y) {
            this.adjacencies.get(y).get(x).remove(edge);
        }
    }

    public Vector<Vertex> finalsVertex(Edge edge) {
        Vector<Vertex> v = new Vector<Vertex>();
        for (int x = 0; x < this.vertexs.size(); x++) {
            for (int y = 0; y < this.vertexs.size(); y++) {
                for (Edge cedge : this.adjacencies.get(x).get(y)) {
                    if (edge == cedge) {
                        v.add(this.vertexs.get(x));
                        v.add(this.vertexs.get(y));
                        return v;
                    }
                }
            }
        }
        return null;
    }

    public Vector<Edge> getIncidenceOfVertex(Vertex vertex) {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Vertice não encontrado");
        }
        Vector<Edge> result = new Vector<Edge>();
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Edge edge : line.get(vertexPosition)) {
                result.add(edge);
            }
        }
        return result;
    }

    private void updateColumnsSize() {
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            while (line.size() < this.adjacencies.size()) {
                Vector<Edge> column = new Vector<Edge>();
                line.add(column);
            }
        }
    }

    public boolean areAdjacent(Vertex vertexOne, Vertex vertexTwo) throws IllegalArgumentException {
        int vertexOnePosition = this.vertexs.indexOf(vertexOne);
        int vertexTwoPosition = this.vertexs.indexOf(vertexTwo);
        if (vertexOnePosition == -1 || vertexTwoPosition == -1) {
            throw new IllegalArgumentException("Vértice não encontrada");
        }

        return this.adjacencies.get(vertexOnePosition).get(vertexTwoPosition).size() > 0;
    }

    public Vector<Vertex> getAdjacencies(Vertex vertex) {
        Vector<Vertex> result = new Vector<Vertex>();

        for (int i = 0; i < this.vertexs.size(); i++) {
            Vertex vertexTwo = this.vertexs.get(i);
            if (vertex == vertexTwo)
                continue;
            if (this.areAdjacent(vertex, vertexTwo))
                result.add(vertexTwo);
        }

        return result;
    }

    public Vertex getOposite(Vertex vertex, Edge edge) throws IllegalArgumentException {
        int vertexPosition = this.vertexs.indexOf(vertex);
        if (vertexPosition == -1) {
            throw new IllegalArgumentException("Aresta não encontrada");
        }
        Vertex result = null;
        for (int i = 0; i < this.adjacencies.size(); i++) {
            if (i != vertexPosition) {
                for (Edge v : this.adjacencies.get(i).get(vertexPosition)) {
                    if (v.equals(edge)) {
                        result = this.vertexs.get(i);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean isDirected(Edge edge) throws IllegalArgumentException {
        for (int x = 0; x < this.vertexs.size(); x++) {
            for (int y = 0; y < this.vertexs.size(); y++) {
                for (Edge cedge : this.adjacencies.get(x).get(y)) {
                    if (edge == cedge) {
                        return !this.adjacencies.get(y).get(x).contains(edge);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Aresta não encontrada");
    }

    // my methods
    public int getOrder() {
        return this.vertexs.size();
    }

    public int degree(int edge) {
        int result = 0;
        for (Vector<Edge> column : this.adjacencies.get(edge)) {
            result += column.size();
        }
        return result;
    }

    public boolean isRegular() {
        boolean result = true;
        int degree = this.degree(0);
        for (int i = 1; i < this.vertexs.size(); i++) {
            if (this.degree(i) != degree) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isMultigraph() {
        boolean result = false;
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Vector<Edge> column : line) {
                if (column.size() > 1) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isComplete() {
        boolean result = true;
        for (Vector<Vector<Edge>> line : this.adjacencies) {
            for (Vector<Edge> column : line) {
                int indexOfLine = this.adjacencies.indexOf(line);
                int indexOfColumn = line.indexOf(column);
                if (column.size() == 0 && indexOfLine != indexOfColumn) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // se é conexo
    public boolean isConnected() {
        Graph graph = new Graph(this);
        int parts = 0;

        while (graph.getVertexs().size() > 1) {
            Vertex v0 = graph.getVertexs().get(0);
            while (this.getAdjacencies(v0).size() > 0) {
                v0 = graph.fuse(v0, this.getAdjacencies(v0).get(0));
            }
            parts += 1;
        }

        return parts <= 1;
    }

    public Vertex fuse(Vertex vOne, Vertex vTwo) {
        int indexOfVone = this.vertexs.indexOf(vOne);
        int indexOfVtwo = this.vertexs.indexOf(vTwo);
        for (int i = 0; i < this.vertexs.size(); i++) {
            // this.adjacencies.get(vOne).get(i).add(this.adjacencies.get(vTwo).get(i));
        }
        return null;
    }

    // se é euleriano
    public boolean isEulerian() {
        boolean result = true;
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.degree(i) % 2 != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    // se tem um caminho euleriano
    public boolean hasEulerianPath() {
        int odd = 0;
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.degree(i) % 2 != 0) {
                odd++;
            }
            if (odd > 2) {
                return false;
            }
        }
        return true;
    }

    // GET EULERIAN PATH fleury
    public Vector<Vertex> getEulerianPath() {
        if (!this.hasEulerianPath()) {
            return null;
        }

        Vector<Vertex> result = new Vector<Vertex>();
        Vector<Vertex> edges = new Vector<Vertex>(this.vertexs);
        Vector<Vector<Vector<Edge>>> adjacencies = new Vector<Vector<Vector<Edge>>>(this.adjacencies);

        // como descobrir se está desconexo
        return result;
    }

    public boolean isBridge(Edge vertex) {
        boolean result = false;
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.degree(i) == 1) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void print() {
        this.printer.print(this);
    }

    public Vector<Vector<Vector<Edge>>> getAdjacencies() {
        return this.adjacencies;
    }

    public IGraphPrinter getPrinter() {
        return this.printer;
    }
}