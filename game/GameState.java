package stark.thedrake.game;

import stark.thedrake.media.GameStateMedia;

import java.util.List;

public interface GameState
{
    Board board();
    TroopStacks troopStacks();
    PlayingSide sideOnTurn();
    Leaders leaders();
    boolean isVictory();
    List<Move> allMoves();
    List<Move> boardMoves(TilePosition position);
    List<Move> stackMoves();
    <T> T putToMedia(GameStateMedia<T> media);
}
