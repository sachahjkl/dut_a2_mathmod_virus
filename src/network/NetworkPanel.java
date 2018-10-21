package network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NetworkPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Network n;
	private int cw;
	

	public NetworkPanel(int cw, Network n) {
		this.n = n;
		this.cw = cw;
		this.setLayout(null);
		int w = n.width() * (cw + 1) - 10, h = n.height() * (cw + 1) - 10;
		int rw = w >= 300 ? w : 300;
		this.setPreferredSize(new Dimension(rw, h+100));
	}

	public void paint(Graphics g) {
		Color[][] ctab = n.toColor();
		for (int i = 0; i < n.height(); ++i) {
			for (int j = 0; j < n.width(); ++j) {
				g.setColor(ctab[i][j]);
				g.fillRect(j * (cw + 1), i * (cw + 1), cw, cw);
			}
		}
	}
}
