package tp.pr3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;

public class Main {

	public static void main(String[] args) {
		FileInputStream file = null;

		if ( args.length == 1){
			CityLoaderFromTxtFile city = new CityLoaderFromTxtFile(); 
			
			File f = new File(args[0]); 
			try {
				file = new FileInputStream(f);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
				System.exit(2);
			}
			RobotEngine engine;
			try {
				engine = new RobotEngine(city.loadCity(file), city.getInitialPlace(), Direction.NORTH);
				engine.startEngine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
				System.exit(2);
			}
		}
		else{
			System.err.println("Bad params."+ LINE_SEPARATOR +"Usage: java tp.pr3.Main <mapfile>" + LINE_SEPARATOR +  LINE_SEPARATOR +"<mapfile> : file with the description of the city.");
			System.exit(1);
		}
	}
	
	static String LINE_SEPARATOR = System.getProperty("line.separator");

}
