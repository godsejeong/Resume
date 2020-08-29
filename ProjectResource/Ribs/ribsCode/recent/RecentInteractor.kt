package com.dev.devkotlin.root.dashboard.my.recent

import com.dev.devkotlin.data.ListDataDiffInterface
import com.dev.devkotlin.data.ListRecentItem
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RecentScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RecentInteractor : Interactor<RecentInteractor.RecentPresenter, RecentRouter>() {

  @Inject
  lateinit var presenter: RecentPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    var recentArray = ArrayList<ListDataDiffInterface>().apply {
      add(ListRecentItem("VideoLink","MAP THE TREASURE in Europe","ATEEZ WORLD TOUR THE FELL 어쩌고 저쩌고"))
      add(ListRecentItem("VideoLink","Chicago, United Statas : Avondale Music Hall","1TEAM US TOUR: Hello! Just One"))
      add(ListRecentItem("VideoLink","Buenos Aires, Argentina","BEFORE WE BEGIN WORLD TOUR"))
      add(ListRecentItem("VideoLink","MAP THE TREASURE in USA","ATEEZ WORLD TOUR THE FELL 어쩌고 저쩌고"))
      add(ListRecentItem("VideoLink","EYES ON ME KOREA","IZONE WORLD TOUR IN SEOLE"))
      add(ListRecentItem("VideoLink","EYES ON ME JAPAN","IZONE WORLD TOUR IN SAITAMA"))
      add(ListRecentItem("VideoLink","EYES ON ME THAILAND","IZONE WORLD TOUR IN TAIPEI"))

    }
    router.recyclerViewSet(recentArray)
    presenter.recyclerSet()
  }

  override fun willResignActive() {
    super.willResignActive()

  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RecentPresenter{
//    fun showProgressBar()
//    fun hideProgressBar()
    fun recyclerSet()
  }


}
