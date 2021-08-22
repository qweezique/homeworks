public class FinanceOperation {
    private String id;
    private String operationId;
    private String operationDate;
    private String operationDescription;


    private double income;
    private double expense;

    @Override
    public String toString() {
        return "Счет № " + id + ":" + '{' +
                '#'+ operationId +
                ",\tДата= " + operationDate +
                ", Приход= " + income +
                ", Расход= " + expense +
                 "\n" +operationDescription +
                '}';
    }

    public FinanceOperation(String id, String operationId, String operationDate, Double income, Double expense, String operationDescription) {
        this.id = id;
        this.operationId = operationId;
        this.operationDate = operationDate;
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public String getId() {
        return id;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

}
