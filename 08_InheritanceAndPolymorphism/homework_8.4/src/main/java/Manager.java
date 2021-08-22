public class Manager implements Employee {

    private static final int MAX_SALES = 140_000;
    private static final int MIN_SALES = 115_000;
    private static final double MANAGER_MULT = 0.05;
    private int salary = (int) (Math.random() * 40_000) + 40_000;
    private int sales;

    protected Manager() {
        sales = (int) Math.random() * (MAX_SALES - MIN_SALES) + MIN_SALES;
        salary += sales * MANAGER_MULT;
    }

    protected int getSales() {
        return sales;
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }
}
