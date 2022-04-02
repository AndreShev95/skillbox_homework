import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "AndreLoveProgramm95";
        int year = 2018;

        try {
            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT pl.course_name, " +
                        "(SELECT COUNT(*) FROM purchaselist WHERE course_name = pl.course_name)/" +
                        "(MONTH(MAX(pl.subscription_date)) - MONTH(MIN(pl.subscription_date)) + 1) AS " +
                        "average_purchase_in_month FROM PurchaseList pl WHERE YEAR(pl.subscription_date) = ? " +
                        "GROUP BY pl.course_name;");
                try (preparedStatement) {
                    preparedStatement.setInt(1, year);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        System.out.printf("Программа выводит среднее количество покупок в месяц для каждого курса " +
                                "в %d году.%n", year);

                        while (resultSet.next()) {
                            String courseName = resultSet.getString("course_name");
                            double month = resultSet.getDouble("average_purchase_in_month");
                            System.out.println(courseName + " - " + month);
                        }
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
