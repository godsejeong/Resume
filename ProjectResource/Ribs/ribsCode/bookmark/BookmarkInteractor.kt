package com.dev.devkotlin.root.dashboard.my.bookmark

import com.dev.devkotlin.data.ListBookmarkItem
import com.dev.devkotlin.data.ListDataDiffInterface
import com.dev.devkotlin.data.ListPurchaseItem
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [BookmarkScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class BookmarkInteractor : Interactor<BookmarkInteractor.BookmarkPresenter, BookmarkRouter>() {

  @Inject
  lateinit var presenter: BookmarkPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    var bookmarkArray = ArrayList<ListDataDiffInterface>().apply {
      add(ListBookmarkItem("ImageLink","BEFORE WE BEGIN WOURLD TOUR"))
      add(ListBookmarkItem("ImageLink","ATEEZ WOURLD TOUR THE FELLOWSHIP"))
      add(ListBookmarkItem("ImageLink","IZONE BOOLM*IZ SPACESHIP"))
      add(ListBookmarkItem("ImageLink","IZONE BOOLM*IZ FIESTA"))
      add(ListPurchaseItem("ImageLink","IZONE COLOR*IZ"))

    }

    router.recyclerViewSet(bookmarkArray)
    presenter.recyclerSet()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface BookmarkPresenter{
    fun recyclerSet()
  }
}
