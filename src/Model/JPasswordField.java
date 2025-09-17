

package Model;

import java.awt.Font;
import javax.swing.border.Border;

public class JPasswordField extends javax.swing.JPasswordField {
    public JPasswordField(int textSize) {
        this.setFont(new Font("SansSerif", 1, textSize));
        this.setHorizontalAlignment(0);
        this.setBorder((Border)null);
    }
}
