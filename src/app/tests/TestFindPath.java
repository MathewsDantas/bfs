package app.tests;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import app.interfaces.ITest;
import app.utils.Djikstra;

public class TestFindPath implements ITest {

    public static void run() {
        try {
            File file = new File("src/app/assets/maze.dat");
            Scanner sc = new Scanner(file);

            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();

            int rows = lines.size();
            int cols = lines.get(0).length();

            int[][] maze = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                for (int j = 0; j < cols; j++) {
                    maze[i][j] = Character.getNumericValue(line.charAt(j));
                }
            }

            long start = System.currentTimeMillis();
            List<Djikstra.Pair<Integer, Integer>> dijkstraPath = Djikstra.dijkstra(maze, 1, 1);
            long end = System.currentTimeMillis();
            System.out.println("Tempo gasto para encontrar o caminho com Dijkstra: " + (end - start) + " ms");
            System.out.print("Caminho: ");
            for (Djikstra.Pair<Integer, Integer> pair : dijkstraPath)
                System.out.print("(" + pair.first + "," + pair.second + ")=>");
            System.out.println("\n");

            start = System.currentTimeMillis();
            List<Djikstra.Pair<Integer, Integer>> aStarPath = Djikstra.AStar(maze, 1, 1);
            end = System.currentTimeMillis();
            System.out.println("Tempo gasto para encontrar o caminho com A*: " + (end - start) + " ms");
            System.out.print("Caminho: ");
            for (Djikstra.Pair<Integer, Integer> pair : aStarPath)
                System.out.print("(" + pair.first + "," + pair.second + ")=>");
            System.out.println("\n");
            System.out.println("Caminho como matriz: ");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    boolean isDijkstraPath = false;
                    for (Djikstra.Pair<Integer, Integer> pair : aStarPath) {
                        if (pair.first == i && pair.second == j) {
                            System.out.print("X ");
                            isDijkstraPath = true;
                            break;
                        }
                    }
                    if (isDijkstraPath)
                        continue;
                    System.out.print(maze[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }
    }

}
