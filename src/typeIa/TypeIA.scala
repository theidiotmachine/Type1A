package typeIa

import com.jme3.app.{FlyCamAppState, SimpleApplication}
import com.jme3.niftygui.NiftyJmeDisplay
import com.jme3.system.AppSettings
import de.lessvoid.nifty.Nifty
import game.GameState
import typeIa.gui.controls.{DynTab, DynTabPane, IconButton, StarPanel}
import typeIa.gui.{MainScreen, StartScreen}


class TypeIA extends SimpleApplication {
  var mainScreen: MainScreen = null
  var gameState: GameState = new GameState
  def simpleInitApp(): Unit = {

    val niftyDisplay: NiftyJmeDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort)
    val nifty: Nifty = niftyDisplay.getNifty
    guiViewPort.addProcessor(niftyDisplay)
    //flyCam.setDragToRotate(true)
    stateManager.detach(stateManager.getState(classOf[FlyCamAppState]))
    flyCam.setEnabled(false)
    //inputManager.rem
    inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT)

    nifty.loadStyleFile("nifty-default-styles.xml")
    nifty.loadControlFile("nifty-default-controls.xml")

    IconButton(nifty)
    DynTab(nifty)
    DynTabPane(nifty)
    StarPanel(nifty)

    StartScreen(nifty, this)
    mainScreen = MainScreen(nifty, this)

    nifty.gotoScreen("start")
  }
}

object TypeIA {
  def main(args: Array[String]): Unit = {
    val app = new TypeIA
    val settings: AppSettings = new AppSettings(true)
    settings.setResolution(1024, 768)
    app.setShowSettings(false)
    app.setSettings(settings)
    app.setDisplayFps(false)
    app.setDisplayStatView(false)
    app.start()
  }
}