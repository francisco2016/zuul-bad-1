import java.util.HashMap;
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
    private HashMap<String, Room> salidas;// implementamos un HashMap para sustituir a los atributos comentados.-----------0112
    //     private Room northExit;
    //     private Room southExit;
    //     private Room eastExit;
    //     private Room westExit;
    //     private Room southeastExit;//----------------------------------- 0110.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param southeast The southeast exit.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room southeast, Room south, Room west) 
    { 
        //          if(north != null)
        //             northExit = north;
        //         if(east != null)
        //             eastExit = east;
        //         if(southeast != null)//--------------------------------------------------- add para 0110.
        //             southeastExit = southeast;        
        //         if(south != null)
        //             southExit = south;
        //         if(west != null)
        //             westExit = west;
        if(north != null)//cambio de código obligado al utilizar el HashMap.
            salidas.put("north", north);
        if(east != null)
            salidas.put("east", east);
        if(southeast != null)//--------------------------------------------------- add para 0110.
            salidas.put("southeast", southeast);        
        if(south != null)
            salidas.put("south", south);
        if(west != null)
            salidas.put("west", west);
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
     * asociado a esa salida o null si no hay salida.--------------------------------------------------------------  0111
     */
    public Room getExit(String adress){
        Room salida = null;
        //         if(adress.equals("north"))
        //             salida = northExit;
        //         if(adress.equals("east"))
        //         salida = eastExit;
        //         if(adress.equals("southeast"))
        //         salida = southeastExit;
        //         if(adress.equals("south"))
        //         salida = southExit;
        //         if(adress.equals("west"))
        //         salida = westExit
        //código modificado devido a la utilización del HashMap.
        if(adress.equals("north"))
            salida = salidas.get("north");
        if(adress.equals("east"))
            salida = salidas.get("east");
        if(adress.equals("southeast"))
            salida = salidas.get("southeast");
        if(adress.equals("south"))
            salida = salidas.get("south");
        if(adress.equals("west"))
            salida = salidas.get("west");
        return salida;
    }

    /**
     * Al pasar los atriutos a private, necesitamos un método que retorne el valor de cada uno de ellos para poder
     * invocarles desde otras clasel. Return a description of the room's exits.
     * For example: "Exits: north east west" -------------------------------------------------------------------------- 0111
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salidaRoom = "Exit: ";
        if(salidas.get("north") != null) {
            salidaRoom += "north ";
        }
        if(salidas.get("east") != null) {
            salidaRoom += "east ";
        }
        if(salidas.get("southeast") != null) {
            salidaRoom += "southeast ";
        }
        if(salidas.get("south") != null) {
            salidaRoom += "south ";
        }
        if(salidas.get("west") != null) {
            salidaRoom += "west ";
        }

        return salidaRoom;
    }

}

