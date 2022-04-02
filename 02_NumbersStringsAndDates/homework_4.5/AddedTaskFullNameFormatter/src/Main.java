import java.util.Scanner;

public class Main {

    public static boolean formatChecking(String input)
    {
        input = input.trim();
        return input.matches("([А-Я][а-я]*(-[А-Я][а-я]*)?\\s)([А-Я][а-я]*(-[А-Я][а-я]*)?\\s)([А-Я][а-я]*(\\s\\bоглы\\b)?)"); //\s[а-я]*
    }

    public static void fullNameFormatter(String input)
    {
        if (formatChecking(input))
        {
            input = input.trim();
            String[] name = input.split("\\s");

            System.out.println("Фамилия: " + name[0].trim());
            System.out.println("Имя: " + name[1].trim());
            if (name.length == 4)
            {
                System.out.println("Отчество: " + name[2].trim() + " " + name[3].trim());
            }
            else {
                System.out.println("Отчество: " + name[2].trim());
            }
        }
        else {
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
        }
    }

}
