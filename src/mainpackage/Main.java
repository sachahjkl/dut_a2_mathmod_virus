package mainpackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import network.Network;
import network.NetworkDisplay;

public class Main {

	public static void main(String[] args) {
		final int w = 100, h = 100;
		// final int duration = 1000; // durée en min
		Network n = new Network(w, h, 60, 5);
		n.insertVirusRandomly();
		NetworkDisplay nd = new NetworkDisplay(n, "Affichage de l'évolution de l'infection", 8);
		nd.display();
//		ArrayList<int[]> logCom = new ArrayList<>();
//		int[] stats = n.getStats();
//		logCom.add(stats);
//		while(stats[0]!= 0) {
//			n.batchVirusPatch();
//			stats = n.getStats();
//			System.out.println("Neutres: " + stats[0] + " - Infectés: " + stats[1] + " - Patchés: " + stats[2]);
//			logCom.add(stats);
//		}
//		String file_name = "./stats/stats_"
//				+ new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()) + ".log";
//		PrintWriter pw = null;
//		try {
//			pw = new PrintWriter(new FileWriter(file_name, true));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		pw.println("n\ti\tp");
//		for (int[] i : logCom)
//			pw.println(i[0] + "\t" + i[1] + "\t" + i[2]);
//		pw.close();
	}
}
