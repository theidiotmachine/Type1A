package typeIa.gui

import com.jme3.app.Application
import com.jme3.app.state.{AbstractAppState, AppStateManager}
import com.jme3.collision.CollisionResults
import com.jme3.input.KeyInput
import com.jme3.input.controls.{ActionListener, KeyTrigger}
import com.jme3.math.{Ray, Vector2f}
import com.jme3.scene.Node
import de.lessvoid.nifty.Nifty
import de.lessvoid.nifty.builder.{LayerBuilder, PanelBuilder, ScreenBuilder}
import de.lessvoid.nifty.controls.Label
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder
import de.lessvoid.nifty.controls.label.builder.LabelBuilder
import de.lessvoid.nifty.screen.{Screen, ScreenController}
import typeIa.TypeIA
import typeIa.gui.controls.dyntab.builder.{DynTabBuilder, DynTabPaneBuilder}
import typeIa.gui.controls.iconbutton.builder.IconButtonBuilder
import typeIa.gui.pages.{SpinnyCameraManager, FarStarViewState, GalaxyViewController}
import typeIa.renderer.stars.StarBillboard
import typeIa.space.neighborhood.Neighborhood
import typeIa.space._

/**
 * Harness for the main screen
 */
class MainScreen(val app: TypeIA) extends AbstractAppState with ScreenController with GalaxyViewController {
  var nifty: Nifty = null
  var screen: Screen = null
  val clusterCubeSizePc: Double = 12.0

  //duped from galaxy view
  private def defaultCamDistPc(clusterCubeSizePc: Double) = clusterCubeSizePc * 3.0
  val spinnyCameraManager = new SpinnyCameraManager(Locpc(0, 0, 0), defaultCamDistPc(clusterCubeSizePc),
    clusterCubeSizePc * 5.0)

  private val tabset = new TabSet

  val shootables = new Node("Shootables")

  /** Nifty GUI ScreenControl methods */
  def bind(nifty: Nifty, screen: Screen) {
    this.nifty = nifty
    this.screen = screen
  }

  override def onStartScreen(): Unit = {
    //right now, init with a new game
    val cam = app.getCamera
    val rootNode = app.getRootNode
    val assetManager = app.getAssetManager
    cam.setFrustumPerspective(45f, cam.getWidth.asInstanceOf[Float] / cam.getHeight, 0.1f, 1000f)

    val hood = Neighborhood(clusterCubeSizePc)
    rootNode.attachChild(shootables);

    hood.stars.map {
      case ps: PrimaryStar =>
        shootables.attachChild(StarBillboard.createPSSystem(ps, assetManager))
      case pdms: PrimaryDetachedMultipleStar =>
        shootables.attachChild(StarBillboard.createPDMSSystem(pdms, assetManager, cam))
      case _ =>
    }

    initKeys()

    val tabState = new TabState(this)
    tabset.addTab(tabState)
    tabset.setCurrentTab(0)
    tabState.goto(tabState.galaxyView.defaultPage)

    setNavigateButtons()
  }

  override def onEndScreen(): Unit = {}

  /** jME3 AppState methods */
  override def initialize(stateManager: AppStateManager, oop: Application): Unit = {
    super.initialize(stateManager, oop)


  }

  override def update(tpf: Float): Unit = {
    val o = tabset.getCurrentTab.getCurrent
    o match {
      case Some(pageState) => pageState.page.update(tpf, this)
      case _ =>
    }
  }

  private def initKeys(): Unit = {
    val inputManager = app.getInputManager
    inputManager.addMapping("Left",
      new KeyTrigger(KeyInput.KEY_LEFT))
    inputManager.addListener(actionListener, "Left")
    inputManager.addMapping("Right",
      new KeyTrigger(KeyInput.KEY_RIGHT))
    inputManager.addListener(actionListener, "Right")
    inputManager.addMapping("Up",
      new KeyTrigger(KeyInput.KEY_UP))
    inputManager.addListener(actionListener, "Up")
    inputManager.addMapping("Down",
      new KeyTrigger(KeyInput.KEY_DOWN))
    inputManager.addListener(actionListener, "Down")
    inputManager.addMapping("Equals",
      new KeyTrigger(KeyInput.KEY_EQUALS))
    inputManager.addListener(actionListener, "Equals")
    inputManager.addMapping("Minus",
      new KeyTrigger(KeyInput.KEY_MINUS))
    inputManager.addListener(actionListener, "Minus")
  }

  var leftHeld = false
  var rightHeld = false
  var upHeld = false
  var downHeld = false
  var equalsHeld = false
  var minusHeld = false

  private val actionListener = new ActionListener {
    override def onAction(name: String, isPressed: Boolean, tpf: Float): Unit = {
      val tpfd = tpf.asInstanceOf[Double]
      if(name == "Left")
        leftHeld = isPressed
      else if(name == "Right")
        rightHeld = isPressed
      else if(name == "Up")
        upHeld = isPressed
      else if(name == "Down")
        downHeld = isPressed
      else if(name == "Minus")
        minusHeld = isPressed
      else if(name == "Equals")
        equalsHeld = isPressed
    }
  }

