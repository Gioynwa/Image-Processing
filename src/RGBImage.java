public class RGBImage implements Image{

	public int width, height, colordepth;
	public RGBPixel [][] pixels;

	/*default constructor*/

	public RGBImage() {}

	/*constructor*/

	public RGBImage(int width, int height, int colordepth) {

		this.width = width;
		this.height = height;
		this.colordepth = colordepth;
		pixels = new RGBPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new RGBPixel( (short)0, (short)0, (short)0 );
			}
		}

	}

	/*copy constructor*/

	public RGBImage(RGBImage copyImg) {

		this.width = copyImg.width;
		this.height = copyImg.height;
		this.colordepth = copyImg.colordepth;
		pixels = new RGBPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new RGBPixel(copyImg.pixels[i][j]);
			}
		}

	}

	/*copy from YUVImage constructor*/

	public RGBImage(YUVImage YUVImg) {

		this.width = YUVImg.width;
		this.height = YUVImg.height;
		this.colordepth = 255;
		pixels = new RGBPixel[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixels[i][j] = new RGBPixel(YUVImg.pixels[i][j]);
			}
		}

	}

    /*grayscale method*/

	public void grayscale() {

		short grey;

		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				grey = (short)( pixels[i][j].getRed() * 0.3 + pixels[i][j].getGreen() * 0.59 + pixels[i][j].getBlue() * 0.11 );
				pixels[i][j].setRed(grey);
				pixels[i][j].setGreen(grey);
				pixels[i][j].setBlue(grey);
			}
		}
	}

	/*doublesize method*/

	public void doublesize() {

		int newWidth = width * 2;
		int newHeight = height * 2;

		RGBPixel [][] newPixels = new RGBPixel[newHeight][newWidth];

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {

				newPixels[2 * i][2 * j] = new RGBPixel(pixels[i][j]);
				newPixels[2 * i + 1][2 * j] = new RGBPixel(pixels[i][j]);
				newPixels[2 * i][2 * j + 1] = new RGBPixel(pixels[i][j]);
				newPixels[2 * i + 1][2 * j + 1] = new RGBPixel(pixels[i][j]);

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

		RGBPixel [][] newPixels = new RGBPixel[newHeight][newWidth];

		for(int i = 0; i < newHeight; i++) {
			for(int j = 0; j < newWidth; j++) {

				short newRed = (short)((pixels[2 * i][2 * j].getRed() + pixels[2 * i + 1][2 * j].getRed() + pixels[2 * i][2 * j + 1].getRed() + pixels[2 * i + 1][2 * j + 1].getRed()) / 4);
				short newGreen = (short)((pixels[2 * i][2 * j].getGreen() + pixels[2 * i + 1][2 * j].getGreen() + pixels[2 * i][2 * j + 1].getGreen() + pixels[2 * i + 1][2 * j + 1].getGreen()) / 4);
				short newBlue = (short)((pixels[2 * i][2 * j].getBlue() + pixels[2 * i + 1][2 * j].getBlue() + pixels[2 * i][2 * j + 1].getBlue() + pixels[2 * i + 1][2 * j + 1].getBlue()) / 4);

				newPixels[i][j] = new RGBPixel( newRed, newGreen, newBlue );

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

		RGBPixel [][] newPixels = new RGBPixel[newHeight][newWidth];

		for(int i = 0; i < newHeight; i++) {
			for(int j = 0; j < newWidth; j++) {

				newPixels[i][j] = pixels[(height - 1) - j][i];

			}
		}

		width = newWidth;
		height = newHeight;
		pixels = newPixels;
	}
}
