class LegalPerson extends PhysicalPerson {
    private static final double FEE_ = 0.01;

    @Override
    protected void take(double amountToTake) {
        super.take(amountToTake + getAmountWithCommission(amountToTake));
    }

    protected double getAmountWithCommission(double amount) {
        return amount * FEE_;
    }

    @Override
    protected void info() {
        System.out.println("Пополнение - без комиссии");
        System.out.println("Снятие - комиссия 1%");
    }
}
