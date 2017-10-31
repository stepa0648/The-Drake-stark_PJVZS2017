/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake;

/**
 *
 * @author severste
 */
public class Troop {

    private final TroopInfo info;
    private final PlayingSide side;
    private final TroopFace face;

    public Troop(TroopInfo info, PlayingSide side, TroopFace face) {
        this.info = info;
        this.side = side;
        this.face = face;
    }

    public TroopInfo info() {
        return info;
    }

    public PlayingSide side() {
        return side;
    }

    public TroopFace face() {
        return face;
    }

    public Offset2D pivot() {
        return info.pivot(face);
    }

    public Troop flipped() {
        if (face == TroopFace.BACK) {
            return new Troop(info, side, TroopFace.FRONT);
        } else {
            return new Troop(info, side, TroopFace.BACK);
        }
    }

}
