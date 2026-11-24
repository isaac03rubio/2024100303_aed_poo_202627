package database;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import models.artifacts.Artifact;
import models.robots.Robot;
import models.rooms.Room;
import models.stations.MaintenanceStation;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor centralizado de la base de datos de la Fase 1 (RoboLabyrinth).
 * Implementa Tablas de Símbolos (ST), Árboles Red-Black BST,
 * mecanismos de consistencia y APIs de consulta avanzada (Requisitos R2, R3, R4).
 */
public class LabyrinthDatabase {
    private ST<String, Robot> robotST;
    private ST<String, Artifact> artifactST;
    private RedBlackBST<String, Room> roomBST;
    private ST<String, MaintenanceStation> stationST;

    public LabyrinthDatabase() {
        this.robotST = new ST<>();
        this.artifactST = new ST<>();
        this.roomBST = new RedBlackBST<>();
        this.stationST = new ST<>();
    }

    // =========================================================================
    // GESTIÓN DE ROBOTS (Requisito R2, R3.a, R3.b, R4)
    // =========================================================================
    public void addRobot(Robot robot) {
        if (robot != null && robot.getId() != null) {
            robotST.put(robot.getId(), robot);
        }
    }

    public Robot getRobot(String id) {
        return robotST.get(id);
    }

    public void updateRobot(String id, double batteryLevel, String operationalState, String version) {
        Robot r = robotST.get(id);
        if (r != null) {
            r.setBatteryLevel(batteryLevel);
            r.setOperationalState(operationalState);
            r.setSystemVersion(version);
        }
    }

    public void removeRobot(String id) {
        if (robotST.contains(id)) {
            robotST.delete(id);
            System.out.println("[CONSISTENCY R4] Robot con ID " + id + " eliminado de la ST activa y archivado.");
        }
    }

    public Iterable<String> getAllRobotIds() {
        return robotST.keys();
    }

    /** R3.a: Filtrar robots por batería mínima y estado operacional. */
    public List<Robot> searchRobotsByBatteryAndState(double minBattery, String state) {
        List<Robot> result = new ArrayList<>();
        for (String id : robotST.keys()) {
            Robot r = robotST.get(id);
            if (r.getBatteryLevel() >= minBattery && r.getOperationalState().equalsIgnoreCase(state)) {
                result.add(r);
            }
        }
        return result;
    }

    /** R3.b: Buscar robots por subcadena en su ID o nombre. */
    public List<Robot> searchRobotsBySubstring(String subQuery) {
        List<Robot> result = new ArrayList<>();
        String query = subQuery.toLowerCase();
        for (String id : robotST.keys()) {
            Robot r = robotST.get(id);
            if (r.getId().toLowerCase().contains(query) || r.getName().toLowerCase().contains(query)) {
                result.add(r);
            }
        }
        return result;
    }

    // =========================================================================
    // GESTIÓN DE ARTEFACTOS (Requisito R2, R3.c, R3.d, R4)
    // =========================================================================
    public void addArtifact(Artifact artifact) {
        if (artifact != null && artifact.getId() != null) {
            artifactST.put(artifact.getId(), artifact);
        }
    }

    public Artifact getArtifact(String id) {
        return artifactST.get(id);
    }

    public void removeArtifact(String id) {
        if (artifactST.contains(id)) {
            artifactST.delete(id);
            System.out.println("[CONSISTENCY R4] Artefacto con ID " + id + " eliminado de la ST y archivado.");
        }
    }

    public Iterable<String> getAllArtifactIds() {
        return artifactST.keys();
    }

    /** R3.c: Búsqueda polimórfica por valor científico mínimo y peso máximo. */
    public List<Artifact> searchArtifactsByValueAndWeight(double minScientificValue, double maxWeight) {
        List<Artifact> result = new ArrayList<>();
        for (String id : artifactST.keys()) {
            Artifact a = artifactST.get(id);
            if (a.getScientificValue() >= minScientificValue && a.getWeight() <= maxWeight) {
                result.add(a);
            }
        }
        return result;
    }

    /** R3.d: Buscar artefactos por subcadena en su descripción. */
    public List<Artifact> searchArtifactsByDescriptionSubstring(String subDescription) {
        List<Artifact> result = new ArrayList<>();
        String query = subDescription.toLowerCase();
        for (String id : artifactST.keys()) {
            Artifact a = artifactST.get(id);
            if (a.getDescription().toLowerCase().contains(query)) {
                result.add(a);
            }
        }
        return result;
    }

    // =========================================================================
    // GESTIÓN DE SALAS EN RED-BLACK BST (Requisito R3.e, R3.f, R4)
    // =========================================================================
    public void addRoom(Room room) {
        if (room != null && room.getCode() != null) {
            roomBST.put(room.getCode(), room);
        }
    }

    public Room getRoom(String code) {
        return roomBST.get(code);
    }

    /**
     * Requisito R4 (Consistencia estricta):
     * Al eliminar una sala, se eliminan en cascada o se desvinculan las estaciones
     * de mantenimiento asociadas a dicha sala para mantener la integridad referencial.
     */
    public void removeRoom(String code) {
        if (roomBST.contains(code)) {
            roomBST.delete(code);
            System.out.println("[CONSISTENCY R4] Sala " + code + " eliminada del Red-Black BST.");

            // Limpieza en cascada de estaciones asociadas a esta sala
            List<String> stationsToRemove = new ArrayList<>();
            for (String stationId : stationST.keys()) {
                MaintenanceStation st = stationST.get(stationId);
                if (st.getLocationRoomCode().equalsIgnoreCase(code)) {
                    stationsToRemove.add(stationId);
                }
            }
            for (String sId : stationsToRemove) {
                stationST.delete(sId);
                System.out.println("[CONSISTENCY R4] Estación dependiente " + sId + " eliminada automáticamente por borrado de sala.");
            }
        }
    }

    public Iterable<String> getAllRoomCodes() {
        return roomBST.keys();
    }

    /** R3.e: Buscar salas por nivel de seguridad mínimo en la Red-Black BST. */
    public List<Room> searchRoomsByMinSecurity(int minSecurity) {
        List<Room> result = new ArrayList<>();
        for (String code : roomBST.keys()) {
            Room room = roomBST.get(code);
            if (room.getSecurityLevel() >= minSecurity) {
                result.add(room);
            }
        }
        return result;
    }

    /** R3.f: Buscar salas por subcadena en su código o tipo de zona. */
    public List<Room> searchRoomsBySubstring(String subQuery) {
        List<Room> result = new ArrayList<>();
        String query = subQuery.toLowerCase();
        for (String code : roomBST.keys()) {
            Room room = roomBST.get(code);
            if (room.getCode().toLowerCase().contains(query) || room.getZoneType().toLowerCase().contains(query)) {
                result.add(room);
            }
        }
        return result;
    }

    // =========================================================================
    // GESTIÓN DE ESTACIONES DE MANTENIMIENTO
    // =========================================================================
    public void addStation(MaintenanceStation station) {
        if (station != null && station.getStationId() != null) {
            stationST.put(station.getStationId(), station);
        }
    }

    public MaintenanceStation getStation(String id) {
        return stationST.get(id);
    }

    public Iterable<String> getAllStationIds() {
        return stationST.keys();
    }
}