import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Movements {
    private List<FinanceOperation> financeOperations;

    public void getFinanceOperations() {
        financeOperations.forEach(System.out::println);
    }

    public Movements(String pathMovementsCsv) {
        try {

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(pathMovementsCsv)));

            csvReader.skip(1);

            String[] fileLines;
            this.financeOperations = new ArrayList<>();

            while ((fileLines = csvReader.readNext()) != null) {
                financeOperations.add(new FinanceOperation(fileLines[1]
                        , fileLines[4]
                        , fileLines[3]
                        , Double.parseDouble(fileLines[6].replaceAll("\"", "").replaceAll(",", "\\."))
                        , Double.parseDouble(fileLines[7].replaceAll("\"", "").replaceAll(",", "\\."))
                        , stringOperationDescFormat(fileLines[5])));
            }

        } catch (
                IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static String stringOperationDescFormat(String text) {
        return text.substring(text.indexOf(" "), text.indexOf("        "))
                .replaceAll("([<>/])", " ")
                .replaceAll(" ", "").strip();
    }

    public double getExpenseSum() {
        return
                financeOperations.stream().mapToDouble(FinanceOperation::getExpense).sum();
    }

    public double getIncomeSum() {
       return
                financeOperations.stream().mapToDouble(FinanceOperation::getIncome).sum();
    }

    public void getDetailExpanse() {
        System.out.print("Детализация расходов:\n");
        for (FinanceOperation financeOperation : financeOperations) {
            System.out.printf("%s %s\t-\t%.2f₽\n", financeOperation.getOperationDate(), financeOperation.getOperationDescription(), financeOperation.getExpense());
        }
    }

    public void getSummaryDetailExpanse() {
        System.out.print("Сводная детализация расходов:\n");
        Map<String, Double> summaryDetailExpMap = new TreeMap<>();
        for (FinanceOperation financeOperation : financeOperations) {
            summaryDetailExpMap.put(financeOperation.getOperationDescription(), financeOperation.getExpense());
        }

        for (Map.Entry<String, Double> e : summaryDetailExpMap.entrySet()) {
            System.out.printf("%s\t-\t%.2f₽\n",e.getKey(), e.getValue());
        }
    }

}
