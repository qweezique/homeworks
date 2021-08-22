public class TopManager implements Employee {
    private static final int TOP_MANAGER_INCOME_DEMAND = 10_000_000;
    private static final double TOP_MANAGER_MULT = 1.5;
    private int salary = (int) (Math.random() * 50_000) + 50_000;
    private Company company;

    protected TopManager(Company company) {
        setCompany(company);
    }

    @Override
    public int getMonthSalary() {
        if (company.getIncome() >= TOP_MANAGER_INCOME_DEMAND)
            return (int) (salary * TOP_MANAGER_MULT);
        return salary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
