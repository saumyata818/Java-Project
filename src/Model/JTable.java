

package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JTable extends javax.swing.JTable {
    public JTable(String[][] data, String[] header, final Color color1, final Color color2) {
        super(data, header);
        this.setRowHeight(40);
        this.setBackground((Color)null);
        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.setModel(tableModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                this.setHorizontalAlignment(0);
                this.setFont(new Font("SansSerif", 1, 20));
                if (hasFocus) {
                    this.setBorder((Border)null);
                }

                if (row % 2 == 0) {
                    this.setBackground(Color.white);
                } else {
                    this.setBackground(color2);
                }

                return this;
            }
        };

        for(int i = 0; i < this.getColumnModel().getColumnCount(); ++i) {
            this.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                this.setHorizontalAlignment(0);
                this.setFont(new Font("SansSerif", 1, 20));
                this.setBackground(color1);
                this.setForeground(Color.white);
                this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                return this;
            }
        };
        this.getTableHeader().setDefaultRenderer(headerRenderer);
        this.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, color1));
        this.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2, color1));
        this.setGridColor(color1);
        this.setRowSelectionAllowed(false);
    }
}
