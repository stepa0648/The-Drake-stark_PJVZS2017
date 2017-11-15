package stark.thedrake.game;

import stark.thedrake.media.LeadersMedia;

public class OneLeaderPlaced implements Leaders
{

    private final PlayingSide side;
    private final TilePosition position;

    public OneLeaderPlaced(PlayingSide side, TilePosition position)
    {
        this.position = position;
        this.side = side;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean isPlaced(PlayingSide side)
    {
        return this.side == side;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public TilePosition position(PlayingSide side)
    {
        if(this.side == side)
            return position;

        throw new UnsupportedOperationException();
    }
//--------------------------------------------------------------------------------------------------
  //if given side has Leader check if it's postion is given position
    @Override
    public boolean leaderOn(PlayingSide side, TilePosition position)
    {
        if(!isPlaced(side))
            return false;

        return position(side).equals(position);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public <T> T putToMedia(LeadersMedia<T> media)
    {
        return media.putOneLeaderPlaced(this);
    }
//--------------------------------------------------------------------------------------------------
}
