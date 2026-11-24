package models.stations;

/**
 * Representa una estación de mantenimiento en el laberinto.
 * Cumple con la modelización de entidades de la Fase 1 (Requisito R1).
 */
public class MaintenanceStation {
    private String stationId;
    private String locationRoomCode;
    private double repairCapacity; // Velocidad o capacidad de reparación
    private boolean operational;

    public MaintenanceStation(String stationId, String locationRoomCode, double repairCapacity, boolean operational) {
        this.stationId = stationId;
        this.locationRoomCode = locationRoomCode;
        this.repairCapacity = repairCapacity;
        this.operational = operational;
    }

    public String getStationId() {
        return stationId;
    }

    public String getLocationRoomCode() {
        return locationRoomCode;
    }

    public double getRepairCapacity() {
        return repairCapacity;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    @Override
    public String toString() {
        return "MaintenanceStation [ID=" + stationId + ", Room=" + locationRoomCode
                + ", RepairCap=" + repairCapacity + ", Operational=" + operational + "]";
    }
}