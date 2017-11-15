package stark.thedrake.game;

import java.util.List;

public interface TheDrakeSetup
{
	 List<TroopInfo> troops();

	//returns info about troop by given key -- name
	 TroopInfo infoByName(String name);
}
