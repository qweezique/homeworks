abstract class Client {
    private double amount;


    protected Client() {

    }

    protected Client(double amount) {
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

    protected void take(double amountToTake) {
        if (amountToTake > getAmount() || amountToTake < 0) {
            System.out.println("Недостаточно средств для вывода");
        } else {
            amount -= amountToTake;
            System.out.println(getAmount());
        }
    }

    protected abstract void info();

}
