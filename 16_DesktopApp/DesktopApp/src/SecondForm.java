import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class SecondForm {
    private JPanel secondPanel;
    private JButton expandButton;
    private JTextField fullNameField;
    private JLabel fullNameLabel;
    private JPanel fullNamePanel;

    public SecondForm(String text, JFrame frame) {
        fullNameField.setText(text);

        expandButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText();
                String name, secondName, thirdName;
                String[] words = fullName.trim().split("\\s+", 3);
                if (words.length < 2) {
                    JOptionPane.showMessageDialog(
                            secondPanel,
                            "Введено недостаточно данных",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (words.length == 2) {
                        thirdName = "";
                    } else {
                        thirdName = words[2];
                    }
                    name = words[1];
                    secondName = words[0];
                    JFrame newFrame = new JFrame();
                    newFrame.setSize(700, 400);
                    newFrame.setTitle("Окно сложения имени");
                    newFrame.add(new MainForm(name, secondName, thirdName, newFrame).getMainPanel());
                    newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    newFrame.setLocationRelativeTo(null);
                    newFrame.setVisible(true);
                    frame.setVisible(false);
                }
            }
        });
    }

    public JPanel getSecondPanel(){
        return secondPanel;
    }
}
