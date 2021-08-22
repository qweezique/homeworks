import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts = new Hashtable<>();
    private final Random random = new Random();
    private final long FREE_TRANSACTIONS_SUM = 50_000;

    public synchronized void isFraud(String accNum)
            throws InterruptedException {
        System.out.println("Выполняется проверка Службы Безопасности");
        Thread.sleep(1000);
        accounts.get(accNum).setBlockStatus(random.nextBoolean());
    }

    public void setAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }

    public void transfer(String fromAccNumber, String toAccNumber, long amount) throws InterruptedException {

        if (accounts.get(fromAccNumber).getBlockStatus() == false) {
            accounts.get(fromAccNumber).withdrawMoney(amount);
            accounts.get(toAccNumber).depositMoney(amount);
            accounts.get(fromAccNumber).saveTransaction(amount);
            System.out.println("Перевод на сумму " + amount + " - СОВЕРШЕН");

            if (accounts.get(fromAccNumber).getTransactionsSum() > FREE_TRANSACTIONS_SUM) {
                isFraud(fromAccNumber);
                accounts.get(fromAccNumber).printBlockStatus();
            }
        } else {
            System.out.println("Перевод на сумму " + amount + " - ОТКЛОНЕН");
            System.out.println("Обратитесь в Службу Безопасности");
        }
    }

    public long getBalance(String accNumber) {
        return accounts.get(accNumber).getMoney();
    }

    public long getSumAllAccounts() {
        return accounts.entrySet().stream().mapToLong(a -> a.getValue().getMoney()).sum();
    }
}
