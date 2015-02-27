package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.Direction;
import tp.pr4.Place;
import tp.pr4.RobotEngine;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Esta clase se encarga de mostrar a walle y la informacion sobre la ciudad
 * Tiene una matriz de PlaceCell de 11x11 cuyo primer lugar enpieza en la posicion (5,5)
 * Tambi�n tiene un area que muestra las descrici�n del lugar y una etiqueta con el icono de walle
 *
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel{
	private final int F = 11;
	private final int C = 11;
	private final int FILINI = 5;
	private final int COLINI = 5;
	
	private JLabel walle;
	private JTextArea texto;
	private PlaceCell [][] places ;
	private JPanel placesPanel;
	private JPanel scrollPanel;
	private JScrollPane scroll;
	private int filas = FILINI;
	private int colum = COLINI;
	private Place lugar;
	
	/**
	 * Constructora con un parametro
	 * @param robot 
	 */
	public NavigationPanel(RobotEngine robot){
		this.places = new PlaceCell[F][C];
		this.placesPanel = new JPanel();
		this.lugar = robot.getNavega().getInitialPlace();
		this.filas = FILINI;
		this.colum = COLINI;

		initNavigPanel();
	}
	
	/**
	 * Inicializa el panel con las celdas para el mapa de la ciudad
	 */
	public void panelPlaces (){
		//Hago un gridLayout de 11x11 para el mapa
        LayoutManager thisLayout = new GridLayout(11,11);
        this.placesPanel.setLayout(thisLayout);

        //Inicializo los botones de los lugares y los meto en su respectivo panel
		for ( int i = 0; i < F; i++){
			for ( int j = 0; j < C; j++){
				if ( i == FILINI && j == COLINI){
					this.places[i][j] = new PlaceCell();
					//this.places[i][j] = new PlaceCell(robot.getNavega().getInitialPlace());
					this.places [i][j].setName("placeCell");
					this.actualizaLog(lugar.toString());
					this.actualizaMapa(lugar);
					
					this.placesPanel.add (places[i][j]);
					
				}
				else{
					this.places[i][j] = new PlaceCell();
					this.places [i][j].setName("placeCell");
					this.placesPanel.add (places[i][j]);
				}
			}
		}
        //Fijo las dimensiones que quiero que tenga y le doy t�tulo al panel
        this.setPreferredSize(new java.awt.Dimension (900, 400));
        this.placesPanel.setBorder( new TitledBorder ("City Map"));
        

	}
	
	/**
	 * M�todo que se encraga de crear el panel en el que se encuentra el Log
	 * El log muestra las descripciones de los lugares por los que pasa walle
	 * 
	 */
	public void panelScroll(){
		//Inicializaciones de los atributos usados en este panel
		this.scrollPanel = new JPanel ();
		this.scroll = new JScrollPane();
    	this.scrollPanel = new JPanel();
    	
    	
    	this.scrollPanel.setBorder(new TitledBorder("Log"));
        this.scrollPanel.setName("Navigation");
        
        //Editamos la parte gr�fica del log
		this.texto = new JTextArea(5, 30);
		this.texto.setEditable(false);
		this.texto.setName("texto");
		this.scroll = new JScrollPane(texto);
		
		//Hacemos que siempre sean visibles los scroll
		this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
		this.scrollPanel.add(this.scroll);
		
        LayoutManager thisLayout = new GridLayout();
        this.scrollPanel.setLayout(thisLayout);
		
	}
	
	/**
	 * M�todo que se encarga de inicializar el panel en el que se encuentra el icono de walle
	 * las celdas del mapa de la ciudad y el log con la informaci�n de los lugares
	 */
	public void initNavigPanel(){
		this.walle = new JLabel();
		
		this.walle.setName( "walle");
		this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleNorth.png")));
		
		this.setLayout(new BorderLayout());
		
		//Creo el panel de ciudad y el de log
		panelScroll();
		this.panelPlaces();
		
		
		//A�ado la etiqueta de walle
		this.add(this.walle,BorderLayout.WEST);
		//A�ado la matriz de la ciudad
		this.add(this.placesPanel,BorderLayout.CENTER);
		//A�ado el panel del log
		this.add(scrollPanel,BorderLayout.SOUTH);

	}
	
	/**
	 * M�todo que se encarga de actualizar todo el panel. Obteniendo del modelo los datos.
	 * @param arg0 contiene el modelo
	 * @param arg1 contiene el argumento pasado desde el modelo.
	 */
	public void update(Observable arg0, Object arg1){
		RobotEngine modelo = (RobotEngine) arg0;	
		
		this.actualizaWalle(modelo.getNavega().getDirection());
		this.places[this.filas][this.colum].addActionListener((ActionListener) modelo);
			if ( !modelo.isPlaceCell()){
				this.actualizaLog(modelo.getNavega().getInitialPlace().toString());
				if ( modelo.isNavOper()){
					this.actualizarCoordenadas(modelo.getNavega().getDirection());
					this.actualizaMapa(modelo.getNavega().getInitialPlace());

				}
			}
			else{
				this.actualizaLog(modelo.getLugar().getPlace().toString());
			}
			
		
	}
	
	/**
	 * Actualiza la imagen de walle segun la direcci�n dada
	 * @param direction en la que mira la imagen de walle
	 */
	public void actualizaWalle ( Direction direction){
		
		if (direction == Direction.NORTH){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleNorth.png")));
		}
		else if (direction == Direction.SOUTH){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleSouth.png")));
		}
		else if (direction == Direction.EAST){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleEast.png")));
		}
		else if (direction == Direction.WEST){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleWest.png")));
		} 
		else this.walle.setName("walle");
	}
	
	
	/**
	 * Actualiza el log de la ventana grafica con la informaci�n del lugar
	 * @param lugar descripcion del lugar a mostrar por el log
	 */
	public void actualizaLog ( String lugar){
		this.texto.setText(lugar);
	}
	
	/**
	 * Actualiza la posicion del nuevo lugar en la matriz de PlaceCell
	 * @param direction hacia la que se tienen 	que actualizar las coordenadas
	 */
	public void actualizarCoordenadas (Direction direction){
		this.places[filas][colum].setBackground(Color.lightGray);
		if (direction == Direction.NORTH){
			this.filas -= 1;
		}
		else if (direction == Direction.SOUTH){
			this.filas += 1;
		}
		else if (direction == Direction.EAST){
			this.colum += 1;
		}
		else if (direction == Direction.WEST){
			this.colum -= 1;
		} 
	}
	
	/**
	 * Se encarga de poner los distintos lugares en las celdas del mapa de la ciudad
	 */
	public void actualizaMapa (Place place){
		this.lugar = place;
		this.places[this.filas][this.colum].setPlace(place);
		this.places[this.filas][this.colum].setText(place.getName().toString());
		this.places[this.filas][this.colum].setBackground(Color.GREEN);
	}

}
