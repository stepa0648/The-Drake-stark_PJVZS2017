package stark.thedrake.game;

public class CaptureOnly extends BoardChange
{
    public CaptureOnly(Board initialBoard, TilePosition origin, TilePosition target)
    {
        super(initialBoard, origin, target);
    }
//--------------------------------------------------------------------------------------------------
  //return new board with a captureOnly change -- capturing, not moving
    @Override
    public Board resultBoard()
    {
        return initialBoard().captureOnly(origin(), target());
    }
//--------------------------------------------------------------------------------------------------
}
