

package Controller;

import Model.Car;
import Model.Database;
import Model.JButton;
import Model.JComboBox;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeleteCar implements Operation {
    private JTextField brand;
    private JTextField model;
    private JTextField color;
    private JTextField year;
    private JTextField price;
    private Database database;
    private JFrame frame;

    public void operation(final Database database, JFrame f, User user) {
        this.database = database;
        this.frame = new JFrame("Delete Car");
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(f);
        this.frame.getContentPane().setBackground(new Color(71, 182, 168));
        this.frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Delete Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        this.frame.add(title, "North");
        JPanel panel = new JPanel(new GridLayout(7, 2, 15, 15));
        panel.setBackground((Color)null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("ID:", 22));
        String[] var10000 = new String[]{" "};
        ArrayList<Integer> idsArray = new ArrayList();

        try {
            ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID`, `Available` FROM `cars`;");

            while(rs0.next()) {
                if (rs0.getInt("Available") < 2) {
                    idsArray.add(rs0.getInt("ID"));
                }
            }
        } catch (Exception e0) {
            JOptionPane.showMessageDialog(this.frame, e0.getMessage());
            this.frame.dispose();
        }

        String[] ids = new String[idsArray.size() + 1];
        ids[0] = " ";

        for(int i = 1; i <= idsArray.size(); ++i) {
            ids[i] = String.valueOf(idsArray.get(i - 1));
        }

        final JComboBox id = new JComboBox(ids, 22);
        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCar.this.updateData(id.getSelectedItem().toString());
            }
        });
        panel.add(id);
        panel.add(new JLabel("Brand:", 22));
        this.brand = new JTextField(22);
        this.brand.setEditable(false);
        panel.add(this.brand);
        panel.add(new JLabel("Model:", 22));
        this.model = new JTextField(22);
        this.model.setEditable(false);
        panel.add(this.model);
        panel.add(new JLabel("Color:", 22));
        this.color = new JTextField(22);
        this.color.setEditable(false);
        panel.add(this.color);
        panel.add(new JLabel("Year:", 22));
        this.year = new JTextField(22);
        this.year.setEditable(false);
        panel.add(this.year);
        panel.add(new JLabel("Price per Hour:", 22));
        this.price = new JTextField(22);
        this.price.setEditable(false);
        panel.add(this.price);
        JButton cancel = new JButton("Cancel", 22);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCar.this.frame.dispose();
            }
        });
        panel.add(cancel);
        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id.getSelectedItem().toString().equals(" ")) {
                    JOptionPane.showMessageDialog(DeleteCar.this.frame, "ID cannot be empty");
                } else {
                    try {
                        String update = "UPDATE `cars` SET `Available`='2' WHERE `ID` = '" + id.getSelectedItem().toString() + "';";
                        database.getStatement().execute(update);
                        JOptionPane.showMessageDialog(DeleteCar.this.frame, "Car deleted successfully");
                        DeleteCar.this.frame.dispose();
                    } catch (SQLException e3) {
                        JOptionPane.showMessageDialog(DeleteCar.this.frame, e3.getMessage());
                    }

                }
            }
        });
        panel.add(confirm);
        this.frame.add(panel, "Center");
        this.frame.setVisible(true);
        this.frame.requestFocus();
    }

    private void updateData(String ID) {
        if (ID.equals(" ")) {
            this.brand.setText("");
            this.model.setText("");
            this.color.setText("");
            this.year.setText("");
            this.price.setText("");
        } else {
            try {
                ResultSet rs1 = this.database.getStatement().executeQuery("SELECT * FROM `cars` WHERE `ID` = '" + ID + "';");
                rs1.next();
                Car car = new Car();
                car.setID(rs1.getInt("ID"));
                this.brand.setText(rs1.getString("Brand"));
                this.model.setText(rs1.getString("Model"));
                this.color.setText(rs1.getString("Color"));
                this.year.setText(String.valueOf(rs1.getInt("Year")));
                this.price.setText(String.valueOf(rs1.getDouble("Price")));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this.frame, e1.getMessage());
                this.frame.dispose();
            }
        }

    }
}
