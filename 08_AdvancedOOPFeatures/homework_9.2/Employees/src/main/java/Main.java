import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
        Optional <Employee> optional = staff.stream()
                .filter(checkYearStart(year))
                .max(Comparator.comparing(Employee::getSalary));
        return optional.orElse(null);
    }

    public static Predicate <Employee> checkYearStart(int year){
        return e -> {
            LocalDate localDate = e.getWorkStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate.getYear() == year;
        };
    }

}