package typeIa.gui

import com.jme3.app.{SimpleApplication, Application}
import com.jme3.app.state.{AppStateManager, AbstractAppState}
import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder._
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder
import de.lessvoid.nifty.screen.{Screen, ScreenController}
import typeIa.TypeIA
import typeIa.renderer.stars.StarBillboard
import typeIa.space.{PrimaryDetachedMultipleStar, PrimaryStar}

/**
 * Splash screen. It's an appstate but it's not attached to the state manager, so I suspect it does nothing
 */
class StartScreen(val app: TypeIA) extends AbstractAppState with ScreenController{

  private var nifty: Nifty = null
  private var screen: Screen = null

  /** Nifty GUI ScreenControl methods */
  def bind(nifty: Nifty, screen: Screen) {
    this.nifty = nifty
    this.screen = screen
  }

  override def onStartScreen(): Unit = {}

  override def onEndScreen(): Unit = {}

  /** jME3 AppState methods */
  override def initialize(stateManager: AppStateManager, app: Application) {
    //this.app = app

  }

  override def update(tpf: Float) {

  }

  def startGame(nextScreen: String) {
    nifty.gotoScreen(nextScreen)
    app.mainScreen.setEnabled(true)
  }

  def quitGame() {
    app.stop()
  }
}

object StartScreen {
  def apply(nifty: Nifty, app: TypeIA): Unit = {
    nifty.addScreen("start", new ScreenBuilder("start"){
      controller(new StartScreen(app))

      layer(new LayerBuilder("bg"){
        childLayoutCenter()

        panel(new PanelBuilder(){
          backgroundColor(GuiStyles.bgColorString)
        })
      })

      layer(new LayerBuilder("logo"){
        childLayoutVertical()

        paddingTop("85px")

        panel(new PanelBuilder() {
          childLayoutHorizontal()
          paddingLeft("128px")
          image(new ImageBuilder(){
            filename("Interface/typeIa/logo.png")
          })
        })

        panel(GuiUtils.vSpacerBuilder("*"))

        panel(new PanelBuilder(){
          childLayoutHorizontal()
          height("50px")

          panel(GuiUtils.hSpacerBuilder("*"))

          control(new ButtonBuilder("StartScreenNewGame", "new game") {
            alignCenter()
            valignBottom()
            height("50px")
            width("121px")
            visibleToMouse(true)
            interactOnClick("startGame(main)")
          })

          panel(GuiUtils.hSpacerBuilder("20px"))

          control(new ButtonBuilder("StartScreenQuit", "quit") {
            alignCenter()
            valignBottom()
            height("50px")
            width("121px")
            visibleToMouse(true)
            interactOnClick("quitGame()")
          })
          paddingRight("48px")
        })

        paddingBottom("54px")
      })

    }.build(nifty))
  }
}
