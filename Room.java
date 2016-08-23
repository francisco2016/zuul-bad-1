import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
    //para poder añadir varios o ningún items en las habitaciones creo un ArrayList de Item y elimino los siguientes atributos.
    //private String descriItem;//---------------------------------------------------------- 0117
    //private int pesoItem;//---------------------------------------------------------- 0117
    private ArrayList<Item> item;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        //this.descriItem = descriItem;//----------------------------------------0117
        //this.pesoItem = pesoItem;//--------------------------------------------0117
        item = new ArrayList<>();   //--------------------------------------------------------------------- 0119
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
     * @return A description of the room, including exits and items.
     */
    public String getLongDescription(){
        //modifico el código para adaptarlo al ArrayList<Item> -------------------------------------------------------- 0119.
        // return "You are " +description+ ".\n" +getExitString()+ ".\n" +descriItem+ ".\n" +pesoItem;
        String descrip = "You are " +description+ "\n" +getExitString();
        if(item.size() != 0){
            for(Item item2: item){
                descrip += item2.descriItem();
            }
        }
        else{
            descrip = "You are " +description+ "\n pero ningún objeto útil.\n" +getExitString();
        }
            
        return descrip;

    }
    /**
     * método para añadir un item a la habitación.-------------------------------------------------------------------- 0199
     */
    public void addItem(Item item3){
        item.add(item3);
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

        Set<String> descripcionSalida = salidas.keySet();
        String salidaRoom = "Exit: "; //VL para retornar el valor de las claves.
        //recorro el conjunto con un for y guardo los valores en la VL salidaRoom
        for(String direccion: descripcionSalida){
            salidaRoom += direccion + " "; 
        }

        return salidaRoom; //finalmente devuelvo la VL:
    }

}

