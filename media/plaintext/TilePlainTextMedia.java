/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;
import stark.thedrake.game.EmptyTile;
import stark.thedrake.game.TroopTile;
import stark.thedrake.media.PrintMedia;
import stark.thedrake.media.TileMedia;

/**
 *
 * @author severste
 */
public class TilePlainTextMedia extends PrintMedia implements TileMedia<Void> {

    public TilePlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putTroopTile(TroopTile tile) {
        PrintWriter w = writer();
        w.println(tile.troop().info().name() + " " + tile.troop().side() + " " + tile.troop().face());
        return null;
    }

    @Override
    public Void putEmptyTile(EmptyTile tile) {
        PrintWriter w = writer();
        w.println("empty");
        return null;
    }

}
