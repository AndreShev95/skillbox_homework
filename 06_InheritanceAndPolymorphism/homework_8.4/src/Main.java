public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.setIncome(11000000);

        for (int i = 1; i <= 80; i++)
        {
            company.hire(new Manager());
        }

        company.hireAll(new TopManager(), 1);
        company.getLowestSalaryStaff(30);
        company.getTopSalaryStaff(15);

        //company.fire(company.getCount() / 2);

        System.out.println(company.getEmployee(1));

        Company company2 = new Company();

        company.changingCompany(company.getEmployee(1), company2);
        System.out.println(company.getEmployee(1));
        company2.getTopSalaryStaff(1);

    }
}
