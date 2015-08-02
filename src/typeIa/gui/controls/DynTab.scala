package typeIa.gui.controls

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder.{TextBuilder, ImageBuilder, PanelBuilder, ControlDefinitionBuilder}
import de.lessvoid.nifty.controls.NiftyControl
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder
import de.lessvoid.nifty.render.NiftyImage
import typeIa.gui.GuiUtils

/**
 * Individual tab in the dyntabpane.
 */
trait DynTab extends NiftyControl{
  /**
   * Make this DynTab a part of the group with the given groupId.
   * @param groupId
   */
  def setGroup(groupId: String): Unit

  def getGroup: DynTabPane

  def select(): Unit

  def isActive: Boolean

  /**
   * Get the image the tab shows.
   * @return image
   */
  def getImage: NiftyImage

  /**
   * Set the current image the tab shows.
   */
  def setImage(image: NiftyImage): Unit
}

object DynTab {
  def apply(nifty: Nifty): Unit = {
    new ControlDefinitionBuilder("dyntab") {
      controller("typeIa.gui.controls.dyntab.DynTabControl")
      inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping")
      style("nifty-dyntab")
      panel(new PanelBuilder() {
        height("48px")
        width("256px")
        childLayoutHorizontal()

        style("#panel")
        focusable(true)
        image(new ImageBuilder("#image"){
          style("#image")
          filename(controlParameter("imageFileName"))
          valignCenter()
        })

        panel(GuiUtils.hSpacerBuilder("9px"))

        text(new TextBuilder("#title"){
          style("#title")
          text(controlParameter("title"))
          valignCenter()
        })

        interactOnClick("onClick()")

        panel(GuiUtils.hSpacerBuilder("*"))

        //also, a close box?
        image(new ImageBuilder("#imageClose"){
          filename("Interface/typeIa/icons/cross icon 1 16.png")
          valignCenter()
          height("16px")
          width("16px")
          interactOnClick("onClose()")
        })

      })
    }.registerControlDefintion(nifty)
  }
}