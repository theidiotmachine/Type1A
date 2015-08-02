package typeIa.gui.controls.iconbutton.builder

import de.lessvoid.nifty.builder.ControlBuilder

/**
 * Builder for icon button
 *
 * Whilst typing this, it occurs to me how much I love Scala
 */
class IconButtonBuilder(id: String) extends ControlBuilder(id, "iconbutton"){
  def fileName(fileNameIn: String): Unit = {
    set("fileName", fileNameIn)
  }
  def this(id: String, fileNameIn: String) {
    this(fileNameIn)
    fileName(fileNameIn)
  }
}
