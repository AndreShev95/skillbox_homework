public class Manager implements Employee{
    private final int MONTH_SALARY_FIX = 40000;
    private final int MAX_PROFIT = 140000;
    private final int MIN_PROFIT = 115000;

    private int salary;
    private String position;

    public Manager() {
        setPosition();
        setMonthSalary();
    }
    public void setMonthSalary() {
        salary = (int) (MONTH_SALARY_FIX + ((MIN_PROFIT - 1) + Math.random() * MAX_PROFIT) * 0.05);
    }

    public int getMonthSalary() {
        return salary;
    }

    public void setPosition() {
        position = "Менеджер";
    }

    public String getPosition() {
        return position;
    }
    public String toString() {
        return getPosition() + " " + getMonthSalary();
    }

}
