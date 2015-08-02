package typeIa.gui.controls.dyntab.builder

import de.lessvoid.nifty.builder.ControlBuilder

/**
 * Created by tim on 26/05/15.
 */
class DynTabBuilder(id: String, iconFilename: String, caption: String) extends ControlBuilder(id, "dyntab"){
  set("imageFileName", iconFilename)
  set("title", caption)
  /**
   * odd. I don't have this ctor in iconbutton
   * @return
   */
  //def this() = super("dyntab")

  def group(group: String): Unit = set("group", group)
}
