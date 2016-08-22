import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salidas;
    private String descriItem;//---------------------------------------------------------- 0117
    private int pesoItem;//---------------------------------------------------------- 0117

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String descriItem, int pesoItem) 
    {
        this.description = description;
        this.descriItem = descriItem;//----------------------------------------0117
        this.pesoItem = pesoItem;//--------------------------------------------0117
        salidas = new HashMap<>();
    }

    /**
     * nuevo mt, para permitir tener un nº ilimitado de salidas, ya que setExits() limita las salidas al nº de parámetros que 
     * se le pasen, por lo que eliminamos.
     * Al invocar este nuevo método queda automáticamente almacenado en el HashMat la nueva dirección y la nueva habitación.
     * ------------------------------------------------------------ 0113
     */
    public void setExit(String direction, Room nextRoom){
        salidas.put(direction, nextRoom);
    }
    
   	/**
   	 *Modifica el método printInfoLocation de la clase Game para que haga uso del método añadido en el paso anterior 
   	 *en vez de la implementación actual.
	 * Return a long description of this room, of the form:
	 *     You are in the 'name of room'--------------------------------------------------------------------------- 0114
	 *     Exits: north west southwest
	 * @return A description of the room, including exits.
    */
	public String getLongDescription(){
	    
       return "You are " +description+ ".\n" +getExitString()+ ".\n" +descriItem+ ".\n" +pesoItem;
	}

    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * toma como parámetro una cadena que representa una dirección y devuelve el objeto de la clase Room
     * asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String adress){

        //UTILIZANDO EL HashMap PASAMOS EL PARÁMETRO AL MÉTODO GET() Y NOS DEVUELVE EL OBJETO CORRESPONDIENTE A ESA CLAVE.
        return salidas.get(adress);
    }

    /**
     * Al pasar los atriutos a private, necesitamos un método que retorne el valor de cada uno de ellos para poder
     * invocarles desde otras clasel. Return a description of the room's exits.
     * For example: "Exits: north east west" -------------------------------------------------------------------------- 0111
     * @ return A description of the available exits.
     */
    public String getExitString(){
        //mediante la utilización del HashMap, podemos reducir código mediante el mt keySet() de dicha clase. Este mt retorna
        //todas las claves del mapa.
        //Estas claves las guardo en una VL de tipo set.
        Set<String> descripcionSalida = salidas.keySet();
        String salidaRoom = "Exit: "; //VL para retornar el valor de las claves.
        //recorro el conjunto con un for y guardo los valores en la VL salidaRoom
        for(String direccion: descripcionSalida){
            salidaRoom += direccion + " "; 
        }
        
        return salidaRoom; //finalmente devuelvo la VL:
    }

}

