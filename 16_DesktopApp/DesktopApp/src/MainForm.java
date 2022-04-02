import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton collapseButton;
    private JTextField secondNameTextField;
    private JTextField nameTextField;
    private JTextField thirdNameTextField;
    private JPanel namePanel;
    private JLabel secondNameLabel;
    private JLabel nameLabel;
    private JLabel thirdNameLabel;

    public MainForm(JFrame frame){
        collapseButton.addActionListener(new Action() {
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
                String name = nameTextField.getText().trim();
                String secondName = secondNameTextField.getText().trim();
                String thirdName = thirdNameTextField.getText().trim();
                String fullName = secondName + " " + name + " " + thirdName;

                if(secondName.length() == 0 || name.length() == 0){
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Не заполнены обязательные поля, введите данные",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JFrame secondFrame = new JFrame();
                    secondFrame.setSize(700, 400);
                    secondFrame.setTitle("Окно разделения имени");
                    secondFrame.add(new SecondForm(fullName.trim(), secondFrame).getSecondPanel());
                    secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    secondFrame.setLocationRelativeTo(null);
                    secondFrame.setVisible(true);
                    frame.setVisible(false);
                }
            }
        });

    }

    public MainForm(String name, String secondName, String thirdName, JFrame frame){
        collapseButton.addActionListener(new Action() {
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
                String name = nameTextField.getText().trim();
                String secondName = secondNameTextField.getText().trim();
                String thirdName = thirdNameTextField.getText().trim();
                String fullName = secondName + " " + name + " " + thirdName;

                if(secondName.length() == 0 || name.length() == 0){
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Не заполнены обязательные поля, введите данные",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JFrame secondFrame = new JFrame();
                    secondFrame.setSize(700, 400);
                    secondFrame.setTitle("Окно разделения имени");
                    secondFrame.add(new SecondForm(fullName.trim(), secondFrame).getSecondPanel());
                    secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    secondFrame.setLocationRelativeTo(null);
                    secondFrame.setVisible(true);
                    frame.setVisible(false);
                }
            }
        });
        secondNameTextField.setText(secondName);
        nameTextField.setText(name);
        thirdNameTextField.setText(thirdName);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
