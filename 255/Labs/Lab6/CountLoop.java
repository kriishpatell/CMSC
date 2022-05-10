import java.util.Scanner;

public class CountLoop 
{
    public static void main(String[] args)
    {
        int sum1, sum2, sum3;
        sum1 = sum2 = sum3 = 0;
        int i, j;
        i = j =1;
        Scanner scr = new Scanner(System.in);

        System.out.println("Enter a value to calculate the summation of");
        int n = scr.nextInt();
        
        //while statement
        while(i <= n){
            sum1 += i;
            i++;
        }
        
        System.out.println();
        System.out.println("The sum is " + sum1);

        //do while statement
        do {
            sum2 += j;
            j++;
        } while (j <= n);
        
        System.out.println();
        System.out.println("The sum is " + sum2);
        
        //for statement
        for(int k = 0; k <= n; k++){
            sum3 += k;
        }
        
        System.out.println();
        System.out.println("The sum is " + sum3);
    }
}


