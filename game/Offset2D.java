package stark.thedrake.game;

public class Offset2D 
{
  public final int x, y;
//--------------------------------------------------------------------------------------------------
  //constructor
  public Offset2D(int x, int y)
  {
        this.x = x;
        this.y = y;
  }
//--------------------------------------------------------------------------------------------------
  //are Offsets same?
  public boolean equalsTo( int x , int y)
  {
     return  this.x == x && this.y == y  ;
  }   
//--------------------------------------------------------------------------------------------------
  //returns new Offset with opposite sign
  public Offset2D yFlipped()
  {
      return new Offset2D(this.x, -this.y );
  } 
//--------------------------------------------------------------------------------------------------
}
