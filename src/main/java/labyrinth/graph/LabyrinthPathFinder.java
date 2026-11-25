package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Utiliza algoritmos de la librería algs4 (como DijkstraSP) para calcular
 * las rutas óptimas y caminos más cortos en el laberinto (Fase 2, Requisito R8).
 */
public class LabyrinthPathFinder {

    /**
     * Calcula y muestra el camino más corto entre dos salas (vértices) del grafo.
     *
     * @param graph El grafo ponderado dirigido del laberinto
     * @param source Vértice de origen
     * @param destination Vértice de destino
     */
    public static void findShortestPath(EdgeWeightedDigraph graph, int source, int destination) {
        DijkstraSP sp = new DijkstraSP(graph, source);

        if (sp.hasPathTo(destination)) {
            System.out.println("[PATHFINDER] Ruta óptima encontrada desde el vértice " + source + " hasta " + destination + ":");
            System.out.println("   Coste total acumulado: " + sp.distTo(destination));
            System.out.print("   Secuencia de pasillos: ");
            for (DirectedEdge e : sp.pathTo(destination)) {
                System.out.print(e.from() + " -> " + e.to() + " (peso: " + e.weight() + ") | ");
            }
            System.out.println();
        } else {
            System.out.println("[PATHFINDER] No existe una ruta posible entre el vértice " + source + " y el vértice " + destination + ".");
        }
    }
}