import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author mohamed
 */
public class Game
{
    static String userGuess;
    public final static String colors = "RGBYWOPCV";
    public static ArrayList<Character> listAns = new ArrayList<Character>();
    public ArrayList<Character> nails = new ArrayList<Character>();
    static Random random = new Random();
    
    Game()
    {
        
    }
    
    Game(int colNum, int gusNum)
    {
        for (int i = 0; i < gusNum; ++i)
        {
            char c = colors.charAt(random.nextInt(colNum));
            listAns.add(c);
        }
    }
     
    public void setUserGuess(int colNum, int gusNum)
    {
        userGuess = GameHelper.CheckInput(colNum, gusNum);
    }
    
    public String getUserGuess()
    {
        return userGuess;
    }
}
