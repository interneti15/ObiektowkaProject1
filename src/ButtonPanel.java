import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    public ButtonPanel(){
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,550,775,150);
        setLayout(null);

        button1 = new JButton();
        button1.setBounds(25,25, 100,100);
        add(button1);

        button2 = new JButton();
        button2.setBounds(150,25, 100,100);
        add(button2);

        button3 = new JButton();
        button3.setBounds(275,25, 100,100);
        add(button3);

        button4 = new JButton();
        button4.setBounds(400,25, 100,100);
        add(button4);

        button5 = new JButton();
        button5.setBounds(525,25, 100,100);
        add(button5);

        button6 = new JButton();
        button6.setBounds(650,25, 100,100);
        add(button6);



    }
}
