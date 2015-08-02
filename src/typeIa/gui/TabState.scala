package typeIa.gui

import typeIa.gui.pages.{FarStarView, PageState, GalaxyView}

import scala.collection.mutable.ListBuffer

/**
 * This is the state structure for a tab in the tab set. It obviously points to the current page state
 */
class TabState(mainScreen: MainScreen) {

  //Not sure this is right
  val galaxyView: GalaxyView = new GalaxyView(mainScreen)
  val farStarView: FarStarView = new FarStarView(mainScreen)

  private val history: ListBuffer[PageState] = ListBuffer[PageState]()
  private var cursor: Int = -1

  /**
   * remove focus from this tab
   */
  def leave(): Unit = {
    history(cursor) = history(cursor).page.leave()
  }

  /**
   * Give focus to this tab
   */
  def enter(): Unit = {
    history(cursor).page.enter(history(cursor))
  }

  def getCurrent: Option[PageState] = if(history.isEmpty) None else Some(history(cursor))

  def prevPage(): Option[PageState] = {
    val thisCursor = cursor - 1
    if(thisCursor < 0)
      None
    else
      Some(history(thisCursor))
  }

  def goto(pageState: PageState): Unit = {
    val ooldPageState = getCurrent
    ooldPageState match {
      case Some(oldPageState) => leave()
      case _ =>
    }

    if(cursor < history.size -1)
      history.remove(cursor + 1, (history.size - 1) - cursor)

    history += pageState
    cursor += 1
    pageState.page.enter(pageState)
  }

  def back(): Unit = {
    leave()
    cursor -= 1
    enter()
  }

  def forward(): Unit = {
    leave()
    cursor += 1
    enter()
  }

  def hasPast: Boolean = {
    cursor > 0
  }

  def hasFuture: Boolean = cursor < history.length - 1
}
