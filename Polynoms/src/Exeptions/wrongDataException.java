package Exeptions;
public class wrongDataException  extends Exception {
	
	// Parameterless Constructor
    public wrongDataException() {}

    // Constructor that accepts a message
    public wrongDataException(String message)
    {
       super(message);
    }

}
