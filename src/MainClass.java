import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class MainClass
{

    public static void main(String[] args) throws SQLException
    {

        //запуск формы с вопросами
//        TestForm app = new TestForm();
//        app.setVisible(true);
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);

    }

}
