package tp.pr3.instructions.exceptions;
/**
 * Exception thrown when a string cannot be parsed 
 * to create a command. The exception has a user-friendly 
 * message with an explanation about the error.
 * This class has many different constructors,
 * one for every constructor of the base class.
 * @author Nerea
 *
 */
@SuppressWarnings("serial")
public class WrongInstructionFormatException extends Exception {
	public WrongInstructionFormatException(){
		super();
	}
	/**
	 * The exception thrown is created with a problem message.
	 * @param arg0
	 */
	public WrongInstructionFormatException(java.lang.String arg0){
		super(arg0);
	}
	/**
	 * Constructor to create the exception with a nested cause.
	 * @param arg0
	 */
	public WrongInstructionFormatException(java.lang.Throwable arg0){
		super (arg0);
	}
	/**
	 * Constructor to create the exception with a nested cause and an error message.
	 * @param arg0
	 * @param arg1
	 */
	public WrongInstructionFormatException(java.lang.String arg0,
            java.lang.Throwable arg1){
		super (arg0, arg1);
	}

}
