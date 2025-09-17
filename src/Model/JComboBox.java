

package Model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class JComboBox extends javax.swing.JComboBox {
    public JComboBox(String[] items, int fontSize) {
        super(items);
        this.setFont(new Font("SansSerif", 1, fontSize));
        this.setBackground(Color.white);
        ((JLabel)this.getRenderer()).setHorizontalAlignment(0);
    }
}
