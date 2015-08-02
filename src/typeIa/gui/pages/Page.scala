package typeIa.gui.pages

import de.lessvoid.nifty.controls.Label
import typeIa.gui.MainScreen
import typeIa.gui.controls.starpanel.builder.StarPanelBuilder

/**
 * A page is the contents of a tab. Pages are either stateless, or have disposable state. A bookmark will point to
 * a page state, and we expect to rehydrate from that
 */
trait Page {
  def defaultPage: PageState

  def update(tpf: Float, mainScreen: MainScreen): Unit

  /**
   * Init the page from a state. Called when we click on links. Almost certainly will call the no-arg enter
   * @param state
   */
  def enter(state: PageState): Unit

  /**
   * Init the page from its current state. Called when we click on the tab
   */
  def enter(): Unit

  def generateState: PageState

  /**
   * Called when we move away, either from a link click or a tab click
   */
  def leave(): PageState

  val mainScreen: MainScreen

  protected def setURL(url: String): Unit = {
    val urlElem = mainScreen.screen.findElementByName("URL")
    val c = urlElem.getNiftyControl(classOf[Label])
    c.setText(url)
  }

  protected def generateStarPanel(mainScreen: MainScreen): Unit = {
    val parent = mainScreen.screen.findElementByName("WorkArea")

    parent.add(new StarPanelBuilder("StarPanel") {
      interactOnClick("clickStarPanel()")
      interactOnMouseOver("mouseOverStarPanel()")
      x("0px")
      y("0px")
      height("100%")
      width("100%")
      //backgroundColor("#0000ff40")
      //color("#0000ff40")
    }.build(mainScreen.nifty, mainScreen.screen, parent))

    parent.layoutElements()
    parent.getNifty.executeEndOfFrameElementActions()
  }

  protected def deleteStarPanel(mainScreen: MainScreen): Unit = {
    val starPanelElem = mainScreen.screen.findElementByName("StarPanel")
    val parent = starPanelElem.getParent
    starPanelElem.markForRemoval()
    mainScreen.nifty.removeElement(mainScreen.screen, starPanelElem)
    parent.layoutElements()
    mainScreen.nifty.executeEndOfFrameElementActions()
  }
}
