package network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NetworkDisplay {
	private JFrame f;
	private JPanel p;
	private JButton start, stop, save;
	private Network n;
	private Timer t;
	private JLabel l, stats;
	private ArrayList<int[]> logCom;
	private static final Color BACK_COLOR = new Color(50, 54, 57);

	public NetworkDisplay(Network n, String title, int cw) {
		this.f = new JFrame(title);
		int w = n.width() * (cw + 1) + 5, h = n.height() * (cw + 1);
		int rw = w >= 300 ? w : 300;
		this.f.setPreferredSize(new Dimension(rw, h + 120));
		this.f.setBackground(BACK_COLOR);
		this.p = new NetworkPanel(cw, n);
		this.n = n;
		this.l = new JLabel("minutes écoulées : " + n.getTimer());
		int[] temp = n.getStats();
		this.stats = new JLabel("Neutres: " + temp[0] + " - Infectés: " + temp[1] + " - Patchés: " + temp[2]);
		this.start = new JButton("Start");
		this.stop = new JButton("Stop");
		this.save = new JButton("Save");
		this.logCom = new ArrayList<int[]>();
		this.t = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] stats = n.getStats();
				if (stats[0] == 0)
					t.stop();
				else {
					n.batchVirusPatch();
					logCom.add(stats);
					refresh();
				}
			}
		});
		this.start.setBounds(4, n.height() * (cw + 1) + 5, 70, 30);
		this.stop.setBounds(84, n.height() * (cw + 1) + 5, 70, 30);
		this.save.setBounds(164, n.height() * (cw + 1) + 5, 70, 30);
		this.start.setFocusPainted(false);
		this.stop.setFocusPainted(false);
		this.save.setFocusPainted(false);
		this.start.setBackground(new Color(59, 89, 182));
		this.stop.setBackground(new Color(59, 89, 182));
		this.save.setBackground(new Color(59, 89, 182));
		this.start.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.stop.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.save.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.start.setForeground(Color.white);
		this.stop.setForeground(Color.white);
		this.save.setForeground(Color.white);
		this.stats.setForeground(Color.white);
		this.l.setForeground(Color.white);
		this.l.setBounds(4, n.height() * (cw + 1) + 40, 200, 20);
		this.stats.setBounds(4, n.height() * (cw + 1) + 60, 290, 20);
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
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String file_name = "./stats/NetworkRecord_"
						+ new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()) + ".txt";
				PrintWriter pw = null;
				try {

					pw = new PrintWriter(new FileWriter(file_name, true));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				pw.println("n\ti\tp");
				for (int[] i : logCom)
					pw.println(i[0] + "\t" + i[1] + "\t" + i[2]);
				pw.close();
			}
		});
		f.add(start);
		f.add(stop);
		f.add(save);
		f.add(l);
		f.add(stats);
		f.add(p);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
	}

	public void refresh() {
		this.l.setText("minutes écoulées : " + n.getTimer());
		int[] temp = n.getStats();
		this.stats.setText("Neutres: " + temp[0] + " - Infectés: " + temp[1] + " - Patchés: " + temp[2]);
		this.f.repaint();
	}

	public void timerStop() {
		this.t.stop();
	}

	public void display() {
		this.init();
	}

}
