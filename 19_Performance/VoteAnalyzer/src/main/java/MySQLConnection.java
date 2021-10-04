import java.sql.*;

public class MySQLConnection {
  private static String dbURL = "jdbc:mysql://localhost:3306/optimization?autoReconnect=true&useSSL=false";
  private static String dbUser = "root";
  private static String dbPass = "20148332";

    private static Connection connection;
    public static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate) " +
                "VALUES " + insertQuery.toString();
        MySQLConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        insertQuery.append(insertQuery.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("')");
        if (insertQuery.length() > 100_000) {
            executeMultiInsert();
            insertQuery = new StringBuilder();
        }
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, COUNT(name) as visits_count FROM voter_count "
                + "GROUP BY name having count(*) > 1 ";
        ResultSet rs = MySQLConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("visits_count"));
        }
    }
}
