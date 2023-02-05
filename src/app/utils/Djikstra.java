package app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.*;

public class Djikstra {

    public static class Pair<T, U> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Node {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static List<Pair<Integer, Integer>> dijkstra(int[][] maze, int srcX, int srcY) {
        int ROW = maze.length;
        int COL = maze[0].length;
        int[] rowNum = { -1, 0, 0, 1 };
        int[] colNum = { 0, -1, 1, 0 };

        long start = System.currentTimeMillis();

        // Matriz de caminho, saidas e distancia
        int[][] dist = new int[ROW][COL];
        List<Pair<Integer, Integer>> exits = new ArrayList<>();
        List<Pair<Integer, Integer>>[][] path = new ArrayList[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                path[i][j] = new ArrayList<>();
                if (maze[i][j] == 3) {
                    exits.add(new Pair<>(i, j));
                }
            }
        }
        path[srcX][srcY].add(new Pair<>(srcX, srcY)); // caminho inicial
        dist[srcX][srcY] = 0; // distancia inicial

        // Matriz de visitados
        boolean[][] visited = new boolean[ROW][COL];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Node(srcX, srcY, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.x][curr.y]) {
                continue;
            }

            visited[curr.x][curr.y] = true;

            // Está processando uma saída
            if (maze[curr.x][curr.y] == 3) {
                return path[curr.x][curr.y];
            }

            for (int k = 0; k < 4; k++) {
                int i = curr.x + rowNum[k];
                int j = curr.y + colNum[k];

                // Se o vizinho é seguro e a distância é menor do que a anterior, atualiza
                if (isSafe(maze, i, j, visited)) {
                    int newDist = dist[curr.x][curr.y] + 1;
                    if (newDist < dist[i][j]) {
                        dist[i][j] = newDist;
                        pq.add(new Node(i, j, newDist));
                        path[i][j] = new ArrayList<>(path[curr.x][curr.y]);
                        path[i][j].add(new Pair<>(i, j));
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public static List<Pair<Integer, Integer>> AStar(int[][] maze, int srcX, int srcY) {
        int ROW = maze.length;
        int COL = maze[0].length;
        int[] rowNum = { -1, 0, 0, 1 };
        int[] colNum = { 0, -1, 1, 0 };

        int[][] dist = new int[ROW][COL];
        List<Pair<Integer, Integer>>[][] path = new ArrayList[ROW][COL];
        List<Pair<Integer, Integer>> exits = new ArrayList<>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                path[i][j] = new ArrayList<>();
                if (maze[i][j] == 3) {
                    exits.add(new Pair<>(i, j));
                }
            }
        }
        path[srcX][srcY].add(new Pair<>(srcX, srcY));
        dist[srcX][srcY] = 0;

        boolean[][] visited = new boolean[ROW][COL];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.dist + heuristic(a, exits)) - (b.dist + heuristic(b, exits)));
        pq.add(new Node(srcX, srcY, 0));

        // Enquanto a fila de prioridade não estiver vazia
        while (!pq.isEmpty()) {
            // Pega o nó com menor distância
            Node curr = pq.poll();

            // Se o nó atual já foi visitado, continua
            if (visited[curr.x][curr.y]) {
                continue;
            }

            // Marca o nó atual como visitado
            visited[curr.x][curr.y] = true;

            // Está processando uma saída
            if (maze[curr.x][curr.y] == 3) {
                return path[curr.x][curr.y];
            }

            // Verifica os vizinhos do nó atual
            for (int k = 0; k < 4; k++) {
                int i = curr.x + rowNum[k];
                int j = curr.y + colNum[k];

                // Se o vizinho é seguro e a distância é menor do que a anterior, atualiza
                if (isSafe(maze, i, j, visited)) {
                    int newDist = dist[curr.x][curr.y] + 1;
                    if (newDist < dist[i][j]) {
                        dist[i][j] = newDist;
                        pq.add(new Node(i, j, newDist));
                        path[i][j] = new ArrayList<>(path[curr.x][curr.y]);
                        path[i][j].add(new Pair<>(i, j));
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public static int heuristic(Node curr, List<Pair<Integer, Integer>> exits) {
        int minDist = Integer.MAX_VALUE;
        for (Pair<Integer, Integer> exit : exits) {
            int dist = (int) Math.sqrt(Math.pow(curr.x - exit.first, 2) + Math.pow(curr.y - exit.second, 2));
            minDist = Math.min(minDist, dist);
        }
        return minDist;
    }

    private static boolean isSafe(int[][] maze, int i, int j, boolean[][] visited) {
        return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length && maze[i][j] != 1 && !visited[i][j];
    }

}
