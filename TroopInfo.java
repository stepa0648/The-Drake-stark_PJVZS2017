package stark.thedrake;

import java.util.List;

/**
 *
 * @author severste
 */
public class TroopInfo {

    private final String name;
    private final Offset2D backPivot;
    private final Offset2D frontPivot;
    private final List<TroopAction> frontActions;
    private final List<TroopAction> backActions;

//------------------------------------------------------------------------------    
    public TroopInfo(String name, Offset2D backPivot, Offset2D frontPivot, List<TroopAction> frontActions, List<TroopAction> backActions) {
        this.name = name;
        this.backPivot = backPivot;
        this.frontPivot = frontPivot;
        this.frontActions = frontActions;
        this.backActions = backActions;
    }

    public TroopInfo(String name, List<TroopAction> frontActions, List<TroopAction> backActions) {
        this(name, new Offset2D(1, 1), new Offset2D(1, 1), frontActions, backActions);
    }

    public TroopInfo(String name, Offset2D pivot, List<TroopAction> actions) {
        this(name, pivot, pivot, actions, actions);
    }

//------------------------------------------------------------------------------
    public String name() {
        return name;
    }

    public Offset2D pivot(TroopFace face) {
        if (face == TroopFace.FRONT) {
            return frontPivot;
        } else {
            return backPivot;
        }
    }

    public List<TroopAction> actions(TroopFace face) {
        if (face == TroopFace.FRONT) {
            return frontActions;
        }
        return backActions;
    }

}
