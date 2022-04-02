public class TopManager implements Employee{
    private final int MONTH_SALARY_FIX = 50000;

    private int salary;
    private String position;


    public TopManager() {
        setPosition();
        setMonthSalary();
    }
    public void setMonthSalary() {
        salary = MONTH_SALARY_FIX;
    }

    public int getMonthSalary() {
        if (Company.getIncome() > 10000000) {
            salary = (int) (MONTH_SALARY_FIX * 2.5);
        }
        return salary;
    }

    public void setPosition() {
        position = "ТОП менеджер";
    }

    public String getPosition() {
        return position;
    }

    public String toString() {
        return getPosition() + " " + salary;
    }
}
