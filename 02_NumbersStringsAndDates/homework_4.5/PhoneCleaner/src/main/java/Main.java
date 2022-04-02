import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      input = input.replaceAll("[^0-9]","");

      if (input.length() == 10)
      {
        input = "7" + input;
      }
      StringBuilder number = new StringBuilder(input);

      if (number.charAt(0) == '8')
      {
        number.setCharAt(0, '7');
      }

      if (number.length() == 11 && number.charAt(0) == '7')
      {
          System.out.println(number);
      }
      else
      {
        System.out.println("Неверный формат номера");
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
    }
  }

}
