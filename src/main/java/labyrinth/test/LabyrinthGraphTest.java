package labyrinth.test;

import graph.LabyrinthGraph;
import graph.LabyrinthPathFinder;

/**
 * Clase de pruebas estáticas para validar el Grafo Dirigido Ponderado
 * y los algoritmos de rutas de la Fase 2 (Requisitos R7 y R8).
 */
public class LabyrinthGraphTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("=== INICIO DE PRUEBAS DE LA FASE 2 (GRAFOS)    ===");
        System.out.println("==================================================\n");

        // 1. Crear un grafo con 4 vértices (representando 4 salas: 0, 1, 2, 3)
        LabyrinthGraph labyrinthGraph = new LabyrinthGraph(4);
        labyrinthGraph.printGraphInfo();

        // 2. Añadir conexiones (pasillos) ponderadas entre salas
        // Ejemplo: Sala 0 -> Sala 1 (peso/coste de energía: 2.5)
        labyrinthGraph.addConnection(0, 1, 2.5);
        // Sala 1 -> Sala 2 (peso: 1.0)
        labyrinthGraph.addConnection(1, 2, 1.0);
        // Sala 0 -> Sala 2 (peso: 5.0)
        labyrinthGraph.addConnection(0, 2, 5.0);
        // Sala 2 -> Sala 3 (peso: 2.0)
        labyrinthGraph.addConnection(2, 3, 2.0);

        System.out.println("\n[TEST R8] Calculando ruta óptima desde la Sala 0 hasta la Sala 3:");
        LabyrinthPathFinder.findShortestPath(labyrinthGraph.getGraph(), 0, 3);

        System.out.println("\n==================================================");
        System.out.println("=== PRUEBAS DE LA FASE 2 COMPLETADAS CON ÉXITO ===");
        System.out.println("==================================================");
    }
}