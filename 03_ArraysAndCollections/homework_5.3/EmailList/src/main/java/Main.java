import java.util.Scanner;

public class Main {

    public static EmailList list = new EmailList();
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if (input.matches("ADD.*")) {
                String email = input.substring(3);
                if (CheckFormat.checkFormat(email)) {
                    list.add(email.trim());
                }
                else {
                    System.out.println(WRONG_EMAIL_ANSWER);
                }
                continue;
            }

            if (input.matches("LIST"))
            {
                list.getSortedEmails();
            }
            //TODO: write code here
        }
    }
}
