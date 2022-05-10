package Labs.Lab10;

public class Fan {
    /*********************************************************
     * Instance variables for the class are initialized below
     *********************************************************/
    public static final int SLOW = 1,
                            MEDIUM = 2,
                            FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    /************************************************************************************************************
     * This is the default constructor for the class, given the variables 1, false, 5, and "blue" correspondingly
     ************************************************************************************************************/
    public Fan(){
        this(SLOW, false, 5, "blue");
    }

    /***********************************************************************************************
     * This constructor assigns the given variable values to the instance variable created before
     ***********************************************************************************************/
    public Fan(int speed, boolean on, double radius, String color){
        this.speed = speed;
        this.on = on;
        this.radius = radius;
        this.color = color;
    }

    /**********************************************************
     * This method returns the int value of the speed variable
     **********************************************************/
    public int getSpeed(){
        return speed;
    }

    /******************************************************************************
     * This method sets the instance variable of speed with the variable provided
     ******************************************************************************/
    public void setSpeed(int speed){
        this.speed = speed;
    }

    /************************************************************************
     * This method returns a boolean value corresponding to the fan's status
     ************************************************************************/
    public boolean isOn(){
        return on;
    }

    /*******************************************************************
     * This method sets the instance variable of on to the one provided
     *******************************************************************/
    public void setOn(boolean on){
        this.on = on;
    }

    /**************************************************
     * This method returns the double value of radius
     **************************************************/
    public double getRadius(){
        return radius;
    }

    /***********************************************************************
     * This method sets the instance variable of radius to the one provided
     ***********************************************************************/
    public void setRadius(double radius){
        this.radius = radius;
    }

    /**************************************************
     * This method returns the String value of color
     **************************************************/
    public String getColor() {
        return color;
    }

    /**********************************************************************
     * This method sets the instance variable of color to the one provided
     **********************************************************************/
    public void setColor(String color){
        this.color = color;
    }

    /**********************************************************************************************************
     * This method returns the description of the fan based on the status of the fan (whether it is on or off)
     **********************************************************************************************************/
    public String toString(){
        if(isOn())
        {
            return "fan is " + this.speed + ", " + this.color + ", and size " + this.radius;
        }
        else
        {
            return "fan is off";
        }
    }
}
