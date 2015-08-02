package typeIa.gui.controls

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder.{ImageBuilder, PanelBuilder, ControlDefinitionBuilder}
import de.lessvoid.nifty.controls.NiftyControl

/**
 * Created by tim on 29/06/15.
 */
trait StarPanel extends NiftyControl{

  /**
   * Activate/Click this button.
   */
  def activate(): Unit

}

object StarPanel {
  def apply(nifty: Nifty): Unit = {
    new ControlDefinitionBuilder("starpanel") {
      controller("typeIa.gui.controls.starpanel.StarPanelControl")
      inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping")
      panel(new PanelBuilder() {
        //do I need a layout?
        childLayoutCenter()
        style("#panel")
        focusable(true)
      })
    }.registerControlDefintion(nifty)
  }
}
