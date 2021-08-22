class BankAccount {

    private double amount;

    protected BankAccount() {
    }

    protected BankAccount(double amount) {
        this.amount = amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }

    protected double getAmount() {
        return amount;
    }

    protected void put(double amountToPut) {
        if (amountToPut >= 0) {
            amount += amountToPut;
            System.out.println(getAmount());
        }
    }

    protected boolean take(double amountToTake) {
        if (amountToTake > getAmount() || amountToTake < 0) {
            System.out.println("Недостаточно средств для вывода");
            return false;
        } else {
            amount -= amountToTake;
            System.out.println(getAmount());
            return true;
        }
    }

    protected boolean send(BankAccount receiver, double amount) {
        if (take(amount)) {
            receiver.put(amount);
            System.out.println("Перевод на сумму " + amount + " успешно осуществлён");
            return true;
        } else {
            System.out.println("Введите сумму перевода, не превышающую текущий остаток на счёте");
            return false;
        }
    }
}
