

package Controller;

import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.JTextField;
import Model.Operation;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddNewCar implements Operation {
    public void operation(final Database database, JFrame f, User user) {
        final JFrame frame = new JFrame("Add New Car");
        frame.setSize(600, 525);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(138, 255, 146));
        frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Add New Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, "North");
        JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
        panel.setBackground((Color)null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Brand:", 22));
        final JTextField brand = new JTextField(22);
        panel.add(brand);
        panel.add(new JLabel("Model:", 22));
        final JTextField model = new JTextField(22);
        panel.add(model);
        panel.add(new JLabel("Color:", 22));
        final JTextField color = new JTextField(22);
        panel.add(color);
        panel.add(new JLabel("Year:", 22));
        final JTextField year = new JTextField(22);
        panel.add(year);
        panel.add(new JLabel("Price per Hour:", 22));
        final JTextField price = new JTextField(22);
        panel.add(price);
        JButton cancel = new JButton("Cancel", 22);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);
        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (brand.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Brand cannot be empty");
                } else if (model.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Model cannot be empty");
                } else if (color.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Color cannot be empty");
                } else if (year.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Year cannot be empty");
                } else if (price.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Price cannot be empty");
                } else {
                    int yearInt;
                    try {
                        yearInt = Integer.parseInt(year.getText());
                    } catch (Exception var11) {
                        JOptionPane.showMessageDialog(frame, "Year must be int");
                        return;
                    }

                    double priceDoub;
                    try {
                        priceDoub = Double.parseDouble(price.getText());
                    } catch (Exception var10) {
                        JOptionPane.showMessageDialog(frame, "Price must be double");
                        return;
                    }

                    int available = 0;

                    try {
                        ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) FROM `cars`;");
                        rs.next();
                        int ID = rs.getInt("COUNT(*)");
                        String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `Available`) VALUES ('" + ID + "','" + brand.getText() + "'," + "'" + model.getText() + "','" + color.getText() + "','" + yearInt + "','" + priceDoub + "'," + "'" + available + "');";
                        database.getStatement().execute(insert);
                        JOptionPane.showMessageDialog(frame, "Car added successfully");
                        frame.dispose();
                    } catch (SQLException er) {
                        JOptionPane.showMessageDialog(frame, er.getMessage());
                    }

                }
            }
        });
        panel.add(confirm);
        frame.add(panel, "Center");
        frame.setVisible(true);
    }
}
