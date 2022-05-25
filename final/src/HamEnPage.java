import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HamEnPage extends JPanel {
    private MainFrame parent;

    public HamEnPage(MainFrame parent){
        this.parent = parent;

        setSize(600, 600);
        setLayout(null);

        JLabel label = new JLabel("Enter binary sequence to encode");
        label.setLocation(50, 50);
        label.setSize(200, 36);
        add(label);

        JTextField textField = new JTextField();
        textField.setLocation(50, 100);
        textField.setSize(400, 36);
        add(textField);

        JLabel label1 = new JLabel("Enter any number to divide your string: ");
        label1.setLocation(50, 150);
        label1.setSize(200, 36);
        add(label1);

        JTextField textField1 = new JTextField();
        textField1.setLocation(50, 200);
        textField1.setSize(400, 36);
        add(textField1);

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
                textArea.selectAll();
                textArea.replaceSelection("");
                CalculatorHamming calculatorHamming = new CalculatorHamming();
                calculatorHamming.hamming(textField.getText(), textField1.getText());
                parent.arrayOfHamming.add(calculatorHamming.hammingcode, calculatorHamming.size);
                textArea.append(calculatorHamming.text);
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
                parent.getHamEnPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });

    }
}
