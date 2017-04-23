package sample.model;

/**
 * Created by Alex Thien An Le on 2/28/2017.
 * Represents drinkable water conditions.
 */

public enum WaterCondition {

    Waste("Waste"),
    TreatableClear("Treatable-Clear"),
    TreatableMuddy("Treatable-Muddy"),
    Potable("Potable"),
    NA("NA");

    private String nameCondition;

    /**
     * Constructor
     * @param nameCondition name of the condition of water
     */
    WaterCondition(String nameCondition) {
        this.nameCondition = nameCondition;
    }

    /**
     * Getter
     * @return the name of the water condition
     */
    public String getNameCondition() {
        return nameCondition;
    }

    /**
     * Setter
     * @param nameCondition name of the condition of water
     */
    public void setNameCondition(String nameCondition) {
        this.nameCondition = nameCondition;
    }

    @Override
    public String toString() {
        return nameCondition;
    }
}
