package ce325.hw2;

import java.io.*;
import java.util.*;

public class PPMImageStacker {
	
	public List<PPMImage> list = new ArrayList<PPMImage>();
	public PPMImage stackedImg;
	
	/*constructor*/
	
	public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException {
		
		/*checks if the given file exists and is a directory*/
		
		if(!dir.exists()) {
			throw new FileNotFoundException("[ERROR] " + dir + " does not exist!");
		}

		if(!dir.isDirectory()) {
			throw new FileNotFoundException("[ERROR] " + dir + " is not a directory!");
		}
		
		/*creating an array with the contents of the given directory*/
		File [] files = dir.listFiles();
		
		/*creating a list with PPM images from the files array*/
		for(File el: files) {

			System.out.println("Reading file: " + el);
			list.add(new PPMImage(el));
		}
	}
	
	/*implement stack algorithm*/
	
	public void stack() {

		int height = list.get(0).height;
		int width = list.get(0).width;
		int colordepth = list.get(0).colordepth;
		short newRed = 0, newGreen = 0, newBlue = 0;

		stackedImg = new PPMImage(width, height, colordepth);

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				for(int k = 0; k < list.size(); k++) {

					newRed += list.get(k).pixels[i][j].getRed();
					newGreen += list.get(k).pixels[i][j].getGreen();
					newBlue += list.get(k).pixels[i][j].getBlue();
			
				}

				newRed = clip(newRed / list.size(), colordepth);
				newGreen = clip(newGreen / list.size(), colordepth);
				newBlue = clip(newBlue / list.size(), colordepth);

				stackedImg.pixels[i][j].setRed(newRed);
				stackedImg.pixels[i][j].setGreen(newGreen);
				stackedImg.pixels[i][j].setBlue(newBlue);
			}
		}
	}

	public PPMImage getStackedImage() {

		return stackedImg;

	}

	/*clip method*/
	
	public short clip(int x, int colordepth) {
		if(x > colordepth) {
			x = colordepth;
		}
		if(x < 0) {
			x = 0;
		}
		return (short)x;
	}
	
}
