package Projects.Project2;

public class MyIndexOutOfBoundsException extends RuntimeException {
	private static final long serialVersionUID = 42l;
	
	public MyIndexOutOfBoundsException(String message) {
		super("This is a custom exception class. " + message);
	}

	public MyIndexOutOfBoundsException() {
		super("This is a custom exception class.");
	}
}
