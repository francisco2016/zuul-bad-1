/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        Room vestibulo,cocina,salon,biblioteca,trastero,h1,terraza;

        vestibulo = new Room("  en le vestíbulo de la casa, dónde vamos, elige.");
        cocina = new Room("  en la cocina, busquemos hielo!!");
        salon = new Room(" en el salón, tiene salidas en todas direcciones.");
        biblioteca = new Room("  en la biblio, busquemos el plano.");
        trastero = new Room("  en el trastero, cojamos el taladro.");
        h1 = new Room(" en la  habitación, tiene buena cama.");
        terraza = new Room("en la terraza, en ella trazamos la huída de emergencia");

        vestibulo.setExit("north", salon);//------------------------------------------------------------------ 0113.
        vestibulo.setExit("east", cocina);

        cocina.setExit("north", trastero);
        cocina.setExit("west", vestibulo);

        salon.setExit("north", h1);
        salon.setExit("east", trastero);
        salon.setExit("southeast", cocina);
        salon.setExit("south", vestibulo );
        salon.setExit("west", biblioteca);

        biblioteca.setExit("east", salon);

        trastero.setExit("north", terraza);
        trastero.setExit("south", cocina);
        trastero.setExit("west", salon);

        h1.setExit("east", trastero);
        h1.setExit("south", salon);

        terraza.setExit("south", trastero);

        currentRoom = vestibulo;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        printLocationInfo();//------------------------------------------------------------------------------------ 0108

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {//-------------------------------------------------------------------- 0114.
            printLocationInfo();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        //System.out.println("around at the university.");
        printLocationInfo();//----------------------------------------------------------- 0116
        System.out.println("Your command words are:");

       // System.out.println("   go quit help");
        parser.comando(); //-------------------------- 0116
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();// ------------------------------------------------------------------------ 0108
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Método privado para resolver la repetición de código en los métodos printWelcome() y goRom().-------------------- 0108
     * Modificado para obtener información del nuevo mt. getLongDescription() en la clase Room. --------------- 0113.
     */
    private void printLocationInfo(){
        
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }
}

