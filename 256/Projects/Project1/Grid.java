/**************************
 * Krish Patel
 * 1/28/2022
 * CMSC 256 Section 901
 *************************/

package Projects.Project1;
import java.util.Arrays;

public class Grid {
    private char[][] grid = new char[3][3];
    
    public Grid(){ 
        for(char[] row : grid){
            Arrays.fill(row, '\u0000');
        }
    }

    public String getRow(int rowIndex) throws IllegalArgumentException{
        String row = ""; 
        if(rowIndex < 0 || rowIndex >= 3)
        {
            throw new IllegalArgumentException("Row index is invalid");
        } else {
            for(int i = 0; i < grid.length; i++)
            {
                if(grid[rowIndex][i] == '\u0000')
                {
                    grid[rowIndex][i] = ' ';
                }
            }
            row = " " + grid[rowIndex][0] + " | " + grid[rowIndex][1] + " | " + grid[rowIndex][2] + " ";
        }
        return row;
    }

    public void setPosition(char value, int rowIndex, int columnIndex) throws IllegalArgumentException{
        if(grid[rowIndex][columnIndex] != '\u0000')
        {
            throw new IllegalArgumentException("Invalid character was provided");
        } 
        else if(rowIndex >= 3 || columnIndex >= 3 || rowIndex < 0 || columnIndex < 0)
        {
            throw new ArrayIndexOutOfBoundsException("");
        } else{
            grid[rowIndex][columnIndex] = value;
        }
    }


    public boolean checkInput(char inputValue) throws IllegalArgumentException{
        if(inputValue == 'X' || inputValue == 'x' || inputValue == 'O' || inputValue == 'o'){
            return true;
        } else {
            throw new IllegalArgumentException("Character is not X or O"); 
        }
    }

    public boolean isFull(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == '\u0000'){
                    return false;
                }
            }
        }
        return true;
    }           

    public boolean isRowMatching(int rowIndex) throws IllegalArgumentException{
        if (rowIndex >= 3 || rowIndex < 0) {    
            throw new IllegalArgumentException("Invalid row index was given");
        }
        char temp = grid[rowIndex][0];
        return temp == grid[rowIndex][1] && temp == grid[rowIndex][2];
    }

    public boolean isColumnMatching(int columnIndex) throws IllegalArgumentException{
        if(columnIndex >= 3 || columnIndex < 0){
            throw new IllegalArgumentException("Invalid column index was given");
        }
        char temp = grid[0][columnIndex];
        return temp == grid[1][columnIndex] && temp == grid[2][columnIndex];
    }

    public boolean hasDiagonalMatch(){
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
        {
            return true;
        } else if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
            return true;
        } else {
            return false;
        }
    }

    public char checkForWinner(){
        if (isRowMatching(0) || hasDiagonalMatch()){
            return grid[0][0];
        }    
        if (isRowMatching(1)){
            return grid[1][0];
        } 
        if (isRowMatching(2)){
            return grid[2][0];
        }   
        if (isColumnMatching(0)){
            return grid[0][0];
        }    
        if (isColumnMatching(1)){
            return grid[1][1];
        }            
        if (isColumnMatching(2)){
            return grid[2][2];
        }
        return '\u0000';
    }

    public String toString() {
        String temp = "";
        temp = getRow(0) + "\n" + "---------\n" + getRow(1) + "\n" + "---------\n" + getRow(2) + "\n";
        return temp;
    }
}
