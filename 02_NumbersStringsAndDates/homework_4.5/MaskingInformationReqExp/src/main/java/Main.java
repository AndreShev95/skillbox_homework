import java.util.Scanner;

public class Main {

    public static String searchAndReplaceDiamonds(String text, String placeholder)
    {
        text = text.replaceAll("<.+?>",placeholder);
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        return text;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("0")) {
                break;
            }
            System.out.println(searchAndReplaceDiamonds(text,"***"));
        }
    }

}