package typeIa.gui.controls.dyntab.builder

import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.loaderv2.types.{ControlType, ElementType}
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.nifty.{Nifty, NiftyIdCreator}
import de.lessvoid.nifty.controls.dynamic.attributes.ControlAttributes
import typeIa.gui.controls.DynTab

/**
 * Created by tim on 26/05/15.
 */
class CreateDynTabControl(dum: Int) extends ControlAttributes{

  setName(CreateDynTabControl.NAME)

  def this(){
    this(3)
    setAutoId(NiftyIdCreator.generate())
  }

  def this(id: String){
    this(4)
    setId(id)
  }

  def create(nifty: Nifty, screen: Screen, parent: Element): DynTab = {
    nifty.addControl(screen, parent, getStandardControl)
    nifty.addControlsWithoutStartScreen()
    parent.findNiftyControl(attributes.get("id"), classOf[DynTab])
  }

  override def createType: ElementType = new ControlType(attributes)
}

object CreateDynTabControl{
  val NAME = "dyntab"
}