/**
 * Write a description of class GameFoundations here.
 *
 * @author (Harrison Morris)
 * @version (v1)
 */
import java.util.Scanner;
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
        int[][] grid = new int[10][10]; 
        int z = 0;
        Scanner keyinput = new Scanner(System.in);
        boolean gameModePick=false;
        String modeChoice;
        System.out.println("Good day to you sir/madam I'm here as your humble guide to this simple game also known as connways game of life. now to let us begin please do the following \n"
            +"please type what board you would like to see \n"
            +"board 1 type b1 \n"
            +"board 2 type b2 \n"
            +"board 3 type b3 \n");
        for (int j = 0; j < 10; j++){
            for (int i = 0; i < 10; i ++){
                grid [j][i] = z;

            }
        }

        switch (keyinput.nextLine()){
            case "b1":
            for (int y = 0; y < 10; y++){
                for (int x = 0; x < 10; x ++){
                    
                    for (int a = -1; a < 2; a ++){
                        for (int b = -1; b < 2; b ++){
                            if (x + b > -1 && y + a > -1 && x + b <= 8 && y + a <= 8 && (b != 0 || a != 0) ){

                                
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
            case "b2":
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
            case "b3":
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