import java.time.LocalDate;

class DepositAccount extends BankAccount {

    protected DepositAccount() {
    }

    protected DepositAccount(double amount) {
        super(amount);
    }

    private LocalDate lastIncome;

    @Override
    protected boolean take(double amountToTake) {
        LocalDate dateCanTake = lastIncome.plusMonths(1);
        if (LocalDate.now().isAfter(dateCanTake)) {
            super.take(amountToTake);
            return true;
        } else
            System.out.println("Вы сможете снять деньги со счета после " + dateCanTake);
        return false;
    }

    @Override
    protected void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }
}

