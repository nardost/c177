package capitals;

import java.text.DecimalFormat;

public class Capital {

    private final String name;
    private final long population;
    private final double squareMileage;

    public Capital(String name, long population, double squareMileage) {
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getSquareMileage() {
        return squareMileage;
    }

    @Override
    public String toString() {
        return String.format(
                "%s | Pop. %s | Area: %.2f sq mi",
                name,
                (new DecimalFormat("###,###,###,###")).format(population),
                squareMileage);
    }
}
