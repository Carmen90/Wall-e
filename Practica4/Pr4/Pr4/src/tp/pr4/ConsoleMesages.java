package tp.pr4;


public class ConsoleMesages {
	static String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * Método que se encarga de mostrar el mensage de final de jugada, ya sea por haber llegado 
	 * a SpaceShip o por haberse quedado sin fuel. Recibe un parametro de entrada que indica cual de los
	 * dos casos es. Si es spaceShip recibira true y si es por falta de fuel recibe false.
	 * @param spaceShip booleano que indica si es por spaceShip o por falta de fuel
	 */
	public static void finishMesage ( boolean spaceShip){
		if ( spaceShip){
			System.out.println ( "WALLÂ·E says: I am at my spaceship. Bye bye");
		}
		else{
			System.out.println ("WALLÂ·E says: I run out of fuel. I cannot move. Shutting down...");
		}
	}
	
	/**
	 * Método que se encarga de mostrar si un objeto ha sido depositado o no. 
	 * Un objeto es depositado si se tiene en el inventario, sino te dice que no lo tienes
	 * @param drop si el objeto se tiene en el inventario o no ( boolean)
	 * @param id identificador del objeto a depositar, tanto si existe en el inventario como si no
	 */
	public static void dropMesage ( boolean drop, String id){
		if ( drop){
			System.out.println("Great! I have dropped " + id);
		}else{
			System.out.println("You do not have any " + id);
		}
	}
	
	/**
	 * Metodo que se encarga de mostrar por consola cuando se mueve el robot.
	 * Muestra en la dirección en la que se mueve, asi como la dirección del lugar al que se ha movido y el estado del robot
	 * @param robot el cual efectua el movimiento
	 * @param navega nos da el lugar al que se ha movido y su direccion respecto al anterior
	 */
	public static void moveMesage ( RobotEngine robot, NavigationModule navega){
		System.out.println ("WALLÂ·E says: Moving in direction " + navega.getCurrentHeading());
		placeMesage (robot, navega);
	}
	
	/**
	 * Método que se encarga de mostrar por consola la descripción de un lugar, asi como su nombre y el estado del robot en dicho lugar
	 * @param robot del que se muestra el estado, siendo este la energia y el material reciclado que posee
	 * @param navega continie la informacion necesaria del lugar del que se muestra la descripcion y el nombre
	 */
	public static void placeMesage (RobotEngine robot, NavigationModule navega){
		System.out.println(navega.getInitialPlace().toString());
		robotStateMesage(robot);
	}
	
	/**
	 * Muestra el estado del robot, vease energía que tiene y material reciclado
	 * @param robot del que se muestra la mencionada información
	 */
	public static void robotStateMesage (RobotEngine robot){
		System.out.println("      * My power is " + robot.getFuel() + LINE_SEPARATOR + "      * My reclycled material is " + robot.getRecycledMaterial());
	}
	
	/**
	 * Método que muestra la direccion en la que etsa mirando el robot así como su estado actual
	 * @param robot del que se muestra el estado
	 * @param navega de donde se saca la información de la dirección a la que se mira
	 */
	public static void lookingAtMesage (RobotEngine robot, NavigationModule navega){
		System.out.println("WALLÂ·E is looking at direction " + navega.getCurrentHeading());
		robotStateMesage(robot);
	}
	
	/**
	 * Método que indica que se ha cogido correctamente un objeto
	 * @param id
	 */
	public static void pickMesage ( String id){
		System.out.println("WALLÂ·E says: I am happy! Now I have " + id );
	}

}
