package typeIa.gui.controls

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder.{ImageBuilder, PanelBuilder, ControlDefinitionBuilder}
import de.lessvoid.nifty.controls.NiftyControl
import de.lessvoid.nifty.layout.align.{HorizontalAlign, VerticalAlign}
import de.lessvoid.nifty.render.NiftyImage
import de.lessvoid.nifty.tools.Color

/**
 * Created by tim on 24/05/15.
 */
trait IconButton extends NiftyControl{

  /**
   * Activate/Click this button.
   */
  def activate(): Unit

  /**
   * Get the image the button shows.
   * @return image
   */
  def getImage: NiftyImage

  /**
   * Set the current image for the button shows.
   */
  def setImage(image: NiftyImage): Unit
}

object IconButton{
  def apply(nifty: Nifty): Unit = {
    new ControlDefinitionBuilder("iconbutton") {
      controller("typeIa.gui.controls.iconbutton.IconButtonControl")
      inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping")
      style("nifty-iconbutton")
      panel(new PanelBuilder() {
        childLayoutCenter()
        style("#panel")
        focusable(true)
        image(new ImageBuilder("#image"){
          style("#image")
          filename(controlParameter("fileName"))
        })
      })
    }.registerControlDefintion(nifty)
  }
}