  private def collide(): CollisionResults = {
    val results = new CollisionResults()
    val click2d = app.getInputManager.getCursorPosition()
    val click3d = app.getCamera.getWorldCoordinates(
      new Vector2f(click2d.x, click2d.y), 0f).clone()
    val dir = app.getCamera.getWorldCoordinates(
      new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal()
    val ray = new Ray(click3d, dir)

    shootables.collideWith(ray, results)
    results
  }

  def mouseOverStarPanel(): Unit = {
    val results = collide
    val tipText = if(results.size() > 0) {
      val collisionResult = results.getClosestCollision
      val geom = collisionResult.getGeometry
      val go = geom.getUserData[GalacticObject]("GalacticObject")
      go.tip
    } else
      ""

    val tip = screen.findElementByName("Tip")
    val c = tip.getNiftyControl(classOf[Label])
    c.setText(tipText)

  }

  override def clickStarPanel(): Unit = {

    /*
    System.out.println("----- Collisions? " + results.size() + "-----")
    var i = 0
    while(i < results.size()){
      // For each hit, we know distance, impact point, name of geometry.
      val dist = results.getCollision(i).getDistance
      val pt = results.getCollision(i).getContactPoint
      val hit = results.getCollision(i).getGeometry.getName
      println("* Collision #" + i)
      println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.")
      i += 1
    }
    */

    val results = collide
    if(results.size() > 0) {
      val collisionResult = results.getClosestCollision
      val geom = collisionResult.getGeometry
      //val sbbc = geom.getControl(classOf[StarBillboardControl])
      val go = geom.getUserData[GalacticObject]("GalacticObject")
      val locpc = go.galacticLoc(0)

      gotoFarStarView(go, locpc, spinnyCameraManager.camXRot, spinnyCameraManager.camYRot)
      //val currentTab = mainScreen.tabset.getCurrentTab
      //currentTab.goto(new FarStarViewState(currentTab.farStarView, 5, locpc, spinnyCameraManager.camXRot, spinnyCameraManager.camYRot))
    }
  }

  private def setNavigateButtons(): Unit = {
    val back = screen.findElementByName("Back")
    if(tabset.getCurrentTab.hasPast)
      back.enable()
    else
      back.disable()
    val forward = screen.findElementByName("Forward")
    if(tabset.getCurrentTab.hasFuture)
      forward.enable()
    else
      forward.disable()
  }

  def gotoFarStarView(go:GalacticObject, locpc: Locpc, camXRot: Float, camYRot: Float): Unit = {
    val currentTab = tabset.getCurrentTab
    currentTab.goto(new FarStarViewState(currentTab.farStarView, go, 5, locpc, camXRot, camYRot))
    setNavigateButtons()
  }

  def back(): Unit = {
    val currentTab = tabset.getCurrentTab
    currentTab.back()
    setNavigateButtons()
  }

  def forward(): Unit = {
    val currentTab = tabset.getCurrentTab
    currentTab.forward()
    setNavigateButtons()
  }
}

object MainScreen {
  def apply(nifty: Nifty, app: TypeIA): MainScreen = {
    val stateManager = app.getStateManager
    val screen = new MainScreen(app)
    screen.setEnabled(false)
    val screenBuilder = new ScreenBuilder("main") {
      controller(screen)
      layer(new LayerBuilder(){
        childLayoutVertical()
        panel(new PanelBuilder("Main") {
          childLayoutVertical()

          height("*")

          panel(new PanelBuilder("Header") {
            backgroundColor(GuiStyles.bgColorString)
            height("146px")

            childLayoutVertical()
            panel(new PanelBuilder() {
              height("48px")
              childLayoutHorizontal()
              control(new DynTabPaneBuilder("MainScreenTabs"))
              control(new DynTabBuilder("Tab1", "Interface/typeIa/icons/galaxy icon 1 16.png", "Galaxy View") {
                group("MainScreenTabs")
              })

              //control(new DynTabBuilder("Tab2", "Interface/typeIa/icons/galaxy icon 1 16.png", "Galaxy View 2"){
              //group("MainScreenTabs")
              //})

            })

            panel(new VDividerBuilder)

            panel(new PanelBuilder() {
              height("48px")

              childLayoutHorizontal()

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new ButtonBuilder("Back", "<") {
                valignCenter()
                height("32px")
                width("32px")
                interactOnClick("back()")
              })

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new ButtonBuilder("Forward", ">") {
                valignCenter()
                height("32px")
                width("32px")
                interactOnClick("forward()")
              })

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new LabelBuilder("URL") {
                valignCenter()
                height("32px")
                width("*")
                label("Home")
                font("undinaru-32.fnt")
              })

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new IconButtonBuilder("BookmarkThis", "Interface/typeIa/icons/bookmark icon 1 16.png") {
                valignCenter()
                height("32px")
                width("32px")
                visibleToMouse(true)
                //interactOnClick("quitGame()")
              })

              panel(GuiUtils.hSpacerBuilder("9px"))
            })

            panel(new VDividerBuilder)

            panel(new PanelBuilder() {
              height("48px")
              childLayoutHorizontal()

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new IconButtonBuilder("NextTurn", "Interface/typeIa/icons/play icon 1 16.png") {
                valignCenter()
                height("32px")
                width("32px")
                visibleToMouse(true)
                //interactOnClick("quitGame()")
              })

              panel(GuiUtils.hSpacerBuilder("9px"))

              panel(new HDividerBuilder("32px"))
            })
          })

          panel(new PanelBuilder("WorkArea"){
            height("*")
            //backgroundColor("#00ff0080")
            childLayoutAbsolute()
            padding("0px")
            margin("0px")
          })

          panel(new PanelBuilder("Footer"){
            childLayoutVertical()
            backgroundColor(GuiStyles.bgColorString)
            height("42px")

            panel(GuiUtils.vSpacerBuilder("9px"))

            panel(new PanelBuilder() {
              height("24px")
              childLayoutHorizontal()

              panel(GuiUtils.hSpacerBuilder("9px"))

              control(new LabelBuilder("Tip") {

                textHAlignLeft()
                height("24px")
                width("*")
                label("Footer")
                font("undinaru-24.fnt")
              })
            })

            panel(GuiUtils.vSpacerBuilder("9px"))

          })
        })
      })
    }
    stateManager.attach(screen)
    nifty.addScreen("main", screenBuilder.build(nifty))
    screen
  }
}
