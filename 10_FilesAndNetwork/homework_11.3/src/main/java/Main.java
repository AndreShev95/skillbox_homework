import java.util.Set;

public class Main {
    public static final String PATH_FILE = "src/test/resources/movementList.csv";
    public static void main(String[] args) {
        Movements movement = new Movements(PATH_FILE);
        System.out.printf("Сумма расходов: %.2f руб.%n", movement.getExpenseSum());
        System.out.printf("Сумма доходов: %.2f руб.%n%n", movement.getIncomeSum());
        System.out.println("Суммы расходов по организациям: ");
        Set<Expenses> treeSet = movement.getExpenseDetails();
        for (Expenses expense : treeSet){
            String value = String.format("%.2f", expense.getSum());
            System.out.println(expense.getName() + " " + value + " руб.");
        }
    }
}
