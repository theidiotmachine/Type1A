package typeIa.gui.controls.dyntab

import java.util.Properties

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.controls.AbstractController
import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.input.NiftyInputEvent
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.xml.xpp3.Attributes
import typeIa.gui.controls.{DynTab, DynTabPaneStateChangedEvent, DynTabPane}
import scala.collection.mutable.ListBuffer

/**
 * Created by tim on 27/05/15.
 */
class DynTabPaneControl extends AbstractController with DynTabPane{
  private var nifty: Nifty = null
  private var screen: Screen = null
  private var activeTab: Option[DynTabControl] = None
  private val dynTabs = ListBuffer[DynTabControl]()

  override def bind(p1: Nifty, p2: Screen, p3: Element, p4: Properties, p5: Attributes): Unit = {
    super.bind(p3)
    nifty = p1
    screen = p2
  }

  override def onStartScreen(): Unit = {
    nifty.publishEvent(getElement.getId, new DynTabPaneStateChangedEvent(activeTab, None))
  }

  override def inputEvent(p1: NiftyInputEvent): Boolean = false

  private def activateTab(clickedTab: DynTabControl): Unit = {
    clickedTab.activate()
    val oClickedTab = Some(clickedTab)
    nifty.publishEvent(getElement.getId, new DynTabPaneStateChangedEvent(oClickedTab, activeTab))
    activeTab = oClickedTab
  }

  def onDynTabClick(clickedTab: DynTabControl): Unit = {
    activeTab match {
      case None => activateTab(clickedTab)
      case Some(t) =>
        t.deactivate()
        if(t != clickedTab)
          activateTab(clickedTab)
    }
  }

  def registerDynTab(dynTabControl: DynTabControl): Unit = {
    dynTabs.+=(dynTabControl)
    if(activeTab == None)
      onDynTabClick(dynTabControl)
  }
}
