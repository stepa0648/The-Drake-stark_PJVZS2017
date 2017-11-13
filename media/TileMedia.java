package stark.thedrake.media;


import stark.thedrake.game.EmptyTile;
import stark.thedrake.game.TroopTile;

public interface TileMedia<T> {
	public T putTroopTile(TroopTile tile);
	public T putEmptyTile(EmptyTile tile);
}
