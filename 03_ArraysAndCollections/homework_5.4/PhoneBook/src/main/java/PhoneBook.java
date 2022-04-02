import java.util.*;

public class PhoneBook {

    public TreeMap<String, String> phoneBook = new TreeMap<>();
    public TreeMap<String, String> phoneBookReverse = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (name.matches("[А-я]+") && phone.matches("[0-9]{11}")){
            if (phoneBook.containsValue(phone)){
                phoneBook.remove(phoneBookReverse.get(phone));
            }
            phoneBook.put(name, phone);
            phoneBookReverse.put(phone, name);
            System.out.println("Контакт сохранен!");
        }
        else {
            System.out.println("Неверный формат ввода");
        }
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        String name = "";
        if (phoneBook.containsValue(phone)){
            String key = phoneBookReverse.get(phone);
            name = key + " - " + phoneBook.get(key);
            System.out.println(name);
        }
        else {
            System.out.println("Такого номера нет в телефонной книге.");
            System.out.println("Введите имя абонента для номера " + phone + ":");
        }
        return name;
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> phones = new TreeSet<>();
        if (phoneBook.containsKey(name)){
            String phone = name + " - " + phoneBook.get(name);
            System.out.println(phone);
            phones.add(phone);
        }
        else {
            System.out.println("Такого имени в телефонной книге нет.");
            System.out.println("Введите номер телефона для абонента " + name + ":");
        }
        return phones;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<>();
        if (phoneBook.size() != 0) {
            for (String name : phoneBook.keySet()) {
                String value = name + " - " + phoneBook.get(name);
                System.out.println(value);
                allContacts.add(value);
            }
        } else {
            System.out.println("Список пуст");
        }
        return allContacts;
    }
}