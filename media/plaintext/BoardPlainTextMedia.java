package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;

import stark.thedrake.game.Board;
import stark.thedrake.game.Tile;
import stark.thedrake.media.BoardMedia;
import stark.thedrake.media.PrintMedia;

public class BoardPlainTextMedia extends PrintMedia implements BoardMedia<Void> {
	private final TilePlainTextMedia tileMedia;
	private final CapturedTroopsPlainTextMedia capturedMedia;
	
	public BoardPlainTextMedia(OutputStream stream) {
		super(stream);
		this.tileMedia = new TilePlainTextMedia(stream);
		this.capturedMedia = new CapturedTroopsPlainTextMedia(stream);
	}
//--------------------------------------------------------------------------------------------------
	@Override
	public Void putBoard(Board board) {
		PrintWriter w = writer();

		w.println(board.dimension());
		for(Tile tile : board) {
			tile.putToMedia(tileMedia);
		}
		
		capturedMedia.putCapturedTroops(board.captured());
		
		return null;
	}
//--------------------------------------------------------------------------------------------------
}
