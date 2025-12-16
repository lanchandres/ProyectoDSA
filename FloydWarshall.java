import java.util.*;
// parte de :
// Raphaela Canadas :)

public class FloydWarshall {

  
    static final long INF = (long) 1e15;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // entrada: n m y luego m lineas u v w
        // vertices 0..n-1
        System.out.print("n: ");
        int n = sc.nextInt();

        System.out.print("m: ");
        int m = sc.nextInt();

        // cambia a true si el grafo es dirigido pero es no dirigio porsi 
        boolean dirigido = false;

        long[][] dist = new long[n][n];
        int[][] next = new int[n][n]; // matriz que guarda el siguiente nodo en el camino corto

        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;  // la distancia de un nodo a sí mismo es 0
            for (int j = 0; j < n; j++) {
                next[i][j] = -1;  
            }
        }

        // leer las aristas
        System.out.println("mete aristas: u v w");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            if (w < dist[u][v]) {
                dist[u][v] = w;
                next[u][v] = v;  // el siguiente nodo es v
            }
            if (!dirigido && w < dist[v][u]) {
                dist[v][u] = w;
                next[v][u] = u;  // el siguiente nodo es u (por ser no dirigido) jejje
            }
        }

        // algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] >= INF) continue; // si no hay camino de i a k lo saltamos
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] >= INF) continue; // si no hay camino de k a j lo saltamos
                    long cand = dist[i][k] + dist[k][j];
                    if (cand < dist[i][j]) {
                        dist[i][j] = cand;
                        next[i][j] = next[i][k];  
                    }
                }
            }
        }

        // imprimir la matriz de distancias mínimas
        System.out.println("\nmatriz de distancias minimas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] >= INF / 2) System.out.print("inf\t");
                else System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }

        // consulta de camino entre dos nodos
        System.out.print("\nconsulta (u v): ");
        int u = sc.nextInt();
        int v = sc.nextInt();

        if (dist[u][v] >= INF / 2) {
            System.out.println("no hay camino de " + u + " a " + v);
        } else {
            System.out.println("distancia minima de " + u + " a " + v + " = " + dist[u][v]);

           
            List<Integer> path = reconstructPath(u, v, next);
            System.out.println("camino: " + path);

         
        }

        sc.close();
    }

    // func para reconstruir el camino desde u hasta v usando la matriz next uwu

    private static List<Integer> reconstructPath(int u, int v, int[][] next) {
        List<Integer> path = new ArrayList<>();
        if (next[u][v] == -1) {
            return path; // no hay camino
        }

        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }
}
