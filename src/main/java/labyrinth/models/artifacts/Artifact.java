package models.artifacts;

/**
 * Clase base abstracta que representa un artefacto recuperable en el laberinto.
 * Cumple con los requisitos de polimorfismo de la Fase 1.
 */
public abstract class Artifact {
    protected String id;
    protected String description;
    protected double weight;          // Peso del artefacto
    protected double scientificValue; // Valor científico
    protected double hazardImpact;    // Impacto operacional o nivel de peligro

    /**
     * Constructor para inicializar el artefacto.
     *
     * @param id              Identificador único del artefacto
     * @param description     Descripción del artefacto
     * @param weight          Peso
     * @param scientificValue Valor científico
     * @param hazardImpact    Nivel de peligro o impacto operacional
     */
    public Artifact(String id, String description, double weight, double scientificValue, double hazardImpact) {
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.scientificValue = scientificValue;
        this.hazardImpact = hazardImpact;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public double getScientificValue() {
        return scientificValue;
    }

    public double getHazardImpact() {
        return hazardImpact;
    }

    @Override
    public String toString() {
        return "Artifact [ID=" + id + ", Desc=" + description + ", Weight=" + weight
                + "kg, Value=" + scientificValue + ", Hazard=" + hazardImpact + "]";
    }
}