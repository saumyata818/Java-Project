

package Model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;

public class JButton extends javax.swing.JButton {
    public JButton(String text, int textSize) {
        super(text);
        this.setBackground(Color.black);
        this.setFont(new Font("SansSerif", 1, textSize));
        this.setForeground(Color.white);
        this.setBorder((Border)null);
    }
}
