package Labs.Lab9;
import java.util.*;

public class Lab9
{
    public static void addTo10(int [][] array)
    {
        int row = 0;
        while (row < array.length)
        {
            int rowSum = 0;
            for(int col = 0; col < array[row].length; col++)
            {
                rowSum = rowSum + array[row][col];
            }

            int col = 0;
            while(array[row][col] != 0)
            {
                col++;
            }

            array[row][col] = 10 - rowSum;
            row++;
        }

        for (int i = 0; i < array.length; i++)
        {
            for(int j = 0; array[i] != null && j < array[i].length; j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
     }
   
   public static void setHints(int [][] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                if(isBomb(array[i][j]))
                {
                    if(isInBounds(i-1, j-1, array) && !isBomb(array[i-1][j-1]))
                    {
                        array[i-1][j-1] += 1;
                    }
                    if(isInBounds(i-1, j, array) && !isBomb(array[i-1][j]))
                    {
                        array[i-1][j] += 1;
                    }
                    if(isInBounds(i-1, j+1, array) && !isBomb(array[i-1][j+1]))
                    {
                        array[i-1][j+1] += 1;
                    }
                    if(isInBounds(i, j-1, array) && !isBomb(array[i][j-1]))
                    {
                        array[i][j-1] += 1;
                    }
                    if(isInBounds(i, j ,array) && !isBomb(array[i][j]))
                    {
                        array[i][j] += 1;
                    }
                    if(isInBounds(i, j+1, array) && !isBomb(array[i][j+1]))
                    {
                        array[i][j+1] += 1;
                    }
                    if(isInBounds(i+1, j-1, array) && !isBomb(array[i+1][j-1]))
                    {
                        array[i+1][j-1] += 1;
                    }
                    if(isInBounds(i+1, j, array) && !isBomb(array[i+1][j]))
                    {
                        array[i+1][j] += 1;
                    }
                    if(isInBounds(i+1, j+1, array) && !isBomb(array[i+1][j+1]))
                    {
                        array[i+1][j+1] += 1;
                    }
                }
            }
        }
        for(int k = 0; k < array.length; k++)
        {
            for(int l = 0; l < array.length; l++)
            {
                System.out.print(array[k][l] + " ");
            }
            System.out.println();
        }
    }
   
    public static boolean isInBounds(int i, int j, int[][] board)
     {
         return (i >= 0 && i < board.length) && (j >= 0 && j < board[i].length);
     }

     public static boolean isBomb(int value)
     {
         return value == -1;
     }

     public static void main(String[] args) {
     int [][] matrix = {{6, 3, 2, 0, 4},
                        {34, 53, 0, 23, 1},
                        {0, 23, 54, 11, 7}};

     int [][] matrix2 = {{-1, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0},
                          {0, 0, 0, -1, 0, -1},
                          {0, 0, 0, -1, 0, 0},
                          {0, 0, 0, 0, 0, 0}};

     int [][] matrix3 = {{ 0, -1, 0, 0, 0, 0 },
                         { 0, 0, 0, 0, 0, 0 },
                         { -1, 0, 0, 0, 0, 0 },
                         { -1, 0, 0, 0, 0, 0 },
                         { 0, 0, 0, 0, 0, 0 },
                         { 0, 0, 0, 0, 0, 0 } };

     addTo10(matrix);
     setHints(matrix2);
     setHints(matrix3);
    }
 }




