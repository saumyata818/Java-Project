
package Model;

import java.awt.Color;
import java.awt.Font;

public class JLabel extends javax.swing.JLabel {
    public JLabel(String text, int fontSize) {
        super(text);
        this.setFont(new Font("SansSerif", 1, fontSize));
        this.setBackground((Color)null);
        this.setHorizontalAlignment(0);
    }
}
