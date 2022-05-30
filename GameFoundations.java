/**
 * Write a description of class GameFoundations here.
 *
 * @author (Harrison Morris)
 * @version (v1)
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
        int row=10;
        int col=30; //coloum
        
        int[][] grid = new int[col][row]; 

        int z = 0;
        Scanner keyinput = new Scanner(System.in);
        boolean gameModePick=false;
        String modeChoice; 
        System.out.println("Good day to you sir/madam I'm here as your humble guide to this simple game also known as connways game of life. now to let us begin please do the following \n"
            +"please type what board you would like to see \n"
            +"board 1 type b1 \n"
            +"board 2 type b2 \n"
            +"board 3 type b3 \n");

        
        switch (keyinput.nextLine()){
            case "b1":// this makes a square full of 1s
            for (int y = 0; y < col; y++){
                for (int x = 0; x < row; x ++){

                    for (int a = -1; a < 2; a ++){
                        for (int b = -1; b < 2; b ++){
                            if (x + b > -1 && y + a > -1 && x + b <= col && y + a <= row && (b != 0 || a != 0) ){

                                grid [y][x] = 1;
                            } else {
                                grid [y][x] = 0;

                            }

                        }
                    }
                    System.out.print (grid [y][x] + ", "); 
                }
                System.out.println (" ");
            }
            break;
            case "b2": //this makes a sqaure of 1s with a border of 0s
            for (int y = 0; y < 10; y++){
                for (int x = 0; x < 10; x ++){

                    for (int a = -1; a < 2; a ++){
                        for (int b = -1; b < 2; b ++){
                            if (x + b > 1 && y + a > 1 && x + b <= 7 && y + a <= 7 && (b != 0 || a != 0) ){

                                grid [y][x] = 1;
                            } 
                        }
                    }
                    System.out.print (grid [y][x] + ", "); 
                }
                System.out.println (" ");
            }
            break;
            case "x":
            int gss = 10; //grid shape size
            //for (i=0,i<gss,i++){\
            //grid[i][i]=1
            //grid[i][gss-1-i]}
            break;

            case "b3": //random gen
            

            

            // place random numbers into the array 'mygrid'
            for (int i=0; i<row; i++){
                for (int j=0; j<col; j++){
                    //mygrid[i][j] = rand() 2 this is used for capping the amount of random numbers that can be generated;
                    Random rand = new Random(); 
                    int int_random = rand.nextInt(2); 
                    grid[i][j] = int_random ;
                }
            }

            //what the grid displays
            System.out.println();
            for (int i=0; i < row; i++)
            {
                for (int j = 0; j < col; j++)
                {
                    if (grid[i][j] == 0)
                        System.out.print("0");
                    else
                        System.out.print("1");
                }
                System.out.println();
            }
            System.out.println();
            break;
            case "b5": //if using pre-made file
                System.out.println("enter the exact name of your custom file (incl .txt) - must be in file directory");
                File customFile=new File (keyinput.nextLine());
                try{
                    Scanner fileRead = new Scanner(customFile);
                    while(fileRead.hasNextLine()){
                        //reading the 0's and 1's of custom file
                        String num = fileRead.nextLine();
                        System.out.println(num);

                        if (num == "1"){
                            System.out.println("*");
                        }else if (num == "0"){
                            System.out.println(".");
                        }else{
                            System.out.println("");
                        }

                    }
                }
                catch(IOException e) {
                    //in case anything goes wrong
                    e.printStackTrace();
                }
                break;


        }

        // while(!gameModePick){
        // modeChoice=keyinput.nextLine().toLowerCase();
        // if(modeChoice.equals("multi")){
        // gameModePick = true;
        // offline=false;
        // }else if (modeChoice.equals("single")){
        // gameModePick = false;
        // offline=true;

        // }

        // }
    }
}