import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
    private JButton buttonBegin;
    private JSeparator separator1;
    private JSeparator separator2;
    private JSeparator separator3;

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4right;
    private int rowCount = 0;

    public ResultSet resultSet;
    public int i = 0;
    public int id1 = 3;
    public String sel = "";
    private ButtonGroup group = new ButtonGroup();

    public TestForm() throws SQLException
    {
//инициализация формы
        super("Тестирование");

        this.setSize(700, 700);
        this.setContentPane(panelTest);
        setDefaultCloseOperation();

        lablePicture.setIcon(new ImageIcon("pictures/pic1.jpg"));
//группа взаимосвязанных обьектов

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


//вывод конкретной строки

        buttonNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               getSomeQuestion(statement);
                group.clearSelection();
            }
        });
        buttonBegin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonBegin.setVisible(false);
                buttonNext.setVisible(true);
                radioButtonAnswer1.setVisible(true);
                radioButtonAnswer2.setVisible(true);
                radioButtonAnswer3.setVisible(true);
                radioButtonAnswer4.setVisible(true);
                lableQuestion.setVisible(true);
                separator1.setVisible(true);
                separator2.setVisible(true);
                separator3.setVisible(true);

                getSomeQuestion(statement);
            }
        });
    }

    private void setDefaultCloseOperation() throws SQLException
    {
        this.setVisible(false);
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);

    }

    private void getSomeQuestion(Statement statement)
    {
        if (i < rowCount)
        {
            try
            {
                resultSet = statement.executeQuery("SELECT ID, Question, Answer1, Answer2, Answer3, Answer4right " +
                        "FROM basequestion WHERE (Test=2 AND ID=" + id1 + ")");
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
            ArrayList<String> list1=new ArrayList<>();
            list1.add(answer1);
            list1.add(answer2);
            list1.add(answer3);
            list1.add(answer4right);

            lablePicture.setIcon(new ImageIcon("pictures/pic2.jpg"));
            lableQuestion.setText(id + ". " + question);

            Random rand = new Random();
            ArrayList<String> list2=new ArrayList<>();
            while(list1.size() > 0) {
                int index = rand.nextInt(list1.size());
                list2.add(list1.remove(index));
            }

            radioButtonAnswer1.setText(list2.get(0));
            radioButtonAnswer2.setText(list2.get(1));
            radioButtonAnswer3.setText(list2.get(2));
            radioButtonAnswer4.setText(list2.get(3));

            ++i;
            id1++;

            if (radioButtonAnswer1.isSelected())
            {
                sel = sel + 1;
                radioButtonAnswer1.setSelected(false);
            } else if (radioButtonAnswer2.isSelected())
            {
                sel = sel + 2;
            } else if (radioButtonAnswer3.isSelected())
            {
                sel = sel + 3;
            } else if (radioButtonAnswer4.isSelected())
            {
                sel = sel + 4;
            }

        } else
        {
            if (radioButtonAnswer1.isSelected())
            {
                sel = sel + 1;
            } else if (radioButtonAnswer2.isSelected())
            {
                sel = sel + 2;
            } else if (radioButtonAnswer3.isSelected())
            {
                sel = sel + 3;
            } else if (radioButtonAnswer4.isSelected())
            {
                sel = sel + 4;
            }
            String message = "";
            message += "Гребаные вопросы закончились. Конец теста.\n";
            message += "Даны ответы: " + sel;

            JOptionPane.showMessageDialog(null,
                    message,
                    "Зэ энд",
                    JOptionPane.PLAIN_MESSAGE);


        }
    }

}
