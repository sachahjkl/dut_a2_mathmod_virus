package network;

import java.awt.Color;
import java.util.Random;

public class Network {
	private Computer[][] cotab;
	private int timer;
	private int PATCH_START, PATCH_INTERVAL; 

	public Network(int w, int h, int ps, int pi) {
		this.cotab = new Computer[h][w];
		for (int i = 0; i < cotab.length; i++) {
			for (int j = 0; j < cotab[0].length; j++) {
				cotab[i][j] = new Computer(i, j);
			}
		}
		this.timer = 0;
		this.PATCH_INTERVAL = pi;
		this.PATCH_START = ps;
	}

	public Computer getComputer(int i, int j) {
		if (i < this.height() && i >= 0 && j < this.width() && j >= 0)
			return cotab[i][j];
		else
			return null;
	}

	public int height() {
		return this.cotab.length;
	}

	public int width() {
		final int r0 = 0;
		return this.cotab[r0].length;
	}

	public void incrementTimer() {
		++this.timer;
	}
	
	public int getTimer() {
		return this.timer;
	}

	public void insertVirusAt(int i, int j) {
		if (i < this.height() && i >= 0 && j < this.width() && j >= 0)
			this.cotab[i][j].infect();
	}

	public void applyPatchAt(int i, int j) {
		if (i < this.height() && i >= 0 && j < this.width() && j >= 0)
			this.cotab[i][j].patch();
	}

	public void insertVirusRandomly() {
		Random r = new Random();
		int i = r.nextInt(this.height());
		int j = r.nextInt(this.width());
		this.insertVirusAt(i, j);
	}

	public void applyPatchRandomly() {
		Random r = new Random();
		int i = r.nextInt(this.height());
		int j = r.nextInt(this.width());
		this.applyPatchAt(i, j);
	}

	public void batchVirusPatch() {
		for (Computer[] tab : this.cotab) {
			for (Computer c : tab) {
				if (c.isInfected())
					c.infect(this, timer);
			}
		}
		if (timer >= PATCH_START && timer % PATCH_INTERVAL == 0)
			this.applyPatchRandomly();
		this.incrementTimer();
	}

	public Color[][] toColor() {
		Color[][] c = new Color[this.height()][this.width()];
		for (int i = 0; i < this.height(); i++) {
			for (int j = 0; j < this.width(); j++) {
				c[i][j] = this.cotab[i][j].toColor();
			}
		}
		return c;
	}
}
