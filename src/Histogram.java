package ce325.hw2;

import java.io.*;
import java.util.*;

public class Histogram {

	private int [] histogram;
	private int [] tuner;
	private int numOfPixels;
	private YUVImage img;
    private static final int MAX_LUM = 235;
    
	/*constructor*/
	
	public Histogram(YUVImage img) {

		this.img = img;

		numOfPixels = img.width * img.height;
		histogram = new int[236];

		for(int el: histogram) {
			el = 0;
		}

		for(int i = 0; i < img.height; i++) {
			for(int j = 0; j < img.width; j++) {

				histogram[img.pixels[i][j].getY()]++;

			}
		}

	}
	
	/*writing the histogram into a string*/

	public String toString() {

		StringBuffer sBuffer = new StringBuffer();

		int max = 0;

		for(int i = 0; i < histogram.length; i++) {
			if(histogram[i] > max) {
				max = histogram[i];
			}
		}

		int devider = (max / 80) + 1;

		for(int i = 0; i < histogram.length; i++) {

			int numOfStars = histogram[i] / devider;

			sBuffer.append(i + "\t");

			for(int j = 0; j < numOfStars; j++) {
				sBuffer.append("*");
			}

			sBuffer.append("\n");

		}

		return sBuffer.toString();
	}
	
	/*writing the string into a file*/

	public void toFile(File file) {

		FileWriter out = null;
		PrintWriter pw = null;
	
		try {
		
			out = new FileWriter(file);
			pw = new PrintWriter(out);

			pw.print(toString());

		} catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(pw != null) {
				pw.close();
			}
		}

	}
    
    /*method to equalize the histogram*/
    
	public void equalize() {

		tuner = new int[236];
		int [] mid = new int[236];
		double [] temp = new double[236];
		YUVImage newImg = new YUVImage(img);

		for(int i = 0; i < temp.length; i++) {
			temp[i] = histogram[i];
		}
        
        //possibility distribution
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i] / numOfPixels;
		}
        
        //cumulative possibility distribution
		for(int i = 1; i < temp.length; i++) {
			temp[i] = temp[i] + temp[i-1];
		}
        
        //multiply cumulative possibility distribution with max luminocity
		for(int i = 0; i < histogram.length; i++) {
			tuner[i] = (int)(temp[i] * MAX_LUM);
		}
        
        //equalized image creation
		for(int i = 0; i < tuner.length; i++) {
			for(int j = 0; j < img.height; j++) {
				for(int k = 0; k < img.width; k++) {
					if(img.pixels[j][k].getY() == i) {
						newImg.pixels[j][k].setY(getEqualizedLuminocity(i));
					}
				}
			}
		}
        
		for(int i = 0; i < histogram.length; i++) {
			mid[tuner[i]] = histogram[i];
		}

		histogram = mid;
		
		img = newImg;
	}

	public YUVImage getEqualizedImage() {
		return img;
	}

	public short getEqualizedLuminocity(int luminocity) {

		return (short)tuner[luminocity];
	}
	

}
