import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if (input.matches("[А-я]+")) {
                Set<String> phone = phoneBook.getPhonesByName(input);
                if (phone.isEmpty()) {
                    String inputPhone = scanner.nextLine();
                    phoneBook.addContact(inputPhone, input);
                }
            }
            else if (input.matches("[0-9]{11}")) {
                String name = phoneBook.getNameByPhone(input);
                if (name.isEmpty()) {
                    String inputName = scanner.nextLine();
                    phoneBook.addContact(input, inputName);
                }
            }
            else if (input.matches("LIST")) {
                phoneBook.getAllContacts();
            }
        }
    }
}
