public class Expenses implements Comparable<Expenses>{
    private String name;
    private double sum;

    public Expenses(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }
    public String getName(){
        return name;
    }

    public double getSum(){
        return sum;
    }

    @Override
    public int compareTo(Expenses expense) {
        return name.compareTo(expense.getName());
    }
}
