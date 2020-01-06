package connection;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//192.168.198.65:1521/xe", "hr", "hr");
            Statement stmt = con.createStatement();

            for (int i = 100; i < 120; i++) {
                ResultSet rs = stmt.executeQuery(" select * from employees where employee_id=" + i);
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

            ResultSet rs = stmt.executeQuery("select sn.NAME,ms.VALUE from v$mystat ms, v$statname sn where ms.STATISTIC#=sn.STATISTIC# and (sn.NAME ='parse count (total)' or sn.NAME = 'parse count (hard)')");
            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getInt(2));


            String sql;
            PreparedStatement statement;
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            ;
            ResultSet resultset;
            String name;


            try {
                sql = "SELECT t.first_name FROM hr.employees t WHERE employee_id = ?";
                //sql2 = "";
                statement = connection.prepareStatement(sql);
                for (int i = 0; i < 10000; i++) {
                    statement.setInt(1, i);
                    resultset = statement.executeQuery();
                    if (resultset.next()) {
                        name = resultset.getString("first_name");
                        System.out.println(name);
                    }

                    resultset.close();
                }
                statement.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

