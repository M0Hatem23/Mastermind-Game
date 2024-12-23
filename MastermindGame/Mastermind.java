import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author Mohamed Hatem
 */

public class Mastermind
{
    static Scanner in = new Scanner(System.in);
    public static int colorsNum = 0;
    public static int attemptsNum = 0;
    public static int guessNum = 0;
    static boolean win = false;
    
    private static void message()
    {
        System.out.print("\nEnter your choice : ");
    }
    
    private static void theGame(Game game, int attemptsNumber, int colorsNumber, int guessNumber,String mood)
    {
        System.out.print("You Have only " + attemptsNumber + " attempts [");
        for (int i = 0; i < colorsNumber; ++i)
        {
            System.out.print(Game.colors.charAt(i));
            if (i != colorsNumber-1)
                System.out.print(", ");
            else
                System.out.println("]\n");
        }
        while (attemptsNumber > 0)
        {
            System.out.print(attemptsNumber + ": ");
            GameHelper helper = new GameHelper();
            game.setUserGuess(colorsNumber, guessNumber);
            helper.makeInputArraylist(game.getUserGuess());
            helper.printInputArraylist();
            helper.checkColor(game.getUserGuess());
            helper.printNail();
            int counter = Collections.frequency(helper.InputArraylist, '1');
            if (counter == guessNumber)
            {
                win = true;
                System.out.println("\nYou win ;)");
                break;
            }
            attemptsNumber--;
        }
        finishGame();
        Game.listAns.clear();
        Again(mood);
    }
    
    private static void runGame()
    {
        difficulty();
        System.out.println("|-------------------------------------------" + (attemptsNum > 9 ? "-|" : "|"));
        System.out.println("|Attempts number : " + attemptsNum + " | " + guessNum + " colors secret code |");
        System.out.println("|-------------------------------------------" + (attemptsNum > 9 ? "-|" : "|"));
        int attemptsNumber = attemptsNum;
        int colorsNumber = colorsNum;
        int guessNumber = guessNum;
        Game game = new Game(colorsNumber, guessNumber);
        theGame(game, attemptsNumber, colorsNumber, guessNumber, "play");
    }
    
    private static void debugGame()
    {
        difficulty();
        System.out.println("|--------------------------------------------" + (attemptsNum > 9 ? "-|" : "|"));
        System.out.println("|attemptsNum = " + attemptsNum + ", colorsNum = " + colorsNum + ", guessNum = " + guessNum + "|");
        System.out.println("|--------------------------------------------" + (attemptsNum > 9 ? "-|" : "|"));
        int attemptsNumber = attemptsNum;
        int colorsNumber = colorsNum;
        int guessNumber = guessNum;
        Game game = new Game(colorsNumber, guessNumber);
        System.out.println("The answer is : " + Game.listAns);
        theGame(game, attemptsNumber, colorsNumber, guessNumber, "debug");
    }
    
    private static void finishGame()
    {
        if (!win)
            System.out.println("\nYou lose ):");
    	win = false;
        GameHelper.printHistory();
    }
    
    private static void Again(String mood)
    {
        System.out.print("\nwould you like to " + mood + " again [y,n] : ");
        String choice = in.next().toUpperCase();
        switch (choice)
        {
            case "Y" -> {   
                if (mood.equals("play"))
                    runGame();
                else if (mood.equals("debug"))
                    debugGame();
            } 
            
            default -> mainMenu();
        }
    }
    
    private static void chooseMood()
    {
        System.out.println("\n1.Play");
        System.out.println("2.Debug");
        System.out.println("3.Back");   
        message();
        String choice = in.next();
        switch  (choice)
        { 
            case "1" -> {
                System.out.println("\n playing mood is on!");
                runGame();
            }
            
            case "2" -> {
                System.out.println("\nDebugging mood is on!");
                debugGame();
            }
            
            case "3" -> mainMenu();
            
            default -> {
                System.out.println("Invalid input!");
                chooseMood();
            }
        }
    }
    
    private static void runningGame()
    {
        System.out.println("\n-----------");
        System.out.println("1.Main menu");
        System.out.println("-----------");
        message();
        String choice = in.next();
        switch (choice)
        {
            case "1" -> mainMenu();
            
            default -> runningGame();
        }
    }
       
    private static void mainMenu()
    {
        System.out.println("\n1.Game Rules");
        System.out.println("2.Start game");
        System.out.println("3.About us");
        System.out.println("0.Quit Game");
        message();
        String choice = in.next();
        switch (choice)
        {
            case "0" -> {
                System.out.println("\nThanks for playing :) ");
                System.exit(0);
            }
			
            case "1" -> {
                GameHelper.GameRules();  
                runningGame();
            }
            
            case "2" -> chooseMood();
            
            case "3" -> {
                GameHelper.aboutUs();
                runningGame();
            }
            
            default -> {
                System.out.println("Invalid Input!");
                mainMenu();
            }
        }
    }
    private static void difficulty()
    {
        System.out.println("|------------------|");
        System.out.println("| 1.Eassy          |");
        System.out.println("| 2.Medium         |");
        System.out.println("| 3.Hard           |");
        System.out.println("| 4.Brute          |");
        System.out.println("|------------------|");
        System.out.println("| 5.Back           |");
        System.out.println("|------------------|");
        message();
        String choice = in.next();
        
        switch (choice)
        {			
            case "1" -> {
                System.out.println("\nEassy version enabled!");
                guessNum = 3;
                colorsNum = 5;
                attemptsNum = 10;
            }
            
            case "2" -> {
                System.out.println("\nMedium version enabled!");
                guessNum = 4;
                colorsNum = 6;
                attemptsNum = 10;
            }
            
            case "3" -> {
                System.out.println("\nHard version enabled!");
                guessNum = 5;
                colorsNum = 8;
                attemptsNum = 10;
            }
            
            case "4" -> {
                System.out.println("\nBrute version enabled!");
                guessNum = 5;
                colorsNum = 9;
                attemptsNum = 8;
            }
            
            case "5" -> {
                chooseMood();
            }
            
            default -> {    
                System.out.println("Invalid input!");
                difficulty();
            }
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("\n\t\t\t\tWelcome to mastermind!");
        mainMenu();
    }
}
