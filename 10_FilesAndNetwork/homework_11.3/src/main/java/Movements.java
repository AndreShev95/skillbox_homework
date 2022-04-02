import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

public class Movements {
    public double expenseSum, incomeSum, additional;
    public boolean repeat = false;
    public TreeSet<Expenses> expensesTreeSet = new TreeSet<>();

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            splitText(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void splitText(List<String> text){
        for (String word : text) {
            String[] movements = word.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 8);
            if (!movements[1].matches("[0-9]+")) {
                continue;
            }
            for (int i = 6; i < 8; i++) {
                if (movements[i].matches("(\").+")) {
                    movements[i] = movements[i].replaceAll("\"", "");
                    movements[i] = movements[i].replace(',', '.');
                }
            }
            countingSum(movements);
        }
    }

    public void countingSum(String[] movements){
        if (movements[7].equals("0")) {
            incomeSum += Double.parseDouble(movements[6]);
        } else {
            expenseSum += Double.parseDouble(movements[7]);
            formatExpense(movements);
        }
    }

    public void formatExpense(String[] input) {
        String key, keyEdit;
        if (!input[5].contains("\\")) {
            key = input[5].substring(input[5].indexOf("/") + 1).substring(0, input[5].indexOf("/"));
        } else {
            key = input[5].substring(input[5].indexOf("\\") + 1).substring(0, input[5].indexOf("\\"));
        }
        double value = Double.parseDouble(input[7]);
        keyEdit = key.replaceAll("[\\\\/]", " ").trim();
        putExpense(keyEdit, value);
    }

    public void putExpense(String name, double value){
        if (!expensesTreeSet.isEmpty()) {
            for (Expenses expense : expensesTreeSet) {
                if (expense.getName().equals(name)) {
                    additional = expense.getSum();
                    repeat = true;
                    break;
                }
            }
        }
        if (!repeat){
            expensesTreeSet.add(new Expenses(name,value));
        }
        else {
            expensesTreeSet.add(new Expenses(name, value + additional));
        }
    }

    public double getExpenseSum() {
        return expenseSum;
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public TreeSet<Expenses> getExpenseDetails() {
        return expensesTreeSet;
    }
}
