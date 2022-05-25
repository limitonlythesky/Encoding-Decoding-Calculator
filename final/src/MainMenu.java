import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private MainFrame parent;

    public MainMenu(MainFrame parent) {
        this.parent = parent;

        setSize(600, 600);
        setLayout(null);

        JLabel label = new JLabel("Choose your option:");
        label.setLocation(100, 40);
        label.setSize(300, 48);
        add(label);

        JButton button1 = new JButton("Shannon-Fano / Huffman encode");
        button1.setLocation(100, 100);
        button1.setSize(300, 48);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getSfhEnPage().setVisible(true);
            }
        });

        JButton button2 = new JButton("Hamming encode");
        button2.setLocation(100, 160);
        button2.setSize(300, 48);
        add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getHamEnPage().setVisible(true);
            }
        });

        JButton button3 = new JButton("Hamming decode");
        button3.setLocation(100, 220);
        button3.setSize(300, 48);
        add(button3);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getHamDecPage().setVisible(true);
            }
        });

        JButton button4 = new JButton("Shannon-Fano / Huffman decode");
        button4.setLocation(100, 280);
        button4.setSize(300, 48);
        add(button4);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getSfhDecPage().setVisible(true);
            }
        });

        JButton button5 = new JButton("Entropy");
        button5.setLocation(100, 340);
        button5.setSize(300, 48);
        add(button5);

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getEntropyPage().setVisible(true);
            }
        });
    }
}
