import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistForm extends JFrame
{
    private JButton buttonRegistration;
    private JTextField textFieldAddLog;
    private JPanel panelRegist;
    private JRadioButton radioButtonAdmin;
    private JRadioButton radioButtonUser;
    private JRadioButton radioButtonTeacher;
    private JPasswordField passwordFieldAddPass;
    private JPasswordField passwordFieldConfirmPass;

    private String addLog;
    private String addPass;
    private String confirmPass;
    private String status = "";

    public RegistForm() throws SQLException
    {
        //инициализация формы
        super("Регистрация");

        this.setSize(500, 400);
        this.setContentPane(panelRegist);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonAdmin);
        group.add(radioButtonUser);
        group.add(radioButtonTeacher);

        String BASE_PATH = "jdbc:sqlite:assets/basequestion.db"; //путь к БД
        DriverManager.registerDriver(new JDBC()); //регистрация и подключение к БД
        Connection connection = DriverManager.getConnection(BASE_PATH); //обьект для соединения с БД
//создание взаимодействия с базой
        Statement statement = connection.createStatement();

//действие для кнопки Регистрация
        buttonRegistration.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                LoginForm loginForm = null;
                try
                {
                    loginForm = new LoginForm();
                    loginForm.setVisible(true);
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

                addLog = textFieldAddLog.getText();
                addPass = new String(passwordFieldAddPass.getPassword());
                confirmPass = new String(passwordFieldConfirmPass.getPassword());

                if (radioButtonAdmin.isSelected())
                {
                    status = "admin";
                } else if (radioButtonUser.isSelected())
                {
                    status = "user";
                } else if (radioButtonTeacher.isSelected())
                {
                    status = "teacher";
                }






            }
        });

    }

}
