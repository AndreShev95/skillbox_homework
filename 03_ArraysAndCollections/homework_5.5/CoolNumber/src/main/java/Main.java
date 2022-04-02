import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {
        List<String> coolNumbers = CoolNumbers.generateCoolNumbers();
        String number = "А555ОР199";

        CoolNumbers.bruteForceSearchInList(coolNumbers, number);

        CoolNumbers.binarySearchInList(coolNumbers, number);

        HashSet<String> hashSet = new HashSet<>(coolNumbers);
        CoolNumbers.searchInHashSet(hashSet, number);

        TreeSet<String> treeSet = new TreeSet<>(coolNumbers);
        CoolNumbers.searchInTreeSet(treeSet, number);
    }
}
