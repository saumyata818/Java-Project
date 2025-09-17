

package Controller;

import Model.Database;
import Model.JButton;
import Model.JComboBox;
import Model.JLabel;
import Model.Operation;
import Model.Rent;
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

public class ReturnCar implements Operation {
    public void operation(final Database database, JFrame f, final User user) {
        final JFrame frame = new JFrame("Return Car");
        frame.setSize(600, 260);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(174, 103, 152));
        frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Rent Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, "North");
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground((Color)null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Rent ID:", 22));
        String[] var10000 = new String[]{" "};
        ArrayList<Integer> idsArray = new ArrayList();

        try {
            ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID` FROM `rents` WHERE `User` = '" + user.getID() + "';");

            while(rs0.next()) {
                idsArray.add(rs0.getInt("ID"));
            }
        } catch (Exception e0) {
            JOptionPane.showMessageDialog(frame, e0.getMessage());
            frame.dispose();
        }

        String[] ids = new String[idsArray.size() + 1];
        ids[0] = " ";

        for(int i = 1; i <= idsArray.size(); ++i) {
            ids[i] = String.valueOf(idsArray.get(i - 1));
        }

        final JComboBox id = new JComboBox(ids, 22);
        panel.add(id);
        JButton showRents = new JButton("Show my Rents", 22);
        showRents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (new ShowUserRents(user.getID())).operation(database, frame, user);
            }
        });
        panel.add(showRents);
        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id.getSelectedItem().toString().equals(" ")) {
                    JOptionPane.showMessageDialog(frame, "Rent ID cannot be empty");
                } else {
                    try {
                        String select = "SELECT * FROM `rents` WHERE `ID` = '" + id.getSelectedItem().toString() + "';";
                        ResultSet rs = database.getStatement().executeQuery(select);
                        rs.next();
                        Rent r = new Rent();
                        r.setID(rs.getInt("ID"));
                        r.setUser(user);
                        r.setDateTime(rs.getString("DateTime"));
                        r.setHours(rs.getInt("Hours"));
                        r.setTotal(rs.getDouble("Total"));
                        r.setStatus(rs.getInt("Status"));
                        if (r.getStatusToString().equals("Delayed")) {
                            JOptionPane.showMessageDialog(frame, r.getDelayedHours() + " delayed hours\nYou will have to pay 1000$ as fine");
                        }

                        String update = "UPDATE `rents` SET `Status`='1' WHERE `ID` = '" + id.getSelectedItem().toString() + "';";
                        database.getStatement().execute(update);
                        JOptionPane.showMessageDialog(frame, "Car returned successfully");
                        frame.dispose();
                    } catch (SQLException exception) {
                        JOptionPane.showMessageDialog(frame, exception.getMessage());
                    }

                }
            }
        });
        panel.add(confirm);
        frame.add(panel, "Center");
        frame.setVisible(true);
        frame.requestFocus();
    }
}
