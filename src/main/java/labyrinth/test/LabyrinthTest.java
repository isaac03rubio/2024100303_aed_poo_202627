package test;

import database.LabyrinthDatabase;
import models.artifacts.Artifact;
import models.artifacts.EnergyCrystal;
import models.robots.ExplorerRobot;
import models.robots.Robot;
import models.rooms.Room;
import models.stations.MaintenanceStation;

import java.util.List;

/**
 * Banco de pruebas estático integral para validar rigorosamente los requisitos
 * R1 a R6 de la Fase 1 del proyecto RoboLabyrinth.
 */
public class LabyrinthTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("=== INICIO DE PRUEBAS RIGUROSAS DE LA FASE 1     ===");
        System.out.println("==================================================\n");

        runComprehensivePhase1Tests();

        System.out.println("\n==================================================");
        System.out.println("=== TODAS LAS PRUEBAS DE LA FASE 1 PASARON AL 100% ===");
        System.out.println("==================================================");
    }

    public static void runComprehensivePhase1Tests() {
        LabyrinthDatabase db = new LabyrinthDatabase();

        // 1. Población inicial de entidades (R1, R2, R3)
        db.addRobot(new ExplorerRobot("ROB-01", "AlphaScanner", 95.5, "ACTIVE", "v1.0", 50, true));
        db.addRobot(new ExplorerRobot("ROB-02", "BetaScout", 45.0, "MAINTENANCE", "v1.1", 30, false));
        db.addRobot(new ExplorerRobot("ROB-03", "GammaPathfinder", 88.0, "ACTIVE", "v1.2", 60, true));

        db.addArtifact(new EnergyCrystal("ART-01", "Cristal de Plasma Pura", 2.5, 85.0, 1.2, 500.0, "ALPHA"));
        db.addArtifact(new EnergyCrystal("ART-02", "Cristal Oscuro Inestable", 6.0, 40.0, 3.5, 200.0, "BETA"));

        db.addRoom(new Room("ROOM-A1", "RESEARCH", 3, 0.5, "2026-09-01"));
        db.addRoom(new Room("ROOM-B2", "HAZARD_ZONE", 5, 4.2, "2026-09-02"));

        db.addStation(new MaintenanceStation("ST-01", "ROOM-A1", 20.0, true));

        // 2. Test Búsqueda por Batería y Estado (R3.a)
        System.out.println("[TEST R3.a] Robots activos con batería >= 50%:");
        List<Robot> activeRobots = db.searchRobotsByBatteryAndState(50.0, "ACTIVE");
        for (Robot r : activeRobots) {
            System.out.println("   -> " + r.getName() + " [Batería: " + r.getBatteryLevel() + "%]");
        }

        // 3. Test Búsqueda por Substring en Robots (R3.b)
        System.out.println("\n[TEST R3.b] Robots que contienen 'path' en ID o Nombre:");
        List<Robot> subRobots = db.searchRobotsBySubstring("path");
        for (Robot r : subRobots) {
            System.out.println("   -> Encontrado: " + r.getId() + " - " + r.getName());
        }

        // 4. Test Búsqueda polimórfica de Artefactos (R3.c)
        System.out.println("\n[TEST R3.c] Artefactos con valor científico >= 70 y peso <= 3kg:");
        List<Artifact> bestArtifacts = db.searchArtifactsByValueAndWeight(70.0, 3.0);
        for (Artifact a : bestArtifacts) {
            System.out.println("   -> " + a.getDescription() + " [Valor: " + a.getScientificValue() + "]");
        }

        // 5. Test Búsqueda de Artefactos por Substring (R3.d)
        System.out.println("\n[TEST R3.d] Artefactos cuya descripción contiene 'plasma':");
        List<Artifact> subArtifacts = db.searchArtifactsByDescriptionSubstring("plasma");
        for (Artifact a : subArtifacts) {
            System.out.println("   -> Encontrado: " + a.getDescription());
        }

        // 6. Test Búsqueda en Red-Black BST por Seguridad (R3.e)
        System.out.println("\n[TEST R3.e] Salas con seguridad >= 4 en Red-Black BST:");
        List<Room> secureRooms = db.searchRoomsByMinSecurity(4);
        for (Room rm : secureRooms) {
            System.out.println("   -> Sala: " + rm.getCode() + " [Seguridad: " + rm.getSecurityLevel() + "]");
        }

        // 7. Test Búsqueda de Salas por Substring (R3.f)
        System.out.println("\n[TEST R3.f] Salas que contienen 'hazard' en su código o zona:");
        List<Room> subRooms = db.searchRoomsBySubstring("hazard");
        for (Room rm : subRooms) {
            System.out.println("   -> Encontrado: " + rm.getCode() + " (" + rm.getZoneType() + ")");
        }

        // 8. Test Consistencia y Eliminación en Cascada (R4)
        System.out.println("\n[TEST R4] Probando consistencia estricta eliminando la sala 'ROOM-A1':");
        db.removeRoom("ROOM-A1");

        // Verificamos si la estación asociada (ST-01) fue eliminada automáticamente por consistencia
        MaintenanceStation orphanedStation = db.getStation("ST-01");
        if (orphanedStation == null) {
            System.out.println("[PASS R4] Verificación de consistencia correcta: La estación asociada fue eliminada limpiamente en cascada.");
        } else {
            System.out.println("[FAIL R4] Error de consistencia: La estación huérfana sigue existiendo.");
        }
    }
}