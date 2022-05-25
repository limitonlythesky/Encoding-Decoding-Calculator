import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SFHEnPage extends JPanel {
    private MainFrame parent;
    boolean choice = true;

    public SFHEnPage(MainFrame parent){
        this.parent = parent;

        setSize(600, 600);
        setLayout(null);

        JLabel label2 = new JLabel("Enter text to encode");
        label2.setLocation(50, 50);
        label2.setSize(200, 36);
        add(label2);

        JTextField textField = new JTextField();
        textField.setLocation(50, 100);
        textField.setSize(400, 36);
        add(textField);

        JLabel label1 = new JLabel("Choose algorithm:");
        label1.setLocation(50, 150);
        label1.setSize(200, 36);
        add(label1);

        JButton shannon = new JButton("Shannon-Fano");
        shannon.setLocation(50, 200);
        shannon.setSize(150, 36);
        add(shannon);

        shannon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = true;
            }
        });

        JButton huffman = new JButton("Huffman");
        huffman.setLocation(300, 200);
        huffman.setSize(150, 36);
        add(huffman);

        huffman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = false;
            }
        });

        JTextArea textArea = new JTextArea();

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setLocation(50, 250);
        scrollPane.setSize(400, 190);
        add(scrollPane, BorderLayout.CENTER);

        JButton calculate = new JButton("Calculate");
        calculate.setLocation(50, 450);
        calculate.setSize(150, 36);
        add(calculate);

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator calculator = new Calculator();
                textArea.selectAll();
                textArea.replaceSelection("");
                if(choice == true){
                    calculator.buildTreeShannon(textField.getText());
                }else{
                    calculator.buildTreeHuffman(textField.getText());
                }
                textArea.append(calculator.text);
                parent.arrayOfTrees.add(calculator.enc, calculator.root);
            }
        });

        JButton back = new JButton("Back");
        back.setLocation(300, 450);
        back.setSize(150, 36);
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
                textArea.replaceSelection("");
                textField.setText("");
                parent.getSfhEnPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });

    }
}
