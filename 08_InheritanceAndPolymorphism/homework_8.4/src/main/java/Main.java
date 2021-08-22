import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Company company = new Company();


        for (int i = 0; i < 100; i++) {
            company.hire(new Operator());
        }

        List<Employee> workers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            workers.add(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            workers.add(new TopManager(company));
        }

        company.hireAll(workers);

        printTopAndLowestSalary(company);
        System.out.println();
        System.out.println();
        System.out.println("Количество сотрудников = " + company.getEmployeesList().size());
        System.out.println("Доход компании = " + company.getIncome());

        int halfSize = company.getEmployeesList().size() / 2;
        for (int i = 0; i < halfSize; i++) {
            company.fire(company.getEmployeesList().get(i));
        }
        System.out.println();
        System.out.println("Количество сотрудников после сокращения 50% штата = " + company.getEmployeesList().size());
        System.out.println("Доход компании после сокращения штата = " + company.getIncome());
        System.out.println();
        System.out.println();
        printTopAndLowestSalary(company);
    }

    private static void printTopAndLowestSalary(Company company) {
        System.out.println("=======TOP SALARY STAFF=======");
        List<Employee> topSalary = company.getTopSalaryStaff(5);
        for (Employee e : topSalary) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("==============================");

        System.out.println("======LOWEST SALARY STAFF======");
        List<Employee> lowSalary = company.getLowestSalaryStaff(5);
        for (Employee e : lowSalary) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("==============================");
    }
}
