class CardAccount extends BankAccount {
    private static final double COMMISSION_FEE = 0.01;

    protected CardAccount() {
    }

    protected CardAccount(double amount) {
        super(amount);
    }

    @Override
    protected boolean take(double amountToTake) {
        return super.take(amountToTake + getAmountWithCommission(amountToTake));
    }

    private double getAmountWithCommission(double amount) {
        return amount * COMMISSION_FEE;
    }
}
