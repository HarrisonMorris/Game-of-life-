/**
 * Write a description of class GameFoundations here.
 *
 * @author (Harrison Morris)
 * @version (v6)
 */
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
public class GameFoundations
{

    Scanner input = new Scanner (System.in);
    // This scanner will allow me to take in the users input from the keyboard 
    boolean gameMode = false; 
    /**
     * Constructor for objects of class GameFoundations
     */
    public GameFoundations()
    {

        System.out.println("Good day to you sir/madam I'm here as your humble guide to this simple game also known as connways game of life. now to let us begin please do the following \n"
            +"please type run\n");
        int row = 10;
        int col = 12;
        boolean [][] grid = new boolean[row][col];
        for (int y = 0; y < grid[0].length; y++){
            for (int x = 0; x < grid.length; x++){
                grid [x][y] = false;  

            }
        }
        grid [row-1][col-1] = true;
        grid [row-2][col-2] = true;
        grid [row-1][col-2] = true;
        //grid.length and grid[0].length are the row and col variables of the grid array

        boolean gameRun = true;
        int trueCellNeighbours; 
        printArray(grid);

        Scanner UI = new Scanner(System.in);
        while (gameRun == true){

            switch (UI.nextLine()){
                case "run":

                System.out.println("Please type run to advance one generation");
                boolean [][] newGen = new boolean[grid.length][grid[0].length]; 

                for (int y = 0; y < grid[0].length; y++){
                    for (int x = 0; x < grid.length; x++){
                        //the y and x loops are for the cell to detect the surrounding cells y for the up and down and x for the side to side
                        newGen[x][y] = false;
                        trueCellNeighbours = 0;
                        for (int a = -1; a < 2; a ++)
                            for (int b = -1; b < 2; b ++){

                                if ( (y + b > -1 && x + a > -1 && y + b <= grid[0].length - 1 && x + a <= grid.length - 1 && (b != 0 || a != 0) )&&
                                (grid [x+a][y+b] == true) ) {
                                    newGen[x][y] = true;
                                    trueCellNeighbours++;
                                }
                                if (trueCellNeighbours > 3){
                                    newGen[x][y] = false;
                                }else if (trueCellNeighbours  < 2){
                                    newGen[x][y] = false;
                                }else if (trueCellNeighbours == 2 && newGen[x][y] == true){
                                    newGen[x][y] = true;

                                }else if(trueCellNeighbours == 3){
                                    newGen[x][y] = true; 

                                }else{
                                    newGen[x][y] = false;

                                }

                            }
                        if(newGen [x][y] == true){
                            trueCellNeighbours = trueCellNeighbours -1;

                        }   

                    }

                }

                grid = newGen; 
                printArray(newGen);
                break;
                case "quit":
                gameRun = false;
                break;
            }
        }

    }

    public void printArray (boolean [][] grid){
        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid[0].length; y++){
                if(grid [x][y] == true){
                    System.out.print("O" + ", ");
                }  else if(grid [x][y] == false){
                    System.out.print("X" + ", ");

                }
            }
            System.out.println();
        }
        System.out.println("");
    }

}
