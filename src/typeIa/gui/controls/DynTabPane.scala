package typeIa.gui.controls

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder.ControlDefinitionBuilder
import de.lessvoid.nifty.controls.NiftyControl

/**
 * Control for a set of dyn tabs
 */
trait DynTabPane extends NiftyControl{

}

object DynTabPane {
  def apply(nifty: Nifty): Unit = {
    new ControlDefinitionBuilder("dyntabpane") {
      controller("typeIa.gui.controls.dyntab.DynTabPaneControl")
      width("0px")
      height("0px")
      visible(false)
    }.registerControlDefintion(nifty)
  }
}
