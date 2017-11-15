package stark.thedrake.media;

import stark.thedrake.game.BothLeadersPlaced;
import stark.thedrake.game.NoLeadersPlaced;
import stark.thedrake.game.OneLeaderPlaced;

public interface LeadersMedia<T>
{
	T putNoLeadersPlaced(NoLeadersPlaced leaders);
	T putOneLeaderPlaced(OneLeaderPlaced leaders);
	T putBothLeadersPlaced(BothLeadersPlaced leaders);
}
