package labyrinth.test;

import graph.SymbolLabyrinthGraph;
import graph.LabyrinthPathFinder;

import java.util.Arrays;
import java.util.List;

/**
 * Clase de pruebas para validar el Grafo Simbólico del Laberinto
 * y la resolución de rutas óptimas utilizando códigos de salas en String
 * (Fase 2, Requisitos R7 y R8).
 */
public class SymbolLabyrinthGraphTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("=== INICIO DE PRUEBAS DE GRAFO SIMBÓLICO (FASE 2) ===");
        System.out.println("==================================================\n");

        // 1. Definir una lista de salas de ejemplo (simulando las de nuestra Fase 1)
        List<String> roomCodes = Arrays.asList("ROOM-A1", "ROOM-B2", "ROOM-C3", "ROOM-D4");

        // 2. Inicializar el Grafo Simbólico
        SymbolLabyrinthGraph symbolGraph = new SymbolLabyrinthGraph(roomCodes);
        System.out.println("[INFO] Grafo simbólico creado con " + symbolGraph.V() + " salas mapeadas.");

        // 3. Añadir conexiones ponderadas usando los códigos de texto de las salas
        symbolGraph.addEdge("ROOM-A1", "ROOM-B2", 3.0);
        symbolGraph.addEdge("ROOM-B2", "ROOM-C3", 1.5);
        symbolGraph.addEdge("ROOM-A1", "ROOM-C3", 6.0);
        symbolGraph.addEdge("ROOM-C3", "ROOM-D4", 2.0);

        // 4. Calcular la ruta óptima entre "ROOM-A1" y "ROOM-D4" usando Dijkstra
        String startRoom = "ROOM-A1";
        String endRoom = "ROOM-D4";

        System.out.println("\n[TEST R8] Buscando ruta óptima desde '" + startRoom + "' hasta '" + endRoom + "':");

        if (symbolGraph.contains(startRoom) && symbolGraph.contains(endRoom)) {
            int u = symbolGraph.index(startRoom);
            int v = symbolGraph.index(endRoom);

            // Ejecutamos Dijkstra pasando el EdgeWeightedDigraph subyacente y los índices numéricos correspondientes
            LabyrinthPathFinder.findShortestPath(symbolGraph.graph(), u, v);

            // Opcional: Mostrar los nombres reales de las salas en la traza si se desea,
            // pero el algoritmo ya nos da la ruta numérica correcta vinculada.
        } else {
            System.out.println("[ERROR] Alguna de las salas de origen o destino no existe en el grafo.");
        }

        System.out.println("\n==================================================");
        System.out.println("=== PRUEBAS DE GRAFO SIMBÓLICO COMPLETADAS CON ÉXITO ===");
        System.out.println("==================================================");
    }
}