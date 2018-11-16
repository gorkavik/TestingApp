import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ListWithData extends JFrame
{
    private JPanel panelList;
    public ResultSet resultSet;

    public ListWithData(String data) throws SQLException
    {
      //  setDefaultCloseOperation();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String BASE_PATH = "jdbc:sqlite:assets/basequestion.db"; //путь к БД
        DriverManager.registerDriver(new JDBC()); //регистрация и подключение к БД
        Connection connection = DriverManager.getConnection(BASE_PATH); //обьект для соединения с БД
//создание взаимодействия с базой
        Statement statement = connection.createStatement();
        String[][] data1 = null;
        String[] headers = null;

        switch (data)
        {
            case "questions":
            {
                resultSet = statement.executeQuery("SELECT * FROM basequestion");

                int count = 0;
                data1 = new String[5][6];
                int i = 0;
                while (resultSet.next())
                {
                    //Массив содержащий информацию для таблицы
                    data1[i][0] = resultSet.getString("ID");
                    data1[i][1] = resultSet.getString("Question");
                    data1[i][2] = resultSet.getString("Answer1");
                    data1[i][3] = resultSet.getString("Answer2");
                    data1[i][4] = resultSet.getString("Answer3");
                    data1[i][5] = resultSet.getString("Answer4right");

                    i++;
                    count++;
                }

                System.out.println("Количество вопросов в базе: " + count);
                //Массив содержащий заголоки таблицы
                headers = new String[]{"Номер", "Вопрос", "Ответ 1", "Ответ 2", "Ответ 3", "Ответ 4 (правильный)"};
                break;
            }

            case "users":
            {
                resultSet = statement.executeQuery("SELECT u.ID, u.Firstname, u.Lastname, u.Year, "
                        +"u.Kurs, u.Spec, u.Grup, l.Userlogin FROM baseusers u LEFT JOIN baselogin l ON "
                +"u.Userlogin=l.ID");

                int count = 0;
                data1 = new String[5][8];
                int i = 0;
                while (resultSet.next())
                {
                    //Массив содержащий информацию для таблицы
                    data1[i][0] = resultSet.getString("ID");
                    data1[i][1] = resultSet.getString("Userlogin");
                    data1[i][2] = resultSet.getString("Firstname");
                    data1[i][3] = resultSet.getString("Lastname");
                    data1[i][4] = resultSet.getString("Year");
                    data1[i][5] = resultSet.getString("Kurs");
                    data1[i][6] = resultSet.getString("Spec");
                    data1[i][7] = resultSet.getString("Grup");
                    i++;
                    count++;
                }

                System.out.println("Количество вопросов в базе: " + count);
                //Массив содержащий заголоки таблицы
                headers = new String[]{"Номер", "Тип авторизации", "Имя", "Фамилия", "Год", "Курс",
                        "Специальность", "Группа"};

                break;
            }
        }


        JTable tabOfQuestions;

        //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("Список");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(700, 700);
//        //Устанавливаем завершение программы при закрытии окна
//        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        tabOfQuestions = new JTable(data1, headers);
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(tabOfQuestions);
        //Устаналиваем размеры прокручиваемой области
        tabOfQuestions.setPreferredScrollableViewportSize(new Dimension(650, 650));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        jfrm.getContentPane().add(jscrlp);
        //Отображаем контейнер
        jfrm.setVisible(true);

    }

//    private void setDefaultCloseOperation() throws SQLException
//    {
//        this.setVisible(false);
//        SwitchForm switchForm = new SwitchForm();
//        switchForm.setVisible(true);
//
//    }
}
