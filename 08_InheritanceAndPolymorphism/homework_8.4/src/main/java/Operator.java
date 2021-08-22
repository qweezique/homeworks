public class Operator implements Employee {
    private int salary = (int) (Math.random() * 30_000) + 30_000;

    @Override
    public int getMonthSalary() {
        return salary;
    }

}
