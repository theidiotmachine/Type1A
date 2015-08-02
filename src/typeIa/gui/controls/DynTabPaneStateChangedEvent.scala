package typeIa.gui.controls

import de.lessvoid.nifty.NiftyEvent

/**
 * Created by tim on 27/05/15.
 */
class DynTabPaneStateChangedEvent(val selectedTab: Option[DynTab], val prevSelectedTab: Option[DynTab]) extends NiftyEvent[Unit]{
  def getSelectedId: Option[String] = selectedTab match {
    case None => None
    case Some(t) => Some(t.getId)
  }

  def getPrevSelectedId: Option[String] = prevSelectedTab match {
    case None => None
    case Some(t) => Some(t.getId)
  }
}
