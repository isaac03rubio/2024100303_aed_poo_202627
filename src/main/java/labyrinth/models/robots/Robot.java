package models.robots;

/**
 * Clase base abstracta que representa a un robot autónomo en el laberinto.
 * Cumple con los requisitos de la Fase 1 del proyecto RoboLabyrinth.
 */
public abstract class Robot {
    protected String id;
    protected String name;
    protected double batteryLevel; // Estado de la batería (0.0 a 100.0)
    protected String operationalState; // e.g., "ACTIVE", "MAINTENANCE", "OFFLINE"
    protected String systemVersion;

    /**
     * Constructor para inicializar las propiedades básicas del robot.
     *
     * @param id               Identificador único del robot
     * @param name             Nombre o descripción del robot
     * @param batteryLevel     Nivel inicial de batería
     * @param operationalState Estado operacional actual
     * @param systemVersion    Versión del sistema operativo/software
     */
    public Robot(String id, String name, double batteryLevel, String operationalState, String systemVersion) {
        this.id = id;
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.operationalState = operationalState;
        this.systemVersion = systemVersion;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(String operationalState) {
        this.operationalState = operationalState;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    @Override
    public String toString() {
        return "Robot [ID=" + id + ", Name=" + name + ", Battery=" + batteryLevel
                + "%, State=" + operationalState + ", Version=" + systemVersion + "]";
    }
}