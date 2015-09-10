package typeIa.gui.pages

import typeIa.gui.MainScreen
import typeIa.space.Loc

trait GalaxyViewController {
  def clickStarPanel(): Unit
}

/**
 * The stateless part of a page.
 */
class GalaxyView(val mainScreen: MainScreen) extends Page  {

  private def defaultCamDistPc(clusterCubeSizePc: Double) = clusterCubeSizePc * 2.0

  override def defaultPage: PageState = new GalaxyViewState(this, defaultCamDistPc(mainScreen.clusterCubeSizePc))

  def enter(state: PageState): Unit = {
    val galaxyViewState = state.asInstanceOf[GalaxyViewState]
    mainScreen.spinnyCameraManager.camDistPc = galaxyViewState.camDistPc
    mainScreen.spinnyCameraManager.camXRot = galaxyViewState.camXRot
    mainScreen.spinnyCameraManager.camYRot = galaxyViewState.camYRot
    mainScreen.spinnyCameraManager.camLookAt = Loc.pc(0, 0, 0)
    enter()
  }

  def enter(): Unit = {
    setURL("Galaxy")

    mainScreen.spinnyCameraManager.positionCamera(mainScreen.app.getCamera)

    generateStarPanel(mainScreen)
  }

  def leave(): PageState = {
    deleteStarPanel(mainScreen)
    generateState
  }




  def generateState: PageState =
    new GalaxyViewState(this, mainScreen.spinnyCameraManager.camDistPc, mainScreen.spinnyCameraManager.camXRot,
      mainScreen.spinnyCameraManager.camYRot)

  def update(tpf: Float, mainScreen: MainScreen): Unit = {
    mainScreen.spinnyCameraManager.update(tpf, mainScreen)
  }
}
