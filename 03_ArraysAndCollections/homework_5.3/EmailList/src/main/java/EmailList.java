import java.util.*;

public class EmailList {
    private final TreeSet<String> emails = new TreeSet();

    public void add(String email) {
        // TODO: валидный формат email добавляется
        if (CheckFormat.checkFormat(email)){
            emails.add(email.toLowerCase());
        }
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        if (emails.size() != 0) {
            for (String email : emails)
            System.out.println(email);
        } else {
            System.out.println("Список пуст");
        }
        return new ArrayList<>(emails);
    }
}