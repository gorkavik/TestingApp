import javax.swing.*;

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

        this.setSize(700, 700);
        this.setContentPane(panel1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lablePicture.setIcon(new ImageIcon("pictures/pic1.jpg"));

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonAnswer1);
        group.add(radioButtonAnswer2);
        group.add(radioButtonAnswer3);
        group.add(radioButtonAnswer4);


    }

    public void setValuesForField(String id, String question, String answer1, String answer2, String answer3, String answer4right)
    {
        buttonNext.addActionListener(e ->
        {
            lablePicture.setIcon(new ImageIcon("pictures/pic2.jpg"));
            lableQuestion.setText(id + ". " + question);
            radioButtonAnswer1.setText(answer1);
            radioButtonAnswer2.setText(answer2);
            radioButtonAnswer3.setText(answer3);
            radioButtonAnswer4.setText(answer4right);
        });
    }

}
