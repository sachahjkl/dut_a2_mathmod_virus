package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NetworkDisplay {
	private JFrame f;
	private JPanel p;
	private JButton start, stop;
	private Network n;
	private String title;
	private Timer t;

	public NetworkDisplay(Network n, String title, int cw) {
		this.f = new JFrame(title + ", itération : " + n.getTimer());
		this.p = new NetworkPanel(cw, n);
		this.n = n;
		this.title = title;
		this.start = new JButton("Start");
		this.stop = new JButton("Stop");
		this.t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				n.batchVirusPatch();
				refresh();
			}
		});
	}

	public void init() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
	}

	public void refresh() {
		this.f.setTitle(title + ", itération : " + n.getTimer());
		this.p.repaint();
	}
	
	public void timerStop() {
		this.t.stop();
	}

	public void display() {
		this.init();
		this.t.start();
	}

}
