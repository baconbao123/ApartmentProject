package scrollbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;
//nghien cuu

public class ScrollBarCustom extends JScrollBar {
	public ScrollBarCustom() {
		setUI(new ModernScrollBarUI());
		setPreferredSize(new Dimension(3, 5));
		setForeground(new Color(217, 217, 217));
		setUnitIncrement(20);
		setOpaque(false);
	}
}
