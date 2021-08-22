class PhysicalPerson extends Client {
    protected PhysicalPerson() {
    }

    protected PhysicalPerson(double amount) {
        super(amount);
    }

    @Override
    protected void info() {
        System.out.println("Пополнение и снятие средств - без комиссии");
    }
}
