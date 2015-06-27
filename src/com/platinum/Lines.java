package com.platinum;

import java.util.Random;

public class Lines {
	private Random rand = new Random();
//	private int temp;
	public int[] alpha[], initAlph, length, speed, ypos, size, refresh, charSet[];
	public int xpos, lineAmount;
	public float[] decay;
	public boolean[] isOn;

	public Lines(int i) {
		this.alpha = new int[3][15]; 
		this.charSet = new int[3][15];
		this.decay = new float[3];
		this.initAlph = new int[3];
		this.length = new int[3];
		this.speed = new int[3];
		this.ypos = new int[3];
		this.size = new int[3];
		this.refresh = new int[3];
		this.isOn = new boolean[3];
		this.xpos = i * (DigitalRain.resX / DigitalRain.HORIZ_LINES);

	}

	public void genStats(int j) {
		this.ypos[j] = 0;
		this.isOn[j] = true;
		


		this.length[j] = 3 + rand.nextInt(12); // determine the amount of characters
											// in the line
		
		this.alpha[j][0] = rand.nextInt(255); // determine the alpha of the first
											// character

		this.size[j] = 3 + rand.nextInt(15); // determine the character size

		this.refresh[j] = 2 + rand.nextInt(28); // how many times the characters change
											// per second
		//System.out.println("LINES Instance: " + i + " Secondary: " + j + " Refresh: " + this.refresh[j]);

		this.speed[j] = 60 / (15 + rand.nextInt(30)); // how fast it scrolls

		while (decay[j] < 0.7) { // && decay > 0.9){ //the change in alpha between
								// first and last character
			this.decay[j] = rand.nextFloat();
		}

		for (int g = 1; g < this.length[j]; g++) { // generate the alpha of
												// individual characters.
			alpha[j][g] = (int) (alpha[j][g - 1] * this.decay[j]);

		}
		for(int h = 0; h < this.length[j]; h++){
			
			this.charSet[j][h] = DigitalRain.text[rand.nextInt(64)];
		}
		
		
		lineAmount++;
		


	}
	
	public void nullify(int j){
		this.length[j] = this.refresh[j] = this.ypos[j] = this.speed[j] = 0;
		this.decay[j] = 0;
		this.isOn[j] = false;
		this.lineAmount--;
	}

}
