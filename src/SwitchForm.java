import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SwitchForm extends JFrame
{
    private JButton buttonTest;
    private JButton buttonResult;
    private JButton buttonList;
    private JPanel panelSwitch;
    private JButton buttonExit;

    public SwitchForm()
    {
        //инициализация формы
        super("Меню");

        this.setSize(500, 200);
        this.setContentPane(panelSwitch);
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
                LoginForm loginForm =null;
                try
                {
                    loginForm = new LoginForm();
                    loginForm.setVisible(true);
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void setVisibleElements(String status)
    {
        buttonList.setVisible(false);
        buttonResult.setVisible(false);
    }
}
