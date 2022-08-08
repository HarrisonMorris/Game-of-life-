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
public class GameOfLife
{
    Scanner input = new Scanner (System.in);
    // This scanner will allow me to take in the users input from the keyboard 

    /**
     * Constructor for objects of class GameFoundations
     */
    public GameOfLife()
    {
        Scanner UI = new Scanner(System.in);
        int trueCellNeighbours; 
        boolean gameRun = true; //this tells the computer to loop the code within the while loop until said otherwise.    
        int row = 20;
        int col = 30;
        boolean [][] oldGen = new boolean[row][col]; //this is the grid that is first displayed on the screen but after that it is the previous version of each new grid
        System.out.println("If you want your starting board to be filled with random cells please type random.");
        System.out.println("If you want your starting board to be filled with dead cells so you can create your own board please type blank");
        //these two loops fill the grid array. the first one fills the array with to make ll of them false/dead cels this gives a blank slate for the secound loop to fill the array in a random order with true/alive cells    
        switch (UI.nextLine()){
            case "random":
                for (int printCol = 0; printCol < oldGen[0].length; printCol++){  //grid.length and grid[0].length are the row and col variables of the grid array
                    for (int printRow = 0; printRow < oldGen.length; printRow++){
                        oldGen [printRow][printCol] = false;  
                    }
                }
                for (int printCol = 0; printCol < oldGen[0].length; printCol++){ 
                    for (int printRow = 0; printRow < oldGen.length; printRow++){
                        //Math.random randomly generates numbers within the range of 0 to 1 and this determines how frequently alive/true cells occur .
                        if (Math.random() > 0.75){ 
                            oldGen[printRow][printCol] = true;
                        }
                    }
                }  
                break;
            case "blank"://This will make the grid entierly false giving the user the ability to make their own shapes
                for (int printCol = 0; printCol < oldGen[0].length; printCol++){  //grid.length and grid[0].length are the row and col variables of the grid array
                    for (int printRow = 0; printRow < oldGen.length; printRow++){
                        oldGen [printRow][printCol] = false;  
                    }
                }
                break;
        }
        printArray(oldGen);
        System.out.println("type \"run\" to advance one generation\n"+"please type \"runU\" to run specific number of generation\n "+"please type \"changeCell\" to change the state of a certain cell\n ");
        while (gameRun == true){
            switch (UI.nextLine()){//UI stand for user input
                case "run":
                    System.out.println("type \"run\" to advance one generation");
                    oldGen = makeNewGen(oldGen); // this makes a new generation by getting the old one and putting it through the rules of the game to make a grid.
                    printArray(oldGen);     
                    System.out.println("type \"run\" to advance one generation\n"+"please type \"runU\" to run specific number of generation\n "+"please type \"changeCell\" to change the state of a certain cell\n ");
                    break;
                case "quit":
                    gameRun = false;
                    break;
                case "runU":
                    System.out.println("Please type how many times you want the game to run");
                    int runAmountTimes = UI.nextInt();
                    // runs the makeNewGrid method the amount of times dectated by the user. 
                    for (int checkRunAmountTimes = 0; checkRunAmountTimes < runAmountTimes; checkRunAmountTimes++){
                        oldGen = makeNewGen(oldGen);
                        printArray(oldGen);
                    }
                    System.out.println("type \"run\" to advance one generation\n"+"please type \"runU\" to run specific number of generation\n "+"please type \"changeCell\" to change the state of a certain cell\n ");
                    break;
                case "changeCell": // this intakes the the X and Y coordinate and makes the cell the opposite state that it currently is. 
                    System.out.println("to change cell you will need to type the x,y coodernate in this order to change the cell");
                    System.out.println("make sure the X coodinate is between 0 and "+(row -1)+" and the Y coodinate is between 0 and "+(col -1));
                    printArray(oldGen);
                    int cellCoordinateX = UI.nextInt();
                    int cellCoordinateY = UI.nextInt();                 
                    if (oldGen[cellCoordinateX][cellCoordinateY] == false){
                        oldGen[cellCoordinateX][cellCoordinateY] = true;
                    } else {
                        oldGen[cellCoordinateX][cellCoordinateY] = false;
                    }
                    printArray(oldGen);
                    System.out.println("type \"run\" to advance one generation\n"+"please type \"runU\" to run specific number of generation\n "+"please type \"changeCell\" to change the state of a certain cell\n ");
                    break;
            }
        }
    }
    // this method prints out the current array
    public void printArray (boolean [][] oldGen){
        //this checks each cell of the oldGen array and prints out cell depeneding on if its false(a space appears in cell) or true(a O apears in cell). 
        for (int printCol = 0; printCol < oldGen.length; printCol++){
            for (int printRow = 0; printRow < oldGen[0].length; printRow++){
                if(oldGen [printCol][printRow] == true){
                    System.out.print("O,");
                }  else if(oldGen [printCol][printRow] == false){
                    System.out.print(" ,");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }
    // this method makes the newGen array for the next grid
    public boolean[][] makeNewGen (boolean [][] oldGen ){
        boolean [][] newGen = new boolean[oldGen.length][oldGen[0].length];
        int trueCellNeighbours;
        // sets newGen grid to all false 
        for (int printCol = 0; printCol < oldGen.length; printCol++){
            for (int printRow = 0; printRow < oldGen[0].length; printRow++){
                newGen [printCol][printRow] = false;  
            }
        }
        // this function is used to make the new generation by going through each cell and checking its neighbours status (true/alive or false/dead) this is done through using cellRow and cellColloum to change the cell we are looking at for its neighbours and 
        // using the cellCheckLeft and cellCheckRight to check the cells around the selected cell.
        for (int cellColumn = 0; cellColumn < oldGen[0].length; cellColumn++){
            for (int cellRow = 0; cellRow < oldGen.length; cellRow++){
                trueCellNeighbours = 0;
                for (int cellCheckLeftRight = -1; cellCheckLeftRight < 2; cellCheckLeftRight ++){
                    for (int cellCheckUpDown = -1; cellCheckUpDown < 2; cellCheckUpDown ++){
                        if ( (cellColumn + cellCheckUpDown > -1 && cellRow + cellCheckLeftRight > -1 && cellColumn + cellCheckUpDown <= oldGen[0].length - 1 && cellRow + cellCheckLeftRight <= oldGen.length - 1 && (cellCheckUpDown != 0 || cellCheckLeftRight != 0) )&&
                        (oldGen [cellRow+cellCheckLeftRight][cellColumn+cellCheckUpDown] == true) ) {
                            trueCellNeighbours++;
                        }
                    }
                }
                // these are the rules of conways game of life 
                if (trueCellNeighbours >= 4){
                    newGen[cellRow][cellColumn] = false;
                }else if (trueCellNeighbours  <= 1){
                    newGen[cellRow][cellColumn] = false;
                }else if (trueCellNeighbours == 2 && oldGen[cellRow][cellColumn] == true){
                    newGen[cellRow][cellColumn] = true;
                }else if(trueCellNeighbours == 3){
                    newGen[cellRow][cellColumn] = true; 
                }else{
                    newGen[cellRow][cellColumn] = false;
                }
            }
        }
        return newGen;
    }
}
