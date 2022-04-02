import java.util.Scanner;

public class Main {

    public static String searchAndReplaceDiamonds (String text, String placeholder)
    {
        int index1 = text.indexOf("<");
        int index2 = text.indexOf(">");
        while (index1 != -1 && index2 != -1)
        {
                String part1 = text.substring(0, index1);
                String part2 = text.substring(index2 + 1);
                text = part1 + placeholder + part2;
                index1 = text.indexOf("<");
                index2 = text.indexOf(">");
        }
        return text;
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
    }

    public static void main(String[] args) {
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