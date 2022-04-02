import java.util.*;

public class Company{

    public static int income;

    public ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public void setIncome(int income) {
        this.income = income;
    }

    public static int getIncome() {
        return income;
    }


    public Company() {
        new ArrayList<String>();
        setIncome(income);
    }

    public ArrayList<Employee> hireAll(Employee employee, int count) {
        for (int i = 0; i < count; i++) {
            employeeArrayList.add(employee);
        }
        return employeeArrayList;
    }

    public void hire(Employee employee) {
        employeeArrayList.add(employee);
    }

    public int getCount () { return employeeArrayList.size(); }

    public void fire(int count) {
        for (int i = 0; i < count; i++)
        employeeArrayList.remove(i);
    }

    public Employee getEmployee(int index){
        return employeeArrayList.get(index);
    }

    public void fire(Employee employee) {
        employeeArrayList.remove(employee);
    }

    public void changingCompany(Employee employee, Company company) {
        company.hire(employee);
        fire(employee);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        new ArrayList<String>();
        employeeArrayList.sort((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary())
            {
                return -1;
            }
            else if (o1.getMonthSalary() < o2.getMonthSalary()){
                return 1;
            }
            return 0;
        });

        if (count <= employeeArrayList.size() && count > 0){
            System.out.println("\n" + "Список " + count + " самых высоких зарплат: ");
            for (int i = 0; i < count; i++) {
                System.out.println("# " + (i + 1) + " " + "\t" + employeeArrayList.get(i) + " руб.");
            }

            return employeeArrayList;
        }
        else {
            System.out.println("В компании нет такого количества сотрудников, введите число от 1 до " + getCount());
            return new ArrayList<>();
        }
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        new ArrayList<String>();
        employeeArrayList.sort((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary())
            {
                return 1;
            }
            else if (o1.getMonthSalary() < o2.getMonthSalary()){
                return -1;
            }
            return 0;
        });

        if (count <= employeeArrayList.size() && count > 0){
            System.out.println("\n" + "Список " + count + " самых низких зарплат: ");
            for (int i = 0; i < count; i++) {
                System.out.println("# " + (i + 1) + " " + "\t" + employeeArrayList.get(i) + " руб.");
            }
            return employeeArrayList;
        }
        else {
            System.out.println("В компании нет такого количества сотрудников, введите число от 1 до " + getCount());
            return new ArrayList<>();
        }
    }

}
