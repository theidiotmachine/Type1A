package typeIa.gui.controls.starpanel.builder

import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.loaderv2.types.{ControlType, ElementType}
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.nifty.{Nifty, NiftyIdCreator}
import de.lessvoid.nifty.controls.dynamic.attributes.ControlAttributes
import typeIa.gui.controls.StarPanel

/**
 * Created by tim on 29/06/15.
 */
class CreateStarPanelControl(dum: Int) extends ControlAttributes {
  setName(CreateStarPanelControl.NAME)

  def this(){
    this(3)
    setAutoId(NiftyIdCreator.generate())
  }

  def this(id: String){
    this(4)
    setId(id)
  }

  def create(nifty: Nifty, screen: Screen, parent: Element): StarPanel = {
    nifty.addControl(screen, parent, getStandardControl)
    nifty.addControlsWithoutStartScreen()
    parent.findNiftyControl(attributes.get("id"), classOf[StarPanel])
  }

  override def createType: ElementType = new ControlType(attributes)
}

object CreateStarPanelControl {
  val NAME = "starpanel"
}
