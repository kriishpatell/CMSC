/*******************************************
* Martian Money
********************************************
* Converting Earth money to Martian money
* Krish Patel
* 9/15/2021
* CMSC 255 Section 004
********************************************/
import java.util.Scanner;

public class MartianMoney 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int amount = 1;

        //Receieve amount
        System.out.println("Would you like to randomly enter a dollar amount (1) or enter it yourself (2).");
        int decision = input.nextInt();

        //Determine decision from user input and proceed accordingly
        if (decision == 1)
        {
            amount = (int)(Math.random()* (10000000 - 80) + 80);
            System.out.println(amount);
        }
        
        if (decision == 2)
        {
            System.out.println("Enter a dollar amount between $80 and $10,000,000.");
            amount = input.nextInt();
            if (amount < 80 || amount > 10000000)
            {
                System.out.println("Incorrect input");
            }
        }
        
        //Converting amount in dollars to Martian money
        int tintina = amount / 80;
        int remainingTintina = amount % 80;

        int sutton = remainingTintina / 16;
        int remainingSutton = remainingTintina % 16;

        int knorr = remainingSutton / 8;
        int remainingKnorr = remainingSutton % 8;

        int wernicke = remainingKnorr;

        //Displaying the converted values to the user
        if (amount < 80 || amount > 10000000)
            {
                System.out.println("Incorrect input");
            }
        else
        {
            System.out.print(amount + " is ");
        }
        
        //Checking if the value of the converted value is zero to determine whether or not it should be displayed
        if (tintina > 0)
        {
            System.out.print(tintina + " tintina ");
        } 
        if (sutton > 0)
        {
            System.out.print(sutton + " sutton ");
        }
        if (knorr > 0)
        {
            System.out.print(knorr + " knorr ");
        }
        if (wernicke > 0)
        {
            System.out.print(wernicke + " wernicke");
        }
    }
}
