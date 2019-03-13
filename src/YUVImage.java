import java.io.*;
import java.util.*;

public class YUVImage implements Image {

	public int width, height;
	public YUVPixel [][] pixels;

	/*constructor*/

	public YUVImage(int width, int height) {

		this.width = width;
		this.height = height;

		pixels = new YUVPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new YUVPixel( (short)16, (short)128, (short)128 );
			}
		}

	}

	/*copy constructor*/

	public YUVImage(YUVImage copyImg) {

		this.width = copyImg.width;
		this.height = copyImg.height;
		pixels = new YUVPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new YUVPixel(copyImg.pixels[i][j]);
			}
		}

	}

	/*copy from RGBImage constructor*/

	public YUVImage(RGBImage RGBImg) {

		this.width = RGBImg.width;
		this.height = RGBImg.height;
		pixels = new YUVPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new YUVPixel(RGBImg.pixels[i][j]);
			}
		}

	}

    /*constructor to create a YUVImage from a file*/

	public YUVImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException {

		short Y, U, V;

		try {

			Scanner sc = new Scanner(file);
			String magicNumber = sc.next();

			if (!magicNumber.equals("YUV3")) {
				throw new UnsupportedFileFormatException("Unsupported File Format!");
			}

			width = sc.nextInt();
			height = sc.nextInt();

			pixels = new YUVPixel[height][width];

			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++){
					Y = sc.nextShort();
					U = sc.nextShort();
					V = sc.nextShort();

					pixels[i][j] = new YUVPixel( Y, U, V );
				}
			}

		} catch ( InputMismatchException ex ) {
			System.out.println(ex.getMessage());
		}


	}

    /*grayscale method*/

	public void grayscale() {

		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				pixels[i][j].setU((short)128);
				pixels[i][j].setV((short)128);
			}
		}
	}

    /*doublesize method*/

	public void doublesize() {

		int newWidth = width * 2;
		int newHeight = height * 2;

		YUVPixel [][] newPixels = new YUVPixel[newHeight][newWidth];

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {

				newPixels[2 * i][2 * j] = new YUVPixel(pixels[i][j]);
				newPixels[2 * i + 1][2 * j] = new YUVPixel(pixels[i][j]);
				newPixels[2 * i][2 * j + 1] = new YUVPixel(pixels[i][j]);
				newPixels[2 * i + 1][2 * j + 1] = new YUVPixel(pixels[i][j]);

			}
		}

		width = newWidth;
		height = newHeight;
		pixels = newPixels;
	}

	/*halfsize method*/

	public void halfsize() {

		int newWidth = width / 2;
		int newHeight = height / 2;

		YUVPixel [][] newPixels = new YUVPixel[newHeight][newWidth];

		for(int i = 0; i < newHeight; i++) {
			for(int j = 0; j < newWidth; j++) {

				short newY = (short)((pixels[2 * i][2 * j].getY() + pixels[2 * i + 1][2 * j].getY() + pixels[2 * i][2 * j + 1].getY() + pixels[2 * i + 1][2 * j + 1].getY()) / 4);
				short newU = (short)((pixels[2 * i][2 * j].getU() + pixels[2 * i + 1][2 * j].getU() + pixels[2 * i][2 * j + 1].getU() + pixels[2 * i + 1][2 * j + 1].getU()) / 4);
				short newV = (short)((pixels[2 * i][2 * j].getV() + pixels[2 * i + 1][2 * j].getV() + pixels[2 * i][2 * j + 1].getV() + pixels[2 * i + 1][2 * j + 1].getV()) / 4);

				newPixels[i][j] = new YUVPixel( newY, newU, newV );

			}
		}

		width = newWidth;
		height = newHeight;
		pixels = newPixels;
	}

    /*rotateClockwise method*/

	public void rotateClockwise() {

		int newWidth = height;
		int newHeight = width;

		YUVPixel [][] newPixels = new YUVPixel[newHeight][newWidth];

		for(int i = 0; i < newHeight; i++) {
			for(int j = 0; j < newWidth; j++) {

				newPixels[i][j] = pixels[(height - 1) - j][i];

			}
		}

		width = newWidth;
		height = newHeight;
		pixels = newPixels;
	}

    /*construction of string with the image info*/

	public String toString() {

		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("YUV3\n");
		sBuffer.append(width + " " + height + "\n");

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {

				sBuffer.append(pixels[i][j].getY() + " ");
				sBuffer.append(pixels[i][j].getU() + " ");
				sBuffer.append(pixels[i][j].getV() + "\n");

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
