package typeIa.gui.pages

import typeIa.space.{GalacticObject, Locpc}

/**
 * Created by tim on 06/07/15.
 */
class FarStarViewState(farStarView: FarStarView, val go: GalacticObject, val camDistPc: Double, val starLoc: Locpc,
                       val camXRot: Float = 0, val camYRot: Float = 0) extends PageState(farStarView){

}
