import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class TestForm extends JFrame {
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
    private String answer;
    private int rowCount = 0;

    public ResultSet resultSet;

    public int i = 0;
    public int sel = 0;
    private ButtonGroup group = new ButtonGroup();
    private ArrayList<String> answerList = new ArrayList<>();
    private String answerText;
    private boolean equalsValue;

    public TestForm() throws SQLException {
//инициализация формы
        super("Тестирование");

        this.setSize(700, 700);
        this.setContentPane(panelTest);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


//группа взаимосвязанных обьектов

        group.add(radioButtonAnswer1);
        group.add(radioButtonAnswer2);
        group.add(radioButtonAnswer3);
        group.add(radioButtonAnswer4);

        // Random randtest = new Random();
        int test = 1;// randtest.nextInt(2);
        lablePicture.setIcon(new ImageIcon("pictures/pic" + test + "01.jpg"));

        String BASE_PATH = "jdbc:sqlite:assets/basequestion.db"; //путь к БД
        DriverManager.registerDriver(new JDBC()); //регистрация и подключение к БД
        Connection connection = DriverManager.getConnection(BASE_PATH); //обьект для соединения с БД
//создание взаимодействия с базой
        Statement statement = connection.createStatement();

//получение выборки
        resultSet = statement.executeQuery("SELECT ID, Question, Answer " +
                "FROM Questions WHERE TestId=" + test);

////получение количества строк
//
        while (resultSet.next()) {
            ++rowCount;
            answerList.add(resultSet.getString("Answer"));
        }
        resultSet = statement.executeQuery("SELECT ID, Question, Answer " +
                "FROM Questions WHERE TestId=" + test);

        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getSomeQuestion(statement, test);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                group.clearSelection();
            }
        });

        buttonBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                try {
                    getSomeQuestion(statement, test);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void getSomeQuestion(Statement statement, int test) throws SQLException {
        //вывод конкретной строки
        if (i < 10) {
            try {
                resultSet.next();

                id = resultSet.getString("ID");
                question = resultSet.getString("Question");
                answer = resultSet.getString("Answer");


            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ArrayList<String> list1 = new ArrayList<>();
            list1.add(answer);
            list1.add(answerList.get(11));
            list1.add(answerList.get(13));
            list1.add(answerList.get(15));

            lablePicture.setIcon(new ImageIcon("pictures/pic" + test + id + ".jpg"));
            lableQuestion.setText(id + ". " + question);

            Random rand = new Random();
            ArrayList<String> list2 = new ArrayList<>();
            while (list1.size() > 0) {
                int index = rand.nextInt(list1.size());
                list2.add(list1.remove(index));
            }

            radioButtonAnswer1.setText(list2.get(0));
            radioButtonAnswer2.setText(list2.get(1));
            radioButtonAnswer3.setText(list2.get(2));
            radioButtonAnswer4.setText(list2.get(3));

            i++;
//какая кнопка нажата
            if (radioButtonAnswer1.isSelected()) {
                answerText = radioButtonAnswer1.getText();
            } else if (radioButtonAnswer2.isSelected()) {
                answerText = radioButtonAnswer2.getText();
            } else if (radioButtonAnswer3.isSelected()) {
                answerText = radioButtonAnswer3.getText();
            } else if (radioButtonAnswer4.isSelected()) {
                answerText = radioButtonAnswer4.getText();
            }
            if (answer.equals(answerText)) {
                sel++;
//                System.out.println(sel);
            }
        } else {
            if (radioButtonAnswer1.isSelected()) {
                answerText = radioButtonAnswer1.getText();
            } else if (radioButtonAnswer2.isSelected()) {
                answerText = radioButtonAnswer2.getText();
            } else if (radioButtonAnswer3.isSelected()) {
                answerText = radioButtonAnswer3.getText();
            } else if (radioButtonAnswer4.isSelected()) {
                answerText = radioButtonAnswer4.getText();
            }
            if (answer.equals(answerText)) {
                sel++;
//                System.out.println(sel);
            }
            String message = "";
            message += "Конец теста.\n";
            message += "Правильных ответов: " + sel;

            JOptionPane.showMessageDialog(null, message, "Зэ энд", JOptionPane.PLAIN_MESSAGE);

            this.setVisible(false);
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        }
    }

}
