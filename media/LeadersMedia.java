package stark.thedrake.media;

import stark.thedrake.game.BothLeadersPlaced;
import stark.thedrake.game.NoLeadersPlaced;
import stark.thedrake.game.OneLeaderPlaced;

public interface LeadersMedia<T> {

    public T putNoLeadersPlaced(NoLeadersPlaced leaders);

    public T putOneLeaderPlaced(OneLeaderPlaced leaders);

    public T putBothLeadersPlaced(BothLeadersPlaced leaders);
}
