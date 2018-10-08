import javax.swing.*;
import java.awt.*;

public class TestForm1 extends JFrame
{
    private JButton buttonNext = new JButton("Дальше");
    private JPanel panel1;
    private JRadioButton radioButtonAnswer1 = new JRadioButton("Ответ 1");
    private JRadioButton radioButtonAnswer2 = new JRadioButton("Ответ 2");
    private JRadioButton radioButtonAnswer3 = new JRadioButton("Ответ 3");
    private JRadioButton radioButtonAnswer4 = new JRadioButton("Ответ 4");
    private JLabel lablePicture = new JLabel();
    private JLabel lableQuestion = new JLabel("Какой-то гребаный вопрос");

    public TestForm1()
    {
        super("Тестирование");
        //setBounds(200, 200, 500, 500);
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(7, 1, 2, 2));
       
        lablePicture.setIcon(new ImageIcon("E:\\Java\\TestingApp\\src\\pictures\\pic1.jpg"));
        container.add(lablePicture);
        container.add(lableQuestion);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonAnswer1);
        group.add(radioButtonAnswer2);
        group.add(radioButtonAnswer3);
        group.add(radioButtonAnswer4);
        container.add(radioButtonAnswer1);
        container.add(radioButtonAnswer2);
        container.add(radioButtonAnswer3);
        container.add(radioButtonAnswer4);

        container.add(buttonNext);


    }
}
