/**
 * The Region class represents a geographical location with a name, population, and cost.
 * It provides methods to retrieve the population and cost of the location,
 * as well as a method to generate a string representation of the object.
 */
public class Region {
    private String name;
    private int population;
    private double baseCost;

    /**
     * Creates a new Region object with the given name, population, and cost.
     * @param name the name of the location
     * @param pop the population of the location
     * @param baseCost the base cost of the location
     */
    public Region(String name, int pop, double baseCost) {
        this.name = name;
        this.population = pop;
        this.baseCost = baseCost;
    }

    /**
     * Returns the population of the location
     * @return the population of the location
     */
    public int getPopulation() { return this.population; }

    /**
     * Returns the cost of the location
     * @param index a number indicating when this region is provided relief. A larger value for
     *              index indicates that this region is helped later.
     * @return the cost of providing relief to this region. Regions that are
     *         helped later (i.e. with a larger index value) have a higher cost.
     */
    public double getCost(int index) {
        return (1 + 0.1 * index) * this.baseCost;
    }


    /**
     * Returns a String representation of a Region object in the format:
     * "<name>: pop. <population>, cost: $<cost>"
     * @return the String representation of a Region object
     */
    public String toString() {
        return name + ": pop. " + population + ", base cost: $" + baseCost;
    }

    /**
     * Compares the specified object with this location for equality. Returns true if the
     * specified object is also a location and the two locations have the
     * same name, population, and cost.
     * @param other object to be compared for equality with this location
     * @return true if the specified object is equal to this location
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Region)) {
            return false;
        }
        Region otherLoc = (Region)other;

        return this.name.equals(otherLoc.name) &&
                this.population == otherLoc.population &&
                this.baseCost == otherLoc.baseCost;
    }

    /**
     * Returns the hash code value for this location
     * @return the hash code value for this location
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(population);
        result = 31 * result + Double.hashCode(baseCost);
        return result;
    }
}


