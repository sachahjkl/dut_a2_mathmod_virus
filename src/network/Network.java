package network;

import java.awt.Color;
import java.util.Random;

public class Network {
	private Computer[][] cotab;
	private int timer;
	private int PATCH_START, PATCH_INTERVAL;

	public Network(int witdh, int height, int patch_start, int patch_int, int delayInfection) {
		this.cotab = new Computer[height][witdh];
		for (int i = 0; i < cotab.length; i++) {
			for (int j = 0; j < cotab[0].length; j++) {
				cotab[i][j] = new Computer(i, j);
			}
		}
		Computer.setDelayInfection(delayInfection);
		this.timer = 0;
		this.PATCH_INTERVAL = patch_int;
		this.PATCH_START = patch_start;
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
	
	public void insertVirusRandomlyNoRepeat() {
		Random r = new Random();
		int i = r.nextInt(this.height());
		int j = r.nextInt(this.width());
		while(this.getComputer(i, j).isInfected()) {
			i = r.nextInt(this.height());
			j = r.nextInt(this.width());
		}
		this.insertVirusAt(i, j);
	}

	public void applyPatchRandomly() {
		Random r = new Random();
		int i = r.nextInt(this.height());
		int j = r.nextInt(this.width());
		this.applyPatchAt(i, j);
	}
	
	public void applyPatchRandomlyNoRepeat() {
		Random r = new Random();
		int i = r.nextInt(this.height());
		int j = r.nextInt(this.width());
		while(this.getComputer(i, j).isPatched()) {
			i = r.nextInt(this.height());
			j = r.nextInt(this.width());
		}
		this.applyPatchAt(i, j);
	}

	public void batchVirusPatch() {
		for (Computer[] tab : this.cotab) {
			for (Computer c : tab) {
				if (c.isInfected())
					c.infect(this, timer);
			}
		}
		 if (timer % 100 == 0)
			 insertVirusRandomly();
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
