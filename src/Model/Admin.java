

package Model;

import Controller.AddNewAccount;
import Controller.AddNewCar;
import Controller.ChangePassword;
import Controller.DeleteCar;
import Controller.EditUserData;
import Controller.ShowAllRents;
import Controller.ShowSpecUserRents;
import Controller.UpdateCar;
import Controller.ViewCars;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Admin extends User {
    private Operation[] operations = new Operation[]{new AddNewCar(), new ViewCars(), new UpdateCar(), new DeleteCar(), new AddNewAccount(1), new ShowAllRents(), new ShowSpecUserRents(), new EditUserData(), new ChangePassword()};
    private JButton[] btns = new JButton[]{new JButton("Add New Car", 22), new JButton("View Cars", 22), new JButton("Update Car", 22), new JButton("Delete Car", 22), new JButton("Add New Admin", 22), new JButton("Show Rents", 22), new JButton("Show User's Rents", 22), new JButton("Edit my Data", 22), new JButton("Change Password", 22)};

    public void showList(final Database database, JFrame f) {
        final JFrame frame = new JFrame("Admin Panel");
        frame.setSize(400, this.btns.length * 80);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Welcome " + this.getFirstName(), 30);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        frame.add(title, "North");
        JPanel panel = new JPanel(new GridLayout(this.btns.length, 1, 15, 15));
        panel.setBackground((Color)null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for(int i = 0; i < this.btns.length; ++i) {
            JButton button = this.btns[i];
            panel.add(button);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Admin.this.operations[finalI].operation(database, frame, Admin.this);
                }
            });
        }

        frame.add(panel, "Center");
        frame.setVisible(true);
    }
}
