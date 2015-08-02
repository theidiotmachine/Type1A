package typeIa.gui.controls.starpanel

import java.util.Properties

import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.controls.{FocusHandler, AbstractController}
import de.lessvoid.nifty.elements.Element
import de.lessvoid.nifty.elements.render.ImageRenderer
import de.lessvoid.nifty.input.NiftyInputEvent
import de.lessvoid.nifty.screen.Screen
import de.lessvoid.xml.xpp3.Attributes
import typeIa.gui.controls.StarPanel

/**
 * Created by tim on 29/06/15.
 */
class StarPanelControl extends AbstractController with StarPanel{
  private var nifty: Nifty = null
  private var screen: Screen = null
  private var focusHandler: FocusHandler = null

  private def buttonClick(): Unit = getElement.onClick()

  /**
   * Activate/Click this button.
   */
  override def activate(): Unit = buttonClick()

  override def onStartScreen(): Unit = {}

  override def inputEvent(p1: NiftyInputEvent): Boolean = {
    val buttonElement = getElement
    p1 match {
      case NiftyInputEvent.NextInputElement =>
        if (focusHandler != null)
          focusHandler.getNext(buttonElement).setFocus()
        true
      case NiftyInputEvent.PrevInputElement =>
        if (focusHandler != null)
          focusHandler.getPrev(buttonElement).setFocus()
        true
      case NiftyInputEvent.Activate =>
        buttonClick()
        true
      case NiftyInputEvent.MoveCursorDown =>
        /*
        if (focusHandler != null) {
          val nextElement = focusHandler.getNext(buttonElement)
          if (nextElement.getParent.equals(buttonElement.getParent)) {
            nextElement.setFocus()
            true
          } else
            false
        } else*/
          false
      case NiftyInputEvent.MoveCursorUp => /*
        if (focusHandler != null) {
          val prevElement = focusHandler.getPrev(buttonElement)
          if (prevElement.getParent.equals(buttonElement.getParent)) {
            prevElement.setFocus()
            true
          } else
            false
        } else*/
          false
      case _ =>
        false
    }
  }

  override def bind(p1: Nifty, p2: Screen, p3: Element, p4: Properties, p5: Attributes): Unit = {
    super.bind(p3)
    nifty = p1
    screen = p2
    focusHandler = screen.getFocusHandler
  }
}
