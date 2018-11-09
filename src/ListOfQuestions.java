import javax.swing.*;

public class ListOfQuestions extends JFrame
{
    private JLabel lableTitle;
    private JPanel panelList;
    private JTable tableOfQuestions;

    public ListOfQuestions()
    {
        //инициализация формы
        super("Меню");

        this.setSize(500, 200);
        this.setContentPane(panelList);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tableOfQuestions.addColumn("ID");
    }
}
