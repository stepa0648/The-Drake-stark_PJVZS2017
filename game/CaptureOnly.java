/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.game;

/**
 *
 * @author severste
 */
public class CaptureOnly extends BoardChange {

    public CaptureOnly(Board initialBoard, TilePosition origin, TilePosition target) {
        super(initialBoard, origin, target);
    }

    @Override
    public Board resultBoard() {
        return initialBoard().captureOnly(origin(), target());
    }

}
