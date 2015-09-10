package typeIa.gui.pages

import typeIa.space.{Loc, LocatedObject}

/**
 * Created by tim on 06/07/15.
 */
class FarStarViewState(farStarView: FarStarView, val go: LocatedObject, val camDistPc: Double, val starLoc: Loc,
                       val camXRot: Float = 0, val camYRot: Float = 0) extends PageState(farStarView){

}
