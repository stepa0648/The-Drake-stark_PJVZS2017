package stark.thedrake.game;

import stark.thedrake.media.LeadersMedia;

public class NoLeadersPlaced implements Leaders {

    @Override
    public boolean isPlaced(PlayingSide side) {
        return false;
    }

    @Override
    public TilePosition position(PlayingSide side) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean leaderOn(PlayingSide side, TilePosition position) {
        return false;
    }

    @Override
    public <T> T putToMedia(LeadersMedia<T> media) {
        return media.putNoLeadersPlaced(this);
    }
}
