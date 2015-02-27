package tp.pr3;

public class Interpreter {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	private static String help="The valid instructions for WALL-E are:" + LINE_SEPARATOR +
	"     MOVE"+ LINE_SEPARATOR + "     " + "TURN <LEFT | RIGHT>" + LINE_SEPARATOR 
	+ "     PICK <id>" + LINE_SEPARATOR +
	"     SCAN [ <id> ]" + LINE_SEPARATOR + "     OPERATE <id>" + LINE_SEPARATOR +
	"     HELP" + LINE_SEPARATOR + "     QUIT" + LINE_SEPARATOR ;
	private static Instruction instruction;
	

	
	public Interpreter(){
		//this.help  		
	}
	
	public static java.lang.String interpreterHelp(){
		return help;
	}
	
	public static Instruction generateInstruction(java.lang.String line){
		instruction = new Instruction ();	
		line= line.trim();//elimina los espacios en blanco.
		
		String [] cadena = line.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		
		
	if ( cadena.length ==  1){
		if(cadena[0].equalsIgnoreCase("move")){
			instruction = new Instruction (Action.MOVE);
		}
		else if(cadena[0].equalsIgnoreCase("QUIT")){
			instruction= new Instruction (Action.QUIT);
		}
		else if(cadena[0].equalsIgnoreCase("HELP")){
		
			instruction= new Instruction(Action.HELP);
		}
		else if(cadena[0].equalsIgnoreCase("TURN")){
			
			instruction= new Instruction(Action.TURN);
		}
		else if ( cadena[0].equalsIgnoreCase("SCAN")){
			instruction= new Instruction(Action.SCAN);
		}
		else {
			instruction= new Instruction();
		}
	}
	else if(cadena.length > 1){
		if(cadena.length==2){
			if ( cadena[0].equalsIgnoreCase("PICK")){
				instruction= new Instruction(Action.PICK, cadena[1]);
			}
			if ( cadena[0].equalsIgnoreCase("SCAN")){
				instruction= new Instruction(Action.SCAN, cadena[1]);
			}
			if ( cadena[0].equalsIgnoreCase("OPERATE")){
				instruction= new Instruction(Action.OPERATE, cadena[1]);
			}
			
		}
		if ( cadena[0].equalsIgnoreCase("TURN")){
	
			if(cadena[1].equalsIgnoreCase("LEFT")){
				instruction = new Instruction(Action.TURN,Rotation.LEFT);
				}
			else if(cadena[1].equalsIgnoreCase("RIGHT")){
			
				instruction= new Instruction(Action.TURN, Rotation.RIGHT);
			}
			else{
				instruction= new Instruction(Action.TURN);
			}
		}
	}
	return instruction;
	
	}


}
