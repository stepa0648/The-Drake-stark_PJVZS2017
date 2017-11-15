package stark.thedrake.game;

public class StepOnly extends BoardChange
{
    public StepOnly(Board initialBoard, TilePosition origin, TilePosition target)
    {
        super(initialBoard, origin, target);
    }
//--------------------------------------------------------------------------------------------------
  //returns a new board with stepOnly change -- only moving
    @Override
    public Board resultBoard()
    {
        return initialBoard().stepOnly( origin(), target());
    }
//--------------------------------------------------------------------------------------------------
}
