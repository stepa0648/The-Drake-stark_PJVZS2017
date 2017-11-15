package stark.thedrake.media.plaintext;

import stark.thedrake.game.EmptyTile;
import stark.thedrake.game.TroopTile;
import stark.thedrake.media.PrintMedia;
import stark.thedrake.media.TileMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class TilePlainTextMedia extends PrintMedia implements TileMedia <Void>
{
    public TilePlainTextMedia(OutputStream stream)
    {
        super(stream);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putTroopTile(TroopTile tile)
    {
        PrintWriter w = writer() ;
        w.printf("%s %s %s\n", tile.troop().info().name(), tile.troop().side(),
                                  tile.troop().face());
        return null;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putEmptyTile(EmptyTile tile)
    {
        PrintWriter w = writer() ;
        w.printf("empty\n");
        return null;
    }
//--------------------------------------------------------------------------------------------------
}
