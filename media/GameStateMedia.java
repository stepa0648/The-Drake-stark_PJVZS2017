
package stark.thedrake.media;

import stark.thedrake.game.MiddleGameState;
import stark.thedrake.game.PlacingGuardsGameState;
import stark.thedrake.game.PlacingLeadersGameState;
import stark.thedrake.game.VictoryGameState;

public interface GameStateMedia<T>
{
	T putPlacingLeadersGameState(PlacingLeadersGameState state);
	T putPlacingGuardsGameState(PlacingGuardsGameState state);
	T putMiddleGameState(MiddleGameState state);
	T putFinishedGameState(VictoryGameState state);
}

