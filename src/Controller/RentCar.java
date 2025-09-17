package Controller;

import Model.Car;
import Model.Database;
import Model.JButton;
import Model.JComboBox;
import Model.JLabel;
import Model.JTextField;
import Model.Operation;
import Model.Rent;
import Model.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.Year;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RentCar implements Operation {
    private JTextField brand;
    private JTextField model;
    private JTextField color;
    private JTextField year;
    private JTextField price;
    private Database database;
    private JFrame frame;

    public void operation(final Database database, JFrame f, final User user) {
        this.database = database;
        this.frame = new JFrame("Rent Car");
        this.frame.setSize(600, 650);
        this.frame.setLocationRelativeTo(f);
        this.frame.getContentPane().setBackground(new Color(126, 34, 213));
        this.frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Rent Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        this.frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(8, 2, 15, 15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID:", 22));
        ArrayList<Integer> idsArray = new ArrayList<>();

        try {
            ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID`, `Available` FROM `cars`;");
            while(rs0.next()) {
                if(rs0.getInt("Available") < 2) {
                    idsArray.add(rs0.getInt("ID"));
                }
            }
        } catch (Exception e0) {
            JOptionPane.showMessageDialog(this.frame, e0.getMessage());
            this.frame.dispose();
        }

        String[] ids = new String[idsArray.size() + 1];
        ids[0] = " ";
        for(int i = 1; i <= idsArray.size(); i++) {
            ids[i] = String.valueOf(idsArray.get(i - 1));
        }

        final JComboBox id = new JComboBox(ids, 22);
        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RentCar.this.updateData(id.getSelectedItem().toString());
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

        panel.add(new JLabel("Hours:", 22));
        final JTextField hours = new JTextField(22);
        panel.add(hours);

        JButton showCars = new JButton("Show All Cars", 22);
        showCars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (new ViewCars()).operation(database, RentCar.this.frame, user);
            }
        });
        panel.add(showCars);

        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals(" ")) {
                    JOptionPane.showMessageDialog(RentCar.this.frame, "ID cannot be empty");
                    return;
                }
                if(hours.getText().equals("")) {
                    JOptionPane.showMessageDialog(RentCar.this.frame, "Hours cannot be empty");
                    return;
                }

                int hoursInt;
                try {
                    hoursInt = Integer.parseInt(hours.getText());
                    if(hoursInt <= 0) {
                        JOptionPane.showMessageDialog(RentCar.this.frame, "Hours must be positive");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RentCar.this.frame, "Hours must be a valid integer");
                    return;
                }

                try {
                    ResultSet rsCar = database.getStatement().executeQuery(
                            "SELECT * FROM `cars` WHERE `ID` = '" + id.getSelectedItem().toString() + "';"
                    );
                    if(!rsCar.next()) {
                        JOptionPane.showMessageDialog(RentCar.this.frame, "Car not found");
                        return;
                    }

                    Car car = new Car();
                    car.setID(rsCar.getInt("ID"));
                    car.setBrand(rsCar.getString("Brand"));
                    car.setModel(rsCar.getString("Model"));
                    car.setColor(rsCar.getString("Color"));
                    car.setYear(rsCar.getInt("Year"));
                    car.setPrice(rsCar.getDouble("Price"));
                    car.setAvailable(rsCar.getInt("Available"));

                    if(car.isAvailable() != 0) {
                        JOptionPane.showMessageDialog(RentCar.this.frame, "Car isn't available");
                        return;
                    }

                    // Get next ID
                    ResultSet rsCount = database.getStatement().executeQuery("SELECT COUNT(*) FROM `rents`;");
                    rsCount.next();
                    int rentID = rsCount.getInt(1);

                    int currentYear = Year.now().getValue();
                    double total = car.getPrice() * hoursInt;

                    // Insert using PreparedStatement
                    PreparedStatement pst = database.getConnection().prepareStatement(
                            "INSERT INTO `rents`(`ID`,`User`,`Car`,`DateTime`,`Hours`,`Total`,`Status`) VALUES(?,?,?,?,?,?,?)"
                    );
                    pst.setInt(1, rentID);
                    pst.setString(2, String.valueOf(user.getID()));  // or user.getName() if username
                    pst.setInt(3, car.getID());
                    pst.setInt(4, currentYear);
                    pst.setInt(5, hoursInt);
                    pst.setDouble(6, total);
                    pst.setInt(7, 0);  // Status
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(RentCar.this.frame, "Car rented successfully\nTotal = " + total + " $");
                    RentCar.this.frame.dispose();

                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(RentCar.this.frame, "Database error: " + ex.getMessage());
                }
            }
        });
        panel.add(confirm);

        this.frame.add(panel, BorderLayout.CENTER);
        this.frame.setVisible(true);
        this.frame.requestFocus();
    }

    private void updateData(String ID) {
        if(ID.equals(" ")) {
            this.brand.setText("");
            this.model.setText("");
            this.color.setText("");
            this.year.setText("");
            this.price.setText("");
        } else {
            try {
                ResultSet rs1 = this.database.getStatement().executeQuery("SELECT * FROM `cars` WHERE `ID` = '" + ID + "';");
                if(rs1.next()) {
                    this.brand.setText(rs1.getString("Brand"));
                    this.model.setText(rs1.getString("Model"));
                    this.color.setText(rs1.getString("Color"));
                    this.year.setText(String.valueOf(rs1.getInt("Year")));
                    this.price.setText(rs1.getDouble("Price") + " $");
                }
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(this.frame, e1.getMessage());
                this.frame.dispose();
            }
        }
    }
}
