/* **********************************************************
 * House Development Test
 * **********************************************************
 * House class defining the construction of the buildings
 * Krish Patel
 * 11/9/2021
 * CMSC 255 Section 004
 * **********************************************************/

package Labs.Project6;

public class House
{
    /*****************************************
     * Instance variables for the house class
     *****************************************/
    private String purchaser;
    private int lotNumber;
    private double squareFootage;

    /*******************************************
     * Enumerated types for constant variables
     *******************************************/
    Bedrooms bedrooms;
    Baths baths;
    Colors colors;

    /*************************************************************************
     * Default constructor assigning default values to the instance variables
     *************************************************************************/
    public House()
    {
        purchaser = null;
        lotNumber = 1;
        squareFootage = 500.00;
        bedrooms = Bedrooms.ONE_BEDROOM;
        baths = Baths.ONE;
        colors = Colors.WHITE;
    }

    /********************************************************************************************************************
     * Parameterized constructor passing in variables from the main method and reassigning it to the instance variables
     ********************************************************************************************************************/
    public House(String purchaser, int lotNumber, double squareFootage, Bedrooms bedrooms, Baths baths, Colors colors)
    {
        this.purchaser = purchaser;
        this.lotNumber = lotNumber;
        this.squareFootage = squareFootage;
        this.bedrooms = bedrooms;
        this.baths = baths;
        this.colors = colors;
    }

    /***************************************************************
     * Getter and setter methods for each of the instance variables
     ***************************************************************/
    public String getPurchaser()
    {
        return purchaser;
    }
    public void setPurchaser(String purchaser)
    {
        this.purchaser = purchaser;
    }

    public int getLotNumber()
    {
        return lotNumber;
    }
    public void setLotNumber(int lotNumber)
    {
        this.lotNumber = lotNumber;
    }

    public double getSquareFootage()
    {
        return squareFootage;
    }
    public void setSquareFootage(double squareFootage)
    {
        this.squareFootage = squareFootage;
    }

    public Bedrooms getBedrooms()
    {
        return bedrooms;
    }
    public void setBedrooms(Bedrooms bedrooms)
    {
        this.bedrooms = bedrooms;
    }

    public Baths getBaths()
    {
        return baths;
    }
    public void setBaths(Baths baths)
    {
        this.baths = baths;
    }

    public Colors getColors()
    {
        return colors;
    }
    public void setColors(Colors colors)
    {
        this.colors = colors;
    }

    /************************************************************************************
     * toString method returning each of the instance variables, indented on a new line
     ************************************************************************************/
    public String toString(){
        String result =
                "\n" + "\t" + purchaser +
                "\n" + "\t" + lotNumber +
                "\n" + "\t" + String.format("%.2f",squareFootage) +
                "\n" + "\t" + bedrooms +
                "\n" + "\t" + baths +
                "\n" + "\t" + colors +
                "\n";
        return result;
    }
}
