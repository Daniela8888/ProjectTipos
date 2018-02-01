package sk.akademiasovy.tipos.main.database;

import sk.akademiasovy.tipos.main.Bet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabase {
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String dbName = "tipos";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String userName2 = "user2";
    private final String userName1 = "user";
    private final String password = "secret";
    private Connection conn;

    public void testConnection() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName1, password);
            if (conn == null) {
                System.out.println("Connection failed");

            } else {
                System.out.println("Connection OK");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error. I cannot connect to the database!");
        }

    }

    public boolean insertValuesIntoDrawHistory(int arr[]) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName1, password);
            String ccc = "INSERT INTO draw_history(ball1, ball2, ball3, ball4, ball5)";
            ccc += "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(ccc);
            preparedStatement.setInt(1, arr[1]);
            preparedStatement.setInt(2, arr[2]);
            preparedStatement.setInt(3, arr[3]);
            preparedStatement.setInt(4, arr[4]);
            preparedStatement.setInt(5, arr[5]);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error. I cannot connect to the database!");
        }
        return true;
    }

    public List<Bet> getNewBets() {
        try {
            Class.forName(driver).newInstance();
            List<Bet> list = new ArrayList<>();
            conn = DriverManager.getConnection(url + dbName, userName1, password);
            String ccc = "SELECT * FROM bets" + "INNER JOIN bet_details ON bets.id=bet_details.idb " + "WHERE bets.draw_id IS NULL";
            PreparedStatement preparedStatement = conn.prepareStatement(ccc);
            ResultSet resultSet = preparedStatement.executeQuery();                                               //resultset=tabulka
            while (resultSet.next()) {
                System.out.println("Bet: " + resultSet.getInt("id") + "user id: " + resultSet.getInt("idu") + " date: " + resultSet.getDate("date"));
                System.out.println(" > " + resultSet.getInt("bet1") + " " + resultSet.getInt("bet2") + " " + resultSet.getInt("bet3") + " " + resultSet.getInt("bet4") + " " + resultSet.getInt("bet5"));
                Bet bet = new Bet(resultSet.getInt("id"), resultSet.getInt("idu"), resultSet.getDate("date"), resultSet.getInt("bet1"), resultSet.getInt("bet2"), resultSet.getInt("bet3"), resultSet.getInt("bet4"), resultSet.getInt("bet5"));
                list.add(bet);
            }
            return list;

        }catch (Exception e) {
            System.out.println("Error." + e.getMessage());

        }
            return null;
    }
}




