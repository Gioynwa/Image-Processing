package ce325.hw2;

public class RGBPixel {

	private int pixel;
    
    /*constructor*/
    
	public RGBPixel(short red, short green, short blue) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
	
	/*copy constructor*/

	public RGBPixel(RGBPixel pixel) {
		setRed(pixel.getRed());
		setGreen(pixel.getGreen());
		setBlue(pixel.getBlue());
	}
	
	/*copy from YUVPixel constructor*/

	public RGBPixel(YUVPixel pixel) {
		int C = pixel.getY() - 16;
		int D = pixel.getU() - 128;
		int E = pixel.getV() - 128;

		setRed(clip((298 * C + 409 * E + 128) >> 8));
		setGreen(clip(( 298 * C - 100 * D - 208 * E + 128) >> 8));
		setBlue(clip(( 298 * C + 516 * D + 128) >> 8));
	}
	
	/*methods for getting pixel values*/

	public short getRed() {
		int temp = 16711680;
		int res = pixel & temp;
		res = res >> 16;
		return (short)res;
	}

	public short getGreen() {
		int temp = 65280;
		int res = pixel & temp;
		res = res >> 8;
		return (short)res;
	}

	public short getBlue() {
		int temp = 255;
		int res = pixel & temp;
		return (short)res;
	}
	
	public int getPixel() {
		return pixel;
	}
	
	/*methods for setting pixel values*/

	public void setRed(short red) {
		int temp = 65535;
		pixel = pixel & temp;
		pixel = (red << 16) + pixel;
	}

	public void setGreen(short green) {
		int temp = 16711935;
		pixel = pixel & temp;
		pixel = (green << 8) + pixel;
	}

	public void setBlue(short blue) {
		int temp = 16776960;
		pixel = pixel & temp;
		pixel = blue + pixel;
	}
    
    /*clip method*/
    
	public short clip(int x) {
		if(x > 255) {
			x = 255;
		}
		if(x < 0) {
			x = 0;
		}
		return (short)x;
	}
}
