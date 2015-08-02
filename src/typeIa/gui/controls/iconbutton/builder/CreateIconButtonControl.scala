package typeIa.gui.controls.iconbutton.builder

import de.lessvoid.nifty.{Nifty, NiftyIdCreator}
import de.lessvoid.nifty.controls.dynamic.attributes.ControlAttributes
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.loaderv2.types.ControlType
import de.lessvoid.nifty.loaderv2.types.ElementType
import typeIa.gui.controls.IconButton
;

/**
 * Created by tim on 24/05/15.
 */
class CreateIconButtonControl(dum: Int) extends ControlAttributes{
  setName(CreateIconButtonControl.NAME)

  def this(){
    this(3)
    setAutoId(NiftyIdCreator.generate())
  }

  def this(id: String){
    this(4)
    setId(id)
  }

  def create(nifty: Nifty, screen: Screen, parent: Element): IconButton = {
    nifty.addControl(screen, parent, getStandardControl)
    nifty.addControlsWithoutStartScreen()
    parent.findNiftyControl(attributes.get("id"), classOf[IconButton])
  }

  override def createType: ElementType = new ControlType(attributes)
}

object CreateIconButtonControl {
  val NAME = "iconbutton"
}
