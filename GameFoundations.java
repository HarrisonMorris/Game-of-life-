
/**
 * Write a description of class GameFoundations here.
 *
 * @author (your name)
 * @version (v1)
 */
import java.util.Scanner;
public class GameFoundations
{
    
    gibraltar
    Scanner input = new Scanner (System.in);
    // This scanner will allow me to take in the users input from the keyboard 
    boolean gameMode = false; 
    /**
     * Constructor for objects of class GameFoundations
     */
    public GameFoundations()
    {
        int[][] grid = new int[10][10]; 
        int x = 1;
        Scanner ban = new Scanner(System.in);
        

        for (int j = 0; j < 10; j++){
            for (int i = 0; i < 10; i ++){
                grid [j][i] = x;
                x = x + 1; 
            }
        }
        switch(ban.nextLine()){
            case "print": 
                for (int j = 0; j < 10; j++){
                    for (int i = 0; i < 10; i ++){
                        System.out.print (grid [j][i] + ", ");

                    }
                    System.out.println ();
                }
                break; 
            case "left":  
                for (int j = 0; j < 10; j++){
                    for (int i = 0; i < 10; i ++){
                        if (i == 0){
                            System.out.print ("x, ");
                        }else{
                            System.out.print (grid [j][i - 1] + ", ");

                        }
                    }
                    System.out.println ();
                }
                break;

                case"down": 
                for (int j = 0; j < 10; j++){
                    for (int i = 0; i < 10; i ++){
                        if (j == 9){
                            System.out.print ("x, ");
                        }else{
                            System.out.print (grid [j + 1][i] + ", ");

                        }
                    }
                    System.out.println ();
                }
                
                case"surrounding":
                for (int j = 0; j < 10; j++){
                    for (int i = 0; i < 10; i ++){
                        int total = 0;
                        int sum = 0;
                        System.out.print (grid [j][i] + ", ");
                        for (int a = -1; a < 2; a ++){
                            for (int b = -1; b < 2; b ++){
                                 total = grid [j + a][i + b] ; 
                                 sum = sum + total; 
                                 

                                 
                        
                            }
                        }
                        System.out.println (sum); 
                    }
                }
                System.out.println ();
        }
        break;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
