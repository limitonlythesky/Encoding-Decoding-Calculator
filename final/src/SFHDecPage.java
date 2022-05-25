import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SFHDecPage extends JPanel {
    private MainFrame parent;

    public SFHDecPage(MainFrame parent){
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
                System.out.println(parent.arrayOfTrees.get().get(0).getElement0());
                for(int i = 0; i < parent.arrayOfTrees.get().size(); i++){
                    if(parent.arrayOfTrees.get().get(i).getElement0().equals(textField.getText())){
                        textArea.selectAll();
                        textArea.replaceSelection("");
                        CalculatorDec calculatorDec = new CalculatorDec(parent.arrayOfTrees.get().get(i).getElement1());
                        calculatorDec.traversal(textField.getText() + " ", 0, 0, parent.arrayOfTrees.get().get(i).getElement1());
                        textArea.append(calculatorDec.text);
                        textArea.append("Decoded string: " + calculatorDec.ans);
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
                parent.getSfhDecPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });

    }
}
