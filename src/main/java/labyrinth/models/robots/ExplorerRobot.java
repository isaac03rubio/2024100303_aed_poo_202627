package models.robots;

/**
 * Representa un robot explorador especializado en el mapeo del laberinto.
 * Extiende la clase abstracta Robot.
 */
public class ExplorerRobot extends Robot {
    private int scannerRange; // Rango de escaneo en metros o celdas
    private boolean nightVisionActive;

    /**
     * Constructor para el robot explorador.
     *
     * @param id               Identificador único
     * @param name             Nombre del robot
     * @param batteryLevel     Nivel de batería
     * @param operationalState Estado operacional
     * @param systemVersion    Versión del sistema
     * @param scannerRange     Rango de escaneo del sensor
     * @param nightVisionActive Indica si la visión nocturna está activa
     */
    public ExplorerRobot(String id, String name, double batteryLevel, String operationalState,
                         String systemVersion, int scannerRange, boolean nightVisionActive) {
        super(id, name, batteryLevel, operationalState, systemVersion);
        this.scannerRange = scannerRange;
        this.nightVisionActive = nightVisionActive;
    }

    public int getScannerRange() {
        return scannerRange;
    }

    public void setScannerRange(int scannerRange) {
        this.scannerRange = scannerRange;
    }

    public boolean isNightVisionActive() {
        return nightVisionActive;
    }

    public void setNightVisionActive(boolean nightVisionActive) {
        this.nightVisionActive = nightVisionActive;
    }

    @Override
    public String toString() {
        return "Explorer" + super.toString() + ", Range=" + scannerRange
                + "m, NightVision=" + (nightVisionActive ? "ON" : "OFF") + "]";
    }
}