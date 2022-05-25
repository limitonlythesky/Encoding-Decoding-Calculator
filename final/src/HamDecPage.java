import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HamDecPage extends JPanel {
    private MainFrame parent;

    public HamDecPage(MainFrame parent){
        this.parent = parent;

        setSize(600, 600);
        setLayout(null);

        JLabel label = new JLabel("Enter binary sequence to decode");
        label.setLocation(50, 50);
        label.setSize(200, 36);
        add(label);

        JTextField textField = new JTextField();
        textField.setLocation(50, 100);
        textField.setSize(400, 36);
        add(textField);

        JLabel label1 = new JLabel("Enter the percent (between 30 - 50%):");
        label1.setLocation(50, 150);
        label1.setSize(250, 36);
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
                for(int i = 0; i < parent.arrayOfHamming.get().size(); i++){
                    if(parent.arrayOfHamming.get().get(i).getElement0().equals(textField.getText())){
                        textArea.selectAll();
                        textArea.replaceSelection("");
                        CalculatorHammingDec calculatorHamming = new CalculatorHammingDec(parent.arrayOfHamming.get().get(i).getElement1());
                        calculatorHamming.hammingDec(textField.getText(), Integer.valueOf(textField1.getText()));
                        textArea.append(calculatorHamming.text);
                    }
                }
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
                parent.getHamDecPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });
    }
}
