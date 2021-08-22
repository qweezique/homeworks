import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pw = "20148332";


        try {
            Connection connection = DriverManager.getConnection(url, user, pw);
            Statement statement = connection.createStatement();
            String sqlQuery = ("SELECT pl.course_name, \n" +
                    "COUNT(pl.subscription_date)/\n" +
                    "(month(max(subscription_date)) - month(min(subscription_date)) + 1) as AVG_PURCHASE_PER_MONTH\n" +
                    "FROM PurchaseList pl \n" +
                    "WHERE YEAR (pl.subscription_date) = 2018\n" +
                    "GROUP BY pl.course_name \n" +
                    "ORDER BY AVG_PURCHASE_PER_MONTH DESC;");

            ResultSet resultset = statement.executeQuery(sqlQuery);
            while (resultset.next()){
                String courseName = resultset.getString("course_name");
                String avgPurchasePerMonth = resultset.getString("AVG_PURCHASE_PER_MONTH");
                System.out.println(courseName + "\t" +avgPurchasePerMonth);
            }

            connection.close();
            statement.close();
            resultset.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
