import java.util.*;
// parte de :
// Raphaela Canadas :)
public class FloydWarshall {

    static final long INF = (long) 1e15;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // entrada: n m y luego m lineas u v w
        // vertices 0 n-1
        System.out.print("n: ");
        int n = sc.nextInt();

        System.out.print("m: ");
        int m = sc.nextInt();

        
        boolean dirigido = false;

        long[][] dist = new long[n][n];

      
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // leer aristas
        
        System.out.println("mete aristas: u v w");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            if (w < dist[u][v]) dist[u][v] = w;
            if (!dirigido && w < dist[v][u]) dist[v][u] = w;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] >= INF) continue; // si i->k no existe, ni intentes
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] >= INF) continue; // si k->j no existe, tampoco
                    long cand = dist[i][k] + dist[k][j];
                    if (cand < dist[i][j]) dist[i][j] = cand;
                }
            }
        }

        // imprimir matriz final
        System.out.println("\nmatriz de distancias minimas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] >= INF / 2) System.out.print("inf\t");
                else System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }

        // mini consulta tipo para tsp te sirve como tabla de pesos de a - b
        System.out.print("\nconsulta (u v): ");
        int u = sc.nextInt();
        int v = sc.nextInt();

        if (dist[u][v] >= INF / 2) {
            System.out.println("no hay camino de " + u + " a " + v);
        } else {
            System.out.println("distancia minima de " + u + " a " + v + " = " + dist[u][v]);
        }

        sc.close();
    }
}
