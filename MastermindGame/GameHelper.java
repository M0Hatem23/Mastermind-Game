import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
/**
 *
 * @author mohamed
 */
public class GameHelper
{
    static Scanner in = new Scanner(System.in);
    private static String userGuess;
    public ArrayList<Character> InputArraylist = new ArrayList();
    private static ArrayList<String> guessHistory = new ArrayList<String>();
    private static ArrayList<ArrayList<Character>> nailsHistory = new ArrayList<ArrayList<Character>>();
    public ArrayList<Character> coloneListAns = new ArrayList(Game.listAns);
    Game game = new Game();


    public static void aboutUs()
    {
        System.out.println("""
            \n\t\t\t\tWelcome to our game!
About us :-

1. Mahmoud Ahmed Shehata Mansour                        [2300736]
2. Mohamed Hatem Kamal El-Nagar                        [2300739]
3. Mohammed Ahmed Abdelhameed Elgzzar                   [2300740]
4. Mohamed Khaled Fahim Aly                             [2300731]
5. Omar Mohamed Ramzy Mohamed                           [2301247]
6. Abdallah Gaber Gaber Azgola                          [2300722]""");
    }
    public static void GameRules()
    {
        System.out.println("""
			\n\t\t\t *Game rules*\n
1-Secret Code: The Computer selects a code (colors).
2-Guessing Attempts: The player tries to guess the code.
3-Feedback:
   [*]: A color that exists in the secret code has been placed in the correct position.
   [+]: A color that exists in the secret code has been placed in the wrong position.
   [-]: A color that dose not exist in the secret code has been placed.
4-Win: Break the code within the allowed attempts.
5-Lose: Fail to guess the code after all attempts are used.""");
    }
    
    public static boolean trueInput(String userGuess, int colorsNum)
    {
        boolean flag = true;
        for (int i = 0; i < userGuess.length(); ++i)
        {
            int pos = Game.colors.indexOf(userGuess.charAt(i));
            if (pos == -1 || pos > (colorsNum - 1))
            {
                flag = false;
                return flag;
            }
        }
        return flag;
    }
    
    public static String CheckInput(int colorsNum, int guessNum)
    {
        while(true)
        { 
            System.out.print("Enter your Guess : ");
            userGuess = in.next().toUpperCase();
            if (userGuess.length() == guessNum && trueInput(userGuess, colorsNum))
                break;
            else
                System.out.println("Invalid input!");
        }
        guessHistory.add(userGuess);
        return userGuess;
    }
    
    public void makeInputArraylist(String input)
    {
        for (int i = 0; i < input.length(); ++i)
            this.InputArraylist.add(input.charAt(i));
    }
    
    public void printInputArraylist()
    {
        for (int i = 0; i < game.userGuess.length(); ++i)
            System.out.printf("%c " ,this.InputArraylist.get(i));
        System.out.println();
    }
    
    public void checkColor(String input)
    {
        for (int i = 0; i < InputArraylist.size(); ++i)
        {
            if (Objects.equals(InputArraylist.get(i), coloneListAns.get(i)))
            {
                game.nails.add('*');
                coloneListAns.set(i, '0');
                this.InputArraylist.set(i, '1');
            }
        }
        
        for (int i = 0; i < InputArraylist.size(); ++i)
        {
            for (int j = 0; j < game.listAns.size(); ++j)
            {
                if (Objects.equals(InputArraylist.get(i), coloneListAns.get(j)))
                {
                    game.nails.add('+');
                    coloneListAns.set(j, '0');
                    break;
                }
            }
        }    
        while(game.nails.size() < Mastermind.guessNum)
            game.nails.add('-');
        nailsHistory.add(game.nails);
    }
    public void printNail()
    {
        for (char c: game.nails)
            System.out.printf("%c ",c);
        System.out.println();
    }
    
    public static void printHistory()
    {
        System.out.println("The CODE was : " + Game.listAns);
        System.out.println("Historia :");
        for (int i = 0; i < guessHistory.size(); ++i)
        {
            System.out.println(guessHistory.get(i) + "   " + nailsHistory.get(i));
        }
        nailsHistory.clear();
        guessHistory.clear();
    }
}
