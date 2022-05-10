import java.util.Scanner;

public class PrintStrings 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your first word");
        String strg1 = input.next();
        System.out.println("Enter your second word");
        String strg2 = input.next();

        if(strg1.compareTo(strg2) < 0)
        {
            System.out.println(strg1 + " " + strg2);
        }
        else
        {
            System.out.println(strg2 + " " + strg1);
        }
    }
}
