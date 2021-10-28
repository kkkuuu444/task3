import org.apache.commons.lang3.math.NumberUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class Main {

    private static List<String> foundStrings = new ArrayList<>();

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
        if (isChecked(args) == 0)
        {
            String userChoice, computerChoice;
            computerChoice = makeChoice(foundStrings);
            HMACCoder.generateHmac(computerChoice);
            do
            {
                System.out.println("HMAC: " + HMACCoder.getHMAC());
                System.out.println("Available moves:");
                for (int i = 0; i < foundStrings.size(); i++)
                {
                    System.out.println(i + 1 + " - " + foundStrings.get(i));
                }
                System.out.print("0 - exit\n? - help\n");
                System.out.print("Enter your move:");
                Scanner input = new Scanner(System.in);
                userChoice = input.nextLine();
                if (!userChoice.isEmpty())
                {
                    if (NumberUtils.isDigits(userChoice))
                    {
                        if (Integer.decode(userChoice) > foundStrings.size() || Integer.decode(userChoice) < 0)
                        {
                            System.err.println("Wrong command. Try again.");
                            continue;
                        } else if (userChoice.charAt(0) == '0')
                        {
                            System.out.println("Exiting...");
                            break;
                        }
                    } else if (userChoice.indexOf("?") == 0 && userChoice.length() == 1)
                    {
                        TableDrawer.drawGameRulesTable(foundStrings);
                        continue;
                    } else
                    {
                        System.err.println("Incomprehensible command, type a command.");
                        continue;
                    }
                } else
                {
                    System.err.println("No command, type a command.");
                    continue;
                }
                System.out.println("Your move: " + foundStrings.get(Integer.decode(userChoice) - 1));
                System.out.println("Computer move: " + computerChoice);
                System.out.println(GameRules.findWinner(computerChoice, userChoice, foundStrings));
                System.out.println("HMACKey: " + HMACCoder.getKEY());
                System.out.println("----------------------------------------");
                computerChoice = makeChoice(foundStrings);
                HMACCoder.generateHmac(computerChoice);
            }while(true);
        }
        else
        {
            System.exit(1);
        }
    }

    private static String makeChoice(List<String> foundStrings)
    {
        SecureRandom index = new SecureRandom();
        return foundStrings.get(index.nextInt(foundStrings.size()));
    }

    private static int isChecked(String[] args)
    {
        if (args.length < 3)
        {
            System.err.println("Quantity of elements is under 3. It has to have 3 or more.");
            return 1;
        } else if (args.length % 2 == 0)
        {
            System.err.println("Array is not odd.");
            return 2;
        }
        for (String str : args)
        {
            if (!foundStrings.contains(str))
            {
                foundStrings.add(str);
            }
            else
            {
                System.err.println("Contains repetative elements!\nYour first met element = " + str);
                return 3;
            }
        }
        return 0;
    }
}
