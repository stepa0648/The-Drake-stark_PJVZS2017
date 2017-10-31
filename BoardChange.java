package stark.thedrake;

/**
 *
 * @author severste
 */
public abstract class BoardChange {

    protected Board initialBoard;
    protected TilePosition origin, target;

    /*
 * Konstruktor, který bere hrací desku, ze které vycházíme a poté
 * dvě souřadnice, jedna, ze které tah vychází a druhá, na kterou
 * tah směřuje.
     */
    protected BoardChange(Board initialBoard, TilePosition origin, TilePosition target) {
        this.initialBoard = initialBoard;
        this.origin = origin;
        this.target = target;

    }

// Gettry
    public Board initialBoard() {
        return initialBoard;
    }

    public TilePosition origin() {
        return origin;
    }

    public TilePosition target() {
        return target;
    }

    /*
 * Metoda, která vrací novou hrací desku vyrobenou podle toho,
 * jaký tah zrovna provádíme.
     */
    public abstract Board resultBoard();

}
