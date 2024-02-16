package table;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImgContractRender extends DefaultTableCellRenderer {
	JLabel lbl = new JLabel();
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value != null) {
            String[] images = value.toString().split(";");
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            if(isSelected==false) {
            	panel.setBackground(Color.WHITE);
            } else {
            	panel.setBackground(com.getBackground());
            }
            for(String imagePath : images) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newImage);
                JLabel imgLabel = new JLabel(newIcon);
                panel.add(imgLabel);
            }
            return panel;
        }
        return lbl;
    }
	
}
