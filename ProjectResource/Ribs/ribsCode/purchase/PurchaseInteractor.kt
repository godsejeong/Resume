package com.dev.devkotlin.root.dashboard.my.purchase

import com.dev.devkotlin.data.ListDataDiffInterface
import com.dev.devkotlin.data.ListPurchaseItem
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [PurchaseScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class PurchaseInteractor : Interactor<PurchaseInteractor.PurchasePresenter, PurchaseRouter>() {

  @Inject
  lateinit var presenter: PurchasePresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    var purchaseArray = ArrayList<ListDataDiffInterface>().apply {
      add(ListPurchaseItem("ImageLink","BEFORE WE BEGIN WOURLD TOUR"))
      add(ListPurchaseItem("ImageLink","ATEEZ WOURLD TOUR THE FELLOWSHIP"))
      add(ListPurchaseItem("ImageLink","IZONE BOOLM*IZ SPACESHIP"))
      add(ListPurchaseItem("ImageLink","IZONE BOOLM*IZ FIESTA"))
      add(ListPurchaseItem("ImageLink","IZONE COLOR*IZ"))
    }

    router.recyclerViewSet(purchaseArray)
    presenter.recyclerSet()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface PurchasePresenter{
    fun recyclerSet()
  }
}
