package stark.thedrake.game;

import java.util.Iterator;
import stark.thedrake.media.BoardMedia;

/**
 *
 * @author severste
 */
public class Board implements Iterable<Tile> {

    private final int dimension;
    private final Tile[][] tiles;
    private final CapturedTroops capturedTroops;

    // Primární konstruktor
    public Board(int dimension, CapturedTroops captured, Tile... tiles) {
        this.dimension = dimension;
        this.capturedTroops = captured;
        this.tiles = new Tile[dimension][dimension];

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                this.tiles[col][row] = new EmptyTile(new TilePosition(col, row));
            }
        }

        for (Tile tile : tiles) {
            this.tiles[tile.position().i][tile.position().j] = tile;
        }

    }

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru se specefikovanými dlaždicemi.
    // Všechny ostatní dlažice se berou jako prázdné.
    public Board(int dimension, Tile... tiles) {
        this(dimension, new CapturedTroops(), tiles);
    }

    public Board(Board old, CapturedTroops newCapturedTroops) {
        this.dimension = old.dimension;
        this.tiles = old.tiles;
        this.capturedTroops = newCapturedTroops;
    }

    // Rozměr hrací desky
    public int dimension() {
        return dimension;
    }

// Vrací dlaždici na zvolené pozici. Pokud je pozice mimo desku, vyhazuje IllegalArgumentException
    public Tile tileAt(TilePosition position) {
        if (!this.contains(position)) {
            throw new IllegalArgumentException("Dlazdice mimo desku");
        }

        return tiles[position.i][position.j];
    }

// Ověřuje, že pozice se nachází na hrací desce
    public boolean contains(TilePosition... positions) {
        for (TilePosition pos : positions) {
            if (pos.i >= dimension || pos.i < 0 || pos.j >= dimension || pos.j < 0) {
                return false;
            }
        }
        return true;

    }

// Vytváří novou hrací desku s novými dlaždicemi z pole tiles. Všechny ostatní dlaždice zůstávají stejné
    public Board withTiles(Tile... tiles) {
        Tile[] newTiles = new Tile[dimension * dimension + tiles.length];
        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                newTiles[counter] = this.tiles[i][j];
                counter++;
            }
        }

        for (Tile tile : tiles) {
            newTiles[counter] = tile;
            counter++;
        }

        return new Board(new Board(dimension, newTiles), this.capturedTroops);
    }

    public Board withCaptureAndTiles(TroopInfo info, PlayingSide side, Tile... tiles) {
        Board newBoard = this.withTiles(tiles);
        return new Board(newBoard, capturedTroops.withTroop(side, info));
    }

    // Vrací zajaté jednotky
    public CapturedTroops captured() {
        return capturedTroops;
    }

    // Stojí na pozici origin jednotka?
    public boolean canTakeFrom(TilePosition origin) {
        if (!this.contains(origin)) {
            return false;
        }
        return (tileAt(origin).hasTroop());
    }

    /*
 * Lze na danou pozici postavit zadanou jednotku? Zde se řeší pouze
 * jednotky na hrací ploše, nikoliv zásobník, takže se v podstatě
 * pouze ptám, zda dlaždice na pozici target přijme danou jednotku.
     */
    public boolean canPlaceTo(Troop troop, TilePosition target) {
        if (!this.contains(target)) {
            return false;
        }
        return tileAt(target).acceptsTroop(troop);
    }

    // Může zadaná jednotka zajmout na pozici target soupeřovu jednotku?
    public boolean canCaptureOn(Troop troop, TilePosition target) {
        if (!canTakeFrom(target)) {
            return false;
        }
        if (troop.side() == tileAt(target).troop().side()) {
            return false;
        }
        return true;
    }

    /*
 * Stojí na políčku origin jednotka, která může udělat krok na pozici target
 * bez toho, aby tam zajala soupeřovu jednotku?
     */
    public boolean canStepOnly(TilePosition origin, TilePosition target) {
        return contains(origin, target) && tileAt(origin).hasTroop() && !tileAt(target).hasTroop();
    }

    /*
 * Stojí na políčku origin jednotka, která může zůstat na pozici origin
 * a zajmout soupeřovu jednotku na pozici target?
     */
    public boolean canCaptureOnly(TilePosition origin, TilePosition target) {
        if (!this.contains(origin, target)) {
            return false;
        }

        if (!tileAt(origin).hasTroop()) {
            return false;
        }

        if (!canCaptureOn(tileAt(origin).troop(), target) || !canTakeFrom(origin)) {
            return false;
        }
        return true;
    }

    /*
 * Stojí na pozici origin jednotka, která může udělat krok na pozici target
 * a zajmout tam soupeřovu jednotku?
     */
    public boolean canStepAndCapture(TilePosition origin, TilePosition target) {
        if (!this.contains(origin, target)) {
            return false;
        }
        if (!tileAt(origin).hasTroop()) {
            return false;
        }

        if (!canCaptureOn(tileAt(origin).troop(), target) || !canTakeFrom(origin)) {
            return false;
        }
        return true;
    }

    /*
 * Nová hrací deska, ve které jednotka na pozici origin se přesunula
 * na pozici target bez toho, aby zajala soupeřovu jednotku.
     */
    public Board stepOnly(TilePosition origin, TilePosition target) {
        Troop attacker = tileAt(origin).troop();
        return withTiles(new EmptyTile(origin), new TroopTile(target, attacker.flipped()));
    }

    /*
 * Nová hrací deska, ve které jednotka na pozici origin se přesunula
 * na pozici target, kde zajala soupeřovu jednotku.
     */
    public Board stepAndCapture(TilePosition origin, TilePosition target) {
        Troop attacker = tileAt(origin).troop();
        Troop targetTroop = tileAt(target).troop();

        return withCaptureAndTiles(
                targetTroop.info(),
                targetTroop.side(),
                new EmptyTile(origin),
                new TroopTile(target, attacker.flipped()));
    }

    /*
 * Nová hrací deska, ve které jednotka zůstává stát na pozici origin
 * a zajme soupeřovu jednotku na pozici target.
    TroopInfo info, PlayingSide side, Tile... tiles
     */
    public Board captureOnly(TilePosition origin, TilePosition target) {
        Troop targetTroop = tileAt(target).troop();
        TroopInfo info = targetTroop.info();
        PlayingSide side = targetTroop.side();
        return withCaptureAndTiles(info, side, new TroopTile(origin, tileAt(origin).troop().flipped()), new EmptyTile(target));
    }

    @Override
    public Iterator<Tile> iterator() {
        return new Iterator<Tile>() {
            //position Width
            private int posW = 0;
            //position Height
            private int posH = 0;

            @Override
            public boolean hasNext() {
                return (posW < dimension && posH < dimension);
            }

            @Override
            public Tile next() {
                Tile nextTile = tileAt(new TilePosition(posW, posH));

                if (posW == dimension - 1) {
                    posW = 0;
                    posH++;
                } else {
                    posW++;
                }

                return nextTile;
            }
        };
    }

    public <T> T putToMedia(BoardMedia<T> media) {
        return media.putBoard(this);
    }

}
