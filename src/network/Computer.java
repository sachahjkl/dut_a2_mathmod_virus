package network;

import java.awt.Color;
import java.util.Random;

public class Computer {
	private boolean patched, infected;
	private int lastInfection;
	private int i;
	private int j;
	private static final int delay = 30;
	private static final Color C_PATCHED = new Color(76, 175, 80);
	private static final Color C_INFECTED = new Color(213, 0, 0);
	private static final Color C_NEUTRAL = new Color(245, 245, 245);
	private static final int WAYS_OF_INFECTION = 8;

	public Computer(int i, int j) {
		this.i = i;
		this.j = j;
		this.infected = false;
		this.patched = false;
		this.lastInfection = 0;
	}

	public void infect(Network n, int timer) {
		assert (n != null);
		if (this.infected && this.canInfect(timer)) {
			Random r = new Random();
			int choice = r.nextInt(WAYS_OF_INFECTION);
			Computer c = null;
			switch (choice) { // voir propagation_mode.png
			case 0:
				c = n.getComputer(i - 1, j);
				break;
			case 1:
				c = n.getComputer(i + 1, j);
				break;
			case 2:
				c = n.getComputer(i, j - 1);
				break;
			case 3:
				c = n.getComputer(i, j + 1);
				break;
			case 4:
				c = n.getComputer(i - 1, j - 1);
				break;
			case 5:
				c = n.getComputer(i - 1, j + 1);
				break;
			case 6:
				c = n.getComputer(i + 1, j + 1);
				break;
			case 7:
				c = n.getComputer(i + 1, j - 1);
				break;
			default:
				break;
			}
			if (c != null) {
				this.lastInfection = timer;
				c.lastInfection = this.lastInfection + 1;
				c.infect();
			}
		}
	}

	public void patch() {
		this.patched = true;
	}

	public void infect() {
		if (!this.patched)
			this.infected = true;
	}

	public boolean isInfected() {
		return infected;
	}

	public boolean isPatched() {
		return patched;
	}

	private boolean canInfect(int timer) {
		return timer - lastInfection > delay;
	}

	public Color toColor() {
		if (patched)
			return Computer.C_PATCHED;
		else if (infected)
			return Computer.C_INFECTED;
		else
			return Computer.C_NEUTRAL;
	}

}
