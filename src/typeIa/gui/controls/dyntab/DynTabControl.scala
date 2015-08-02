package typeIa.gui.controls.dyntab

import java.util.Properties

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.controls.AbstractController
import de.lessvoid.nifty.effects.EffectEventId
import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.elements.render.ImageRenderer
import de.lessvoid.nifty.input.NiftyInputEvent
import de.lessvoid.nifty.render.NiftyImage
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.xml.xpp3.Attributes
import typeIa.gui.controls.{DynTabPane, DynTab}

/**
 * Created by tim on 27/05/15.
 */
class DynTabControl extends AbstractController with DynTab{
  private var nifty: Nifty = null
  private var screen: Screen = null
  private var dynTabPane: DynTabPaneControl = null
  private var active: Boolean = false

  private var iconImageElement: Element = null
  private var iconImageRenderer: ImageRenderer = null

  private def linktoDynTabPane(groupId: String): Unit = {
    //yuck, nulls
    if(groupId == null) {
      dynTabPane = null
    } else {
      dynTabPane = screen.findNiftyControl(groupId, classOf[DynTabPaneControl])
      dynTabPane.registerDynTab(this)
    }
  }

  def onClick(): Unit = {
    if(dynTabPane != null)
      dynTabPane.onDynTabClick(this)
  }

  /**
   * Make this DynTab a part of the group with the given groupId.
   * @param groupId
   */
  override def setGroup(groupId: String): Unit = linktoDynTabPane(groupId)

  override def isActive: Boolean = active

  override def select(): Unit = onClick()

  override def getGroup: DynTabPane = dynTabPane

  override def onStartScreen(): Unit = {}

  /**
   * Get the the image the button shows.
   * @return image
   */
  override def getImage: NiftyImage = iconImageRenderer.getImage

  /**
   * Set the current image for the button shows.
   */
  override def setImage(image: NiftyImage): Unit = iconImageRenderer.setImage(image)

  override def inputEvent(p1: NiftyInputEvent): Boolean = {
    if (p1 == NiftyInputEvent.NextInputElement) {
      screen.getFocusHandler.getNext(getElement).setFocus()
      true
    } else if (p1 == NiftyInputEvent.PrevInputElement) {
      screen.getFocusHandler.getPrev(getElement).setFocus()
      true
    } else if (p1 == NiftyInputEvent.Activate) {
      onClick()
      true
    } else
      false
  }

  override def bind(p1: Nifty, p2: Screen, p3: Element, p4: Properties, p5: Attributes): Unit = {
    super.bind(p3)
    nifty = p1
    screen = p2
    linktoDynTabPane(p4.getProperty("group"))
    iconImageElement = getElement.findElementByName("#image")
    iconImageRenderer = iconImageElement.getRenderer(classOf[ImageRenderer])
  }

  def activate(): Unit = {
    if (!active) {
      getElement.stopEffect(EffectEventId.onCustom)
      getElement.startEffect(EffectEventId.onCustom, null, "show")
      getElement.setFocus()
      active = true
    }
  }

  def deactivate(): Unit = {
    if(active) {
      getElement.stopEffect(EffectEventId.onCustom)
      getElement.startEffect(EffectEventId.onCustom, null, "hide")
      active = false
    }
  }
}
