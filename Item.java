
/**
 * Esta clase crea objetos con el fin de poder ser añadidos a las habitaciones que se describen en el juego.------------ 0119.
 * @autor Francisco.
 */
public class Item
{
    // Los items han de  tener un nombre y un peso.
    private String nameItem;
    private float pesoItem;

    /**
     * Constructor for inicializar los atributos
     */
    public Item(String nameItem, float pesoItem)
    {
        this.nameItem = nameItem;
        this.pesoItem = pesoItem;
    }

    /**
     * Retorna la descripción de un item.
     */
    public String descriItem()
    {
        return nameItem+ " de (" +pesoItem+ ") Kg.";
    }
}
