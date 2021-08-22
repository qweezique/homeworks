import java.util.Random;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Random randomMoneyAmount = new Random();
        Bank bank = new Bank();
        Account account1 = new Account("1", 100_000);
        Account account2 = new Account("2", 200_000);
        Account account3 = new Account("3", 400_000);

        bank.setAccount(account1);
        bank.setAccount(account2);
        bank.setAccount(account3);

        System.out.println(bank.getBalance("1"));
        System.out.println(bank.getBalance("2"));
        System.out.println(bank.getBalance("3"));
        System.out.println("Баланс банка " + bank.getSumAllAccounts());

        System.out.println("********************************");



//        bank.transfer("3", "1", 60_000);
//        bank.transfer("3", "1", 30_000);
//        bank.transfer("3", "1", 20_000);
//        bank.transfer("3", "1", 80_000);
//
//        System.out.println(bank.getBalance("1"));
//        System.out.println(bank.getBalance("3"));
//
//        System.out.println("Баланс банка " + bank.getSumAllAccounts());



       Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {

                    System.out.println("\nThread-1: transfer from account1 ti account2");
                    bank.transfer("1", "2", randomMoneyAmount.nextInt(30000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {

                    System.out.println("\nThread-2: transfer from account2 ti account3");
                    bank.transfer("2", "3", randomMoneyAmount.nextInt(20000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {

                    System.out.println("\nThread-3: transfer from account3 ti account1");
                    bank.transfer("3", "1", randomMoneyAmount.nextInt(50000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();


        System.out.println(bank.getBalance("1"));
        System.out.println(bank.getBalance("2"));
        System.out.println(bank.getBalance("3"));
        System.out.println("Баланс банка " + bank.getSumAllAccounts());


    }
}
