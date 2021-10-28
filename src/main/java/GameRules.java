import java.util.List;

public abstract class GameRules {

    public final static String WIN = "WIN";
    public final static String DRAW = "DRAW";
    public final static String LOSE = "LOSE";

    public static String findWinner(String computerChoice, String userChoice, List<String> choices)
    {
        String result = DRAW;
        final int borderOfLose = choices.size() / 2;
        final int compIndex = choices.indexOf(computerChoice), userIndex = choices.indexOf(userChoice);
        final int diff = compIndex - userIndex;
        if (diff == 0)
        {
            result = DRAW;
        } else if ((diff < -borderOfLose && userIndex > compIndex) || (diff <= borderOfLose && compIndex > userIndex))
        {
            result = "COMPUTER " + WIN;
        } else if ((diff >= -borderOfLose && compIndex < userIndex) || (diff > borderOfLose && userIndex < compIndex))
        {
            result = "USER " + WIN;
        }
        return result;
    }
}
