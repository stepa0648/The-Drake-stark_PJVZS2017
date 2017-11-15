package stark.thedrake.game;

import stark.thedrake.media.TroopStacksMedia;

import java.util.List;

public interface TroopStacks
{
    Troop peek(PlayingSide side);
    TroopStacks pop(PlayingSide side);
    List<TroopInfo> troops(PlayingSide side);
    <T> T putToMedia(TroopStacksMedia<T> media);
}

