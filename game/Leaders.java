package stark.thedrake.game;

import stark.thedrake.media.LeadersMedia;

public interface Leaders
{
    boolean isPlaced(PlayingSide side);
    boolean leaderOn(PlayingSide side, TilePosition position);
    TilePosition position(PlayingSide side);
    <T> T putToMedia(LeadersMedia<T> media);
}
