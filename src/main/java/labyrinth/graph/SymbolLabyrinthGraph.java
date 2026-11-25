package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.ST;
import java.util.List;

/**
 * Permite gestionar un Grafo Dirigido Ponderado utilizando códigos de salas
 * en formato String (ej. "ROOM-A1"), mapeándolos internamente a vértices numéricos
 * mediante Tablas de Símbolos (Fase 2, Requisito R7).
 */
public class SymbolLabyrinthGraph {
    private ST<String, Integer> st; // Mapea código de sala (String) -> índice de vértice (int)
    private String[] keys;          // Mapea índice de vértice (int) -> código de sala (String)
    private EdgeWeightedDigraph graph;

    /**
     * Constructor que inicializa el grafo simbólico a partir de una lista de códigos de salas.
     *
     * @param roomCodes Lista con los códigos de todas las salas del laberinto.
     */
    public SymbolLabyrinthGraph(List<String> roomCodes) {
        st = new ST<>();
        int n = roomCodes.size();
        keys = new String[n];

        for (int i = 0; i < n; i++) {
            st.put(roomCodes.get(i), i);
            keys[i] = roomCodes.get(i);
        }

        graph = new EdgeWeightedDigraph(n);
    }

    public boolean contains(String roomCode) {
        return st.contains(roomCode);
    }

    public int index(String roomCode) {
        return st.get(roomCode);
    }

    public String name(int v) {
        return keys[v];
    }

    public EdgeWeightedDigraph graph() {
        return graph;
    }

    /**
     * Añade una conexión ponderada dirigida entre dos salas usando sus códigos de texto.
     *
     * @param fromRoom Sala de origen
     * @param toRoom   Sala de destino
     * @param weight   Coste o peso del pasillo
     */
    public void addEdge(String fromRoom, String toRoom, double weight) {
        if (contains(fromRoom) && contains(toRoom)) {
            int u = index(fromRoom);
            int v = index(toRoom);
            graph.addEdge(new DirectedEdge(u, v, weight));
        } else {
            System.out.println("[ERROR SYMBOL GRAPH] Una o ambas salas no existen en el grafo: " + fromRoom + " -> " + toRoom);
        }
    }

    public int V() {
        return graph.V();
    }

    public int E() {
        return graph.E();
    }
}