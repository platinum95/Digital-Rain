package com.platinum;

import java.util.Random;
import processing.core.PApplet;
import processing.core.PFont;

public class DigitalRain extends PApplet{
	static final long serialVersionUID = 1;
	private Random randGen = new Random();
	private boolean nulled;
	public static int resX, resY;
	public static int HORIZ_LINES;
	private int pos[], count;
	private static final int HORIZ_LINE_SPACE = 10, VERT_LINE_SPACE = 15;
	private static PFont font = new PFont();
	private static final int FPS = 60;
	private Lines[] lineInstance;
	private String printOut = new String();
	public static int[] text = {1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390, 1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399, 1400, 1401, 1402, 1403, 1404, 1405, 1406, 1407, 1408, 1409, 1410, 1411, 1412, 1413, 1414, 1415, 1416, 1488, 1489, 1490, 1491, 1492, 1493, 1494, 1495, 1496, 1497, 1498, 1499, 1500, 1501, 1502, 1503, 1504, 1505, 1506, 1507, 1508, 1509, 1510, 1511, 1512, 1513, 1514};


	public static void main(String args[]){
		PApplet.main(new String[] { com.platinum.DigitalRain.class.getName() });	
	}
	
	public void setup(){
		resX = this.displayWidth;
		resY = this.displayHeight;
		setDisplay(this);
		HORIZ_LINES = resX/HORIZ_LINE_SPACE;
		pos = new int[HORIZ_LINES];
		for(int i = 0; i < HORIZ_LINES; i++){
			pos[i] = i * (resX/HORIZ_LINES);
		}
		font = createFont("GNU Unifont", 128, true);
		frameRate(FPS);
		lineInstance = new Lines[HORIZ_LINES];
		//text = new int[50];
		
	}
	
	public void draw(){
		
		this.background(0);
		for(int i = 0; i < HORIZ_LINES; i++){
			if(this.count % 1 == 0)
				getNewLines(i);
			if(lineInstance[i] != null){
				for(int j = 0; j < 3; j++){
					if(lineInstance[i].isOn[j] == true){
						//nulled = false;
						
						
						//reset chars
						//System.out.println("RESET: Instance: " + i + " Secondary: " +  j + ". Refresh: " + lineInstance[i].refresh[j]);
						if(count % lineInstance[i].refresh[j] == 0){
							resetChar(i, j);							
						}
						//move down one
						if(count % lineInstance[i].speed[j] == 0){
							nulled = moveDown(i, j);
						}
						//redraw
						if(!nulled)
							redraw(i, j);
						
						
						
					}
				}
			}
			
		}
		count++;
		if(count == FPS)
			count = 0;
	}
	
	private static void setDisplay(PApplet display){
		display.size(resX, resY);
		display.background(0);
	}
	
	private void getNewLines(int i){
		//for(int i = 0; i < HORIZ_LINES; i++){
			if(lineInstance[i] == null || lineInstance[i].lineAmount < 3){
				if(randGen.nextInt(1000) > 975){
					if(lineInstance[i] == null)
						lineInstance[i] = new Lines(i);
					for(int j = 0; j < 3; j++){
						if(lineInstance[i].isOn[j] == false){
							lineInstance[i].genStats(j);
							//System.out.println("GENSTATS instance: " + i + ". Secondary: " + j + " Refresh: " + lineInstance[i].refresh[j]);
							break;
						}
					}
					
				}
			}
	//	}
	}
	
	private void resetChar(int i, int j){
		for(int g = 0; g < lineInstance[i].length[j]; g++){
			
			lineInstance[i].charSet[j][g] = text[randGen.nextInt(64)];
		}
		
	}
	
	private boolean moveDown(int i, int j){
		lineInstance[i].ypos[j] = lineInstance[i].ypos[j] + VERT_LINE_SPACE;
		
		if(lineInstance[i].ypos[j] - (lineInstance[i].length[j] * VERT_LINE_SPACE) >= resY){
			lineInstance[i].isOn[j] = false;
			System.out.println("Am here " + i);

			//lineInstance[i].nullify(j);
		}
		if((lineInstance[i].isOn[0] == false && lineInstance[i].isOn[1] == false && lineInstance[i].isOn[2] == false) || lineInstance[i].lineAmount == 0){
			lineInstance[i] = null;
			System.out.println("Nullifying instance: " + i);
			return true;
			
		}
		else
			return false;
	}
	
	private void redraw(int i, int j){
		
		//System.out.println("Instance: " + i + " Secondary: " + j);// + "  Length: " + lineInstance[i].length[j]);
		
		for(int g = 0; g < lineInstance[i].length[j]; g++){
			textFont(font, lineInstance[i].size[j]);
			if(g == 0)
				fill(255, 255, 255, lineInstance[i].alpha[j][g]);
			else
				fill(0, 255, 0, lineInstance[i].alpha[j][g]);
			printOut = Character.toString((char) lineInstance[i].charSet[j][g]);
			
			//System.out.println("xpos: " + lineInstance[i].xpos + " ypos: " + lineInstance[i].ypos[j]);
			text(printOut, lineInstance[i].xpos, lineInstance[i].ypos[j] - (g * VERT_LINE_SPACE));
		}
		
	}
	
}
