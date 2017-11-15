package stark.thedrake.media;

import stark.thedrake.game.EmptyTile;
import stark.thedrake.game.TroopTile;

public interface TileMedia<T>
{
	T putTroopTile(TroopTile tile);
	T putEmptyTile(EmptyTile tile);
}
