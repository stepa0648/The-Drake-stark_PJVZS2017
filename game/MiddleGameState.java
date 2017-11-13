package stark.thedrake.game;

import java.util.ArrayList;
import java.util.List;
import stark.thedrake.media.GameStateMedia;

public class MiddleGameState extends BaseGameState {

    public MiddleGameState(
            Board board,
            TroopStacks troopStacks,
            BothLeadersPlaced leaders,
            PlayingSide sideOnTurn) {
        super(
                board,
                troopStacks,
                leaders,
                sideOnTurn);
    }

    @Override
    public BothLeadersPlaced leaders() {
        return (BothLeadersPlaced) super.leaders();
    }

    /* Všechny tahy, které může hráč, jenž je zrovna a tahu, provést
 * v tomto stavu hry.
     */
    @Override
    public List<Move> allMoves() {
        // Zde doplňte vlastní implementaci
        List<Move> allMoves = new ArrayList<>();

        allMoves.addAll(stackMoves());

        for (Tile tile : board()) {
            allMoves.addAll(boardMoves(tile.position()));
        }
        return allMoves;
    }

    /* Všechny tahy, které může hráč, jenž je zrovna a tahu, provést
 * z políčka na pozici position.
     */
    @Override
    public List<Move> boardMoves(TilePosition position) {
        // Zde doplňte vlastní implementaci
        List<Move> boardMoves = new ArrayList<>();

        Board board = board();

        if (!board.tileAt(position).hasTroop()) {
            return boardMoves;
        }

        Troop troop = board.tileAt(position).troop();

        if (troop.side() != sideOnTurn()) {
            return boardMoves;
        }

        List<BoardChange> boardChanges = new ArrayList<>(troop.changesFrom(position, board));

        for (BoardChange change : boardChanges) {
            boardMoves.add(new BoardMove(this, change));
        }
        return boardMoves;
    }

    /* Všechny tahy, které může hráč, jenž je zrovna a tahu, provést
 * ze zásobníku.
     */
    @Override
    public List<Move> stackMoves() {
        List<Move> result = new ArrayList<>();
        Troop troop = troopStacks().peek(sideOnTurn());
        for (Tile tile : board()) {
            if (canPlaceFromStack(tile.position())) {
                result.add(new PlaceFromStack(this, tile.position()));
            }
        }

        return result;
    }

    @Override
    public boolean isVictory() {
        return false;
    }

    public boolean canPlaceFromStack(TilePosition target) {
        // Zde doplňte vlastní implementaci
        Troop troop = troopStacks().peek(sideOnTurn());

        if (!board().canPlaceTo(troop, target)) {
            return false;
        }

        //sousedi
        List<TilePosition> positions = new ArrayList<>();
        positions.add(new TilePosition(target.i - 1, target.j));
        positions.add(new TilePosition(target.i + 1, target.j));
        positions.add(new TilePosition(target.i, target.j - 1));
        positions.add(new TilePosition(target.i, target.j + 1));

        for (TilePosition pos : positions) {
            if (!board().contains(pos)) {
                continue;
            }
            Tile tile = board().tileAt(pos);
            if (tile.hasTroop() && tile.troop().side() == sideOnTurn()) {
                return true;
            }

        }

        return false;
    }

    public MiddleGameState placeFromStack(TilePosition target) {
        Troop troop = troopStacks().peek(sideOnTurn());
        return new MiddleGameState(
                board().withTiles(
                        new TroopTile(target, troop)),
                troopStacks().pop(sideOnTurn()),
                leaders(),
                sideOnTurn().opposite());
    }

    @Override
    public <T> T putToMedia(GameStateMedia<T> media) {
        return media.putMiddleGameState(this);
    }
}
