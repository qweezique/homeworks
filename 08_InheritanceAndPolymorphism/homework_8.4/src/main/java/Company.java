import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {
    private final List<Employee> employeesList = new ArrayList<>();

    protected List<Employee> getEmployeesList() {
        return new ArrayList<>(employeesList);
    }

    protected int getIncome() {
        int income = 0;
        for (Employee e : employeesList) {
            if (e instanceof Manager) {
                income += ((Manager) e).getSales();
            }
        }
        return income;
    }

    protected void hire(Employee employee) {
        employee.setCompany(this);
        employeesList.add(employee);
    }

    protected void hireAll(List <Employee> list) {
        employeesList.addAll(list);
    }


    protected void fire(Employee employee) {
        employee.setCompany(null);
        employeesList.remove(employee);
    }


    protected List<Employee> getList(int count, Comparator<Employee> cmp) {
        if (count < 0) {
            return Collections.emptyList();
        }
        if (count > employeesList.size()) {
            count = employeesList.size();
        }
        employeesList.sort(cmp);
        return new ArrayList<>(employeesList.subList(0, count));
    }


    protected List<Employee> getTopSalaryStaff(int count) {
        return getList(count, Comparator.reverseOrder());
    }

    protected List<Employee> getLowestSalaryStaff(int count) {
        return getList(count, Comparator.naturalOrder());
    }
}























