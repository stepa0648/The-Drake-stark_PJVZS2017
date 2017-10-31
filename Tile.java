/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake;

/**
 *
 * @author severste
 */
public abstract class Tile {

    private final TilePosition position;

    // Konstruktor, který očekává pozici dlaždice na hracím plánu
    protected Tile(TilePosition position) {
        this.position = position;
    }

// Pozice dlaždice na hracím plánu
    public TilePosition position() {
        return position;
    }

    /* Je možné na dlaždici postavit zadanou jednotku?
 * Parametr troop teď vlastně nepotřebujeme, ale může se hodit
 * nějakým dalším potomkům třídy Tile
     */
    public abstract boolean acceptsTroop(Troop troop);

// Stojí na dlaždici nějaká jednotka?
    public abstract boolean hasTroop();

// Jednotka, která na dlaždici zrovna stojí. Pokud tam žádná není, vyhazuje UnsupportedOperationException
    public abstract Troop troop();

}
