import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class LoginForm extends JFrame
{
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPanel panelLogin;
    private JButton buttonEnter;
    private JLabel lableLogin;
    private JLabel lablePass;
    private JLabel lableTitle;
    public String login;
    public String pass;


    public LoginForm()
    {
        //инициализация формы
        super("Авторизация");

        this.setSize(300, 200);
        this.setContentPane(panelLogin);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buttonEnter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                login=loginField.getText();
                pass=passwordField.getPassword().toString();
                lableLogin.setText(login);
                lablePass.setText(pass);
                if (Objects.equals(login, "1") )
                {
                    setVisible(false);
                    TestForm app = null;
                    try
                    {
                        app = new TestForm();
                    } catch (SQLException e1)
                    {
                        e1.printStackTrace();
                    }
                    app.setVisible(true);
                } else
                {
                    JOptionPane.showMessageDialog(null,
                            "непральна епт",
                            "заголовок",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
