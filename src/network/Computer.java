package network;

import java.awt.Color;
import java.util.Random;

public class Computer {
	private static Random r = new Random();
	private boolean patched, infected;
	private int lastInfection;
	private int i;
	private int j;
	private static int delay = 30;
	private static final Color C_PATCHED = new Color(76, 175, 80);
	private static final Color C_INFECTED = new Color(213, 0, 0);
	private static final Color C_NEUTRAL = new Color(245, 245, 245);
	// private static final int WAYS_OF_INFECTION = 4;

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
			int di = Computer.r.nextInt(3) - 1;
			int dj = Computer.r.nextInt(3) - 1;
			if (dj == 0 && di == 0) {
				if (r.nextBoolean())
					di += r.nextInt(2) == 1 ? 1 : -1;
				else
					dj += r.nextInt(2) == 1 ? 1 : -1;
			}
			Computer c = n.getComputer(i + di, j + dj);
			if (c != null) {
				if (!c.isInfected() && !c.isPatched()) {
					this.lastInfection = timer;
					c.lastInfection = this.lastInfection + 1;
					c.infect();
				}
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

	public static void setDelayInfection(int d) {
		Computer.delay = d;
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
