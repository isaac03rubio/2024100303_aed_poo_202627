package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Gestiona el Grafo Dirigido Ponderado para modelar las conexiones espaciales,
 * pasillos y rutas del laberinto (Fase 2, Requisito R7).
 */
public class LabyrinthGraph {
    private EdgeWeightedDigraph graph;
    private int numVertices;

    /**
     * Constructor del grafo para un número determinado de salas (vértices).
     *
     * @param numVertices Número total de salas en el laberinto
     */
    public LabyrinthGraph(int numVertices) {
        this.numVertices = numVertices;
        this.graph = new EdgeWeightedDigraph(numVertices);
    }

    /**
     * Añade un pasillo o conexión dirigida con un peso específico (distancia, coste energético, etc.).
     *
     * @param from   Vértice de origen (ID numérico de la sala)
     * @param to     Vértice de destino (ID numérico de la sala)
     * @param weight Peso de la arista (ej. coste o distancia)
     */
    public void addConnection(int from, int to, double weight) {
        if (from >= 0 && from < numVertices && to >= 0 && to < numVertices) {
            DirectedEdge edge = new DirectedEdge(from, to, weight);
            graph.addEdge(edge);
        } else {
            System.out.println("[ERROR GRAPH] Índices de vértices fuera de rango.");
        }
    }

    public EdgeWeightedDigraph getGraph() {
        return graph;
    }

    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Muestra información básica del grafo en consola.
     */
    public void printGraphInfo() {
        System.out.println("Grafo del Laberinto inicializado con " + numVertices + " vértices y " + graph.E() + " aristas.");
    }
}