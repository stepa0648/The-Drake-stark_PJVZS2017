package stark.thedrake.game;

public abstract class BoardChange
{
    private Board initialBoard ;
    private TilePosition origin ;
    private TilePosition target ;
//--------------------------------------------------------------------------------------------------
  //constructor that takes board and positions from and where the change shpuld happened
    protected BoardChange(Board initialBoard, TilePosition origin, TilePosition target)
    {
        this.initialBoard = initialBoard ;
        this.origin = origin ;
        this.target = target ;
    }
//--------------------------------------------------------------------------------------------------
    public Board initialBoard()
    {
        return initialBoard;
    }
//--------------------------------------------------------------------------------------------------
    public TilePosition origin()
    {
        return origin;
    }
//--------------------------------------------------------------------------------------------------
    public TilePosition target()
    {
        return target;
    }
//--------------------------------------------------------------------------------------------------
  //returns new Board, depends on which move is made
    public abstract Board resultBoard();
//--------------------------------------------------------------------------------------------------
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((initialBoard == null) ? 0 : initialBoard.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoardChange other = (BoardChange) obj;
        if (!(initialBoard == other.initialBoard)) {
            return false;
        }
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }
//--------------------------------------------------------------------------------------------------
}
