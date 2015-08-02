package typeIa.gui.controls.dyntab.builder

import de.lessvoid.nifty.controls.dynamic.attributes.ControlAttributes
import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.loaderv2.types.{ControlType, ElementType}
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.nifty.{Nifty, NiftyIdCreator}
import typeIa.gui.controls.DynTabPane

/**
 * Dumb nifty creator boilerplate
 */
class CreateDynTabPaneControl(dum: Int) extends ControlAttributes{
  setName(CreateDynTabPaneControl.NAME)

  def this(){
    this(3)
    setAutoId(NiftyIdCreator.generate())
  }

  def this(id: String){
    this(4)
    setId(id)
  }

  def create(nifty: Nifty, screen: Screen, parent: Element): DynTabPane = {
    nifty.addControl(screen, parent, getStandardControl)
    nifty.addControlsWithoutStartScreen()
    parent.findNiftyControl(attributes.get("id"), classOf[DynTabPane])
  }

  override def createType: ElementType = new ControlType(attributes)
}
object CreateDynTabPaneControl {
  val NAME = "dyntabpane"
}
