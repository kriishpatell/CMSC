/************************************************************************************
 * Moon Search
 * **********************************************************************************
 * Determining and manipulating certain values for an array that is given and passed
 * Krish Patel
 * 10/16/2021
 * CMSC 255 Section 004
 ************************************************************************************/
package Labs.Project4;

import java.util.Scanner;
import java.util.Arrays;

public class MoonSearch
{
    public static void main(String[] args)
    {
        Scanner scr = new Scanner(System.in);

        String[] names = {"Phobus", "Deimos", "Adrastea", "Aitne", "Amalthea", "Ananke", "Aoede", "Arche"};

        double[] radii = new double[]{11.3, 6.2, 68.9, 33.6, 71.2, 26.8, 255.9, 47.4};
        double[] density = new double[]{1.8, 1.4, 14.2, 33.3, 16.4, 68.1, 121.3, 38.2};
        double[] distance = new double[]{3.7, 23.4, 550391.6, 227894.9, 778893.6, 143323.5, 287223.5, 449655.1};

        System.out.printf("The average radius is: %.1f", calcAvg(radii));
        System.out.println();

        System.out.printf("The average density is: %.1f", calcAvg(density));
        System.out.println();

        System.out.printf("The highest radius is: %.1f", findHighValue(radii));
        System.out.println();

        System.out.printf("The lowest distance is: %.1f", findLeastValue(distance));
        System.out.println();

        findHighestTwo(names, radii);
        findLeastTwo(names, radii);

        System.out.println("Enter a moon:");
        String moon = scr.nextLine().trim();

        if (findMoon(names, moon) == true)
        {
            System.out.println(moon + " is a moon in the array");
        }
        else
        {
            System.out.println(moon + " is not a moon in the array");
        }
    }

    /****************************************************************
     * Calculate the average of the given array by using array length
     * to determine how many values are in the array, then dividing
     * them by the sum, which is calculate in a for loop based on
     * how many values are within the array
     ****************************************************************/
    public static double calcAvg(double[] values)
    {
        double length = values.length;
        double sum = 0;
        for (int i = 0; i < values.length; i++)
        {
            sum += values[i];
        }
        double average = sum / length;
        return average;
    }

    /****************************************************************
     * Find the highest value using array length to determine how
     * many values are in the array and then using a for loop,
     * converting values of assigned "max" if the value within the
     * array is larger than that of the previous "max"
     ****************************************************************/
    public static double findHighValue(double[] values)
    {
        double max = values[0];
        for(int i = 1; i < values.length; i++)
        {
            if (values[i] > max)
            {
                max = values[i];
            }
        }
        return max;
    }

    /****************************************************************
     * Find the lowest value using array length to determine how
     * many values are in the array and then using a for loop,
     * converting values of assigned "min" if the value within the
     * array is lower than that of the previous "min"
     ****************************************************************/
    public static double findLeastValue(double[] values)
    {
        double min = values[0];
        for(int i = 1; i < values.length; i++)
        {
            if(values[i] < min)
            {
                min = values[i];
            }
        }
        return min;
    }

    /****************************************************************
     * Find the largest value similarly to how the findHighValue
     * method, utilizing a for loop dependant on the array length.
     * Within if statements, determine if a value is larger than the
     * other, and assign it to the "largest" variable accordingly.
     * Given 'i' within the for loop, use it to assign the name to
     * the corresponding largest value.
     ****************************************************************/
    public static String[] findHighestTwo(String[] names, double[] values)
    {
        double largest = values[0];
        double secondLargest = values[1];
        String largestName = names[0];
        String secondLargestName = names[1];
        double temp;

        if (largest < secondLargest)
        {
            temp = largest;
            largest = secondLargest;
            secondLargest = temp;
        }

        for (int i = 2; i < values.length; i++)
        {
            if(values[i] > largest)
            {
                secondLargest = largest;
                secondLargestName = largestName;
                largest = values[i];
                largestName = names[i];
            }
            else if(values[i] > secondLargest && values[i] != largest)
            {
                secondLargest = values[i];
                secondLargestName = names[i];
            }
        }
        System.out.println("The highest two moons for radii are: ");
        System.out.println(largestName);
        System.out.println(secondLargestName);
        return names;
    }

    /****************************************************************
     * Find the lowest value similarly to how the findLowValue
     * method, utilizing a for loop dependant on the array length.
     * Within if statements, determine if a value is lower than the
     * other, and assign it to the "lowest" variable accordingly.
     * Given 'i' within the for loop, use it to assign the name to
     * the corresponding lowest value.
     ****************************************************************/
    public static String[] findLeastTwo(String[] names, double[] values)
    {
        double lowest = values[0];
        double secondLowest = values[1];
        String lowestName = names[0];
        String secondLowestName = names[1];
        double temp;

        if (lowest > secondLowest)
        {
            temp = lowest;
            lowest = secondLowest;
            secondLowest = temp;
        }

        for(int i = 2; i < values.length; i++)
        {
            if(values[i] < lowest)
            {
                secondLowest = lowest;
                secondLowestName = lowestName;
                lowest = values[i];
                lowestName = names[i];
            }
            else if (values[i] < secondLowest && values[i] != lowest)
            {
                secondLowest = values[i];
                secondLowestName = names[i];
            }
        }
        System.out.println("The lowest two moons for density are: ");
        System.out.println(secondLowestName);
        System.out.println(lowestName);
        return names;
    }

    /****************************************************************
     * Initialize a boolean variable, and pass in a string array, as
     * well as the inputted string from the user. Using .equals,
     * determine whether the value is within the array. If true,
     * return the true value. Else return false, and pass call back
     * to main method, where the printing is executed.
     ****************************************************************/
    public static boolean findMoon(String[] names, String moon)
    {
        boolean ifFound = false;
        for (String x : names)
        {
            if(x.equals(moon))
            {
                ifFound = true;
                return ifFound;
            }
            else
            {
                ifFound = false;
            }
        }
        return ifFound;
    }
}

