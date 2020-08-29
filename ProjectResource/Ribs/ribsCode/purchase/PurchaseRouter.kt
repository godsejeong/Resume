package com.dev.devkotlin.root.dashboard.my.purchase

import android.view.View
import com.dev.devkotlin.adapter.ItemListAdapter
import com.dev.devkotlin.data.ListDataDiffInterface

import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.bookmark_rib.view.*
import kotlinx.android.synthetic.main.purchase_rib.view.*
import kotlinx.android.synthetic.main.recent_rib.view.*

/**
 * Adds and removes children of {@link PurchaseBuilder.PurchaseScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class PurchaseRouter(
    view: PurchaseView,
    interactor: PurchaseInteractor,
    component: PurchaseBuilder.Component) : ViewRouter<PurchaseView, PurchaseInteractor, PurchaseBuilder.Component>(view, interactor, component){

    fun recyclerViewSet(item : ArrayList<ListDataDiffInterface>){
        ItemListAdapter().apply {
            view.purchase_rv.adapter = this
            this.submitList(item)
        }
    }
}
