package stark.thedrake.game;

import stark.thedrake.media.TileMedia;

public abstract class Tile
{
    TilePosition position ;

    protected Tile( TilePosition position )
    {
        this.position = position ;
    }
//------------------------------------------------------------------------------
    public TilePosition position()
    {
        return position ;
    }
//------------------------------------------------------------------------------
    public abstract boolean acceptsTroop(Troop troop);
    public abstract boolean hasTroop();
    public abstract Troop troop();
    public abstract  <T> T putToMedia(TileMedia<T> media);
//------------------------------------------------------------------------------
}
