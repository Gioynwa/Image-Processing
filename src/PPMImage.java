import java.io.*;
import java.util.*;

public class PPMImage extends RGBImage {

    /*calling constructors of parent class*/

	public PPMImage(int width, int height, int colordepth) {

		super(width, height, colordepth);

	}

	public PPMImage(RGBImage copyImg) {

		super(copyImg);

	}

	public PPMImage(YUVImage YUVImg) {

		super(YUVImg);

	}

	/*constructor to create a YUVImage from a file*/

	public PPMImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException {

		short red, green, blue;

		Scanner sc = new Scanner(file);
		String magicNumber = sc.next();

		if (!magicNumber.equals("P3")) {
			throw new UnsupportedFileFormatException("Unsupported File Format!");
		}

		super.width = sc.nextInt();
		super.height = sc.nextInt();
		super.colordepth = sc.nextInt();

		super.pixels = new RGBPixel[height][width];

		for(int i = 0; i < super.height; i++) {
			for(int j = 0; j < super.width; j++){

				red = sc.nextShort();
				green = sc.nextShort();
				blue = sc.nextShort();

				super.pixels[i][j] = new RGBPixel(red, green, blue);
			}
		}
	}

	/*construction of string with the image info*/

	public String toString() {

		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("P3\n");
		sBuffer.append(width + " " + height + "\n");
		sBuffer.append(colordepth + "\n");

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {

				sBuffer.append(pixels[i][j].getRed() + " ");
				sBuffer.append(pixels[i][j].getGreen() + " ");
				sBuffer.append(pixels[i][j].getBlue() + "\n");

			}
		}

		return sBuffer.toString();
	}

	/*method for writing the string into a file*/

	public void toFile(java.io.File file) {

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

}
