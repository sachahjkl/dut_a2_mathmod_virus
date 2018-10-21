package network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NetworkDisplay {
	private JFrame f;
	private JPanel p;
	private JButton start, stop;
	private Network n;
	private Timer t;
	private JLabel l;
	private static final Color BACK_COLOR = new Color(50, 54, 57);

	public NetworkDisplay(Network n, String title, int cw) {
		this.f = new JFrame(title);
		int w = n.width() * (cw + 1) + 5, h = n.height() * (cw + 1) ;
		int rw = w >= 250 ? w : 250;
		this.f.setPreferredSize(new Dimension(rw, h+100));
		this.f.setBackground(BACK_COLOR);
		this.p = new NetworkPanel(cw, n);
		this.n = n;
		this.l = new JLabel("minutes écoulées : " + n.getTimer());
		this.start = new JButton("Start");
		this.stop = new JButton("Stop");
		this.t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				n.batchVirusPatch();
				refresh();
			}
		});
		this.start.setBounds(4, n.height() * (cw + 1) + 5, 70, 30);
		this.stop.setBounds(84, n.height() * (cw + 1) + 5, 70, 30);
		this.start.setFocusPainted(false);
		this.stop.setFocusPainted(false);
		this.start.setBackground(new Color(59,89,182));
		this.stop.setBackground(new Color(59,89,182));
		this.start.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.stop.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.start.setForeground(Color.white);
		this.stop.setForeground(Color.white);
		this.l.setForeground(Color.white);
		this.l.setBounds(4, n.height() * (cw + 1) + 40, 140, 20);
	}

	public void init() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.start();
			}
		});
		this.stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}
		});
		f.add(start);
		f.add(stop);
		f.add(l);
		f.add(p);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
	}

	public void refresh() {
		this.l.setText("minutes écoulées : " + n.getTimer());
		this.f.repaint();
	}

	public void timerStop() {
		this.t.stop();
	}

	public void display() {
		this.init();
	}

}
