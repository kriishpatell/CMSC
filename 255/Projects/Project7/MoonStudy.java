/* ***************************************************************
 * MoonStudy.java
 * ***************************************************************
 * Reading and analyzing a text file with data and manipulating
 * the variables to find the data of different Moons
 * ***************************************************************
 * Krish Patel
 * 12/01/2021
 * CMSC 255 Section 004
 * ***************************************************************/
package Labs.Project7;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MoonStudy
{
    /**
     * Main method which initializes and prompts user for files
     * that are to be read by the program
     */
    public static void main(String[] args) throws Exception
    {
        String inputFileName, outputFileName;
        Scanner input = new Scanner(System.in);
        PrintWriter printer = new PrintWriter(System.out);

        if(args.length > 0)
        {
            inputFileName = args[0];
            outputFileName = args[1];
        }
        else
        {
            inputFileName = input.nextLine();
            outputFileName = input.nextLine();
        }
        File inputFile = new File(inputFileName.trim()).getAbsoluteFile();
        File outputFile = new File(outputFileName.trim()).getAbsoluteFile();

        /*
          Inputting and returning ArrayLists of the data within the files
         */
        ArrayList<String> moonString = openFile(inputFile);
        ArrayList<Moon> moons = createObjects(moonString);

        /*
          Defining the attribute variables
         */
        String radius = "radius";
        String density = "density";

        /*
          Methods for finding that data for each moon and sending to outputFile
         */

        PrintWriter writer = null;
        writer = new PrintWriter(outputFile);

        outputToFile("The mean of radii: ", findMean(moons, radius), writer);
        outputToFile("The highest density value is: ", findHighValue(moons, density), writer);
        outputToFile("The moon closest to the mean is: ", findMeanMoon(moons, density, findMean(moons, density)), writer);
        outputToFile("The moons below the mean value for radii are: ", findLowestMoons(moons, 50, radius), writer);
    }

    /**
     * Stores the data in the data file into an ArrayList per
     * line. Catches exception if file is not found.
     */
    public static ArrayList<String> openFile(File inputFile) throws FileNotFoundException
    {
        ArrayList<String> lines = new ArrayList<String>();
        Scanner input = new Scanner(inputFile);
        try {
            while(input.hasNext())
            {
                lines.add(input.nextLine().trim());
            }
        } catch(Exception ex) {
            System.out.println("Incorrect input filename");
        }
        System.out.println("Input file correct");
        return lines;
    }

    /**
     * Converts elements within the ArrayList created in openFile()
     * into a Moon ArrayList, which stores parsed double values
     * from each line based on its location in the ArrayList
     */
    public static ArrayList<Moon> createObjects(ArrayList<String> lines)
    {
        ArrayList<Moon> moonData = new ArrayList<>();
        double radius, density, distance = 0;
        String[] tokens;
        String name;
        for(int i = 0; i < lines.size(); i++)
        {
            tokens = lines.get(i).split("\t");
            name = tokens[0];

            try {
                radius = Double.parseDouble(tokens[1]);
                if(radius < 0)
                {
                    radius = 0.0;
                }
            } catch (NumberFormatException ex) {
                radius = 0.0;
            }

            try {
                density = Double.parseDouble(tokens[2]);
                if(density < 0)
                {
                    density = 0.0;
                }
            } catch (NumberFormatException ex) {
                density = 0.0;
            }

            try {
                distance = Double.parseDouble(tokens[3]);
                if(distance < 0)
                {
                    distance = 0.0;
                }
            } catch (NumberFormatException ex) {
                distance = 0.0;
            }

            Moon moon = new Moon(name,radius,density,distance);
            moonData.add(moon);
        }
        return moonData;
    }

    /**
     * Adds numerical value to meanSum variable for each line, given
     * the type of moon attribute, and then is divided by the size
     * of the ArrayList moons size to find the mean value of that attribute
     */
    public static double findMean(ArrayList<Moon> moons, String attribute)
    {
        double meanSum = 0, mean;
        if(attribute.equalsIgnoreCase("radius"))
        {
            for(int i = 0; i < moons.size(); i++)
            {
                meanSum += moons.get(i).getRadius();
            }
        }
        else if (attribute.equalsIgnoreCase("density"))
        {
            for(int i = 0; i < moons.size(); i++)
            {
                meanSum += moons.get(i).getDensity();
            }
        }
        else if (attribute.equalsIgnoreCase(("distance")))
        {
            for(int i = 0; i < moons.size(); i++)
            {
                meanSum += moons.get(i).getDistance();
            }
        }
        mean = meanSum / moons.size();
        return mean;
    }

    /**
     * Value for maxValue is looped and replaced with moons value that
     * it is being compared with, if it is larger than that of maxValue
     * which is then returned
     */
    public static double findHighValue(ArrayList<Moon> moons, String attribute)
    {
        double maxValue = 0;
        for(int i = 0; i < moons.size(); i++)
        {
            if(attribute.equalsIgnoreCase("Radius"))
            {
                if(moons.get(i).getRadius() > maxValue)
                {
                    maxValue = moons.get(i).getRadius();
                }
            }
            else if (attribute.equalsIgnoreCase("Density"))
            {
                if(moons.get(i).getDensity() > maxValue)
                {
                    maxValue = moons.get(i).getDensity();
                }
            }
            else if (attribute.equalsIgnoreCase("Distance"))
            {
               if(moons.get(i).getDistance() > maxValue)
               {
                   maxValue = moons.get(i).getDistance();
               }
            }
        }
        return maxValue;
    }

    /**
     * Takes the mean value from the findMean method and compares it
     * to the corresponding value of each moon, to determine which moon
     * is the closest to the mean value of that attribute
     */
    public static Moon findMeanMoon(ArrayList<Moon> moons, String attribute, double meanValue)
    {
        double closestMoonValue = 1000000;
        double distance;
        Moon moon = null;
        for (int i = 0; i < moons.size(); i++)
        {
            if(attribute.equalsIgnoreCase("Radius"))
            {
                distance = Math.abs(moons.get(i).getRadius() - meanValue);
                {
                    if (distance < closestMoonValue)
                    {
                        closestMoonValue = distance;
                        moon = moons.get(i);
                    }
                }
            }
            else if (attribute.equalsIgnoreCase("Density"))
            {
                distance = Math.abs(moons.get(i).getDensity() - meanValue);
                {
                    if (distance < closestMoonValue)
                    {
                        closestMoonValue = distance;
                        moon = moons.get(i);
                    }
                }
            }
            else if (attribute.equalsIgnoreCase("Distance"))
            {
                distance = Math.abs(moons.get(i).getDistance() - meanValue);
                {
                    if (distance < closestMoonValue)
                    {
                        closestMoonValue = distance;
                        moon = moons.get(i);
                    }
                }
            }
        }
        return moon;
    }

    /**
     * Finds moons that are lower than a given value by the user by
     * using a for loop to check each value within the ArrayList and
     * check whether it was larger than that of the value provided
     */
    public static ArrayList<Moon> findLowestMoons(ArrayList<Moon> moons, double value, String attribute)
    {
        ArrayList<Moon> lowestMoons = new ArrayList<>();

        for(int i = 0; i < moons.size(); i++)
        {
            if(attribute.equalsIgnoreCase("radius"))
            {
                if(moons.get(i).getRadius() < value)
                {
                    lowestMoons.add(moons.get(i));
                }
            }
            else if (attribute.equalsIgnoreCase("density"))
            {
                if(moons.get(i).getDensity() < value)
                {
                    lowestMoons.add(moons.get(i));
                }
            }
            else if (attribute.equalsIgnoreCase("distance"))
            {
                if(moons.get(i).getDistance() < value)
                {
                    lowestMoons.add(moons.get(i));
                }
            }
        }
        return lowestMoons;
    }

    /**
     * Outputs the outputMessage provided by the user, as well
     * as the moons ArrayList that is called to through the program (method)
     */
    public static void outputToFile(String outputMessage, ArrayList<Moon> moons, PrintWriter out) throws FileNotFoundException
    {
        try{
            out.print(outputMessage);
            for(int i = 0; i < moons.size(); i++)
            {
                out.print(moons.get(i).toString().replaceAll("\\s", " ") + " ");
            }
            out.close();
        } catch (Exception ex) {
            System.out.println("Incorrect output filename");
        }
    }

    /**
     * Outputs the outputMessage provided by the user, as well
     * as the moon that is called through the program (method)
     */
    public static void outputToFile(String outputMessage, Moon moon, PrintWriter out) throws FileNotFoundException
    {
        try {
            String moonString = moon.toString().replaceAll("\\s", " ");
            out.println(outputMessage + moonString + "\n");

            out.close();
        } catch (Exception ex) {
            System.out.println("Incorrect output filename");
        }
    }

    /**
     * Outputs the outputMessage provided by the user, as well
     * as the double value that corresponds with which method is called
     */
    public static void outputToFile(String outputMessage, double value, PrintWriter out) throws FileNotFoundException
    {
        try {
            out.println(outputMessage + String.format("%.2f\n", value));
            out.close();
        } catch (Exception ex) {
            System.out.println("Incorrect output filename");
        }
    }
}
