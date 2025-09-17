

package Model;

import Controller.ChangePassword;
import Controller.EditUserData;
import Controller.RentCar;
import Controller.ReturnCar;
import Controller.ShowUserRents;
import Controller.ViewCars;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends User {
    private Operation[] operations = new Operation[]{new ViewCars(), new RentCar(), new ReturnCar(), new ShowUserRents(-9999), new EditUserData(), new ChangePassword()};
    private JButton[] btns = new JButton[]{new JButton("View Cars", 22), new JButton("Rent Car", 22), new JButton("Return Car", 22), new JButton("Show My Rents", 22), new JButton("Edit My Data", 22), new JButton("Change Password", 22)};

    public void showList(final Database database, JFrame f) {
        final JFrame frame = new JFrame("Client Panel");
        frame.setSize(400, this.btns.length * 90);
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
            int finalI1 = i;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Client.this.operations[finalI1].operation(database, frame, Client.this);
                }
            });
        }

        frame.add(panel, "Center");
        frame.setVisible(true);
    }
}
