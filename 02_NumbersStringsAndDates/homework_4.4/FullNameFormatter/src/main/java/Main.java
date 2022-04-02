import java.util.Scanner;

public class Main {

  public static boolean formatChecking(String input)
  {
    input = input.trim();
    boolean kirillica = true;
    int space = 0;

    for (int i = 0; i < input.length(); i++)
    {
      char symbol = input.charAt(i);
      if (((int) symbol >= 1040 && (int) symbol <= 1103) || (int) symbol == 45 || (int) symbol == 32)
      {
        kirillica = true;
        if ((int) symbol == 32)
        {
          space++;
        }
      }
      else
      {
        kirillica = false;
        break;
      }
    }
    return kirillica && space == 2;
  }

  public static void fullNameFormatter(String input)
  {
    if (formatChecking(input))
    {
      String surname = input.substring(0,input.indexOf(" "));
      System.out.println("Фамилия: " + surname.trim());

      String name = input.substring(input.indexOf(" "),input.lastIndexOf(" "));
      System.out.println("Имя: " + name.trim());

      String patronymic = input.substring(input.lastIndexOf(" "));
      System.out.println("Отчество: " + patronymic.trim());
    }
    else
    {
      System.out.println("Введенная строка не является ФИО");
    }
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      fullNameFormatter(input);

      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

}