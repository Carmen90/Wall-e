package tp.pr3.cityLoader;


import java.util.Scanner;

import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;

	public class CityLoaderFromTxtFile {
		private Place [] place;
		private Street [] street;
		
	public 	CityLoaderFromTxtFile (){
		this.place = new Place[20];
		this.street = new Street [20];
		for (int i=0; i < this.place.length; i++){
			this.place[i]= new Place ();
			this.street[i] = new Street ();
		}
		
	}
	
	public City loadCity(java.io.InputStream file)
            throws java.io.IOException{
		
		

		Scanner entrada = new Scanner (file);
		
		String resp;
		resp = controlNext ( entrada);
		
			if ( resp.equalsIgnoreCase("BeginCity")){
					while ( !resp.equalsIgnoreCase("EndCity")){
						resp = controlNext ( entrada);
						if ( resp.equalsIgnoreCase("BeginPlaces")){
							creaPlaces (resp, entrada);
	
						}
						else if ( resp.equalsIgnoreCase("BeginStreets")){
							creaStreets ( resp, entrada);
									
						}
						else if ( resp.equalsIgnoreCase("BeginItems")){
							creaItems (resp,entrada);
						}
						else if ( !resp.equalsIgnoreCase("EndCity")){
							throw new WrongCityFormatException();
						}
					}
			}
			else{
				throw new WrongCityFormatException();
			}
			
		return new City(this.street);
	}
	/**
	 * Returns the place where the robot will start the simulation
	 * @return: The initial place
	 */
	public Place getInitialPlace (){
		return this.place[0];
	}
	
	public void creaPlaces ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		int num = 0;
		int i = 0;
		String descripcion = "";
		String nombre = "";
		
		while ( !resp.equalsIgnoreCase("EndPlaces") ){
			resp = controlNext(entrada);
				if ( resp.equalsIgnoreCase("place")){
					num = controlNextInt(entrada);
					nombre = controlNext(entrada);
					descripcion = controlNext (entrada);
					if (descripcion.charAt(0)== '\"'){
						while (descripcion.charAt(descripcion.length()-1) != '\"'){
							descripcion +=" " + controlNext(entrada);
						}
					}
					resp = controlNext( entrada);
						if ( resp.equalsIgnoreCase("noSpaceShip")){
							descripcion = descripcion.replace("_", " ");
							descripcion = descripcion.replace("\"", "");
							if( this.place[num].getName().equalsIgnoreCase("") && num == i){
								this.place[num] = new Place (nombre, false, descripcion);
							}else {
								throw new WrongCityFormatException();
							}
						}
						else if ( resp.equalsIgnoreCase("spaceShip")){
							descripcion = descripcion.replace("_", " ");
							descripcion = descripcion.replace("\"", "");
							if( this.place[num].getName().equalsIgnoreCase("")){
								this.place[num] = new Place (nombre, true, descripcion);
							}else {
								throw new WrongCityFormatException();
							}
						}
						else{
							throw new WrongCityFormatException();
						}
			}
			else if (!resp.equalsIgnoreCase("EndPlaces")){
				throw new WrongCityFormatException();
			}
		i++;
		}
	}

	public void creaStreets ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		
		int num = 0, i = 0;
		int numPlaceIni = 0;
		int numPlaceFin = 0;
		Direction direccion = Direction.UNKNOWN;
		String code = null;
		
		while ( !resp.equalsIgnoreCase("EndStreets") ){
			resp = controlNext(entrada);
			if ( resp.equalsIgnoreCase("street")){
				num = controlNextInt(entrada);
				resp = controlNext(entrada);
				if ( resp.equalsIgnoreCase("place")){
					numPlaceIni = controlNextInt ( entrada);
					resp = controlNext (entrada);
					direccion = Direction.valueOf(resp.toUpperCase());
					resp = controlNext (entrada);
					if (resp.equalsIgnoreCase("place")){
						numPlaceFin = controlNextInt (entrada);
						resp = controlNext (entrada);
						controlPlacesCorrectos (numPlaceIni, numPlaceFin);
						if (this.street[num].getId().equalsIgnoreCase("") && num == i){
							if ( resp.equalsIgnoreCase("open")){
								this.street[num] = new Street (this.place[numPlaceIni], direccion, this.place[numPlaceFin]);
								i++;
							}
							else if (resp.equalsIgnoreCase("closed")){
								code = controlNext(entrada);
								this.street[num] = new Street ( this.place[numPlaceIni], direccion, this.place[numPlaceFin], true, code);
								i++;
								
							}
							else{
								throw new WrongCityFormatException();
							}
						}
						else{
							throw new WrongCityFormatException();
						}
					}
					else{
						throw new WrongCityFormatException();//no place 2
					}
				}
				else{
					throw new WrongCityFormatException();//no place
				}
			}
		}

	}

	public void creaItems ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		int i = 0;
		
		while ( !resp.equalsIgnoreCase("EndItems")){
			resp = controlNext(entrada);
			
			if ( resp.equalsIgnoreCase("fuel")){
				i = creaFuel ( i, entrada);
				
			}
			else if (resp.equalsIgnoreCase("codecard")){
				i = creaCodeCard (i, entrada);
				
			}
			else if (resp.equalsIgnoreCase("garbage")){
				i = creaGarbage (i, entrada);

			}
			else if (!resp.equalsIgnoreCase("EndItems")){
				throw new WrongCityFormatException();
			}
		}
	}
	public int creaFuel (int i, Scanner entrada)
			throws WrongCityFormatException {
		int num = 0;
		int power = 0; 
		int cantidad = 0;
		int numPlace = 0;
		String nombre = null; 
		String descripcion = null;
		String resp = null;
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext (entrada);
		if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion += controlNext(entrada);
			}
		}
		power = controlNextInt (entrada);
		cantidad = controlNextInt (entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if( i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new Fuel (nombre, descripcion, power, cantidad));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	public int creaCodeCard ( int i, Scanner entrada)
			throws WrongCityFormatException {
		int num = 0;
		String nombre = null;
		String descripcion = null;
		String resp = null;
		String code = null;
		int numPlace = 0;
		
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext ( entrada);
		if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion += controlNext(entrada);
			}
		}
		code = controlNext ( entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if ( i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new CodeCard (nombre, descripcion, code));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	public int creaGarbage ( int i, Scanner entrada) throws WrongCityFormatException{
		int num = 0;
		String nombre = null;
		String descripcion = null;
		int cantidad = 0;
		String resp = null;
		int numPlace = 0;	
		
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext ( entrada);
		if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion += controlNext(entrada);
			}
		}
		cantidad = controlNextInt (entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if(i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new Garbage (nombre, descripcion, cantidad));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	public String controlNext ( Scanner entrada) 
			throws WrongCityFormatException{
		String resp = "";
		
		if (entrada.hasNext()){
			resp = entrada.next();
		} else{
			throw new WrongCityFormatException();
		}
		return resp;
	}
	
	public int controlNextInt ( Scanner entrada) 
			throws WrongCityFormatException{
		String resp = null;
		int numero = 0;
		
		if (entrada.hasNext()){
			resp = entrada.next();
			numero = Integer.parseInt(resp);
		} else{
			throw new WrongCityFormatException();
		}
		return numero;
	}
	
	public void controlPlacesCorrectos (int numIni, int numFin)
			throws  WrongCityFormatException{
		boolean encI = false;
		boolean encF = false;
		String inicio = this.place[numIni].getName();
		String fin = this.place[numFin].getName();
		int i=0;
		if ( !inicio.equalsIgnoreCase(fin) && numIni <this.place.length && numFin <this.place.length){
			while ( (!encI || !encF) && i < this.place.length){
				if ( this.place[i].getName() != ""){
					if(inicio.equalsIgnoreCase(this.place[i].getName())){
						encI = true;
					}
					if( fin.equalsIgnoreCase(this.place[i].getName())){
						encF = true;
					}
				}
				i++;
			}
			if ( !encI || !encF){
				throw new WrongCityFormatException();
			}
		}
		else{
			throw new WrongCityFormatException();
		}
	}

	
}

