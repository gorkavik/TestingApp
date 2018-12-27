import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SwitchForm extends JFrame
{
    private JButton buttonTest;
    private JButton buttonResult;
    private JButton buttonListQuestions;
    private JPanel panelSwitch;
    private JButton buttonExit;
    private JButton buttonListUsers;

    public SwitchForm() throws SQLException
    {
        //инициализация формы
        super("Меню");

        this.setSize(500, 200);
        this.setContentPane(panelSwitch);
//        setDefaultCloseOperation();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buttonTest.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                TestForm testForm = null;
                try
                {
                    testForm = new TestForm();
                    testForm.setVisible(true);
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        buttonExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                try
                {
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });


        buttonListQuestions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ListWithData listWithData = new ListWithData("questions");
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        buttonListUsers.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ListWithData listWithData = new ListWithData("users");
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void setVisibleElements(String status)
    {
        switch (status)
        {
            case "user":
            {
                buttonListQuestions.setVisible(false);
                buttonResult.setVisible(false);
                buttonListUsers.setVisible(false);
            }
            case "admin":
            {
            }
            case "teacher":
            {
            }
        }

    }
}
