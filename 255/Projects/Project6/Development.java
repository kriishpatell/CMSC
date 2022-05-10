/* *****************************************************************
 * House Development Test
 * *****************************************************************
 * Development class defining the locations and lots for the houses
 * Krish Patel
 * 11/9/2021
 * CMSC 255 Section 004
 * *****************************************************************/

package Labs.Project6;

import java.util.ArrayList;

public class Development {
    /***********************************************
     * Instance variables for the development class
     ***********************************************/
    private String name;
    private String location;
    private int yearEst;
    private int numLots;
    private ArrayList<House> houses;

    /*************************************************************************
     * Default constructor assigning default values to the instance variables
     *************************************************************************/
    public Development() {
        name = null;
        location = null;
        yearEst = 0;
        numLots = 0;
        houses = new ArrayList<House>();
    }

    /********************************************************************************************************************
     * Parameterized constructor passing in variables from the main method and reassigning it to the instance variables
     ********************************************************************************************************************/
    public Development(String name, String location, int yearEst, int numLots) {
        this.name = name;
        this.location = location;
        this.yearEst = yearEst;
        this.numLots = numLots;
        houses = new ArrayList<House>();
    }

    /***************************************************************
     * Getter and setter methods for each of the instance variables
     ***************************************************************/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getYearEst() {
        return yearEst;
    }
    public void setYearEst(int yearEst) {
        this.yearEst = yearEst;
    }

    public int getNumLots() {
        return numLots;
    }
    public void setNumLots(int numLots) {
        this.numLots = numLots;
    }

    /***********************************************************************
     * addHouse method which adds a house parameter to the ArrayList houses
     ***********************************************************************/
    public void addHouse(House newHouse) {
        houses.add(newHouse);
    }

    /************************************************
     * Method that returns the size of the ArrayList
     ************************************************/
    public int getNumHouses()
    {
        return houses.size();
    }

    /****************************************************
     * Method that returns the elements of the ArrayList
     ****************************************************/
    public ArrayList<House> getHouses()
    {
        return this.houses;
    }

    /**************************************************************************
     * toString method that concatenates each of the variables, printing on a
     * new line as well as the houses that are assigned to each development
     **************************************************************************/
    public String toString()
    {
        String result = name + "\n" + location + "\n" + yearEst + "\n" + numLots + "\nHouses:\n";
        for (int i = 0; i < getNumHouses(); i++)
        {
            result = result + houses.get(i).toString();
        }
        return result;
    }
}
