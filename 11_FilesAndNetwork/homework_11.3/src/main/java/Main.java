public class Main {



    public static void main(String[] args) {
        final String path = "/Users/qweezique/IdeaProjects/java_basics/11_FilesAndNetwork/homework_11.3/src/Data/movementList.csv";

        Movements financeMovement = new Movements(path);
        financeMovement.getFinanceOperations();
        System.out.println(financeMovement.getExpenseSum());
        System.out.println(financeMovement.getIncomeSum());
        financeMovement.getDetailExpanse();
        financeMovement.getSummaryDetailExpanse();
    }
}

