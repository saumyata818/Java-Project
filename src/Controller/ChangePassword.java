
package Controller;

import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.JPasswordField;
import Model.Operation;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChangePassword implements Operation {
    public void operation(final Database database, JFrame f, final User user) {
        final JFrame frame = new JFrame("Change Password");
        frame.setSize(600, 380);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(124, 149, 230));
        frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Change Password", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, "North");
        JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15));
        panel.setBackground((Color)null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Old Password:", 22));
        final JPasswordField oldPassword = new JPasswordField(22);
        panel.add(oldPassword);
        panel.add(new JLabel("New Password:", 22));
        final JPasswordField newPassword = new JPasswordField(22);
        panel.add(newPassword);
        panel.add(new JLabel("Confirm Password:", 22));
        final JPasswordField confirmPassword = new JPasswordField(22);
        panel.add(confirmPassword);
        JButton cancel = new JButton("Cancel", 22);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);
        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (oldPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Old Password cannot be empty");
                } else if (newPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "New Password cannot be empty");
                } else if (confirmPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Confirm Password cannot be empty");
                } else if (!oldPassword.getText().equals(user.getPassword())) {
                    JOptionPane.showMessageDialog(frame, "Incorrect Password");
                } else if (!newPassword.getText().equals(confirmPassword.getText())) {
                    JOptionPane.showMessageDialog(frame, "Password doesn't match");
                } else {
                    try {
                        String update = "UPDATE `users` SET `Password`='" + newPassword.getText() + "' WHERE `ID` = '" + user.getID() + "';";
                        database.getStatement().execute(update);
                        JOptionPane.showMessageDialog(frame, "Password changed successfully");
                        user.setPassword(newPassword.getText());
                        frame.dispose();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(frame, e.getMessage());
                    }

                }
            }
        });
        panel.add(confirm);
        frame.add(panel, "Center");
        frame.setVisible(true);
    }
}
