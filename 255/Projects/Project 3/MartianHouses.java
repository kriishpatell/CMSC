/*****************************************************************************************
* Project 3 - Living on Mars
******************************************************************************************
* Determining the cost of living on Mars given an inputted value for length of a wall
* Krish Patel
* 9/29/2021
* CMSC 255 Section 004
******************************************************************************************/
import java.util.Scanner; 

public class MartianHouses 
{
    public static void main(String[] args)
    {
        Scanner scr = new Scanner(System.in); 

        do{
            /***************************************************************
            * Prompt the user to enter name and length of side of the house
            ****************************************************************/
            System.out.println("Enter the settlers name:");
            String name = scr.nextLine(); 
            System.out.println("Enter the length of a side of the house:");
            double length = scr.nextDouble(); 
    
            /******************************************************************
            * Define and initialize variables for areas to determine the total
            *******************************************************************/
            double areaFloor, areaOuter, areaTotal, costTotal;
    
            /********************************************************************
            * Calculate the values of the variables assigned using math operands
            *********************************************************************/ 
            areaFloor = 2 * Math.pow(length, 2) * (1 + Math.sqrt(2)); 
            areaOuter = 8 * 12 * length;
            areaTotal = (areaFloor * 2) + areaOuter;
    
            /*****************************************************************************
            * Calculate the base cost of the house by multiplying the total area by 14.50
            ******************************************************************************/
            costTotal = areaTotal * 14.50; 
    
            /*********************************************************************************************
            * Print name, areaTotal, and costTotal, rounding the two double variables to 2 decimal points
            **********************************************************************************************/
            System.out.printf(name + " has a house surface area of %,.2f", + areaTotal);
            System.out.printf(" and cost of $%,.2f", costTotal);
            
            /***************************************************************
            * Prompt user whether or not they would like to continue or not
            ****************************************************************/
            System.out.println();
            System.out.println("Would you like to enter another house? Enter no to exit.");

            scr.nextLine();  
        }while(!scr.nextLine().trim().equalsIgnoreCase("no")); 
    }    
}