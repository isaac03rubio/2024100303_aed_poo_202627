package models.artifacts;

/**
 * Representa un cristal energético recuperable, subtipo de Artifact.
 */
public class EnergyCrystal extends Artifact {
    private double energyCapacity; // Capacidad de energía almacenada
    private String purityGrade;    // Grado de pureza (e.g., "ALPHA", "BETA", "GAMMA")

    /**
     * Constructor para el cristal energético.
     *
     * @param id              Identificador único
     * @param description     Descripción
     * @param weight          Peso
     * @param scientificValue Valor científico
     * @param hazardImpact    Nivel de peligro
     * @param energyCapacity  Capacidad energética
     * @param purityGrade     Grado de pureza
     */
    public EnergyCrystal(String id, String description, double weight, double scientificValue,
                         double hazardImpact, double energyCapacity, String purityGrade) {
        super(id, description, weight, scientificValue, hazardImpact);
        this.energyCapacity = energyCapacity;
        this.purityGrade = purityGrade;
    }

    public double getEnergyCapacity() {
        return energyCapacity;
    }

    public void setEnergyCapacity(double energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public String getPurityGrade() {
        return purityGrade;
    }

    public void setPurityGrade(String purityGrade) {
        this.purityGrade = purityGrade;
    }

    @Override
    public String toString() {
        return "EnergyCrystal [" + super.toString() + ", EnergyCap=" + energyCapacity
                + "kWh, Purity=" + purityGrade + "]";
    }
}