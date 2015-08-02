package typeIa.gui

import scala.collection.mutable.ListBuffer

/**
 * Created by tim on 24/06/15.
 */
class TabSet {
  private val tabs = ListBuffer[TabState]()
  private var currentTab = 0

  def addTab(tabState: TabState) = {
    tabs += tabState
  }

  def setCurrentTab(newCurrentTab: Int): Unit = {
    if(currentTab != newCurrentTab) {
      tabs(currentTab).leave
      currentTab = newCurrentTab
      tabs(currentTab).enter
    }
  }

  def getCurrentTab: TabState = {
    tabs(currentTab)
  }
}
