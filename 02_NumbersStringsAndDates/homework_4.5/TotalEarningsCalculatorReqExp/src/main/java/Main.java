import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static int calculateSalarySum(String text){
    int sum = 0;
    String[] words = text.split("\\s");
    for (String word : words) {
      if (word.matches("\\d+")) {
        sum += Integer.parseInt(word);
      }
    }

    //TODO: реализуйте метод
    if (sum == 0)
    {
      System.out.println("Никто ничего не заработал");
    }
    else
    {
      System.out.println(sum);
    }
    return sum;
  }

  public static void main(String[] args)
  {
    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    calculateSalarySum(text);
  }
}