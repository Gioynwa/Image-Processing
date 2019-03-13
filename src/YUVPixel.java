package ce325.hw2;

public class YUVPixel {

	private int pixel;
    
    /*constructor*/
    
	public YUVPixel(short Y, short U, short V) {
		setY(Y);
		setU(U);
		setV(V);
	}
    
    /*copy constructor*/
    
	public YUVPixel(YUVPixel pixel) {
		setY(pixel.getY());
		setU(pixel.getU());
		setV(pixel.getV());
	}
    
    /*copy from RGBPixel constructor*/
    
	public YUVPixel(RGBPixel pixel) {
		short R = pixel.getRed();
		short G = pixel.getGreen();
		short B = pixel.getBlue();

		setY((short)(((66 * R + 129 * G +  25 * B + 128) >> 8) + 16));
		setU((short)(((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128));
		setV((short)(((112 * R - 94 * G - 18 * B + 128) >> 8) + 128));
	}
	
	/*methods for getting pixel values*/

	public short getY() {
		int temp = 16711680;
		int res = pixel & temp;
		res = res >> 16;
		return (short)res;
	}

	public short getU() {
		int temp = 65280;
		int res = pixel & temp;
		res = res >> 8;
		return (short)res;
	}

	public short getV() {
		int temp = 255;
		int res = pixel & temp;
		return (short)res;
	}
	
	/*methods for setting pixel values*/

	public void setY(short Y) {
		int temp = 65535;
		pixel = pixel & temp;
		pixel = (Y << 16) + pixel;
	}

	public void setU(short U) {
		int temp = 16711935;
		pixel = pixel & temp;
		pixel = (U << 8) + pixel;
	}

	public void setV(short V) {
		int temp = 16776960;
		pixel = pixel & temp;
		pixel = V + pixel;
	}

}
