public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

    int amount = 0;

    for (int i = 0; i < text.length(); i++)
    {
      char c = text.charAt(i);
      if ((int) c >= 48 && (int) c <= 57)
      {
        int indexBegin = i;
        int indexEnd = text.indexOf(" ",indexBegin);
        String earnings = text.substring(indexBegin, indexEnd);
        amount = amount + Integer.parseInt(earnings);
        i = i + earnings.length();
      }
    }
    
    System.out.println(amount);
    //TODO: напишите ваш код, результат вывести в консоль
  }

}