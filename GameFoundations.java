/**
 * Conways game of life is a game where you make a 2d grid and within this grid you have cells. These cells have two states either dead or alive if the cells are dead they will do nothing but if the cell is alive they have a set of rules which they follow
 * which allows them to either infect other cells so they become alive or become a dead cell.
 * Game of life is an algorithim that creates intresting evolving shapes based of the games rules.  
 * The reason I chose this game to develop is because I wanted to make a game that would test my current skills but also develope other skills like managing problems
 * and figuring out new code that I havent come across. it would also develope my skills at reaseraching into errors in my code.
 * @author (Harrison Morris)
 * @version (v4.5)
 */
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class gameOfLife
{
    // This scanner will allow me to take in the users input from the keyboard 
    Scanner input = new Scanner (System.in);

    /**
     * Constructor for objects of class gameOfLife
     * In this method gameOfLife is used as the UI for the player and simulates conways game of life
     */
    public gameOfLife()
    {
        clearScreen();
        Scanner UI = new Scanner(System.in);
        boolean gameRun = true; //this tells the computer to loop the code within the while loop until said otherwise.    
        int row = 20;
        int col = 30;
        boolean [][] oldGen = new boolean[row][col]; //this stores the previous generations of conways game of life
        printSlow("If you want your starting board to be filled with random cells please type random. \n");
        printSlow("If you want your starting board to be filled with dead cells so you can create your own board please type anything else\n");

        //these two loops fill the grid array. the first one fills the array with to make ll of them false/dead cels this gives a blank slate for the secound loop to fill the array in a random order with true/alive cells    
        for (int printCol = 0; printCol < oldGen[0].length; printCol++){  //grid.length and grid[0].length are the row and col variables of the grid array
            for (int printRow = 0; printRow < oldGen.length; printRow++){
                oldGen [printRow][printCol] = false;  
            }
        }
        if(UI.nextLine().toLowerCase().equals("random")){
            for (int printCol = 0; printCol < oldGen[0].length; printCol++){ 
                for (int printRow = 0; printRow < oldGen.length; printRow++){
                    //Math.random randomly generates numbers within the range of 0 to 1 and this determines how frequently alive/true cells occur .
                    if (Math.random() > 0.75){ 
                        oldGen[printRow][printCol] = true;
                    }
                }
            }  
        }
        clearScreen();
        printArray(oldGen); // prints intial state of array to user
        while (gameRun){            
            printSlow("type \"run\" to advance one generation\n"+"please type \"runU\" to run specific number of generation\n "+"please type \"change cell\" to change the state of a certain cell\n ");
            switch (UI.nextLine().toLowerCase()){//UI stands for user input
                case "run":
                    clearScreen();
                    oldGen = makeNewGen(oldGen); // this makes a new generation by getting the old one and putting it through the rules of the game to make a grid.
                    printArray(oldGen); 
                    break;
                case "quit":
                    clearScreen();
                    gameRun = false;
                    break;
                case "runu":
                    clearScreen();
                    printSlow("Please type how many times you want the game to run\n");
                    int runAmountTimes = UI.nextInt();
                    for (int checkRunAmountTimes = 0; checkRunAmountTimes < runAmountTimes; checkRunAmountTimes++){// runs the makeNewGrid method the amount of times dectated by the user. 
                        clearScreen();
                        oldGen = makeNewGen(oldGen);
                        printArray(oldGen);
                        try{//gives the function a certain amount of time before repeating
                            TimeUnit.MILLISECONDS.sleep(125);
                        }
                        catch (Exception e) {
                            System.out.println("runU error");
                        }
                    }
                    break;
                case "changecell": // this intakes the the X and Y coordinate and makes the cell the opposite state that it currently is. 
                    clearScreen();
                    printSlow("to change cell you will need to type the x,y coodernate \n in this ORDER and SEPERATLY to change the cell");
                    printSlow(" make sure the X coodinate is between 0 and "+(row -1)+" and the Y coodinate is between 0 and "+(col -1)+"\n");
                    printArray(oldGen);
                    int cellCoordinateX = UI.nextInt();
                    int cellCoordinateY = UI.nextInt(); 
                    if (cellCoordinateX > row - 1 || cellCoordinateY > col - 1 || cellCoordinateX < 0 ||cellCoordinateY < 0 ) {// this tests to see if the coordinate is within the proper boundaries
                        printSlow("Error your number X or Y coordinate wasnt within expected boundaries please try again "+"\n");
                    } else if (oldGen[cellCoordinateX][cellCoordinateY] == false){
                        oldGen[cellCoordinateX][cellCoordinateY] = true;
                    } else if (oldGen[cellCoordinateX][cellCoordinateY] == true)  {
                        oldGen[cellCoordinateX][cellCoordinateY] = false;
                    } 
                    printArray(oldGen);
                    break;
                default: //gives the user a error message to tell them what they may have done wrong
                    System.out.println("Error you may have either spelt the word incorrectly or \n put in a command that dosent exist please try again");
                    break;
            }
        }
    }
    /**
     * this method prints out the current array
     */ 
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
        System.out.println();
    }
    /**
     * this method makes the newGen array for the next grid
     * this method takes in a oldGen to use as the template for the newGen array 
     */ 
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
                        if ( (cellColumn + cellCheckUpDown > -1 && cellRow + cellCheckLeftRight > -1 && cellColumn + cellCheckUpDown <= oldGen[0].length - 1 && cellRow + cellCheckLeftRight <= oldGen.length - 1 && (cellCheckUpDown != 0 || cellCheckLeftRight != 0) 
                        )&&(oldGen [cellRow+cellCheckLeftRight][cellColumn+cellCheckUpDown] == true) ) {
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
    /**
     * this makes the output to the screen present slower rather than instantanious
     */
    public static void printSlow(String output){ 
        for(int i = 0; i < output.length(); i++){
            char printLetter = output.charAt(i);
            System.out.print(printLetter);
            try{//gives the function a certain amount of time before repeating
                TimeUnit.MILLISECONDS.sleep(10);
            }
            catch (Exception e) {
                System.out.println("printSlow error");
            }
        }
    }
    /**
     * I have got this code from stack overflow
     * https://stackoverflow.com/questions/2979383/how-to-clear-the-console
     * Clears current Screen
     * 
     */
    public static void clearScreen(){
        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {}
    }

}
