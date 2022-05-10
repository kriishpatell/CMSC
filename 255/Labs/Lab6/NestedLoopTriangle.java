public class NestedLoopTriangle
{
    public static void main(String[] args)
    {
       Pyramid1();
       System.out.println(); 
       Pyramid2();
    }

    public static void Pyramid1()
    {
        int row = 10;

        for (int i = 1; i < row; i++)
        {
            System.out.print("*");
            for (int j = 1; j < i; j++)
            {
                System.out.print("**");
            }
            System.out.println();   
        }
    } 

    public static void Pyramid2()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10 - i; j++)
            {
                System.out.print(" ");
            }
            for (int k = 0; k <= i ; k++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
