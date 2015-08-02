package typeIa.gui.pages

import typeIa.gui.MainScreen
import typeIa.space.GalacticObject

/**
 * A view of a star in the main screen. 'Far' because we don;t see planets
 */
class FarStarView(val mainScreen: MainScreen) extends Page {

  var go: GalacticObject = null

  override def defaultPage: PageState = {
    //FIXME needs to be player home world
    ???
  }

  override def update(tpf: Float, mainScreen: MainScreen): Unit = mainScreen.spinnyCameraManager.update(tpf, mainScreen)

  /**
   * Called when we move away, either from a link click or a tab click
   */
  override def leave(): PageState = {
    deleteStarPanel(mainScreen)
    generateState
  }

  /**
   * Init the page from a state. Called when we click on links. Almost certainly will call the no-arg enter
   */
  override def enter(state: PageState): Unit ={
    val farStarViewState = state.asInstanceOf[FarStarViewState]
    mainScreen.spinnyCameraManager.camDistPc = farStarViewState.camDistPc
    mainScreen.spinnyCameraManager.camXRot = farStarViewState.camXRot
    mainScreen.spinnyCameraManager.camYRot = farStarViewState.camYRot
    mainScreen.spinnyCameraManager.camLookAt = farStarViewState.starLoc
    go = farStarViewState.go
    enter()
  }

  /**
   * Init the page from its current state. Called when we click on the tab
   */
  override def enter(): Unit = {

    setURL(go.name)

    mainScreen.spinnyCameraManager.positionCamera(mainScreen.app.getCamera)

    generateStarPanel(mainScreen)
  }

  override def generateState: PageState = new FarStarViewState(this, go, mainScreen.spinnyCameraManager.camDistPc,
    mainScreen.spinnyCameraManager.camLookAt, mainScreen.spinnyCameraManager.camXRot, mainScreen.spinnyCameraManager.camYRot)
}
