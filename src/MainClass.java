import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainClass
{
    public int id;
    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4right;

    public static void main(String[] args) throws SQLException
    {
        String BASE_PATH = "jdbc:sqlite:assets/basequestion.db"; //путь к БД
        DriverManager.registerDriver(new JDBC()); //регистраци и подключение к БД
        Connection connection = DriverManager.getConnection(BASE_PATH); //обьект для соединения с БД

        TestForm app = new TestForm();
        app.setVisible(true);

        Statement statement = connection.createStatement();
        List<String> str = new ArrayList<String>();
        ResultSet resultSet = statement.executeQuery("SELECT id, question, answer1, answer2, answer3, answer4right FROM basequestion WHERE id=1");
        app.setValuesForField(resultSet.getString("id"),
                resultSet.getString("question"),
                resultSet.getString("answer1"),
                resultSet.getString("answer2"),
                resultSet.getString("answer3"),
                resultSet.getString("answer4right"));
    }

}
