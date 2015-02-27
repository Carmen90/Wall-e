package tp.pr3.instructions.exceptions;

@SuppressWarnings("serial")
public class InstructionExecutionException extends Exception{
	
	public InstructionExecutionException(){
		
	}

	public InstructionExecutionException(java.lang.String arg0){
		super (arg0);
	}
	
	public InstructionExecutionException(java.lang.Throwable arg0){
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.String arg0,
            java.lang.Throwable arg1){
		super (arg0,arg1);
	}
}
