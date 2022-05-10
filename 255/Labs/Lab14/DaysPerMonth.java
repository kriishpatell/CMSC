package Labs.Lab14;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
/**
 *   DaysPerMonth
 *   VCU - Computer Science Department
 *   A program that prompts the user for a month and year (both as integers)
 *   then displays the number of days in that month.
 **/

public class DaysPerMonth {

    public static void main(String[] args){
        if (args.length == 0)
        {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter an input file");
            String filename = input.nextLine();
            File inputFile = new File(filename);

            File outputFile = new File("lab14Output.txt");
            processFile(inputFile, outputFile);
        }
        else
        {
            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);
            processFile(inputFile, outputFile);
        }
    }


    public static void processFile(File inputFile, File outputFile){
        try
        {
            PrintWriter output= new PrintWriter(outputFile);
            Scanner input = new Scanner(inputFile);
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] split = line.split(",");
                try
                {
                    int month = Integer.parseInt(split[0]);
                    int year = Integer.parseInt(split[1]);
                    if (month < 1 || month > 12)
                    {
                        output.println("Month must be between 1 and 12");
                    }
                    else if (year < 0)
                    {
                        output.println("Year cannot be negative");
                    }
                    else
                    {
                        int numDays= getDays(month,year);
                        output.println("There are "+ numDays + " days in this month.");
                    }
                }
                catch (NumberFormatException ex)
                {
                    output.println("Not an integer");
                }
            }
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Bad File Name");
        }

    }

    private static int getDays(int mon, int yr) {
        int numDays = 0;

        switch(mon) {
            case 2: // February
                numDays = 28;
                if (yr % 4 == 0) {
                    numDays = 29;
                    if (yr % 100 == 0 && yr % 400 != 0) {
                        numDays = 28;
                    }
                }
                break;
            case 4:   //April
            case 6:   // June
            case 9:   // September
            case 11:  // November
                numDays = 30;
                break;

            default: numDays = 31;  // all the rest
        }
        return numDays;
    }
}