package stark.thedrake;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Move> boardMoves(TilePosition position) {
        // Zde doplňte vlastní implementaci
        List<Move> boardMoves = new ArrayList<>();
        Board board = board();
        Troop troop = board.tileAt(position).troop();
        List<BoardChange> boardChanges = new ArrayList<>(troop.changesFrom(position, board));
        for (BoardChange change : boardChanges) {
            boardMoves.add(new BoardMove(this, change));
        }

        return boardMoves;
    }

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
        for (Tile tile : board()) {
            if (tile.position().isNextTo(target)) {
                if (tile.hasTroop() && tile.troop().side() == sideOnTurn()) {
                    return true;
                }
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
}
