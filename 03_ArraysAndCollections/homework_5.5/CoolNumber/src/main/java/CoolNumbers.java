import java.util.*;

public class CoolNumbers {
    public static long a, b, c;

    public static List<String> generateCoolNumbers() {
        String coolNumber;
        String[] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        TreeSet<String> arrNumber = new TreeSet<>();
        for(int i = 111; i <= 999; i += 111) {
            for (String a1 : letters) {
                for (String a2 : letters) {
                    for (String a3 : letters) {
                        for (int reg = 1; reg <= 199; reg++) {
                            coolNumber = String.format("%s%03d%s%s%02d", a1, i, a2, a3, reg);
                            arrNumber.add(coolNumber);
                        }
                    }
                }
            }
        }
        List<String> coolNumbers = new ArrayList<>(arrNumber);
        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        a = System.nanoTime();
        boolean search = false;
        for (String coolNumber : list){
            if (coolNumber.equals(number)){
                search = true;
                break;
            }
        }
        b = System.nanoTime();
        c = b - a;
        if (search){
            System.out.println("Поиск перебором: номер " + "найден, поиск занял " + c + " нс");
        }
        else {
            System.out.println("Поиск перебором: номер " + "не найден, поиск занял " + c + " нс");
        }
        return search;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        Collections.sort(sortedList);
        a = System.nanoTime();
        boolean search = false;
        if (Collections.binarySearch(sortedList, number) >= 0){
            search = true;
        }
        b = System.nanoTime();
        c = b - a;
        if (search){
            System.out.println("Бинарный поиск: номер " + "найден, поиск занял " + c + " нс");
        }
        else {
            System.out.println("Бинарный поиск: номер " + "не найден, поиск занял " + c + " нс");
        }
        return search;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        a = System.nanoTime();
        boolean search = false;
        if (hashSet.contains(number)){
                search = true;
        }
        b = System.nanoTime();
        c = b - a;
        if (search){
            System.out.println("Поиск в HashSet: номер " + "найден, поиск занял " + c + " нс");
        }
        else {
            System.out.println("Поиск в HashSet: номер " + "не найден, поиск занял " + c + " нс");
        }
        return search;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        a = System.nanoTime();
        boolean search = false;
        if (treeSet.contains(number)){
            search = true;
        }
        b = System.nanoTime();
        c = b - a;
        if (search){
            System.out.println("Поиск в TreeSet: номер " + "найден, поиск занял " + c + " нс");
        }
        else {
            System.out.println("Поиск в TreeSet: номер " + "не найден, поиск занял " + c + " нс");
        }
        return search;
    }

}
