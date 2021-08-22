import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accNumber;
    private long money;
    private List<Long> transactions = new ArrayList<>();
    private volatile boolean isBlock = false;

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void depositMoney(long moneyAmount) {
        if (moneyAmount >= 0)
            money += moneyAmount;
        else
            System.out.println("ОШИБКА: передано отрицательное значение");
    }

    public void withdrawMoney(long moneyAmount) {
        if (moneyAmount >= 0)
            money -= moneyAmount;
        else
            System.out.println("ОШИБКА: передано отрицательное значение");
    }

    public List<Long> getTransactions() {
        return transactions;
    }

    public long getTransactionsSum() {
        return
                getTransactions().stream().reduce(0L, Long::sum);
    }

    public void saveTransaction(long transactionAmount) {
        transactions.add(transactionAmount);
    }

    public void printBlockStatus() {

        if (isBlock == false)
            System.out.println("Вы можете выполнять транзакции");
        else
            System.out.println("Дальнейшие операции по счету заблокированы: \n!!! Выявлены подозрительные операции, обратитесь в Службу Безопасности банка");
    }

    public void setBlockStatus(boolean trueIsBlocked) {
        this.isBlock = trueIsBlocked;
    }

    public boolean getBlockStatus() {
        return this.isBlock;
    }
}
