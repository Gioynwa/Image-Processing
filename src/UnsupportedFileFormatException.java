/*Exception class*/

public class UnsupportedFileFormatException extends java.lang.Exception {

	private String msg;

	public UnsupportedFileFormatException() {}

	public  UnsupportedFileFormatException(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}

}
