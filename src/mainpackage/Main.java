package mainpackage;

import network.Network;
import network.NetworkDisplay;

public class Main {

	public static void main(String[] args) {
		final int w = 100, h = 100;
		// final int duration = 1000; // durée en min
		Network n = new Network(w, h, 60, 5);
		NetworkDisplay nd = new NetworkDisplay(n, "Affichage de l'évolution de l'infection", 8);
		n.insertVirusRandomly();
		nd.display();
	}
}
