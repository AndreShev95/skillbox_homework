import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


public class Main {

    public static String collectBirthdays(int year, int month, int day) {
        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        String birthdays = "";
        StringBuilder builder = new StringBuilder();
        builder.append(birthdays);

        for (int i = 0; birthday.isBefore(today) || birthday.isEqual(today); i++){
            DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy - E", new Locale("en"));
            builder.append(i + " - " + printFormat.format(birthday));
            builder.append("\n");
            birthday = birthday.plusYears(1);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        int day = 27;
        int month = 2;
        int year = 2016;

        System.out.print(collectBirthdays(year, month, day));
    }
}
