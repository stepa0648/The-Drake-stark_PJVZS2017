package stark.thedrake.game;

import stark.thedrake.media.GameStateMedia;

import java.util.ArrayList;
import java.util.List;

public class MiddleGameState extends BaseGameState
{

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
//--------------------------------------------------------------------------------------------------
    @Override
    public BothLeadersPlaced leaders()
    {
        return (BothLeadersPlaced)super.leaders();
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public List<Move> allMoves()
    {
        List allMoves = new ArrayList<Move>() ;
        allMoves.addAll( stackMoves() );

        for (Tile tile : board())
        {
            allMoves.addAll(boardMoves(tile.position()));
        }

        return allMoves;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public List<Move> boardMoves(TilePosition position)
    {
        List<Move> boardMoves = new ArrayList<>();
        Board board = board();
    //if there is no tile at position return empty arrList
        if (!board.tileAt(position).hasTroop())
        {
            return boardMoves;
        }
    //if the troop at position is't troop of side that is on move, return empty arrList
        Troop troop = board.tileAt(position).troop();

        if (troop.side() != sideOnTurn())
        {
            return boardMoves;
        }
    //generate all changes that troop at position can make
        List<BoardChange> boardChanges = new ArrayList<>(troop.changesFrom(position, board));
        for (BoardChange change : boardChanges)
        {
            boardMoves.add(new BoardMove(this, change));
        }

        return boardMoves;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public List<Move> stackMoves()
    {
        List<Move> result = new ArrayList<>();
        for (Tile tile : board())
        {
            if (canPlaceFromStack(tile.position()))
                result.add(new PlaceFromStack(this, tile.position()));

        }

        return result;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean isVictory()
    {
        return false;
    }
//--------------------------------------------------------------------------------------------------
    public boolean canPlaceFromStack(TilePosition target)
    {
      //troop to be placed
        Troop troop = troopStacks().peek(sideOnTurn());

      //can i place troop at position target?
        if (!board().canPlaceTo(troop, target))
        {
            return false;
        }

      //neigbours, i can only place troop at target if it has neighbours on positions that colludes
      //with target
        List<TilePosition> positions = new ArrayList<>();
        positions.add(new TilePosition(target.i - 1, target.j));
        positions.add(new TilePosition(target.i + 1, target.j));
        positions.add(new TilePosition(target.i, target.j - 1));
        positions.add(new TilePosition(target.i, target.j + 1));

        for (TilePosition pos : positions)
        {
            if ( board().contains(pos) )
            {
                Tile tile = board().tileAt(pos);
                if (tile.hasTroop() && tile.troop().side() == sideOnTurn())
                    return true ;
            }
        }

        return false;
    }
//--------------------------------------------------------------------------------------------------
    public MiddleGameState placeFromStack(TilePosition target)
    {
        Troop troop = troopStacks().peek(sideOnTurn());
        return new MiddleGameState(
                board().withTiles(
                        new TroopTile(target, troop)),
                troopStacks().pop(sideOnTurn()),
                leaders(),
                sideOnTurn().opposite());
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public <T> T putToMedia(GameStateMedia<T> media)
    {
        return media.putMiddleGameState(this);
    }
//--------------------------------------------------------------------------------------------------
}

