public interface Employee extends Comparable<Employee> {
    int getMonthSalary();

    default void setCompany(Company company) {
    }

    @Override
    default int compareTo(Employee o) {
        return getMonthSalary() - o.getMonthSalary();
    }

}