public class Operator implements Employee{
    private final int MONTH_SALARY_FIX = 30000;
    private int salary;
    private String position;

    public Operator() {
        setPosition();
        setMonthSalary();
    }
    public void setMonthSalary() {
        salary = MONTH_SALARY_FIX;
    }

    public int getMonthSalary() {
        return salary;
    }

    public void setPosition() {
        position = "Оператор";
    }

    public String getPosition() {
        return position;
    }
    public String toString() {
        return getPosition() + " " + getMonthSalary();
    }
}
