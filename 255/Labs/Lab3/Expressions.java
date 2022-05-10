
public class Expressions 
{
  public static void main(String[] args)
  {
    int a = 3;
    int b = 4;
    int c = 5;
    int d = 17;
    
    /**********************************************************
     * 3 and 4 are added with sum 7       
     * 7 is divided by 5 with 1 the result of integer division
     * The value displayed is 1
     ***********************************************************/
    System.out.println("#1 " + ((a + b) / c));   
    
    /**********************************************************
     * Because division has higher precedence, 4 is divided by 5 with 0 the result of integer division       
     * 3 is added to 0 with sum 3
     *  The value displayed is 3
     **********************************************************/
    System.out.println("#2 " + (a + b / c));  

    /**********************************************************
     * Double ++ signs indicate a preincrement, meaning it will raise the value of the variable by 1
     * The new value is used in the statement
     * The value displayed is 4
     * ********************************************************/
    System.out.println("#3 " + (++a)); 

    /**********************************************************
     * Double -- signs indicate a predecrement which decreases the value of the variable by 1
     * However, this new value would not be displayed because it displays the new value
     * The value displayed is 3
     * ********************************************************/
    System.out.println("#4 " + (--a));

    /**********************************************************
     * Double ++ signs indiciate a postincrement, increasing the variable by 1
     * Originial value of the variable is used in the statement
     * The value displayed is 3
     * ********************************************************/
    System.out.println("#5 " + (a++)); 

    /**********************************************************
     * Double -- signs indicate a postdecrement which decreases the variable by 1
     * Uses the original value in the statement
     * The value displayed is 4
     * ********************************************************/
    System.out.println("#6 " + (a--));

    /**********************************************************
     * Adds 1 to the assigned variable giving 4
     * The value displayed is 4
     * ********************************************************/
    System.out.println("#7 " + (a + 1)); 

    /**********************************************************
     * % sign gives us the remainder of the how much remains after dividing d by c
     * Thus dividing 17 by 5 gives us 3 remainder 2
     * The value displayed is 2
     * ********************************************************/
    System.out.println("#8 " + (d % c)); 

    /**********************************************************
     * Divides d by c, therefore the expression would equate to 17/5 = 3.4
     * This value is approximated because we are not using floating-point expressions
     * The value displayed 3
     * ********************************************************/
    System.out.println("#9 " + (d / c)); 

    /**********************************************************
     * Dividing 17 by 4 gives 4 remainder 1
     * % gives us the remainder
     * The value displayed is 1
     * ********************************************************/
    System.out.println("#10 " + (d % b));

    /**********************************************************
     * Dividing 17 by 4 gives 4.2
     * The displayed value is 4
     * ********************************************************/
    System.out.println("#11 " + (d / b)); 

    /**********************************************************
     * Since division occurs before addition and subtraction a(3)/d(17) = 0.17 -> 0
     * Therefore, the remaining expression gives 17 + 0 + 4 which = 21
     * The displayed value is 21
     * ********************************************************/
    System.out.println("#12 " + (d + a / d + b));

    /**********************************************************
     * Parentheses take precedence over division, so operations within them must be performed first
     * This would equate to (17 + 3) / (17 + 4) = 20 / 21 = 0.95 = 0 (Rounding up does not occur)
     * The displayed value is 0
     * ********************************************************/
    System.out.println("#13 " + ((d + a) / (d + b)));  

    /**********************************************************
     * This method returns the positive square root of the variable
     * Therefore it would be sqrt(4) which would = 2
     * The displayed output is 2.0 because this method always returns a floating point value with one decimal
     * ********************************************************/
    System.out.println("#14 " + (Math.sqrt(b)));

    /**********************************************************
     * This operation takes a to the power of b. This would equal 3^4 = 81
     * The displayed value is 81.0
     * ********************************************************/
    System.out.println("#15 " + (Math.pow(a, b)));  

    /**********************************************************
     * This operation returns the absolute value of the variable
     * Since absolute value functions cannot be negative, it returns its equal positive value (-3 -> 3)
     * The displayed value is 3
     * ********************************************************/
    System.out.println("#16 " + (Math.abs(-a)));

    /**********************************************************
     * This operation returns the largest value within the set provided
     * Since this set is provided with 3 and 4, it returns a 4 because it is the larger of the two
     * The displayed value is 4
     * ********************************************************/
    System.out.println("#17 " + (Math.max(a, b)));    
  } 
} 
