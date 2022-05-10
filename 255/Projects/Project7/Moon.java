/* ***************************************
 * Moon.java
 * ***************************************
 * Moon class for MoonStudy.java program
 * ***************************************
 * Krish Patel
 * 12/01/2021
 * CMSC 255 Section 004
 * ***************************************/
package Labs.Project7;

public class Moon
{
    /**
     * Defining instance variables for the class Moon
     */
    private String name;
    private double radius;
    private double density;
    private double distance;

    /**
     * Default constructor which assigns default values
     * of null and 0.0 to the instance variables accordingly
     */
    public Moon()
    {
        name = null;
        radius = 0.0;
        density = 0.0;
        distance = 0.0;
    }

    /**
     * Parameterized constructor which takes in inputs
     * from the user and reassigns values of instance variables
     *
     * @param name name of moon
     * @param radius radius of moon
     * @param density density of moon
     * @param distance distance of moon
     */
    public Moon(String name, double radius, double density, double distance)
    {
        this.name = name;
        this.radius = radius;
        this.density = density;
        this.distance = distance;
    }

    /**
     * Getters and setters for each instance variable
     *
     * Get methods return the value of the instance variable
     * Set methods reassign the value of the instance variable with the parameter
     */
    public String getName()
    {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    public double getDensity() { return density; }
    public void setDensity(double density) { this.density = density; }
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    /**
     * Method returns all the instance variables as a
     * String, formatted to 2 decimal points accordingly
     *
     * @return all variables stored as a String
     */
    public String toString()
    {
        return name +  String.format("\t%.2f", radius) +  String.format("\t%.2f", density) +  String.format("\t%.2f", distance);
    }
}
