package pr1;

public class Interpreter {
	private String help;
	private Instruction instruction;

	
	public Interpreter(){
		this.help= "MOVE"+ '\n' + "TURN { LEFT | RIGHT }" + '\n' + "HELP" + '\n' + "QUIT";
		this.instruction = null;
	}
	
	public java.lang.String interpreterHelp(){
		return help;
	}
	
	public Instruction generateInstruction(java.lang.String line){
	
		line= line.trim();//elimina los espacios en blanco.
		
		//line= line.toUpperCase();//pone todo a mayusculas. ******Creo que sobra ya que el equalsIgnore 
		//ignora entre mayus y minus luego no hace falta pasarlas a mayus
		
		String [] cadena = line.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posición del array*/
		
		
	if ( cadena.length ==  1){
		if(cadena[0].equalsIgnoreCase(Action.MOVE.toString())){
			this.instruction = new Instruction (Action.MOVE);
		}
		else if(cadena[0].equalsIgnoreCase(Action.QUIT.toString())){
			this.instruction= new Instruction (Action.QUIT);
		}
		else if(cadena[0].equalsIgnoreCase(Action.HELP.toString())){
		
			this.instruction= new Instruction(Action.HELP);
		}
		else if(cadena[0].equalsIgnoreCase(Action.MOVE.toString())){
		
			this.instruction= new Instruction(Action.MOVE);
		}
		else {
			System.out.println("WALL-E says: I do not understand. Please repeat.");
		}
	}
	else if(cadena.length > 1){
		if ( cadena [0].equals(Action.TURN)){
	
			if(cadena[1].equalsIgnoreCase("LEFT")){
			
					this.instruction = new Instruction(Action.TURN,Rotation.LEFT);
				}
			else if(cadena[1].equalsIgnoreCase("RIGHT")){
			
				this.instruction= new Instruction(Action.TURN, Rotation.RIGHT);
			}
		}
	}else
		System.out.println("WALL-E says: I do not understand. Please repeat.");
	
	return this.instruction;
	}


}
