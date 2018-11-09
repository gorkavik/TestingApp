import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TestForm extends JFrame
{
    private JButton buttonNext;
    private JPanel panelTest;
    private JRadioButton radioButtonAnswer1;
    private JRadioButton radioButtonAnswer2;
    private JRadioButton radioButtonAnswer3;
    private JRadioButton radioButtonAnswer4;
    private JLabel lablePicture;
    private JLabel lableQuestion;

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4right;
    private int rowCount = 0;

    public ResultSet resultSet;
    public int i = 1;
    public int id1=3;

    public TestForm() throws SQLException
    {
//инициализация формы
        super("Тестирование");

        this.setSize(700, 700);
        this.setContentPane(panelTest);
        setDefaultCloseOperation();

        lablePicture.setIcon(new ImageIcon("pictures/pic1.jpg"));
//группа взаимосвязанных обьектов
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonAnswer1);
        group.add(radioButtonAnswer2);
        group.add(radioButtonAnswer3);
        group.add(radioButtonAnswer4);

        String BASE_PATH = "jdbc:sqlite:assets/basequestion.db"; //путь к БД
        DriverManager.registerDriver(new JDBC()); //регистрация и подключение к БД
        Connection connection = DriverManager.getConnection(BASE_PATH); //обьект для соединения с БД
//создание взаимодействия с базой
        Statement statement = connection.createStatement();

//получение выборки
        resultSet = statement.executeQuery("SELECT Question, Answer1, Answer2, Answer3, Answer4right " +
                "FROM basequestion WHERE Test=2");

//получение количества строк

        while (resultSet.next())
        {
            ++rowCount;
        }
        //String s = "row " + rowCount + " ";

//вывод конкретной строки


        buttonNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (i <= rowCount)
                {
                try
                {
                    resultSet = statement.executeQuery("SELECT ID, Question, Answer1, Answer2, Answer3, Answer4right " +
                            "FROM basequestion WHERE (Test=2 AND ID="+id1+")");
                    id = resultSet.getString("ID");
                    question = resultSet.getString("Question");
                    answer1 = resultSet.getString("Answer1");
                    answer2 = resultSet.getString("Answer2");
                    answer3 = resultSet.getString("Answer3");
                    answer4right = resultSet.getString("Answer4right");

                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


                lablePicture.setIcon(new ImageIcon("pictures/pic2.jpg"));
                lableQuestion.setText(id + ". " + question);
                radioButtonAnswer1.setText(answer1);
                radioButtonAnswer2.setText(answer2);
                radioButtonAnswer3.setText(answer3);
                radioButtonAnswer4.setText(answer4right);
                ++i;
                    id1++;
                } else
                {
                    JOptionPane.showMessageDialog(null,
                            "Гребаные вопросы закончились",
                            "Зэ энд",
                            JOptionPane.PLAIN_MESSAGE);

                }
            }
        });
    }

    private void setDefaultCloseOperation() throws SQLException
    {
        this.setVisible(false);
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);

    }


}
