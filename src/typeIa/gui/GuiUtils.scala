package typeIa.gui

import de.lessvoid.nifty.builder.PanelBuilder

/**
 * Created by tim on 24/05/15.
 */
class HSpacerBuilder(space: String) extends PanelBuilder{
  width(space)
}
class VSpacerBuilder(space: String) extends PanelBuilder{
  height(space)
}
class HDividerBuilder(heightIn: String = "*") extends PanelBuilder{
  backgroundColor(GuiStyles.dividerColorString)
  width("1px")
  height(heightIn)
  valignCenter()
}
class VDividerBuilder(widthIn: String = "*") extends PanelBuilder{
  backgroundColor(GuiStyles.dividerColorString)
  height("1px")
  width(widthIn)
  alignCenter()
}
object GuiUtils {
  def hSpacerBuilder(space: String): PanelBuilder = new HSpacerBuilder(space)
  def vSpacerBuilder(space: String): PanelBuilder = new VSpacerBuilder(space)
  def hDividerBuilder(): PanelBuilder = new HDividerBuilder
  def vDividerBuilder(): PanelBuilder = new VDividerBuilder
}
