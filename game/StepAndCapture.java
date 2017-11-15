package stark.thedrake.game;

public class StepAndCapture extends BoardChange
{
    public StepAndCapture(Board initialBoard, TilePosition origin, TilePosition target)
    {
        super(initialBoard, origin, target);
    }
//--------------------------------------------------------------------------------------------------
  //return new board with a stepAndCapture change -- capturing and moving
    @Override
    public Board resultBoard()
    {
      return initialBoard().stepAndCapture( origin(), target());
    }
//--------------------------------------------------------------------------------------------------
}
