package network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NetworkPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Network n;
	private int mw;
	private static final Color BACK_COLOR = new Color(66, 66, 66);

	public NetworkPanel(int mw, Network n) {
		this.n = n;
		this.mw = mw;
		this.setPreferredSize(new Dimension(n.width() * (mw + 1) - 10, n.height() * (mw + 1) - 10));
		this.setBackground(BACK_COLOR);
	}

	public void paint(Graphics g) {
		Color[][] ctab = n.toColor();
		for (int i = 0; i < n.height(); ++i) {
			for (int j = 0; j < n.width(); ++j) {
				g.setColor(ctab[i][j]);
				g.fillRect(j * (mw + 1) ,i * (mw + 1), mw, mw);
			}
		}
	}
}
