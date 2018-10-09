import javax.swing.*;
import java.awt.*;

public class TestForm extends JFrame
{
    private JButton buttonNext;
    private JPanel panel1;
    private JRadioButton radioButtonAnswer1;
    private JRadioButton radioButtonAnswer2;
    private JRadioButton radioButtonAnswer3;
    private JRadioButton radioButtonAnswer4;
    private JLabel lablePicture;
    private JLabel lableQuestion;

    public TestForm()
    {
        super("Тестирование");
        //this.setBounds(200, 200, 700, 700);
        this.setSize(700,700);
        this.setContentPane(panel1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lablePicture.setIcon(new ImageIcon("pictures/pic1.jpg"));

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonAnswer1);
        group.add(radioButtonAnswer2);
        group.add(radioButtonAnswer3);
        group.add(radioButtonAnswer4);

        buttonNext.addActionListener(e -> lablePicture.setIcon(new ImageIcon("pictures/pic2.jpg")));
    }

}
