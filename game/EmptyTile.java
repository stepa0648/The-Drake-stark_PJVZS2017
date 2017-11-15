package stark.thedrake.game;

import stark.thedrake.media.TileMedia;

public class EmptyTile extends Tile
{
    public EmptyTile( TilePosition position  ) 
    {
        super( position ) ;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean hasTroop()
    {
        return false;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean acceptsTroop(Troop troop)
    {
        return true;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Troop troop()
    {
        throw new UnsupportedOperationException()   ;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public <T> T putToMedia(TileMedia<T> media)
    {
        return media.putEmptyTile(this);
    }
//--------------------------------------------------------------------------------------------------
}
