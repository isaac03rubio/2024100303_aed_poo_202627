package models.rooms;

/**
 * Representa una sala o zona dentro del laberinto laberíntico.
 * Cumple con los requisitos de la Fase 1.
 */
public class Room {
    private String code;          // Código identificador único de la sala
    private String zoneType;      // Tipo de zona (e.g., "RESEARCH", "HAZARD", "STORAGE")
    private int securityLevel;    // Nivel de seguridad (e.g., 1 a 5)
    private double hazardIndex;   // Índice de peligro ambiental o radiación
    private String detectionDate; // Fecha de detección o registro

    /**
     * Constructor para la sala del laberinto.
     *
     * @param code          Código único de la sala
     * @param zoneType      Tipo de zona
     * @param securityLevel Nivel de seguridad
     * @param hazardIndex   Índice de peligro ambiental
     * @param detectionDate Fecha de detección
     */
    public Room(String code, String zoneType, int securityLevel, double hazardIndex, String detectionDate) {
        this.code = code;
        this.zoneType = zoneType;
        this.securityLevel = securityLevel;
        this.hazardIndex = hazardIndex;
        this.detectionDate = detectionDate;
    }

    public String getCode() {
        return code;
    }

    public String getZoneType() {
        return zoneType;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public double getHazardIndex() {
        return hazardIndex;
    }

    public String getDetectionDate() {
        return detectionDate;
    }

    @Override
    public String toString() {
        return "Room [Code=" + code + ", Type=" + zoneType + ", Security=" + securityLevel
                + ", HazardIndex=" + hazardIndex + ", Date=" + detectionDate + "]";
    }
}