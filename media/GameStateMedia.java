
package stark.thedrake.media;


import stark.thedrake.game.MiddleGameState;
import stark.thedrake.game.PlacingGuardsGameState;
import stark.thedrake.game.PlacingLeadersGameState;
import stark.thedrake.game.VictoryGameState;

public interface GameStateMedia<T> {
	public T putPlacingLeadersGameState(PlacingLeadersGameState state);
	public T putPlacingGuardsGameState(PlacingGuardsGameState state);
	public T putMiddleGameState(MiddleGameState state);
	public T putFinishedGameState(VictoryGameState state);
}

